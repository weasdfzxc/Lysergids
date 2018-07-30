package com.jiamin.pojo;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "clinic")
@PrimaryKeyJoinColumn(name = "clinicID")
public class Clinic extends Organization {
	@ManyToOne
	@JoinColumn(name = "bbID", nullable = false)
	private BloodBank bloodBank;

	public Clinic() {
	}

	public BloodBank getBloodBank() {
		return bloodBank;
	}

	public void setBloodBank(BloodBank bloodBank) {
		this.bloodBank = bloodBank;
	}

}
