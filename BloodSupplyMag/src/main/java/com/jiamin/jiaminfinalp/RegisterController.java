package com.jiamin.jiaminfinalp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jiamin.dao.OrganizationDAO;
import com.jiamin.dao.UserDAO;
import com.jiamin.exception.OperateException;
import com.jiamin.validator.*;
import com.jiamin.pojo.User;

/**
 * Handles requests for the application home page.
 */
@Controller
public class RegisterController {

	@Autowired
	@Qualifier("registerValidator")
	RegisterValidator validator;
	

	@Autowired
	@Qualifier("userDao")
	UserDAO userDao;
	
	@Autowired
	@Qualifier("organDao")
	OrganizationDAO organDao;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	@RequestMapping(value = "/signup.htm", method = RequestMethod.GET)
	public ModelAndView registerPage() {
		ModelAndView mv = new ModelAndView();
		String[] genderlist = new String[]{"Male","Female"};
		mv.addObject("genderlist",genderlist);
		mv.addObject("user",new User());
		mv.setViewName("register");
		return mv;
	}
	
	@RequestMapping(value = "/signup.htm", method = RequestMethod.POST)
	public ModelAndView loginCheck(HttpServletRequest request,  @ModelAttribute("user") User user, BindingResult result) throws Exception {
		ModelAndView mv = new ModelAndView();
		validator.validate(user, result);

		if (result.hasErrors()) {
			return new ModelAndView("register", "user", user);
		}
		
		try {

			System.out.print("registerUser");

			User u = userDao.get(request.getParameter("username"));
			
			if(u != null){
				System.out.println("username already exists try another one");
				return new ModelAndView("register", "errorMessage", "username already exists try another one");
			}
			
			user.setRole("user");
			user.setOrgan(organDao.getOrgan(1));
			userDao.register(user);
			Email email = new SimpleEmail();
			email.setHostName("smtp.googlemail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("xdweasdfzxc@gmail.com", "sjm19930909001245"));
			email.setSSLOnConnect(true);
			email.setFrom(user.getEmail());
			email.setSubject("Hi,"+user.getUsername());
			email.setMsg("Sign up successfully");
			email.addTo(user.getEmail());
			email.send();
			return new ModelAndView("success", "successMessage", "Register successfully");

		} catch (OperateException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("register", "errorMessage", "while sign up");
		}
	}
	
}
