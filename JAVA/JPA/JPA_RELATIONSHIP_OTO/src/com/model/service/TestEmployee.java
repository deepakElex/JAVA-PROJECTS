package com.model.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.model.relationship.OTO.Department;
import com.model.relationship.OTO.Employee;


public class TestEmployee {

	/**
	 * @param args 
	 */
	public static void main(String[] args) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("DerbyPersistenceUnit");
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		
		//Create Department Entity
		Department department = new Department();
		department.setName("Development");
		//Store Department
		entitymanager.persist(department);
		//Create Employee Entity
		Employee employee = new Employee();
		employee.setEname("Satish");
		employee.setSalary(45000.0);
		employee.setDeg("Technical Writer");
		employee.setDepartment(department);
		//Store Employee
		entitymanager.persist(employee);


		

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
