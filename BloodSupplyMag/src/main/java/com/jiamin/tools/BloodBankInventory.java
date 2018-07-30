package com.jiamin.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.jiamin.pojo.Blood;
import com.jiamin.pojo.BloodBank;

public class BloodBankInventory {
	private int bbID;
	private String bbName;
	private int typea;
	private int typeb;
	private int typeab;
	private int typeo;
	private String typearatio;
	private String typebratio;
	private String typeabratio;
	private String typeoratio;

	public void initratio() {
		int amount = typea + typeb + typeab + typeo;
		if (amount == 0) {
			typearatio = "no blood";
			typebratio = "no blood";
			typeabratio = "no blood";
			typeoratio = "no blood";
		} else {
			typearatio = 100 * typea / amount + "%";
			typebratio = 100 * typeb / amount + "%";
			typeabratio = 100 * typeab / amount + "%";
			typeoratio = 100 * typeo / amount + "%";
		}
	}

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

	public int getTypea() {
		return typea;
	}

	public void setTypea(int typea) {
		this.typea = typea;
	}

	public int getTypeb() {
		return typeb;
	}

	public void setTypeb(int typeb) {
		this.typeb = typeb;
	}

	public int getTypeab() {
		return typeab;
	}

	public void setTypeab(int typeab) {
		this.typeab = typeab;
	}

	public int getTypeo() {
		return typeo;
	}

	public void setTypeo(int typeo) {
		this.typeo = typeo;
	}

	public String getTypearatio() {
		return typearatio;
	}

	public void setTypearatio(String typearatio) {
		this.typearatio = typearatio;
	}

	public String getTypebratio() {
		return typebratio;
	}

	public void setTypebratio(String typebratio) {
		this.typebratio = typebratio;
	}

	public String getTypeabratio() {
		return typeabratio;
	}

	public void setTypeabratio(String typeabratio) {
		this.typeabratio = typeabratio;
	}

	public String getTypeoratio() {
		return typeoratio;
	}

	public void setTypeoratio(String typeoratio) {
		this.typeoratio = typeoratio;
	}

}
