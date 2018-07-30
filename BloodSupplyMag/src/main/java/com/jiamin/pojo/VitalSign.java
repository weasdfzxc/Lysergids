package com.jiamin.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "vitalsign")
public class VitalSign {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "VID", unique = true, nullable = false)
	private int vitalSignID;
	@ManyToOne
	@JoinColumn(name = "PID", nullable = false)
	private Person person;
	@Column
	private String date;
	@Column
	private String bloodtype;
	@Column
	private String hemoglobin;
	@Column
	private String infection;
	@Column
	private String diabetes;
	@Column
	private String tempCondition;
	@Column
	private String permCondition;
	@Column
	private String isHealthy;

	public VitalSign() {
	}

	public int getVitalSignID() {
		return vitalSignID;
	}

	public void setVitalSignID(int vitalSignID) {
		this.vitalSignID = vitalSignID;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getBloodtype() {
		return bloodtype;
	}

	public void setBloodtype(String bloodtype) {
		this.bloodtype = bloodtype;
	}

	public String getHemoglobin() {
		return hemoglobin;
	}

	public void setHemoglobin(String hemoglobin) {
		this.hemoglobin = hemoglobin;
	}

	public String getInfection() {
		return infection;
	}

	public void setInfection(String infection) {
		this.infection = infection;
	}

	public String getDiabetes() {
		return diabetes;
	}

	public void setDiabetes(String diabetes) {
		this.diabetes = diabetes;
	}

	public String getTempCondition() {
		return tempCondition;
	}

	public void setTempCondition(String tempCondition) {
		this.tempCondition = tempCondition;
	}

	public String getPermCondition() {
		return permCondition;
	}

	public void setPermCondition(String permCondition) {
		this.permCondition = permCondition;
	}

	public String getIsHealthy() {
		return isHealthy;
	}

	public void setIsHealthy(String isHealthy) {
		this.isHealthy = isHealthy;
	}

}
