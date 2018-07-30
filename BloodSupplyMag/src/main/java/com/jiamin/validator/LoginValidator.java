package com.jiamin.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.jiamin.pojo.User;

public class LoginValidator implements Validator {

	public boolean supports(Class aClass) {
		return aClass.equals(User.class);
	}

	public void validate(Object obj, Errors errors) {
		User user = (User) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "error.username.empty", "username Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.password.empty", "password Required");
		
		// check if user exists
		
	}
}
