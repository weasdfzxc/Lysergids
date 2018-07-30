package com.jiamin.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "blood")
public class Blood {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BID", unique = true, nullable = false)
	private int bloodID;
	@ManyToOne
	@JoinColumn(name = "DPID", nullable = false)
	private Person donor;
	@ManyToOne
	@JoinColumn(name = "UPID", nullable = true)
	private Person usePerson;
	@Column
	private String bloodType;
	@Column
	private String date;
	@ManyToOne
	@JoinColumn(name = "BBID", nullable = true)
	private BloodBank bloodBank;
	@ManyToOne
	@JoinColumn(name = "wruID", nullable = true)
	private WorkReqUse workReqUse;
	@OneToOne
	@JoinColumn(name = "wrdID", nullable = false)
	private WorkReqDonate workReqDonate;
	@Column
	private int volum;

	public Blood() {
	}

	public int getBloodID() {
		return bloodID;
	}

	public void setBloodID(int bloodID) {
		this.bloodID = bloodID;
	}

	public Person getDonor() {
		return donor;
	}

	public void setDonor(Person donor) {
		this.donor = donor;
	}

	public Person getUsePerson() {
		return usePerson;
	}

	public void setUsePerson(Person usePerson) {
		this.usePerson = usePerson;
	}

	public String getBloodType() {
		return bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public BloodBank getBloodBank() {
		return bloodBank;
	}

	public void setBloodBank(BloodBank bloodBank) {
		this.bloodBank = bloodBank;
	}

	public int getVolum() {
		return volum;
	}

	public void setVolum(int volum) {
		this.volum = volum;
	}

	public WorkReqUse getWorkReqUse() {
		return workReqUse;
	}

	public void setWorkReqUse(WorkReqUse workReqUse) {
		this.workReqUse = workReqUse;
	}

	public WorkReqDonate getWorkReqDonate() {
		return workReqDonate;
	}

	public void setWorkReqDonate(WorkReqDonate workReqDonate) {
		this.workReqDonate = workReqDonate;
	}
}
