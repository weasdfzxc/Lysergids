package com.jiamin.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.jiamin.exception.OperateException;
import com.jiamin.pojo.Blood;
import com.jiamin.pojo.Organization;
import com.jiamin.pojo.User;
import com.jiamin.pojo.VitalSign;
import com.jiamin.pojo.WorkReqDonate;
import com.jiamin.pojo.WorkReqUse;
import com.jiamin.tools.RandomGenerateTool;

public class UserDAO extends DAO {

	public UserDAO() {
	}

	public User get(String username, String password) throws OperateException {
		try {
			begin();
			Query q = getSession().createQuery("from User where username = :username and password = :password");
			q.setString("username", username);
			q.setString("password", password);			
			User user = (User) q.uniqueResult();
			commit();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new OperateException("Could not get user " + username, e);
		}
	}
	
	public User getreset(String username, String email) throws OperateException {
		try {
			begin();
			Query q = getSession().createQuery("from User where username = :username and email = :email");
			q.setString("username", username);
			q.setString("email", email);			
			User user = (User) q.uniqueResult();
			commit();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new OperateException("Could not get user " + username, e);
		}
	}
	
	public List<User> getList() throws OperateException {
		try {
			begin();
			Query q = getSession().createQuery("from User");	
			List<User> userList =  q.list();
			commit();
			return userList;
		} catch (HibernateException e) {
			rollback();
			throw new OperateException("Could not get user list", e);
		}
	}
	
	public User get(String username) throws OperateException {
		try {
			begin();
			Query q = getSession().createQuery("from User where username = :username");
			q.setString("username", username);		
			User user = (User) q.uniqueResult();
			commit();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new OperateException("Could not get user " + username, e);
		}
	}
	
	public User get(int userId) throws OperateException {
		try {
			begin();
			Query q = getSession().createQuery("from User where pID= :uID");
			q.setInteger("uID", userId);		
			User user = (User) q.uniqueResult();
			commit();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new OperateException("Could not get user " + userId, e);
		}
	}

	public User register(User u)
			throws OperateException {
		try {
			begin();
			System.out.println("inside DAO");

			User user = new User();
 			Organization organ = u.getOrgan();
//			List<WorkReqDonate> donateQueue = u.getDonateQueue();
//			List<WorkReqUse> useQueue = u.getUseQueue();
//			List<VitalSign> vitalSignHistory = u.getVitalSignHistory();
//			Set<Blood> donateSet = u.getDonateSet();
//			Set<Blood> usedSet = u.getUsedSet();

			user.setFirstName(u.getFirstName());
			user.setLastName(u.getLastName());
			user.setEmail(u.getEmail());
			user.setGender(u.getGender());
			user.setDateOfBirth(u.getDateOfBirth());
			user.setPhone(u.getPhone());
//			user.setVitalSignHistory(vitalSignHistory);
//			user.setDonateSet(donateSet);
//			user.setUsedSet(usedSet);
//			user.setUseQueue(useQueue);
//			user.setDonateQueue(donateQueue);
			user.setOrgan(organ);
			user.setUsername(u.getUsername());
			user.setPassword(u.getPassword());
			user.setStatus("active");
			user.setRole(u.getRole());
			RandomGenerateTool rgt = new RandomGenerateTool();
			user.setBloodType(rgt.randBloodType());
			//organ.getUserSet().add(user);
			
			getSession().save(user);
			commit();
			return user;

		} catch (HibernateException e) {
			rollback();
			throw new OperateException("Exception while creating user: " + e.getMessage());
		}
	}
	
	public void update(User u) throws OperateException {
		try {
			begin();
			System.out.println(u.getEmail());
			getSession().merge(u);
			commit();
		} catch (HibernateException e) {
			rollback();
			System.out.println(e);
			throw new OperateException("Could not update user " + u.getUsername(), e);
		}
	}

	public void delete(User user) throws OperateException {
		try {
			begin();
			getSession().delete(user);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new OperateException("Could not delete user " + user.getUsername(), e);
		}
	}
}