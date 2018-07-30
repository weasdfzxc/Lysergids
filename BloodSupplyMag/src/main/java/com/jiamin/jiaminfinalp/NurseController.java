package com.jiamin.jiaminfinalp;


import java.text.SimpleDateFormat;
import java.util.Date;
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

/**
 * Handles requests for the application home page.
 */
@Controller
public class NurseController {

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
	
	@RequestMapping(value = "/nurse/home.htm", method = RequestMethod.GET)
	public ModelAndView nurseStation(HttpServletRequest request) {
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
			mv.addObject("errorMessage", "Login as nurse first");
			mv.addObject("user", new User());
			mv.setViewName("login");
			return mv;
		}
		if (!user.getRole().equals("nurse")) {
			mv.addObject("errorMessage", "Login as nurse first");
			mv.addObject("user", new User());
			mv.setViewName("login");
			return mv;
		}
		try {
			//List<WorkReqDonate> wrdList = wrDao.wrdListByStaDes("pending(nurse)", user.getOrgan().getOrganName());
			List<WorkReqUse> wruList = wrDao.wruListByStaTri("Blood shortage", "Delivered", "Waiting for blood", user);
			//List<WorkReqDonate> wrdtList = wrDao.wrdListByStaDes("Tested", user.getOrgan().getOrganName());
			List<WorkReqDonate> wrdtList = wrDao.wrdListByStaDesUser("Tested", user.getOrgan().getOrganName(), user);
			List<WorkRequest> wrList = wrDao.wrListByStaDes("Requset sent", user.getOrgan().getOrganName());
			//List<WorkRequest> wrsList = wrDao.wrListByStaDes("Pending(nurse)", user.getOrgan().getOrganName());
			List<WorkReqDonate> wrsList = wrDao.wrdListByStaDesUser("Pending(nurse)", user.getOrgan().getOrganName(), user);
			List<WorkReqUse> wrsuList = wrDao.wruListByStaDesUser("Pending(nurse)", user.getOrgan().getOrganName(), user);
			mv.addObject("nwruList", wruList);
			mv.addObject("nwrsList", wrsList);
			mv.addObject("nwrsuList", wrsuList);
			mv.addObject("nwrdtList", wrdtList);
			mv.addObject("nwrList", wrList);
		} catch (OperateException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("nurseworkarea", "errorMessage", "error while get organization list");
		}
		mv.setViewName("nurseworkarea");
		return mv;
	}
	
	@RequestMapping(value = "/nurse/sendtest.htm", method = RequestMethod.GET)
	public ModelAndView sendTest(HttpServletRequest request) {
		HttpSession session = (HttpSession) request.getSession();
		ModelAndView mv = new ModelAndView();
		int id = Integer.parseInt(request.getParameter("wrid"));
		User user = (User) session.getAttribute("user");
		if (user == null) {
			mv.addObject("errorMessage", "Login as nurse first");
			mv.addObject("user", new User());
			mv.setViewName("login");
			return mv;
		}
		if (!user.getRole().equals("nurse")) {
			mv.addObject("errorMessage", "Login as nurse first");
			mv.addObject("user", new User());
			mv.setViewName("login");
			return mv;
		}
		try {
			WorkReqDonate wrd = wrDao.getWrd(id);
			if (wrd != null){
				boolean flag = false;
				for (User u : wrd.getUserSet()){
					if (u.getpID() == user.getpID())
						flag = true;
				}
				boolean authority = false;
				if (user.getRole().equals("nurse") && wrd.getStatus().equals("Pending(nurse)")){
					authority = true;
				}
				
				if (!(flag && authority)){
					mv.addObject("errorMessage", "user have no authority on this action");
					mv.setViewName("error");
					return mv;
				}
			}else {
				mv.addObject("errorMessage", "no such workrequest");
				mv.setViewName("redirect:/nurse/home.htm");
				return mv;
			}
			wrd.setStatus("Waiting for test");
			wrDao.updateWrd(wrd);
		} catch (OperateException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("redirect:/nurse/home.htm", "errorMessage", "error while get organization list");
		}
		mv.setViewName("redirect:/nurse/home.htm");
		return mv;
	}
	
	@RequestMapping(value = "/nurse/drawblood.htm", method = RequestMethod.GET)
	public ModelAndView drawBlood(HttpServletRequest request) {
		HttpSession session = (HttpSession) request.getSession();
		ModelAndView mv = new ModelAndView();
		int id = Integer.parseInt(request.getParameter("wrid"));
		User user = (User) session.getAttribute("user");
		if (user == null) {
			mv.addObject("errorMessage", "Login as nurse first");
			mv.addObject("user", new User());
			mv.setViewName("login");
			return mv;
		}
		if (!user.getRole().equals("nurse")) {
			mv.addObject("errorMessage", "Login as nurse first");
			mv.addObject("user", new User());
			mv.setViewName("login");
			return mv;
		}
		try {
			WorkReqDonate wrd = wrDao.getWrd(id);
			if (wrd != null){
				boolean flag = false;
				for (User u : wrd.getUserSet()){
					if (u.getpID() == user.getpID())
						flag = true;
				}
				boolean authority = false;
				if (user.getRole().equals("nurse") && wrd.getStatus().equals("Tested")){
					authority = true;
				}
				
				if (!(flag && authority)){
					mv.addObject("errorMessage", "user have no authority on this action");
					mv.setViewName("error");
					return mv;
				}
			}else {
				mv.addObject("errorMessage", "no such workrequest");
				mv.setViewName("redirect:/nurse/home.htm");
				return mv;
			}
			Blood blood = new Blood();
			blood.setBloodType(wrd.getPerson().getBloodType());
			blood.setDonor(wrd.getPerson());
			SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
			blood.setDate(df.format(new Date()));
			blood.setVolum(wrd.getQuantity());
			blood.setWorkReqDonate(wrd);
			//wrd.setBlood(blood);
			infoDao.createBlood(blood);
			
			int oid = user.getOrgan().getOid();
			BloodBank bb = organDao.getClinic(oid).getBloodBank();
			wrd.setDestination(bb.getOrganName());
			wrd.setStatus("Waiting for transport");
			wrDao.updateWrd(wrd);
		} catch (OperateException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("redirect:/nurse/home.htm", "errorMessage", "error while get organization list");
		}
		mv.setViewName("redirect:/nurse/home.htm");
		return mv;
	}
	
	@RequestMapping(value = "/nurse/useblood.htm", method = RequestMethod.GET)
	public ModelAndView useBlood(HttpServletRequest request) {
		HttpSession session = (HttpSession) request.getSession();
		ModelAndView mv = new ModelAndView();
		int id = Integer.parseInt(request.getParameter("wrid"));
		User user = (User) session.getAttribute("user");
		if (user == null) {
			mv.addObject("errorMessage", "Login as nurse first");
			mv.addObject("user", new User());
			mv.setViewName("login");
			return mv;
		}
		if (!user.getRole().equals("nurse")) {
			mv.addObject("errorMessage", "Login as nurse first");
			mv.addObject("user", new User());
			mv.setViewName("login");
			return mv;
		}
		try {
			WorkReqUse wrd = wrDao.getWru(id);
			if (wrd != null){
				boolean flag = false;
				for (User u : wrd.getUserSet()){
					if (u.getpID() == user.getpID())
						flag = true;
				}
				boolean authority = false;
				if (user.getRole().equals("nurse") && wrd.getStatus().equals("Delivered")){
					authority = true;
				}
				
				if (!(flag && authority)){
					mv.addObject("errorMessage", "user have no authority on this action");
					mv.setViewName("error");
					return mv;
				}
			}else {
				mv.addObject("errorMessage", "no such workrequest");
				mv.setViewName("redirect:/nurse/home.htm");
				return mv;
			}
			wrd.setStatus("Used");
			SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
			wrd.setSolveDate(df.format(new Date()));
			for (Blood blood: wrd.getUseBloodList()){
				blood.setUsePerson(wrd.getPerson());
				infoDao.update(blood);
			}
			wrDao.updateWru(wrd);
		} catch (OperateException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("redirect:/nurse/home.htm", "errorMessage", "error while update worrequse list");
		}
		mv.setViewName("redirect:/nurse/home.htm");
		return mv;
	}
	
	@RequestMapping(value = "/nurse/sendrequire.htm", method = RequestMethod.GET)
	public ModelAndView sendRequire(HttpServletRequest request) {
		HttpSession session = (HttpSession) request.getSession();
		ModelAndView mv = new ModelAndView();
		int id = Integer.parseInt(request.getParameter("wrid"));
		User user = (User) session.getAttribute("user");
		if (user == null) {
			mv.addObject("errorMessage", "Login as nurse first");
			mv.addObject("user", new User());
			mv.setViewName("login");
			return mv;
		}
		if (!user.getRole().equals("nurse")) {
			mv.addObject("errorMessage", "Login as nurse first");
			mv.addObject("user", new User());
			mv.setViewName("login");
			return mv;
		}
		try {
			WorkReqUse wrd = wrDao.getWru(id);
			if (wrd != null){
				boolean flag = false;
				for (User u : wrd.getUserSet()){
					if (u.getpID() == user.getpID())
						flag = true;
				}
				boolean authority = false;
				if (user.getRole().equals("nurse") && wrd.getStatus().equals("Pending(nurse)")){
					authority = true;
				}
				
				if (!(flag && authority)){
					mv.addObject("errorMessage", "user have no authority on this action");
					mv.setViewName("error");
					return mv;
				}
			}else {
				mv.addObject("errorMessage", "no such workrequest");
				mv.setViewName("redirect:/nurse/home.htm");
				return mv;
			}
			int oid = user.getOrgan().getOid();
			BloodBank bb = organDao.getClinic(oid).getBloodBank();
			int i = 0;
			for (Blood blood : bb.getBloodList()){
				if (blood.getBloodType().equals(wrd.getPerson().getBloodType()))
					i += blood.getVolum();
			}
			if (i >= wrd.getQuantity()){
				wrd.setStatus("Waiting for blood");
				wrd.setDestination(bb.getOrganName());
			}else {
				wrd.setStatus("Blood shortage");
				wrd.setDestination(bb.getOrganName());
			}
			wrDao.updateWru(wrd);
		} catch (OperateException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("redirect:/nurse/home.htm", "errorMessage", "error while get organization list");
		}
		mv.setViewName("redirect:/nurse/home.htm");
		return mv;
	}
	

}
