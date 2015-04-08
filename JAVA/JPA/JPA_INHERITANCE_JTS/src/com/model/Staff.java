package com.model;

import java.io.Serializable;
import java.lang.String;

import javax.annotation.Generated;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Staff
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@SequenceGenerator(name = "StaffSeqGenerator", allocationSize = 1, initialValue = 1)
public class Staff implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "StaffSeqGenerator")
	private int sid;
	@Basic
	private String sname;

	private static final long serialVersionUID = 1L;

	public Staff() {
		super();
	}

	public Staff(int sid, String sname) {
		this.sid = sid;
		this.sname = sname;
	}

	public int getSid() {
		return this.sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getSname() {
		return this.sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	@Override
	public String toString() {
		return "Staff [sid=" + sid + ", sname=" + sname + "]";
	}

}
