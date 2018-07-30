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

import com.jiamin.dao.UserDAO;
import com.jiamin.exception.OperateException;
import com.jiamin.validator.*;
import com.jiamin.pojo.User;

/**
 * Handles requests for the application home page.
 */
@Controller
public class LoginController {

	@Autowired
	@Qualifier("loginValidator")
	LoginValidator validator;

	@Autowired
	@Qualifier("userDao")
	UserDAO userDao = new UserDAO();

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	@RequestMapping(value = "/login.htm", method = RequestMethod.GET)
	public ModelAndView loginPage() {
		return new ModelAndView("login", "user", new User());
	}

	@RequestMapping(value = "/login.htm", method = RequestMethod.POST)
	public ModelAndView loginCheck(HttpServletRequest request, @ModelAttribute("user") User user, BindingResult result)
			throws Exception {
		ModelAndView mv = new ModelAndView();
		validator.validate(user, result);

		if (result.hasErrors()) {
			return new ModelAndView("login", "user", user);
		}
		HttpSession session = (HttpSession) request.getSession();

		try {

			System.out.print("loginUser");

			User u = userDao.get(request.getParameter("username"), request.getParameter("password"));

			if (u == null) {
				System.out.println("UserName/Password does not exist");
				return new ModelAndView("login", "errorMessage", "UserName/Password does not match, try again");
			}
			
			if (u.getStatus().equals("disable")){
				return new ModelAndView("login", "errorMessage", "User has been disabled, please contact admin to active");
			}

			session.setAttribute("user", u);
			if (u.getRole().equals("user")) {
				mv.setViewName("userhome");
				System.out.println("login as user");
			} else if (u.getRole().equals("admin")) {
				return new ModelAndView("redirect:/admin/home.htm");
			} else if (u.getRole().equals("nurse")) {
				return new ModelAndView("redirect:/nurse/home.htm");
			} else if (u.getRole().equals("labassistant")) {
				return new ModelAndView("redirect:/lab/home.htm");
			} else if (u.getRole().equals("deliver")) {
				return new ModelAndView("redirect:/deliver/home.htm");
			} else if (u.getRole().equals("bmcm")) {
				return new ModelAndView("redirect:/bmc/home.htm");
			}

		} catch (OperateException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("login", "errorMessage", "error while login");
		}
		return mv;
	}

	@RequestMapping(value = "/forget.htm", method = RequestMethod.GET)
	public ModelAndView fogetPage() {
		return new ModelAndView("forgetpassword");
	}

	@RequestMapping(value = "/forget.htm", method = RequestMethod.POST)
	public ModelAndView fogetCheck(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		try {

			User u = userDao.getreset(request.getParameter("username"), request.getParameter("email"));

			if (u == null) {
				System.out.println("UserName/Email does not match");
				return new ModelAndView("forgetpassword", "errorMessage", "UserName/Email does not match, try again");
			}
			Email email = new SimpleEmail();
			email.setHostName("smtp.googlemail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("xdweasdfzxc@gmail.com", "sjm19930909001245"));
			email.setSSLOnConnect(true);
			email.setFrom(request.getParameter("email"));
			email.setSubject("reset password");
			email.setMsg("Here is the link to reset your password: http://localhost:8080/jiaminfinalp/resetpassword.htm?GsiL6FQi-JXwPOS5xa*Gkscm5AUvMk53HgQNU="
							+ u.getpID() + "&5sER5t*nC8f=5QA/pmQSOtD");
			email.addTo(request.getParameter("email"));
			email.send();
			mv.addObject("successMessage", "Send reset email successfully");
			mv.setViewName("success");
		} catch (OperateException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("forgetpassword", "errorMessage", "error while sending reset email");
		}
		return mv;
	}

	@RequestMapping(value = "/resetpassword.htm", method = RequestMethod.GET)
	public ModelAndView resetCheck(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		String id = request.getParameter("GsiL6FQi-JXwPOS5xa*Gkscm5AUvMk53HgQNU");
		String security = request.getParameter("5sER5t*nC8f");
		if (id == null || !security.equals("5QA/pmQSOtD")) {
			return new ModelAndView("forgetpassword", "errorMessage", "error while vertify security");
		}
		HttpSession session = (HttpSession) request.getSession();
		session.setAttribute("resetpid", id);
		mv.setViewName("resetpassword");
		return mv;
	}

	@RequestMapping(value = "/resetpassword.htm", method = RequestMethod.POST)
	public ModelAndView resetpassword(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		HttpSession session = (HttpSession) request.getSession();
		try {
			int id = Integer.parseInt((String) session.getAttribute("resetpid"));
			String password = request.getParameter("password");
			String repassword = request.getParameter("repassword");
			if (password == null || !password.equals(repassword)) {
				return new ModelAndView("resetpassword", "errorMessage", "two password not match");
			}
			User u = userDao.get(id);
			u.setPassword(password);
			userDao.update(u);
			mv.addObject("successMessage", "reset password successfully");
			mv.setViewName("success");
		} catch (OperateException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("resetpassword", "errorMessage", "error while vertify security");
		}
		return mv;
	}

}
