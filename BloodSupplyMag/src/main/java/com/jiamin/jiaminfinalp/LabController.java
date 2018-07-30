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
import com.jiamin.pojo.User;
import com.jiamin.pojo.VitalSign;
import com.jiamin.pojo.WorkReqDonate;
import com.jiamin.tools.RandomGenerateTool;

/**
 * Handles requests for the application home page.
 */
@Controller
public class LabController {

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
	
	@RequestMapping(value = "/lab/home.htm", method = RequestMethod.GET)
	public ModelAndView labStation(HttpServletRequest request) {
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
			mv.addObject("errorMessage", "Login as labassistant first");
			mv.addObject("user", new User());
			mv.setViewName("login");
			return mv;
		}
		if (!user.getRole().equals("labassistant")) {
			mv.addObject("errorMessage", "Login as labassistant first");
			mv.addObject("user", new User());
			mv.setViewName("login");
			return mv;
		}
		try {
			List<WorkReqDonate> wrsList = wrDao.wrdListByBiStaDesUser("Pending(labassistant)","Test", user.getOrgan().getOrganName(), user);
			List<WorkReqDonate> wrdList = wrDao.wrdListByStaDes("Waiting for test", user.getOrgan().getOrganName());
			mv.addObject("lwrdList", wrdList);
			mv.addObject("lwrsList", wrsList);
			//System.out.println(wrsList.get(0).getStatus());
		} catch (OperateException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("labworkarea", "errorMessage", "error while get WorkReqDonate list");
		}
		mv.setViewName("labworkarea");
		return mv;
	}
	
	@RequestMapping(value = "/lab/test.htm", method = RequestMethod.GET)
	public ModelAndView test(HttpServletRequest request) {
		HttpSession session = (HttpSession) request.getSession();
		ModelAndView mv = new ModelAndView();
		int id = Integer.parseInt(request.getParameter("wrid"));
		User user = (User) session.getAttribute("user");
		if (user == null) {
			mv.addObject("errorMessage", "Login as labassistant first");
			mv.addObject("user", new User());
			mv.setViewName("login");
			return mv;
		}
		if (!user.getRole().equals("labassistant")) {
			mv.addObject("errorMessage", "Login as labassistant first");
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
				if (user.getRole().equals("labassistant") && wrd.getStatus().equals("Pending(labassistant)")){
					authority = true;
				}
				
				if (!(flag && authority)){
					mv.addObject("errorMessage", "user have no authority on this action");
					mv.setViewName("error");
					return mv;
				}
			}else {
				mv.addObject("errorMessage", "no such workrequest");
				mv.setViewName("redirect:/lab/home.htm");
				return mv;
			}
			VitalSign vs = new VitalSign();
			SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
			vs.setDate(df.format(new Date()));
			vs.setBloodtype(wrd.getPerson().getBloodType());
			RandomGenerateTool rgt = new RandomGenerateTool();
			vs.setInfection(rgt.randill());
			vs.setTempCondition(rgt.randill());
			if(wrd.getPerson().getVitalSignHistory().size() == 0){
				vs.setHemoglobin(rgt.randHemo());
				vs.setDiabetes(rgt.randDiabetes());
				vs.setPermCondition(rgt.randill());
			}else{
				VitalSign pvs = wrd.getPerson().getVitalSignHistory().get(0);
				vs.setHemoglobin(pvs.getHemoglobin());
				vs.setDiabetes(pvs.getDiabetes());
				vs.setPermCondition(pvs.getPermCondition());
			}
			if (vs.getDiabetes().equals("No") && vs.getHemoglobin().equals("Normal") && vs.getInfection().equals("No") &&vs.getPermCondition().equals("No") && vs.getTempCondition().equals("No"))
				vs.setIsHealthy("Yes");
			else
				vs.setIsHealthy("No");
			vs.setPerson(wrd.getPerson());
			wrd.setStatus("Test");
			wrDao.updateWrd(wrd);
			infoDao.createVitalSign(vs);
		} catch (OperateException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("redirect:/lab/home.htm", "errorMessage", "error while get organization list");
		}
		mv.setViewName("redirect:/lab/home.htm");
		return mv;
	}
	
	@RequestMapping(value = "/lab/sendback.htm", method = RequestMethod.GET)
	public ModelAndView sendReport(HttpServletRequest request) {
		HttpSession session = (HttpSession) request.getSession();
		ModelAndView mv = new ModelAndView();
		int id = Integer.parseInt(request.getParameter("wrid"));
		User user = (User) session.getAttribute("user");
		if (user == null) {
			mv.addObject("errorMessage", "Login as labassistant first");
			mv.addObject("user", new User());
			mv.setViewName("login");
			return mv;
		}
		if (!user.getRole().equals("labassistant")) {
			mv.addObject("errorMessage", "Login as labassistant first");
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
				if (user.getRole().equals("labassistant") && wrd.getStatus().equals("Test")){
					authority = true;
				}
				
				if (!(flag && authority)){
					mv.addObject("errorMessage", "user have no authority on this action");
					mv.setViewName("error");
					return mv;
				}
			}else {
				mv.addObject("errorMessage", "no such workrequest");
				mv.setViewName("redirect:/lab/home.htm");
				return mv;
			}
			wrd.setStatus("Tested");
			wrDao.updateWrd(wrd);
		} catch (OperateException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("redirect:/lab/home.htm", "errorMessage", "error while get organization list");
		}
		mv.setViewName("redirect:/lab/home.htm");
		return mv;
	}
	

}
