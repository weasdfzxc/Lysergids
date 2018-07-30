package com.jiamin.jiaminfinalp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.jiamin.dao.DAO;
import com.jiamin.dao.OrganizationDAO;
import com.jiamin.dao.UserDAO;
import com.jiamin.dao.WorkRequestDAO;
import com.jiamin.exception.OperateException;
import com.jiamin.pojo.Clinic;
import com.jiamin.pojo.Organization;
import com.jiamin.pojo.User;
import com.jiamin.pojo.VitalSign;
import com.jiamin.pojo.WorkReqDonate;
import com.jiamin.pojo.WorkReqUse;

/**
 * Handles requests for the application home page.
 */
@Controller
public class UserController {

	@Autowired
	@Qualifier("userDao")
	UserDAO userDao;

	@Autowired
	@Qualifier("organDao")
	OrganizationDAO organDao;

	@Autowired
	@Qualifier("wrDao")
	WorkRequestDAO wrDao;

	@RequestMapping(value = "/user/home.htm", method = RequestMethod.GET)
	public ModelAndView userhome(HttpServletRequest request) {
		HttpSession session = (HttpSession) request.getSession();
		ModelAndView mv = new ModelAndView();
		if (session.getAttribute("user") == null) {
			mv.addObject("errorMessage", "Login as user first");
			mv.addObject("user", new User());
			mv.setViewName("login");
			return mv;
		}
		mv.setViewName("userhome");
		return mv;
	}

	@RequestMapping(value = "/user/donate.htm", method = RequestMethod.GET)
	public ModelAndView donatePage(HttpServletRequest request) {
		HttpSession session = (HttpSession) request.getSession();
		ModelAndView mv = new ModelAndView();
		if (session.getAttribute("user") == null) {
			mv.addObject("errorMessage", "Login as user first");
			mv.addObject("user", new User());
			mv.setViewName("login");
			return mv;
		}
		try {
			List<Organization> clinicList = organDao.organList("clinic");
			mv.addObject("clinicList", clinicList);
		} catch (OperateException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("userdonate", "errorMessage", "error while get clinic list");
		}

		mv.setViewName("userdonate");
		return mv;
	}

	@RequestMapping(value = "/user/use.htm", method = RequestMethod.GET)
	public ModelAndView usePage(HttpServletRequest request) {
		HttpSession session = (HttpSession) request.getSession();
		ModelAndView mv = new ModelAndView();
		if (session.getAttribute("user") == null) {
			mv.addObject("errorMessage", "Login as user first");
			mv.addObject("user", new User());
			mv.setViewName("login");
			return mv;
		}
		try {
			List<Organization> clinicList = organDao.organList("clinic");
			mv.addObject("clinicList", clinicList);
		} catch (OperateException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("userdonate", "errorMessage", "error while get clinic list");
		}
		mv.setViewName("useruse");
		return mv;
	}

	@RequestMapping(value = "/user/history.htm", method = RequestMethod.GET)
	public ModelAndView historyPage(HttpServletRequest request) {
		HttpSession session = (HttpSession) request.getSession();
		ModelAndView mv = new ModelAndView();
		DAO.close();
		User user = (User) session.getAttribute("user");
		if (session.getAttribute("user") == null) {
			mv.addObject("errorMessage", "Login as user first");
			mv.addObject("user", new User());
			mv.setViewName("login");
			return mv;
		};
		try{
		User u = (User)userDao.get((int)user.getpID());


		List<WorkReqDonate> wrdList = u.getDonateQueue();
		List<WorkReqUse> wruList = u.getUseQueue();
		List<VitalSign> vsList = u.getVitalSignHistory();

		int i = 2;
		mv.addObject("usertype", i);
		mv.addObject("idforexcel",(int)user.getpID());
		mv.addObject("uwrdList", wrdList);
		mv.addObject("uwruList", wruList);
		mv.addObject("vsList", vsList);
		}catch (OperateException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("userhistory", "errorMessage", "error while user data");
		}

		mv.setViewName("userhistory");
		return mv;
	}

	@RequestMapping(value = "/user/profile.htm", method = RequestMethod.GET)
	public ModelAndView profilePage(HttpServletRequest request) {
		HttpSession session = (HttpSession) request.getSession();
		ModelAndView mv = new ModelAndView();
		DAO.close();
		User sessionuser = (User) session.getAttribute("user");
		User user = null;
		try {
			if(sessionuser != null)
				user = userDao.get((int)sessionuser.getpID());
		} catch (OperateException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("nurseworkarea", "errorMessage", "error while get organization list");
		}
		if (user == null) {
			mv.addObject("errorMessage", "Login as user first");
			mv.addObject("user", new User());
			mv.setViewName("login");
			return mv;
		}
		int i = 0;
		if(user.getRole().equals("admin")){
			i = 1;
			mv.addObject("usertype", i);
		}else if(user.getRole().equals("user")){
			i = 2;
			mv.addObject("usertype", i);
		}else if(user.getRole().equals("nurse")){
			i = 3;
			mv.addObject("usertype", i);
		}else if(user.getRole().equals("labassistant")){
			i = 4;
			mv.addObject("usertype", i);
		}else if(user.getRole().equals("deliver")){
			i = 5;
			mv.addObject("usertype", i);
		}else if(user.getRole().equals("bmcm")){
			i = 6;
			mv.addObject("usertype", i);
		}else{
			mv.addObject("usertype", i);
		}
		String avatorpath = "resources/default.jpg";
		if(user.getAvatorpath() != null)
			avatorpath = user.getAvatorpath();
		mv.addObject("avator", avatorpath);
		mv.setViewName("userprofile");
		return mv;
	}

	@RequestMapping(value = "/user/profile.htm", method = RequestMethod.POST)
	public ModelAndView profileUpdate(HttpServletRequest request) {
		HttpSession session = (HttpSession) request.getSession();
		ModelAndView mv = new ModelAndView();
		User user = (User)session.getAttribute("user");
		if (user == null) {
			mv.addObject("errorMessage", "Login as user first");
			mv.addObject("user", new User());
			mv.setViewName("login");
			return mv;
		}
		user.setEmail(request.getParameter("email"));
		user.setPhone(request.getParameter("phone"));
		
		try {
			System.out.println(user.getEmail());
			userDao.update(user);
		} catch (OperateException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("redirect:/user/profile.htm", "errorMessage", "error while update profile");
		}
		mv.setViewName("redirect:/user/profile.htm");
		return mv;
	}

	@RequestMapping(value = "/user/updatepassword.htm", method = RequestMethod.POST)
	public ModelAndView passwordChange(HttpServletRequest request) {
		HttpSession session = (HttpSession) request.getSession();
		ModelAndView mv = new ModelAndView();
		if (session.getAttribute("user") == null) {
			mv.addObject("errorMessage", "Login as user first");
			mv.addObject("user", new User());
			mv.setViewName("login");
			return mv;
		}
		User user = (User) session.getAttribute("user");
		if (!user.getPassword().equals(request.getParameter("cpassword"))){
			return new ModelAndView("redirect:/user/profile.htm", "errorMessage", "current password is wrong, try again");
		}
		user.setPassword(request.getParameter("npassword"));
		try {
			userDao.update(user);
		} catch (OperateException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("redirect:/user/profile.htm", "errorMessage", "error while update password");
		}
		mv.setViewName("redirect:/user/profile.htm");
		return mv;
	}

	@RequestMapping(value = "/user/donate.htm", method = RequestMethod.POST)
	public ModelAndView donate(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		HttpSession session = (HttpSession) request.getSession();

		try {

			System.out.print("user donate");

			Clinic clinic = organDao.getClinic(Integer.parseInt(request.getParameter("oid")));

			if (clinic == null) {
				System.out.println("Selcet clinic first");
				return new ModelAndView("userdonate", "errorMessage", "Selcet clinic first");
			}

			User user = (User) session.getAttribute("user");

			WorkReqDonate wrd = new WorkReqDonate();
			SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
			wrd.setRequestDate(df.format(new Date()));
			wrd.setDestination(clinic.getOrganName());
			wrd.setMessage("Donate");
			wrd.setQuantity(Integer.parseInt(request.getParameter("donations")));
			wrd.setStatus("Requset sent");
			wrd.setPerson(user);
			Set<User> uset= new HashSet<User>();
			uset.add(user);
			wrd.setUserSet(uset);
			wrDao.createWrd(wrd);
			mv.setViewName("redirect:/user/history.htm");

		} catch (OperateException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("login", "errorMessage", "error while submit request");
		}
		return mv;
	}

	@RequestMapping(value = "/user/use.htm", method = RequestMethod.POST)
	public ModelAndView use(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		HttpSession session = (HttpSession) request.getSession();

		try {

			System.out.print("user require blood");

			Clinic clinic = organDao.getClinic(Integer.parseInt(request.getParameter("oid")));

			if (clinic == null) {
				System.out.println("Selcet clinic first");
				return new ModelAndView("userdonate", "errorMessage", "Selcet clinic first");
			}

			User user = (User) session.getAttribute("user");

			WorkReqUse wru = new WorkReqUse();
			SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
			wru.setRequestDate(df.format(new Date()));
			wru.setDestination(clinic.getOrganName());
			wru.setMessage("Use");
			wru.setPerson(user);
			wru.setQuantity(Integer.parseInt(request.getParameter("quantities")));
			wru.setStatus("Requset sent");
			Set<User> uset= new HashSet<User>();
			uset.add(user);
			wru.setUserSet(uset);
			wrDao.createWru(wru);
			mv.setViewName("redirect:/user/history.htm");
		} catch (OperateException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("login", "errorMessage", "error while submit request");
		}
		return mv;
	}
	
	@RequestMapping(value = "/user/report.xls", method = RequestMethod.GET)
	public ModelAndView excelReport(HttpServletRequest request) {
		DAO.close();
		User user = null;
		try {
			user = userDao.get(Integer.parseInt(request.getParameter("pid")));
		} catch (OperateException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("redirect:/user/history.htm", "errorMessage", "error while get user info");
		}
		ExcelView view = new ExcelView();
		view.setUser(user);
		return new ModelAndView(view);
	}

}
