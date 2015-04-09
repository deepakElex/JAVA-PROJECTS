package com.model.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.model.relationship.MTM.Department;
import com.model.relationship.MTM.Employee;



public class TestEmployee {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("DerbyPersistenceUnit");
		
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		// Create Employee1 Entity
		Employee employee1 = new Employee();
		employee1.setEname("Satish");
		employee1.setSalary(45000.0);
		employee1.setDeg("Technical Writer");
		// Create Employee2 Entity
		Employee employee2 = new Employee();
		employee2.setEname("Krishna");
		employee2.setSalary(45000.0);
		employee2.setDeg("Technical Writer");
		// Create Employee3 Entity
		Employee employee3 = new Employee();
		employee3.setEname("Masthanvali");
		employee3.setSalary(50000.0);
		employee3.setDeg("Technical Writer");
		// Store Employee
		entitymanager.persist(employee1);
		entitymanager.persist(employee2);
		entitymanager.persist(employee3);
		// Create EmployeeSet
		Set<Employee> empSet1 = new HashSet();
		empSet1.add(employee1);
		empSet1.add(employee2);

		Set<Employee> empSet2 = new HashSet();
		empSet2.add(employee1);
		empSet2.add(employee2);
		empSet2.add(employee3);

		// Create Department Entity
		Department department1 = new Department();
		department1.setEmployeeSet(empSet1);
		department1.setName("Testing Department");

		// Create Department Entity
		Department department2 = new Department();
		department2.setEmployeeSet(empSet2);
		department2.setName("Developemt Department");

		// Store Department
		entitymanager.persist(department1);
		entitymanager.persist(department2);

		// Create DepartmentSet
		Set<Department> depSet = new HashSet();
		depSet.add(department1);
		depSet.add(department2);

		// Create Employee4 Entity
		Employee employee4 = new Employee();
		employee4.setEname("GOD");
		employee4.setSalary(500000.0);
		employee4.setDeg("GODMODE Writer");
		employee4.setDepartmentSet(depSet);
		// Store Employee
		entitymanager.persist(employee4);

		Query query = entitymanager.createQuery("\nSelect d from Department d order by d.id");
		List<Department> list = (List<Department>) query.getResultList();
		for (Department d : list) {
			System.out.println(d);
		}

		Query query2 = entitymanager.createQuery("\nSelect e from Employee e order by e.eid");
		List<Employee> list2 = (List<Employee>) query2.getResultList();
		for (Employee e : list2) {
			System.out.println(e);
		}

		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}

}
