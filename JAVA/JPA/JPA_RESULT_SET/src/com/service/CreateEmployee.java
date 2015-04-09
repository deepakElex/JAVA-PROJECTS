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
				
		Employee[] employees = { 
				new Employee(1, "Ravi Shankar", 10000, "Singer"),
				new Employee(2, "Mahesh Kumar", 15000, "Engineer"),
				new Employee(3, "Manmohan Singh", 18000, "President"),
				new Employee(4, "Chulbul Pandey", 20000, "Actor") };
		
		for (Employee emp : employees) {
			entitymanager.persist(emp);
		}
				
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}

}
