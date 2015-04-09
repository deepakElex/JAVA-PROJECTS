package com.model.relationship.OTM;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

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
	private List employeelist;

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

	public List getEmployeelist() {
		return employeelist;
	}

	public void setEmployeelist(List employeelist) {
		this.employeelist = employeelist;
	}

	@Override
	public String toString() {
		return "----->	Department [id=" + id + ", name=" + name + ", employeelist=" + employeelist + "]";
	}

}
