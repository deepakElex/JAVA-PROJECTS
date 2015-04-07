package com.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.model.entities.Employee;
import com.model.entities.EmployeePK;


public class FindEmployee {

	public static void main(String[] args) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("DerbyPersistenceUnit");
		EntityManager entitymanager = emfactory.createEntityManager();
		Employee employee = entitymanager.find(Employee.class, new EmployeePK(7001,1));
		System.out.println("employee ID = " + employee.getId());
		System.out.println("employee NAME = " + employee.getName());
		System.out.println("employee SALARY = " + employee.getSalary());
		System.out.println("employee DESIGNATION = " + employee.getDeg());
	}

}
