package com.model.entities;

import javax.persistence.ExcludeDefaultListeners;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Version;

@MappedSuperclass
public abstract class GenericEntity {
	
     @Version 
     protected Integer version;

	//Internal callback methods should always return void and take no arguments. 
	//They can have any name and any access level (public, protected, package and private) but should not be static.
	
    @PrePersist void onPrePersist() {System.out.println("Parent GenericEntity Listener onPrePersist called.");}
    @PostPersist void onPostPersist() {System.out.println("Parent GenericEntity Listener onPostPersist called.");}
    @PostLoad void onPostLoad() {System.out.println("Parent GenericEntity Listeneron PostLoad called.");}
    @PreUpdate void onPreUpdate() {System.out.println("Parent GenericEntity Listener onPreUpdate called.");}
    @PostUpdate void onPostUpdate() {System.out.println("Parent GenericEntity Listener onPostUpdate called.");}
    @PreRemove void onPreRemove() {System.out.println("Parent GenericEntity Listener onPreRemove called.");}
    @PostRemove void onPostRemove() {System.out.println("Parent GenericEntity Listener onPostRemove called.");}
	
}
