package com.jiamin.pojo;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class Fileupload {

	private CommonsMultipartFile photo;

	public Fileupload(){}
	
	public CommonsMultipartFile getPhoto() {
		return photo;
	}

	public void setPhoto(CommonsMultipartFile photo) {
		this.photo = photo;
	}
	
}
