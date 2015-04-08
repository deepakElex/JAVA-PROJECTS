package com.model.entities;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

public class EmployeeListener {

/*	@PrePersist - before a new entity is persisted (added to the EntityManager).
	@PostPersist - after storing a new entity in the database (during commit or flush).
	@PostLoad - after an entity has been retrieved from the database.
	@PreUpdate - when an entity is identified as modified by the EntityManager.
	@PostUpdate - after updating an entity in the database (during commit or flush).
	@PreRemove - when an entity is marked for removal in the EntityManager.
	@PostRemove - after deleting an entity from the database (during commit or flush).
	
	External callback methods (in a listener class) should always return void and take one argument
	that specifies the entity which is the source of the lifecycle event. 
	The argument can have any type that matches the actual value 
	(e.g. in the code above, Object can be replaced by a more specific type). 
	*/
	
	
	@PrePersist
	void onPrePersist(Object o) {
		System.out.println("EmployeeListener onPrePersist called.");
	}

	@PostPersist
	void onPostPersist(Object o) {
		System.out.println("EmployeeListener onPostPersist called.");
	}

	@PostLoad
	void onPostLoad(Object o) {
		System.out.println("EmployeeListener onPostLoad called.");
	}

	@PreUpdate
	void onPreUpdate(Object o) {
		System.out.println("EmployeeListener onPreUpdate called.");
	}

	@PostUpdate
	void onPostUpdate(Object o) {
		System.out.println("EmployeeListener onPostUpdate called.");
	}

	@PreRemove
	void onPreRemove(Object o) {
		System.out.println("EmployeeListener onPreRemove called.");
	}

	@PostRemove
	void onPostRemove(Object o) {
		System.out.println("EmployeeListener onPostRemove called.");
	}
}
