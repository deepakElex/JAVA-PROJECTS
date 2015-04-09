package com.model.relationship.MTM;

import java.io.Serializable;
import java.lang.String;
import java.util.List;
import java.util.Set;

import javax.annotation.Generated;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Department
 * 
 */
@Entity
@SequenceGenerator(name = "DepartmentSeqGenerator", initialValue = 1, allocationSize = 1)
public class Department implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DepartmentSeqGenerator")
	private int id;
	private String name;

	@OneToMany(targetEntity = Employee.class)
	private Set employeeSet;

	private static final long serialVersionUID = 1L;

	public Department() {
		super();
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set getEmployeeSet() {
		return employeeSet;
	}

	public void setEmployeeSet(Set employeeSet) {
		this.employeeSet = employeeSet;
	}

	@Override
	public String toString() {
		return "----->	Department [id=" + id + ", name=" + name + ", employeeSet=" + employeeSet + "]";
	}

}
