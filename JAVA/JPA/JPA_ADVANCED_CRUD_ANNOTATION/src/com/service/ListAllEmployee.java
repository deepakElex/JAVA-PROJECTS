package com.service;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.model.entities.Employee;

public class ListAllEmployee {
	public static void main(String[] args) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("DerbyPersistenceUnit");
		EntityManager entitymanager = emfactory.createEntityManager();

		Query query = entitymanager.createQuery("Select e from Employee e ORDER BY e.account,e.id");
		List<Employee> list = (List<Employee>) query.getResultList();
		for (Employee e : list) {
			System.out.println(e);
		}


/*
		query = entitymanager.createNativeQuery("SELECT SYSDATE FROM DUAL");
		Date result = (Date)query.getSingleResult();
		System.out.println("Date-"+result);
*/

		query = entitymanager.createQuery("Select  e.account,e.id from Employee e ORDER BY e.account,e.id");

		List<Object[]> list2 = query.getResultList();
		for (Object[] objects : list2) {
			int account = (Integer) objects[0];
			int id = (Integer) objects[1];
			System.out.println(" Account " + account + "	| id-	" + id);
		}
		

		query = entitymanager.createNativeQuery("SELECT MAX(SALARY), MIN(SALARY) FROM EMPLOYEE_ADVANCED");
		List<Object[]> results = query.getResultList();
		double max = (Double) results.get(0)[0];
		double min = (Double) results.get(0)[1];
		System.out.println("Max Salary -"+max);
		System.out.println("Min Salary -"+min);

		entitymanager.close();
		emfactory.close();
	}

}
