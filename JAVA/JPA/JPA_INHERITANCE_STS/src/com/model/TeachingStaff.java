package com.model;

import com.model.Staff;
import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: TeachingStaff
 * 
 */
@Entity
@DiscriminatorValue(value="TS")
public class TeachingStaff extends Staff implements Serializable {

	private String qualification;
	private String subjectexpertise;
	private static final long serialVersionUID = 1L;

	public TeachingStaff() {
		super();
	}

	public TeachingStaff(int sid, String sname, String qualification, String subjectexpertise) {
		super(sid, sname);
		this.qualification = qualification;
		this.subjectexpertise = subjectexpertise;
	}

	public String getQualification() {
		return this.qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getSubjectexpertise() {
		return this.subjectexpertise;
	}

	public void setSubjectexpertise(String subjectexpertise) {
		this.subjectexpertise = subjectexpertise;
	}

	@Override
	public String toString() {
		return "TeachingStaff [qualification=" + qualification + ", subjectexpertise=" + subjectexpertise + ", toString()=" + super.toString() + "]";
	}

}
