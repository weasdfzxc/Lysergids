package com.jiamin.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.jiamin.pojo.Blood;
import com.jiamin.pojo.BloodBank;

public class BloodBankStatus {
	private int bbID;
	private String bbName;
	private String bloodType;
	private int quantity;

	public int getBbID() {
		return bbID;
	}

	public void setBbID(int bbID) {
		this.bbID = bbID;
	}

	public String getBbName() {
		return bbName;
	}

	public void setBbName(String bbName) {
		this.bbName = bbName;
	}

	public String getBloodType() {
		return bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
