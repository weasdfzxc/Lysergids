package com.jiamin.pojo;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "bloodmanagecenter")
@PrimaryKeyJoinColumn(name = "bmcID")
public class BloodManageCenter extends Organization{
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "bmc")
	private Set<BloodBank> bloodBankSet;

	public BloodManageCenter() {
	}

	public Set<BloodBank> getBloodBankSet() {
		return bloodBankSet;
	}

	public void setBloodBankSet(Set<BloodBank> bloodBankSet) {
		this.bloodBankSet = bloodBankSet;
	}
	
	
}
