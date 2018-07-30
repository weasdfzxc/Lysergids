package com.jiamin.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.jiamin.pojo.User;

public class RegisterValidator implements Validator {

	public boolean supports(Class aClass) {
		return aClass.equals(User.class);
	}

	public void validate(Object obj, Errors errors) {
		User user = (User) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "error.title.empty", "username Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.actor.empty", "password Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "error.title.empty", "username Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "error.actor.empty", "password Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "error.title.empty", "username Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "error.actor.empty", "password Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gender", "error.title.empty", "username Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dateOfBirth", "error.actor.empty", "password Required");
		// check if user exists
		
	}
}
