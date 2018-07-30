package com.jiamin.jiaminfinalp;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.jiamin.dao.OrganizationDAO;
import com.jiamin.dao.UserDAO;
import com.jiamin.dao.WorkRequestDAO;
import com.jiamin.exception.OperateException;
import com.jiamin.pojo.BloodBank;
import com.jiamin.pojo.BloodManageCenter;
import com.jiamin.pojo.Clinic;
import com.jiamin.pojo.Fileupload;
import com.jiamin.pojo.Organization;
import com.jiamin.pojo.User;
import com.jiamin.pojo.VitalSign;
import com.jiamin.pojo.WorkReqDonate;
import com.jiamin.pojo.WorkReqUse;
import com.jiamin.pojo.WorkRequest;

/**
 * Handles requests for the application home page.
 */
@Controller
public class CommonActionController {

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
	ServletContext servletContext;
	
	

	@RequestMapping(value = "/*/deny.htm", method = RequestMethod.GET)
	public ModelAndView denyAction(HttpServletRequest request) {
		HttpSession session = (HttpSession) request.getSession();
		ModelAndView mv = new ModelAndView();
		User user = (User) session.getAttribute("user");
		int id = Integer.parseInt(request.getParameter("wrid"));
		if (user == null) {
			mv.addObject("errorMessage", "Login as staff first");
			mv.addObject("user", new User());
			mv.setViewName("login");
			return mv;
		}
		if (user.getRole().equals("user")) {
			mv.addObject("errorMessage", "user have no authority on this action");
			mv.setViewName("userhome");
			return mv;
		}

		try {
			WorkReqDonate wrd = wrDao.getWrd(id);
			if (wrd != null) {
				boolean flag = false;
				for (User u : wrd.getUserSet()) {
					if (u.getpID() == user.getpID())
						flag = true;
				}
				boolean authority = false;
				if (user.getRole().equals("nurse")
						&& (wrd.getStatus().equals("Tested") || wrd.getStatus().equals("Pending(nurse)"))) {
					authority = true;
				} else if (user.getRole().equals("labassistant")
						&& (wrd.getStatus().equals("Tested") || wrd.getStatus().equals("pending(nurse)"))) {

				}

				if (!(flag && authority)) {
					mv.addObject("errorMessage", "user have no authority on this action");
					mv.setViewName("error");
					return mv;
				}
			} else {
				WorkReqUse wru = wrDao.getWru(id);
				if (wru != null) {
					boolean flag = false;
					for (User u : wru.getUserSet()) {
						if (u.getpID() == user.getpID())
							flag = true;
					}

					boolean authority = false;
					if (user.getRole().equals("nurse")
							&& (wru.getStatus().equals("Tested") || wru.getStatus().equals("pending(nurse)"))) {
						authority = true;
					} else if (user.getRole().equals("labassistant")
							&& (wru.getStatus().equals("Tested") || wru.getStatus().equals("pending(nurse)"))) {

					}

					if (!(flag && authority)) {
						mv.addObject("errorMessage", "user have no authority on this action");
						mv.setViewName("error");
						return mv;
					}
				} else {
					mv.addObject("errorMessage", "no such request");
					mv.setViewName("error");
					return mv;
				}
			}
			WorkRequest wr = wrDao.getWr(id);
			wr.setStatus("denied");
			SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
			wr.setSolveDate(df.format(new Date()));
			wrDao.updateWr(wr);
			if (user.getRole().equals("nurse")) {
				mv.setViewName("redirect:/nurse/home.htm");
			} else if (user.getRole().equals("labassistant")) {
				mv.setViewName("redirect:/labassistant/home.htm");
			}

		} catch (OperateException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while get workquest list");
		}
		return mv;
	}

	@RequestMapping(value = "/*/assigntome.htm", method = RequestMethod.GET)
	public ModelAndView assigntomeAction(HttpServletRequest request) {
		HttpSession session = (HttpSession) request.getSession();
		ModelAndView mv = new ModelAndView();
		User user = (User) session.getAttribute("user");
		int id = Integer.parseInt(request.getParameter("wrid"));
		if (user == null) {
			mv.addObject("errorMessage", "Login as staff first");
			mv.addObject("user", new User());
			mv.setViewName("login");
			return mv;
		}
		if (user.getRole().equals("user")) {
			mv.addObject("errorMessage", "user have no authority on this action");
			mv.setViewName("userhome");
			return mv;
		}

		try {
			WorkRequest wr = wrDao.getWr(id);
			if (user.getRole().equals("nurse") && wr.getStatus().equals("Requset sent")) {
				String type = wr.getMessage();
				if (type.equals("Donate")) {
					WorkReqDonate wrd = wrDao.getWrd(id);
					wrd.setStatus("Pending(nurse)");
					wrd.getUserSet().add(user);
					wrDao.updateWrd(wrd);
				} else {
					WorkReqUse wru = wrDao.getWru(id);
					wru.setStatus("Pending(nurse)");
					wru.getUserSet().add(user);
					wrDao.updateWru(wru);
				}
				mv.setViewName("redirect:/nurse/home.htm");
			} else if (user.getRole().equals("labassistant") && wr.getStatus().equals("Waiting for test")) {
				WorkReqDonate wrd = wrDao.getWrd(id);
				wrd.setStatus("Pending(labassistant)");
				wrd.getUserSet().add(user);
				wrDao.updateWrd(wrd);
				mv.setViewName("redirect:/lab/home.htm");
			} else if (user.getRole().equals("deliver")
					&& (wr.getStatus().equals("Waiting for transport") || wr.getStatus().equals("Waiting for blood"))) {
				String type = wr.getMessage();
				if (type.equals("Donate")) {
					WorkReqDonate wrd = wrDao.getWrd(id);
					wrd.setStatus("Pending(deliver)");
					wrd.getUserSet().add(user);
					wrDao.updateWrd(wrd);
				} else {
					WorkReqUse wru = wrDao.getWru(id);
					wru.setStatus("Pending(deliver)");
					wru.getUserSet().add(user);
					wrDao.updateWru(wru);
				}
				mv.setViewName("redirect:/deliver/home.htm");
			}

		} catch (OperateException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while get workquest list");
		}
		return mv;
	}

	@RequestMapping(value = "/*/viewuser.htm", method = RequestMethod.GET)
	public ModelAndView viewUser(HttpServletRequest request) {
		HttpSession session = (HttpSession) request.getSession();
		ModelAndView mv = new ModelAndView();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			mv.addObject("errorMessage", "Login as staff first");
			mv.addObject("user", new User());
			mv.setViewName("login");
			return mv;
		}
		if (user.getRole().equals("user")) {
			mv.addObject("errorMessage", "user have no authority on this action");
			mv.setViewName("userhome");
			return mv;
		}

		try {
			int id = Integer.parseInt(request.getParameter("pid"));
			User u = (User) userDao.get(id);

			List<WorkReqDonate> wrdList = u.getDonateQueue();
			List<WorkReqUse> wruList = u.getUseQueue();
			List<VitalSign> vsList = u.getVitalSignHistory();

			int i = 0;
			if (user.getRole().equals("admin")) {
				i = 1;
				mv.addObject("usertype", i);
			} else if (user.getRole().equals("user")) {
				i = 2;
				mv.addObject("usertype", i);
			} else if (user.getRole().equals("nurse")) {
				i = 3;
				mv.addObject("usertype", i);
			} else {
				mv.addObject("usertype", i);
			}
			mv.addObject("idforexcel",id);
			mv.addObject("uwrdList", wrdList);
			mv.addObject("uwruList", wruList);
			mv.addObject("vsList", vsList);
		} catch (OperateException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("userhistory", "errorMessage", "error while user data");
		}

		mv.setViewName("userhistory");
		return mv;
	}

	@RequestMapping(value = "/uploadavator.htm", method = RequestMethod.POST)
	public ModelAndView userhome(HttpServletRequest request, @ModelAttribute("fileupload") Fileupload fileupload) {
		HttpSession session = (HttpSession) request.getSession();
		User user = (User) session.getAttribute("user");
		ModelAndView mv = new ModelAndView();
		if (user == null) {
			mv.addObject("errorMessage", "Login as user first");
			mv.addObject("user", new User());
			mv.setViewName("login");
			return mv;
		}

		try {
			File directory;
			String check = File.separator;
			String path = null;
			if (check.equalsIgnoreCase("\\")) {
				path = servletContext.getRealPath("").replace("build\\", "");
				System.out.println(servletContext.getRealPath(""));
				System.out.println(servletContext.getContextPath());
			}

			if (check.equalsIgnoreCase("/")) {
				path = servletContext.getRealPath("").replace("build/", "");
				path += "/";
			}
			directory = new File(path + "\\resources\\" + user.getpID());
			boolean temp = directory.exists();
			if (!temp) {
				temp = directory.mkdir();
			}
			if (temp) {
				CommonsMultipartFile photoInMemory = fileupload.getPhoto();

				String fileName = photoInMemory.getOriginalFilename();
				// could generate file names as well

				File localFile = new File(directory.getPath(), fileName);

				// move the file from memory to the file

				photoInMemory.transferTo(localFile);
				user.setAvatorpath("resources/" + user.getpID()+"/"+fileName);
				System.out.println("File is stored at " + localFile.getPath());
				userDao.update(user);
			} else {
				System.out.println("Failed to create directory!");
			}

		} catch (IllegalStateException e) {
			System.out.println("*** IllegalStateException: " + e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("*** IOException: " + e.getMessage());
		} catch (OperateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mv.setViewName("redirect:/user/profile.htm");
		return mv;
	}

}
