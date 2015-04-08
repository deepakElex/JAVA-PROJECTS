package com.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.model.entities.Employee;
import com.model.entities.EmployeePK;


public class DeleteEmployee {
	public static void main(String[] args) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("DerbyPersistenceUnit");
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		
		//Query query = entitymanager.createNativeQuery("DELETE FROM EMPLOYEE");
		//int rowCount = query.executeUpdate();

		Employee employee = entitymanager.find(Employee.class, new EmployeePK(7002,4));
		entitymanager.remove(employee);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}
}
