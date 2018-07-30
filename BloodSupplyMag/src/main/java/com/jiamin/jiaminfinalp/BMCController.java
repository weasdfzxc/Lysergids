package com.jiamin.jiaminfinalp;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import com.jiamin.dao.DAO;
import com.jiamin.dao.InfoDAO;
import com.jiamin.dao.OrganizationDAO;
import com.jiamin.dao.UserDAO;
import com.jiamin.dao.WorkRequestDAO;
import com.jiamin.exception.OperateException;
import com.jiamin.pojo.Blood;
import com.jiamin.pojo.BloodBank;
import com.jiamin.pojo.User;
import com.jiamin.pojo.WorkReqDonate;
import com.jiamin.pojo.WorkReqUse;
import com.jiamin.pojo.WorkRequest;
import com.jiamin.tools.BloodBankInventory;
import com.jiamin.tools.BloodBankStatus;
import com.jiamin.tools.WruBbs;

/**
 * Handles requests for the application home page.
 */
@Controller
public class BMCController {

	@Autowired
	@Qualifier("userDao")
	UserDAO userDao;

	@Autowired
	@Qualifier("organDao")
	OrganizationDAO organDao;

	@Autowired
	@Qualifier("wrDao")
	WorkRequestDAO wrDao;
	
	@Autowired
	@Qualifier("infoDao")
	InfoDAO infoDao;
	
	@RequestMapping(value = "/bmc/home.htm", method = RequestMethod.GET)
	public ModelAndView bmc(HttpServletRequest request) {
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
			return new ModelAndView("nurseworkarea", "errorMessage", "error while get user");
		}
		if (user == null) {
			mv.addObject("errorMessage", "Login as bloodManageCenter staff first");
			mv.addObject("user", new User());
			mv.setViewName("login");
			return mv;
		}
		if (!user.getRole().equals("bmcm")) {
			mv.addObject("errorMessage", "Login as bloodManageCenter staff first");
			mv.addObject("user", new User());
			mv.setViewName("login");
			return mv;
		}
		try {
			List<WorkReqUse> wruList = wrDao.wruListBySta("Blood shortage");
			List<WruBbs> wrubbsList = new ArrayList<WruBbs>();
			Set<BloodBank> bankList = organDao.getBMC(user.getOrgan().getOrganName()).getBloodBankSet();
			for (WorkReqUse wru : wruList){
				WruBbs wrubbs = new WruBbs();
				wrubbs.setWru(wru);
				wrubbs.setBbsList(organDao.bbsList(bankList, wru.getPerson().getBloodType(), wru.getQuantity()));
				wrubbsList.add(wrubbs);
			}
			mv.addObject("wrubbsList", wrubbsList);
			//mv.addObject("wruList", wruList);
		} catch (OperateException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("bmcworkarea", "errorMessage", "error while get WorkReqUse list");
		}
		mv.setViewName("bmcworkarea");
		return mv;
	}
	
	@RequestMapping(value = "/bmc/info.htm", method = RequestMethod.GET)
	public ModelAndView bmcdatapanel(HttpServletRequest request) {
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
			return new ModelAndView("nurseworkarea", "errorMessage", "error while get user");
		}
		if (user == null) {
			mv.addObject("errorMessage", "Login as bloodManageCenter staff first");
			mv.addObject("user", new User());
			mv.setViewName("login");
			return mv;
		}
		if (!user.getRole().equals("bmcm")) {
			mv.addObject("errorMessage", "Login as bloodManageCenter staff first");
			mv.addObject("user", new User());
			mv.setViewName("login");
			return mv;
		}
		try {
			Set<BloodBank> bankList = organDao.getBMC(user.getOrgan().getOrganName()).getBloodBankSet();
			List<BloodBankInventory> bbiList = organDao.bbiList(bankList);
			mv.addObject("bbiList", bbiList);
			mv.addObject("user", user);
			//mv.addObject("wruList", wruList);
		} catch (OperateException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("bmcdatapanel", "errorMessage", "error while get bloodbank list");
		}
		mv.setViewName("bmcdatapanel");
		return mv;
	}
	
	@RequestMapping(value = "/bmc/transfer.htm", method = RequestMethod.POST)
	public ModelAndView test(HttpServletRequest request) {
		HttpSession session = (HttpSession) request.getSession();
		ModelAndView mv = new ModelAndView();
		int id = Integer.parseInt(request.getParameter("bb"));
		DAO.close();
		User sessionuser = (User) session.getAttribute("user");
		User user = null;
		try {
			if(sessionuser != null)
			user = userDao.get((int)sessionuser.getpID());
		} catch (OperateException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("nurseworkarea", "errorMessage", "error while get user");
		}
		if (user == null) {
			mv.addObject("errorMessage", "Login as bloodManageCenter staff first");
			mv.addObject("user", new User());
			mv.setViewName("login");
			return mv;
		}
		if (!user.getRole().equals("bmcm")) {
			mv.addObject("errorMessage", "Login as bloodManageCenter staff first");
			mv.addObject("user", new User());
			mv.setViewName("login");
			return mv;
		}
		
		if (id == 0){
			mv.addObject("errorMessage", "Select BloodBank");
			mv.setViewName("error");
			return mv;
		}
		
		try {
			WorkReqUse wru = wrDao.getWru(Integer.parseInt(request.getParameter("wruid")));
			BloodBank bb = organDao.getBloodBank(Integer.parseInt(request.getParameter("bb")));
			wru.setDestination(bb.getOrganName());
			wru.getUserSet().add(user);
			wru.setStatus("Waiting for blood");
			wrDao.updateWru(wru);
		} catch (OperateException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("nurseworkarea", "errorMessage", "error while setting transfer");
		}
		
		mv.setViewName("redirect:/bmc/home.htm");
		return mv;
	}
	
	@RequestMapping(value = "/bmc/report.xls", method = RequestMethod.GET)
	public ModelAndView excelReport(HttpServletRequest request) {
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
			return new ModelAndView("nurseworkarea", "errorMessage", "error while get user");
		}
		if (user == null) {
			mv.addObject("errorMessage", "Login as bloodManageCenter staff first");
			mv.addObject("user", new User());
			mv.setViewName("login");
			return mv;
		}
		if (!user.getRole().equals("bmcm")) {
			mv.addObject("errorMessage", "Login as bloodManageCenter staff first");
			mv.addObject("user", new User());
			mv.setViewName("login");
			return mv;
		}
		try {
			Set<BloodBank> bankList = organDao.getBMC(user.getOrgan().getOrganName()).getBloodBankSet();
			List<BloodBankInventory> bbiList = organDao.bbiList(bankList);
			BMCExcelView view = new BMCExcelView();
			view.setUser(user);
			view.setBbiList(bbiList);
			return new ModelAndView(view);
		} catch (OperateException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("bmcdatapanel", "errorMessage", "error while get bloodbank list");
		}
	}
	

}
