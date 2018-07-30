package com.jiamin.pojo;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "workrequest")
@Inheritance(strategy = InheritanceType.JOINED)
public class WorkRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "wrId", unique = true, nullable = false)
	private int wrId;
	@Column
	private String message;
	@Column
	private String status;
	@Column
	private String requestDate;
	@Column(nullable = true)
	private String solveDate;
	@ManyToOne
	@JoinColumn(name = "PID")
	private User person;
	@Column
	private int quantity;
	@Column
	private String destination;

	public WorkRequest() {
	}

	

	public int getWrId() {
		return wrId;
	}



	public void setWrId(int wrId) {
		this.wrId = wrId;
	}



	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}

	public String getSolveDate() {
		return solveDate;
	}

	public void setSolveDate(String solveDate) {
		this.solveDate = solveDate;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public User getPerson() {
		return person;
	}

	public void setPerson(User person) {
		this.person = person;
	}
	
}
