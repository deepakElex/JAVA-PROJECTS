package com.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.model.Employee;

public class CreateEmployee {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("DerbyPersistenceUnit");
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Employee employee = new Employee();
		// Not Required as its auto
		// employee.setEid(1201);
		employee.setEname("Shyam Pyare");
		employee.setSalary(15000);
		employee.setDeg("Software Tester");
		entitymanager.persist(employee);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}

}
