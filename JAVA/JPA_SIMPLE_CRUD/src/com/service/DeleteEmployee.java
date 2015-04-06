package com.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.model.Employee;

public class DeleteEmployee {
	public static void main(String[] args) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("DerbyPersistenceUnit");
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Employee employee = entitymanager.find(Employee.class, 1);
		entitymanager.remove(employee);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}
}
