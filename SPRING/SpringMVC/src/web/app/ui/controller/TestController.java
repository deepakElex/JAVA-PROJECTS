package web.app.ui.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;




@Controller
public class TestController {

	
	@RequestMapping(method=RequestMethod.GET, value="/test")
	public String test(Locale locale, Model modal ,HttpServletRequest  request){
		
		
		System.out.println(request.getContextPath());
		System.out.println("Test");
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		System.out.println(WebApplicationContext.SERVLET_CONTEXT_BEAN_NAME);
		System.out.println(locale.getCountry());
		modal.addAttribute("Name", "Vikrant");
		return "success";
	}


	@RequestMapping(method=RequestMethod.GET, value="/submitfrm")
	public String submit(Locale locale, Model modal ,HttpServletRequest  request, HttpServletResponse response,ModelMap mmap ) throws UnsupportedEncodingException{
		System.out.println(request.getContextPath());
		System.out.println("submit");
	//	request.setCharacterEncoding("ASCII");
		
		       String czech = (String)request.getParameter("in");
		       String japanese = "gy";

		        System.out.println("czech: " + czech); 
	        System.out.println("UTF-8 czech: " + new String(czech.getBytes("UTF-8")));
	        System.out.println("ISO-8859-1 czech: " + new String(czech.getBytes("ASCII")));
	        System.out.println("ISO-8859-1 japanese: " + new String(czech.getBytes("ISO-8859-1")));
		
	        	
		
		System.out.println(request.getCharacterEncoding());
		
	//	response.setCharacterEncoding("ASCII");
		System.out.println(locale.getCountry());
		
		modal.addAttribute("val",(String)request.getParameter("in"));
		Map mp=modal.asMap();
		
		for (Object key: mp.keySet()){
			
			System.out.println(key+"-"+mp.get(key));
		}
	
		
	for (Object key: mmap.keySet()){
			
			System.out.println(key+"-"+mmap.get(key));
		}
		
		
		
		modal.addAttribute("Name", "Vikrant");
		return "success";
	}
	
	
	@RequestMapping(method=RequestMethod.GET, value="/auth")
	public String authorize(){
		System.out.println("auth");
		return "authorized";
	}
	
	
	  @RequestMapping("/ajax")
	    public ModelAndView helloAjaxTest() {
	        return new ModelAndView("ajax", "message", "Crunchify Spring MVC with Ajax and JQuery Demo..");
	    }
	 
	    @RequestMapping(value = "/ajaxtest", method = RequestMethod.GET)
	    public @ResponseBody
	    String getTime() {
	 
	        Random rand = new Random();
	        float r = rand.nextFloat() * 100;
	        String result = "<br>Next Random # is <b>" + r + "</b>. Generated on <b>" + new Date().toString() + "</b>";
	        System.out.println("Debug Message from CrunchifySpringAjaxJQuery Controller.." + new Date().toString());
	        return result;
	    }
	    
	    @RequestMapping(value="/jsonmethod", produces={"application/json","application/xml"}, consumes="text/html")
	    @ResponseBody
	    public String method6(){
	    	
	        return "Hello";
	    }
	    
	    @RequestMapping("*")
	    @ResponseBody
	    public String fallbackMethod(){
	        return "<H1>Error Page , such url does not exist</H1>Please try again later..";
	    }
	
}
