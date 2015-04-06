package com.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class DerbyTest {
	
	    private static String dbURL = "jdbc:derby://localhost:1527/C:/DEVELOPMENT/DB/MyDB;create=true";
//	    private static String dbURL = "jdbc:derby:C:/DEVELOPMENT/DB/MyDB;create=true"; 
	    private static String tableName = "EMPLOYEE";
	    // jdbc Connection
	    private static Connection conn = null;
	    private static Statement stmt = null;

	    public static void main(String[] args)
	    {
	        createConnection();
	   //     insertRestaurants(4, "Charlie");
	        selectRestaurants();
	        shutdown();
	    }
	    
	    private static void createConnection()
	    {
	        try
	        {
//	            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
//	            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
	            //Get a connection
	            conn = DriverManager.getConnection(dbURL); 
	        }
	        catch (Exception except)
	        {
	            except.printStackTrace();
	        }
	    }
	    
	    private static void insertRestaurants(int id, String name)
	    {
	        try
	        {
	            stmt = conn.createStatement();
	            stmt.execute("insert into " + tableName + " values (" +
	                    id + ",'" + name + "')");
	            stmt.close();
	        }
	        catch (SQLException sqlExcept)
	        {
	            sqlExcept.printStackTrace();
	        }
	    }
	    
	    private static void selectRestaurants()
	    {
	        try
	        {
	            stmt = conn.createStatement();
	            ResultSet results = stmt.executeQuery("select * from " + tableName);
	            ResultSetMetaData rsmd = results.getMetaData();
	            int numberCols = rsmd.getColumnCount();
	            for (int i=1; i<=numberCols; i++)
	            {
	                //print Column Names
	                System.out.print(rsmd.getColumnLabel(i)+"\t\t");  
	            }

	            System.out.println("\n---------------------main----------------------------");

	            while(results.next())
	            {
	                int id = results.getInt(1);
	                String name = results.getString(2);
	                System.out.println(id + "\t\t" + name);
	            }
	            results.close();
	            stmt.close();
	        }
	        catch (SQLException sqlExcept)
	        {
	            sqlExcept.printStackTrace();
	        }
	    }
	    
	    private static void shutdown()
	    {
	        try
	        {
	            if (stmt != null)
	            {
	                stmt.close();
	            }
	            if (conn != null)
	            {
	                DriverManager.getConnection(dbURL + ";shutdown=true");
	                conn.close();
	            }           
	        }
	        catch (SQLException sqlExcept)
	        {
	            
	        }

	    }
	}