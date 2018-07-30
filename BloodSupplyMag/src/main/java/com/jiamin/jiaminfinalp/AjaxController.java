package com.jiamin.jiaminfinalp;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jiamin.dao.OrganizationDAO;
import com.jiamin.pojo.Organization;

/**
 * Handles requests for the application home page.
 */
@Controller
public class AjaxController {
	
	@Autowired
	@Qualifier("organDao")
	OrganizationDAO organDao;
	
	@RequestMapping(value = "/ajax/organ", method = RequestMethod.POST)
	public String showOrgan(HttpServletRequest request, HttpServletResponse response)throws Exception{  
        
		StringBuilder str = new StringBuilder();  
		String otype = request.getParameter("otype");
		String parenttype = "none";
		String defualtMsg = "--Select--";
		System.out.println(otype);
		if(otype.equals("bb")){
			parenttype = "bmc";
		}else if(otype.equals("clinic")){
			parenttype = "bb";
		}
		
		if(parenttype.equals("none")){
			defualtMsg = "no parent organizations";
		}
	    List<Organization> list =  organDao.organList(parenttype);
	    str.append("<option value=\"");     
	    str.append("0");     
	    str.append("\">");     
	    str.append(defualtMsg);     
	    str.append("</option>");    
	    
	    for (Organization organ : list)     
	    {       
	      str.append("<option value=\"");       
	      str.append(organ.getOid());       
	      str.append("\">");       
	      str.append(organ.getOrganName());
	      System.out.println(organ.getOrganName());
	      str.append("</option>");           
	    }     
        //response.setCharacterEncoding("UTF-8");  
        response.setContentType("text/html");  
          
        PrintWriter out=response.getWriter();  
        out.print(str.toString());  
        System.out.println(str.toString());
        out.close();  
          
        return null;  
    }
	
	@RequestMapping(value = "/ajax/user", method = RequestMethod.POST)
	public String showOrganization(HttpServletRequest request, HttpServletResponse response)throws Exception{  
        
		StringBuilder str = new StringBuilder();  
		String urole = request.getParameter("urole");
		String organtype = "none";
		String defualtMsg = "--Select--";
		System.out.println(urole);
		if(urole.equals("bmcm")){
			organtype = "bmc";
		}else if(urole.equals("labassistant")||urole.equals("nurse")){
			organtype = "clinic";
		}else if(urole.equals("bbm")){
			organtype = "bb";
		}else if(urole.equals("deliver")||urole.equals("user")){
			organtype = "system";
		}
		
		if(organtype.equals("none")){
			defualtMsg = "------";
		}
	    List<Organization> list =  organDao.organList(organtype);
	    str.append("<option value=\"");     
	    str.append("0");     
	    str.append("\">");     
	    str.append(defualtMsg);     
	    str.append("</option>");    
	    
	    for (Organization organ : list)     
	    {       
	      str.append("<option value=\"");       
	      str.append(organ.getOid());       
	      str.append("\">");       
	      str.append(organ.getOrganName());
	      System.out.println(organ.getOrganName());
	      str.append("</option>");           
	    }     
        //response.setCharacterEncoding("UTF-8");  
        response.setContentType("text/html");  
          
        PrintWriter out=response.getWriter();  
        out.print(str.toString());  
        System.out.println(str.toString());
        out.close();  
          
        return null;  
    }
	
}
