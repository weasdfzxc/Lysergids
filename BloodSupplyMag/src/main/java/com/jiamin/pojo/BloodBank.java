package com.jiamin.pojo;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "bloodbank")
@PrimaryKeyJoinColumn(name = "bbID")
public class BloodBank extends Organization {
	@ManyToOne
	@JoinColumn(name = "bmcID", nullable = false)
	private BloodManageCenter bmc;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "bloodBank")
	private Set<Clinic> clinicSet;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "bloodBank")
	private List<Blood> bloodList;

	public BloodBank() {
	}

	public BloodManageCenter getBmc() {
		return bmc;
	}

	public void setBmc(BloodManageCenter bmc) {
		this.bmc = bmc;
	}

	public Set<Clinic> getClinicSet() {
		return clinicSet;
	}

	public void setClinicSet(Set<Clinic> clinicSet) {
		this.clinicSet = clinicSet;
	}

	public List<Blood> getBloodList() {
		return bloodList;
	}

	public void setBloodList(List<Blood> bloodList) {
		this.bloodList = bloodList;
	}

}
