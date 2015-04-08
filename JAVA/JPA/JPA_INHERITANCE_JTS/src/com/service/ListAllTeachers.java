package com.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.model.NonTeachingStaff;
import com.model.Staff;
import com.model.TeachingStaff;

public class ListAllTeachers {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("DerbyPersistenceUnit");
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		
		Query query = entitymanager.createQuery("Select s from Staff s ORDER BY s.sid");
		List<Staff> list = (List<Staff>) query.getResultList();
		for (Staff e : list) {
			System.out.println(e);
		}
		
		
		query = entitymanager.createQuery("Select s from TeachingStaff s ORDER BY s.sid");
		List<TeachingStaff> list2 = (List<TeachingStaff>) query.getResultList();
		for (TeachingStaff e : list2) {
			System.out.println(e);
		}
		
		query = entitymanager.createQuery("Select s from NonTeachingStaff s ORDER BY s.sid");
		List<NonTeachingStaff> list3 = (List<NonTeachingStaff>) query.getResultList();
		for (NonTeachingStaff e : list3) {
			System.out.println(e);
		}
		
			entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}

}
