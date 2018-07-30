package com.jiamin.jiaminfinalp;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jiamin.dao.DAO;
import com.jiamin.dao.OrganizationDAO;
import com.jiamin.dao.UserDAO;
import com.jiamin.dao.WorkRequestDAO;
import com.jiamin.exception.OperateException;
import com.jiamin.pojo.BloodBank;
import com.jiamin.pojo.BloodManageCenter;
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
public class AdminController {

	@Autowired
	@Qualifier("userDao")
	UserDAO userDao;

	@Autowired
	@Qualifier("organDao")
	OrganizationDAO organDao;

	@Autowired
	@Qualifier("wrDao")
	WorkRequestDAO wrDao;

	@RequestMapping(value = "/admin/home.htm", method = RequestMethod.GET)
	public ModelAndView adminhome(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		DAO.close();
		try {
			List<Organization> organList = organDao.organList();
			List<User> userList = userDao.getList();
			List<WorkReqDonate> wrdList = wrDao.wrdList();
			List<WorkReqUse> wruList = wrDao.wruList();
			mv.addObject("organList", organList);
			mv.addObject("userList", userList);
			mv.addObject("wrdList", wrdList);
			mv.addObject("wruList", wruList);
		} catch (OperateException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("userdonate", "errorMessage", "error while get organization list");
		}
		mv.setViewName("dashboard");
		return mv;
	}

	@RequestMapping(value = "/admin/adduser.htm", method = RequestMethod.GET)
	public ModelAndView adduserpage(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();

		mv.setViewName("admincreateuser");
		return mv;
	}
	
	@RequestMapping(value = "/admin/addorgan.htm", method = RequestMethod.GET)
	public ModelAndView addOrganpage(HttpServletRequest request) {
		HttpSession session = (HttpSession) request.getSession();
		ModelAndView mv = new ModelAndView();

		mv.setViewName("createorgan");
		return mv;
	}
	
	
	@RequestMapping(value = "/admin/adduser.htm", method = RequestMethod.POST)
	public ModelAndView addUser(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		try {
			User users = userDao.get(request.getParameter("username"));
			if (users != null) {
				System.out.println("username already exists try another one");
				return new ModelAndView("admincreateuser", "errorMessage", "username already exists try another one");
			}
			if (request.getParameter("organ").equals("0")||request.getParameter("urole").equals("none")){
				return new ModelAndView("admincreateuser", "errorMessage", "Select role and organization");
			}
			User user = new User();
 			Organization organ = organDao.getOrgan(Integer.parseInt(request.getParameter("organ")));

			user.setFirstName(request.getParameter("firstName"));
			user.setLastName(request.getParameter("lastName"));
			user.setEmail(request.getParameter("email"));
			user.setGender(request.getParameter("gender"));
			user.setDateOfBirth(request.getParameter("dateOfBirth"));
			user.setPhone(request.getParameter("phone"));
			user.setOrgan(organ);
			user.setUsername(request.getParameter("username"));
			user.setPassword(request.getParameter("password"));
			user.setStatus("active");
			user.setRole(request.getParameter("urole"));
			
			userDao.register(user);
			mv.addObject("successMessage", "create successfully");
		} catch (OperateException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("admincreateuser", "errorMessage", "error while create account");
		}
		
		mv.setViewName("admincreateuser");
		return mv;
	}
	
	@RequestMapping(value = "/admin/addorgan.htm", method = RequestMethod.POST)
	public ModelAndView createOrgan(HttpServletRequest request) throws Exception {
		String otype = request.getParameter("otype");
		String oname = request.getParameter("oname");
		int pareneto = Integer.parseInt(request.getParameter("pareneto"));
		try {
			Organization o = organDao.getOrgan(oname);
			if (o != null) {
				System.out.println("Organization name already exists try another one");
				return new ModelAndView("createorgan", "errorMessage",
						"Organization name already exists try another one");
			}
		} catch (OperateException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("createorgan", "errorMessage", "error while create new organization");
		}
		if (otype.equals("clinic")) {
			Clinic organ = new Clinic();
			organ.setOrganName(oname);
			organ.setOrganType(otype);
			try {
				BloodBank bb = organDao.getBloodBank(pareneto);
				organ.setBloodBank(bb);
				organDao.createClinic(organ);
			} catch (OperateException e) {
				System.out.println("Exception: " + e.getMessage());
				return new ModelAndView("createorgan", "errorMessage", "error while create new organization");
			}
		} else if (otype.equals("bb")) {
			BloodBank organ = new BloodBank();
			organ.setOrganName(oname);
			organ.setOrganType(otype);
			try {
				BloodManageCenter bmc = organDao.getBMC(pareneto);
				organ.setBmc(bmc);
				organDao.createBloodBank(organ);
			} catch (OperateException e) {
				System.out.println("Exception: " + e.getMessage());
				return new ModelAndView("createorgan", "errorMessage", "error while create new organization");
			}
		} else if (otype.equals("bmc")) {
			BloodManageCenter organ = new BloodManageCenter();
			organ.setOrganName(oname);
			organ.setOrganType(otype);
			try {
				organDao.createBMC(organ);
			} catch (OperateException e) {
				System.out.println("Exception: " + e.getMessage());
				return new ModelAndView("createorgan", "errorMessage", "error while create new organization");
			}
		} else {
			System.out.println("no type of organization is selected, please select one");
			return new ModelAndView("createorgan", "errorMessage",
					"no type of organization is selected, please select one");
		}
		return new ModelAndView("createorgan", "successMessage", "create successfully");
	}

	@RequestMapping(value = "/admin/disableuser.htm", method = RequestMethod.GET)
	public ModelAndView disableuser(HttpServletRequest request) {
		try {
			int pid = Integer.parseInt(request.getParameter("pid"));
			User u = userDao.get(pid);
			u.setStatus("disable");
			userDao.update(u);
			return new ModelAndView("redirect:/admin/home.htm");
		} catch (OperateException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("redirect:/admin/home.htm", "errorMessage", "error while disable user");
		}
	}

	@RequestMapping(value = "/admin/activeuser.htm", method = RequestMethod.GET)
	public ModelAndView activeuser(HttpServletRequest request) {
		try {
			int pid = Integer.parseInt(request.getParameter("pid"));
			User u = userDao.get(pid);
			u.setStatus("active");
			System.out.println(u.getStatus());
			userDao.update(u);
			return new ModelAndView("redirect:/admin/home.htm");
		} catch (OperateException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("redirect:/admin/home.htm", "errorMessage", "error while active user");
		}
	}

	@RequestMapping(value = "/admin/deleteorgan.htm", method = RequestMethod.GET)
	public ModelAndView deleteOrgan(HttpServletRequest request) {

		try {
			int oid = Integer.parseInt(request.getParameter("oid"));
			Organization o = organDao.getOrgan(oid);
			organDao.delete(o);
			return new ModelAndView("redirect:/admin/home.htm");
		} catch (OperateException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("redirect:/admin/home.htm", "errorMessage", "error while delete organization");
		}
	}
	
	@RequestMapping(value = "/adminerror.htm", method = RequestMethod.GET)
	public ModelAndView adminerror(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("errorMessage", "Sensitive resources, please don't access");
		mv.setViewName("error");
		return mv;
	}

}
