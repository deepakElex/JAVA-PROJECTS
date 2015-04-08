package com.service.advanced;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.model.entities.Employee;


public class NamedQueries {

	public static void main(String[] args) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("DerbyPersistenceUnit");
		EntityManager entitymanager = emfactory.createEntityManager();
		Query query = entitymanager.createNamedQuery("Employee.find_the_employ_id_by_the_id");
		query.setParameter("id", 251);
		List<Employee> list = query.getResultList();
		for (Employee e : list) {
			System.out.print("Employee ID :" + e.getId());
			System.out.println("\t Employee Name :" + e.getName());
		}

		query = entitymanager.createNamedQuery("Employee.find_the_employ_id_by_the_salary");
		query.setParameter("sal", 25000);
		list = query.getResultList();
		for (Employee e : list) {
			System.out.print("Employee ID :" + e.getId());
			System.out.println("\t Employee Name :" + e.getName());
		}

	}

}
