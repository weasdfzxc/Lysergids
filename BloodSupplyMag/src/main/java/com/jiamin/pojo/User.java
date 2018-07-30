package com.jiamin.pojo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "user")
@PrimaryKeyJoinColumn(name = "pID")
public class User extends Person {
	@Column
	private String username;
	@Column
	private String password;
	@Column
	private String email;
	@Column
	private String phone;
	@Column
	private String role;
	@Column
	private String avatorpath;
	@Column
	private String status;
	@ManyToOne
	@JoinColumn(name = "OID", nullable = false)
	private Organization organ;
	@OneToMany(mappedBy = "person")
	private List<WorkRequest> requestCreateQueue;
	@ManyToMany(mappedBy = "userSet")
	private List<WorkReqDonate> donateQueue;
	@ManyToMany(mappedBy = "userSet")
	private List<WorkReqUse> useQueue;

	public User() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAvatorpath() {
		return avatorpath;
	}

	public void setAvatorpath(String avatorpath) {
		this.avatorpath = avatorpath;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Organization getOrgan() {
		return organ;
	}

	public void setOrgan(Organization organ) {
		this.organ = organ;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<WorkReqDonate> getDonateQueue() {
		return donateQueue;
	}

	public void setDonateQueue(List<WorkReqDonate> donateQueue) {
		this.donateQueue = donateQueue;
	}

	public List<WorkReqUse> getUseQueue() {
		return useQueue;
	}

	public void setUseQueue(List<WorkReqUse> useQueue) {
		this.useQueue = useQueue;
	}

	public List<WorkRequest> getRequestCreateQueue() {
		return requestCreateQueue;
	}

	public void setRequestCreateQueue(List<WorkRequest> requestCreateQueue) {
		this.requestCreateQueue = requestCreateQueue;
	}
	

}
