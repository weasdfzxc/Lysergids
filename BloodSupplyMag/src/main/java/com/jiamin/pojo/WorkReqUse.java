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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "workrequse")
@PrimaryKeyJoinColumn(name = "wruId")
public class WorkReqUse extends WorkRequest{
	@ManyToMany
	@JoinTable(joinColumns = {
			@JoinColumn(name = "wruID", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "uId") })
	private Set<User> userSet;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "workReqUse")
	private List<Blood> useBloodList;

	public WorkReqUse() {
	}

	public Set<User> getUserSet() {
		return userSet;
	}

	public void setUserSet(Set<User> userSet) {
		this.userSet = userSet;
	}

	
	public List<Blood> getUseBloodList() {
		return useBloodList;
	}

	public void setUseBloodList(List<Blood> useBloodList) {
		this.useBloodList = useBloodList;
	}

}
