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
		employee.setEname("Mohan Pyare");
		employee.setSalary(35000);
		employee.setDeg("Software Engineer");
		entitymanager.persist(employee);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}

}
