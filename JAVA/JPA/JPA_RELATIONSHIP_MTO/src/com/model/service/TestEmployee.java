package com.model.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.model.relationship.MTO.Department;
import com.model.relationship.MTO.Employee;


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
		//Create Employee1 Entity
		Employee employee1 = new Employee();
		employee1.setEname("Satish");
		employee1.setSalary(45000.0);
		employee1.setDeg("Technical Writer");
		employee1.setDepartment(department);
		//Create Employee2 Entity
		Employee employee2 = new Employee();
		employee2.setEname("Krishna");
		employee2.setSalary(45000.0);
		employee2.setDeg("Technical Writer");
		employee2.setDepartment(department);
		//Create Employee3 Entity
		Employee employee3 = new Employee();
		employee3.setEname("Masthanvali");
		employee3.setSalary(50000.0);
		employee3.setDeg("Technical Writer");
		employee3.setDepartment(department);
		//Store Employees
		entitymanager.persist(employee1);
		entitymanager.persist(employee2);
		entitymanager.persist(employee3);

		

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
