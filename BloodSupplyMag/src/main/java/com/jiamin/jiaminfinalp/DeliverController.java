package com.jiamin.jiaminfinalp;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
public class DeliverController {

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
	
	@RequestMapping(value = "/deliver/home.htm", method = RequestMethod.GET)
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
			mv.addObject("errorMessage", "Login as distribution staff first");
			mv.addObject("user", new User());
			mv.setViewName("login");
			return mv;
		}
		if (!user.getRole().equals("deliver")) {
			mv.addObject("errorMessage", "Login as distribution staff first");
			mv.addObject("user", new User());
			mv.setViewName("login");
			return mv;
		}
		try {
			List<WorkReqDonate> wrdList = wrDao.wrdListByStaSi("Pending(deliver)", user);
			List<WorkReqUse> wruList = wrDao.wruListByStaSi("Pending(deliver)", user);
			List<WorkRequest> wrList = wrDao.wrListByBiSta("Waiting for transport", "Waiting for blood");
			mv.addObject("wrList", wrList);
			mv.addObject("wrdList", wrdList);
			mv.addObject("wruList", wruList);
		} catch (OperateException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("labworkarea", "errorMessage", "error while get WorkReqDonate list");
		}
		mv.setViewName("deliverworkarea");
		return mv;
	}
	
	@RequestMapping(value = "/deliver/deliver.htm", method = RequestMethod.GET)
	public ModelAndView test(HttpServletRequest request) {
		HttpSession session = (HttpSession) request.getSession();
		ModelAndView mv = new ModelAndView();
		int id = Integer.parseInt(request.getParameter("wrid"));
		DAO.close();
		User sessionuser = (User) session.getAttribute("user");
		User user;
		try {
			user = userDao.get((int)sessionuser.getpID());
		} catch (OperateException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("nurseworkarea", "errorMessage", "error while get organization list");
		}
		if (user == null) {
			mv.addObject("errorMessage", "Login as distribution staff first");
			mv.addObject("user", new User());
			mv.setViewName("login");
			return mv;
		}
		if (!user.getRole().equals("deliver")) {
			mv.addObject("errorMessage", "Login as distribution staff first");
			mv.addObject("user", new User());
			mv.setViewName("login");
			return mv;
		}
		try {
			WorkRequest wr = wrDao.getWr(id);
			if (wr != null){
				String type = wr.getMessage();
				if(type.equals("Donate")){
					System.out.println("donate deliver");
					WorkReqDonate wrd = wrDao.getWrd(id);
					boolean flag = false;
					for (User u : wrd.getUserSet()){
						if (u.getpID() == user.getpID())
							flag = true;
					}
					boolean authority = false;
					if (user.getRole().equals("deliver") && wrd.getStatus().equals("Pending(deliver)")){
						authority = true;
					}
					
					if (!(flag && authority)){
						mv.addObject("errorMessage", "user have no authority on this action");
						mv.setViewName("error");
						return mv;
					}
					
					Blood blood = wrd.getBlood();
					blood.setBloodBank(organDao.getBloodBank(wrd.getDestination()));
					SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
					wrd.setSolveDate(df.format(new Date()));
					wrd.setStatus("Stored");
					infoDao.update(blood);
					wrDao.updateWrd(wrd);
				}else{
					System.out.println("use deliver");
					WorkReqUse wru = wrDao.getWru(id);
					boolean flag = false;
					for (User u : wru.getUserSet()){
						if (u.getpID() == user.getpID())
							flag = true;
					}
					boolean authority = false;
					if (user.getRole().equals("deliver") && wru.getStatus().equals("Pending(deliver)")){
						authority = true;
					}
					
					if (!(flag && authority)){
						mv.addObject("errorMessage", "user have no authority on this action");
						mv.setViewName("error");
						return mv;
					}
					BloodBank bb = organDao.getBloodBank(wru.getDestination());
						
					List<Blood> bloodlist = new ArrayList<Blood>();
					wru.setUseBloodList(bloodlist);
					wru.setStatus("Delivered");
					int amount = 0;
					for (Blood blood : bb.getBloodList()){
						if(wru.getPerson().getBloodType().equals(blood.getBloodType()))
						if(amount < wru.getQuantity()){
							blood.setBloodBank(null);
							wru.getUseBloodList().add(blood);
							blood.setWorkReqUse(wru);
							infoDao.update(blood);
							amount += blood.getVolum();
						}
					}
					System.out.println(amount);
					wrDao.updateWru(wru);
				}
				
			}else {
				mv.addObject("errorMessage", "no such workrequest");
				mv.setViewName("error");
				return mv;
			}
		} catch (OperateException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("redirect:/deliver/home.htm", "errorMessage", "error while get organization list");
		}
		mv.setViewName("redirect:/deliver/home.htm");
		return mv;
	}
	

}
