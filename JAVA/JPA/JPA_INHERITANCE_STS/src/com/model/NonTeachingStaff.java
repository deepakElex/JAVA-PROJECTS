package com.model;

import com.model.Staff;
import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: NonTeachingStaff
 * 
 */
@Entity
@DiscriminatorValue(value = "NTS")
public class NonTeachingStaff extends Staff implements Serializable {

	private String areaexpertise;

	private static final long serialVersionUID = 1L;

	public NonTeachingStaff(int sid, String sname, String areaexpertise) {
		super(sid, sname);
		this.areaexpertise = areaexpertise;
	}

	public NonTeachingStaff() {
		super();
	}

	public String getAreaexpertise() {
		return this.areaexpertise;
	}

	public void setAreaexpertise(String areaexpertise) {
		this.areaexpertise = areaexpertise;
	}

	@Override
	public String toString() {
		return "NonTeachingStaff [areaexpertise=" + areaexpertise + ", toString()=" + super.toString() + "]";
	}

}
