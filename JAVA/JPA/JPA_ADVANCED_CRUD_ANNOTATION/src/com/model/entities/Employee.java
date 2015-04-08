package com.model.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.ExcludeDefaultListeners;
import javax.persistence.ExcludeSuperclassListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.NamedQueries;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import org.apache.derby.iapi.store.raw.log.LogFactory;

@Entity
@NamedQueries({ @NamedQuery(name = "Employee.find_the_employ_id_by_the_id", query = "Select e from Employee e where e.id = :id"),
		@NamedQuery(name = "Employee.find_the_employ_id_by_the_salary", query = "Select e from Employee e where e.salary > :sal"),
		@NamedQuery(name = "Employee.generateSeqId", query = "Select max(e.id) from Employee e where e.salary > :sal") })
@Table(name = "EMPLOYEE_ADVANCED")
@IdClass(EmployeePK.class)
@EntityListeners(EmployeeListener.class)
@SequenceGenerator(name = "EmployeeSeqSeqGen", allocationSize = 2, initialValue = 2000)
//@TableGenerator(name = "EmployeeSeqTabGen", table = "ID_GEN_TABLE", pkColumnName = "GEN_KEY", valueColumnName = "GEN_VALUE", pkColumnValue = "EmpID")
// @ExcludeSuperclassListeners
public class Employee extends GenericEntity {
	@Id
	@Column(name = "EMP_ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EmployeeSeqSeqGen")
	//@GeneratedValue(strategy = GenerationType.TABLE, generator = "EmployeeSeqTabGen")
	private int id;
	@Id
	@Column(name = "EMP_ACC", nullable = false)
	private int account;

	@Transient
	private int dummy = 0;

	private String name;
	private double salary;
	private String deg;

	@Embedded
	private EmployeeEmploymentPeriod employmentPeriod;

	public Employee(int account, String ename, double salary, String deg, Date startDate, Date endDate) {
		super();
		this.account = account;
		this.name = ename;
		this.salary = salary;
		this.deg = deg;

		employmentPeriod = new EmployeeEmploymentPeriod(startDate, endDate);
	}

	public Employee() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAccount() {
		return account;
	}

	public void setAccount(int account) {
		this.account = account;
	}

	public int getDummy() {
		return dummy;
	}

	public void setDummy(int dummy) {
		this.dummy = dummy;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getDeg() {
		return deg;
	}

	public void setDeg(String deg) {
		this.deg = deg;
	}

	public EmployeeEmploymentPeriod getEmploymentPeriod() {
		return employmentPeriod;
	}

	public void setEmploymentPeriod(EmployeeEmploymentPeriod employmentPeriod) {
		this.employmentPeriod = employmentPeriod;
	}

	@Override
	public String toString() {
		return "Employee [Account=" +account+ ", Id=" + id + ", Name=" + name + ", Salary= " + salary + ", deg=" + deg + "]";
	}
}