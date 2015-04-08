package com.service;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.model.Employee;

public class ListAllEmployee {
	public static void main(String[] args) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("DerbyPersistenceUnit");
		EntityManager entitymanager = emfactory.createEntityManager();

		System.out.println("\n----Using createQuery or Named Query---");
		System.out.println("\nUsing createQuery");
		Query query = entitymanager.createQuery("Select e from Employee e order by e.eid");
		List<Employee> list = (List<Employee>) query.getResultList();
		for (Employee e : list) {
			System.out.println(e);
		}

		System.out.println("\nUsing createQuery with Entity Class");
		query = entitymanager.createQuery("Select e from Employee e order by e.eid", Employee.class);
		list = (List<Employee>) query.getResultList();
		for (Employee e : list) {
			System.out.println(e);
		}

		System.out.println("\nUsing createNamedQuery");
		query = entitymanager.createNamedQuery("Employee.listAllEmployee");
		list = (List<Employee>) query.getResultList();
		for (Employee e : list) {
			System.out.println(e);
		}

		System.out.println("\nUsing createNamedQuery with Entity Class");
		query = entitymanager.createNamedQuery("Employee.listAllEmployee", Employee.class);
		list = (List<Employee>) query.getResultList();
		for (Employee e : list) {
			System.out.println(e);
		}
		System.out.println("\n----Using createNativeQuery---");

		System.out.println("\nUsing createNative createNamedQuery * ");
		query = entitymanager.createNamedQuery("Employee.listAllEmployeeNative");
		list = (List<Employee>) query.getResultList();
		for (Employee e : list) {
			System.out.println(e);
		}

		System.out.println("\nUsing createNative createNamedQuery2  ");
		query = entitymanager.createNamedQuery("Employee.listAllEmployeeNative2");
		list = (List<Employee>) query.getResultList();
		for (Employee e : list) {
			System.out.println(e);
		}

		System.out.println("\nUsing createNativeQuery * with Entity Class");
		query = entitymanager.createNativeQuery("Select * from Employee where ename=? order by 1", Employee.class);
		query.setParameter(1, "Mahesh Kumar");
		list = (List<Employee>) query.getResultList();
		for (Employee e : list) {
			System.out.println(e);
		}

		System.out.println("\nUsing custom createNativeQuery with Entity Class");
		query = entitymanager.createNativeQuery("Select e.eid, e.ename from Employee e order by 1", Employee.class);
		list = (List<Employee>) query.getResultList();
		for (Employee e : list) {
			System.out.println(e);
		}

		System.out.println("\nUsing custom createNativeQuery with mapping name random fields ");
		query = entitymanager.createNativeQuery("Select e.eid column1, e.ename column2 from Employee e order by 1", "EmployeeMapping");
		list = (List<Employee>) query.getResultList();
		for (Employee e : list) {
			System.out.println(e);
		}

		entitymanager.close();
		emfactory.close();
	}

}
