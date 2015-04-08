package com.service.advanced;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.model.entities.Employee;


public class Ordering {

	public static void main(String[] args) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("DerbyPersistenceUnit");
		EntityManager entitymanager = emfactory.createEntityManager();
		// Ordering
		Query query = entitymanager.createQuery("Select e " + "from Employee e " + "ORDER BY e.name ASC");
		List<Employee> list = (List<Employee>) query.getResultList();
		for (Employee e : list) {
			System.out.print("Employee ID :" + e.getId());
			System.out.println("\t Employee Name :" + e.getName());
		}
	}
}
