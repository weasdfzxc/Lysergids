package com.jiamin.pojo;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

@Entity
@Table(name = "person")
@Inheritance(strategy = InheritanceType.JOINED) // table per subclass
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pID", unique = true, nullable = false)
	private long pID;
	@Column
	private String firstName;
	@Column
	private String lastName;
	@Column
	private String gender;
	@Column
	private String bloodType;
	@Column
	private String dateOfBirth;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
	private List<VitalSign> vitalSignHistory;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "donor")
	private Set<Blood> donateSet;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "usePerson")
	private Set<Blood> usedSet;

	public Person() {
	}

	public long getpID() {
		return pID;
	}

	public void setpID(long pID) {
		this.pID = pID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBloodType() {
		return bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public List<VitalSign> getVitalSignHistory() {
		return vitalSignHistory;
	}

	public void setVitalSignHistory(List<VitalSign> vitalSignHistory) {
		this.vitalSignHistory = vitalSignHistory;
	}

	public Set<Blood> getDonateSet() {
		return donateSet;
	}

	public void setDonateSet(Set<Blood> donateSet) {
		this.donateSet = donateSet;
	}

	public Set<Blood> getUsedSet() {
		return usedSet;
	}

	public void setUsedSet(Set<Blood> usedSet) {
		this.usedSet = usedSet;
	}

	@Override
	public String toString() {
		return firstName + " " + lastName;
	}
}
