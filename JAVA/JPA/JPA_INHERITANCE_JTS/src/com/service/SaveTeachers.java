package com.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.model.NonTeachingStaff;
import com.model.Staff;
import com.model.TeachingStaff;

public class SaveTeachers {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("DerbyPersistenceUnit");
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		// Staff entity
		Staff s1 = new Staff(1, "Ravi");
		Staff s2 = new Staff(2, "Sham");
		// Teaching staff entity
		TeachingStaff ts1 = new TeachingStaff(3, "Gopal", "MSc MEd", "Maths");
		TeachingStaff ts2 = new TeachingStaff(4, "Manisha", "BSc BEd", "English");
		// Non-Teaching Staff entity
		NonTeachingStaff nts1 = new NonTeachingStaff(5, "Satish", "Accounts");
		NonTeachingStaff nts2 = new NonTeachingStaff(6, "Krishna", "Office Admin");
		// storing all entities
		entitymanager.persist(s1);
		entitymanager.persist(s2);
		entitymanager.persist(ts1);
		entitymanager.persist(ts2);
		entitymanager.persist(nts1);
		entitymanager.persist(nts2);

		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}

}
