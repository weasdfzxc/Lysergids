package com.jiamin.pojo;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "workreqdonate")
@PrimaryKeyJoinColumn(name = "wrdId")
public class WorkReqDonate extends WorkRequest{
	@ManyToMany
	@JoinTable(joinColumns = {
			@JoinColumn(name = "wrdID", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "uId") })
	private Set<User> userSet;
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "workReqDonate")
	private Blood blood;

	public WorkReqDonate() {
	}

	public Set<User> getUserSet() {
		return userSet;
	}

	public void setUserSet(Set<User> userSet) {
		this.userSet = userSet;
	}
	public Blood getBlood() {
		return blood;
	}

	public void setBlood(Blood blood) {
		this.blood = blood;
	}
	
}
