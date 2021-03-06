package com.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.model.entities.Employee;
import com.model.entities.EmployeePK;


public class UpdateEmployee {

	public static void main(String[] args) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("DerbyPersistenceUnit");
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Employee employee = entitymanager.find(Employee.class,  new EmployeePK(7001,1));
		// before update
		System.out.println(employee);
		employee.setSalary(11000);
		entitymanager.getTransaction().commit();
		// after update
		System.out.println(employee);
		entitymanager.close();
		emfactory.close();
	}

}
