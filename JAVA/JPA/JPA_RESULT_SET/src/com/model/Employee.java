package com.model;

import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;

@Entity
@Table
@NamedQueries({

@NamedQuery(name = "Employee.listAllEmployee", query = "Select e from Employee e order by e.eid")

})
@NamedNativeQueries({

		@NamedNativeQuery(name = "Employee.listAllEmployeeNative", query = "Select * from Employee order by 1", resultClass = Employee.class),
		@NamedNativeQuery(name = "Employee.listAllEmployeeNative2", query = "Select e.eid column1, e.ename column2 from Employee e order by 1", resultSetMapping = "EmployeeMapping")

})
@SqlResultSetMappings({

@SqlResultSetMapping(name = "EmployeeMapping", entities = {

@EntityResult(entityClass = Employee.class, fields = { @FieldResult(name = "eid", column = "column1"), @FieldResult(name = "ename", column = "column2") })

}),

@SqlResultSetMapping(name = "EmployeeMappingDirect", entities = {

@EntityResult(entityClass = Employee.class) 

}),

@SqlResultSetMapping(name = "EmployeeMappingWithExtraColumns", entities = {

@EntityResult(entityClass = Employee.class)

}, columns = {@ColumnResult(name="Total")} )

})
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int eid;
	private String ename;
	private double salary;
	private String deg;

	public Employee(int eid, String ename, double salary, String deg) {
		super();
		this.eid = eid;
		this.ename = ename;
		this.salary = salary;
		this.deg = deg;
	}

	public Employee() {
		super();
	}

	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
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

	@Override
	public String toString() {
		return "Employee [eid=" + eid + ", ename=" + ename + ", salary= " + salary + ", deg=" + deg + "]";
	}
}