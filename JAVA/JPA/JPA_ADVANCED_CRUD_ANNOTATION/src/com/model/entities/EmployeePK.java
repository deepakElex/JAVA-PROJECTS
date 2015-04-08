package com.model.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class EmployeePK implements Serializable {

	private static final long serialVersionUID = 1L;

	private int account;

	private int id;

	public EmployeePK() {
		// TODO Auto-generated constructor stub
	}

	public EmployeePK(int account, int id) {
		this.account = account;
		this.id = id;
	}

	public int getAccount() {
		return account;
	}

	public void setAccount(int account) {
		this.account = account;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + account;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeePK other = (EmployeePK) obj;
		if (account != other.account)
			return false;
		if (id != other.id)
			return false;
		return true;
	}

}
