package com.service;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.model.entities.Employee;

public class CreateEmployee {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("DerbyPersistenceUnit");
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		Employee[] employees = { 
				new Employee(7001, "Ravi Shankar", 10000, "Singer", new Date(104, 03, 01), new Date(115, 03, 01)),
				new Employee(7001, "Mahesh Kumar", 15000, "Engineer", new Date(102, 03, 01), new Date(115, 03, 01)),
				new Employee(7002, "Manmohan Singh", 18000, "President", new Date(101, 03, 01), new Date(115, 03, 01)),
				new Employee(7002, "Chulbul Pandey", 20000, "Actor", new Date(100, 03, 01), new Date(115, 03, 01)) };
		
		for (Employee emp : employees) {
			entitymanager.persist(emp);
		}

		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}

}
