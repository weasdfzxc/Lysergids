package com.jiamin.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.jiamin.exception.OperateException;
import com.jiamin.pojo.Blood;
import com.jiamin.pojo.BloodBank;
import com.jiamin.pojo.Organization;
import com.jiamin.pojo.Person;
import com.jiamin.pojo.User;
import com.jiamin.pojo.VitalSign;
import com.jiamin.pojo.WorkReqDonate;
import com.jiamin.pojo.WorkReqUse;
import com.jiamin.tools.RandomGenerateTool;

public class InfoDAO extends DAO {

	public InfoDAO() {
	}

	public Blood getBlood(int bloodID) throws OperateException {
		try {
			begin();
			Query q = getSession().createQuery("from Blood where bloodID = :bloodID");
			q.setInteger("bloodID", bloodID);		
			Blood blood = (Blood) q.uniqueResult();
			commit();
			return blood;
		} catch (HibernateException e) {
			rollback();
			throw new OperateException("Could not get user " + bloodID, e);
		}
	}
	
	public List<Blood> getBloodList(String bloodType) throws OperateException {
		try {
			begin();
			Query q = getSession().createQuery("from Blood where bloodType = :bloodType");
			q.setString("bloodType", bloodType);		
			List<Blood> bloodlist = q.list();
			commit();
			return bloodlist;
		} catch (HibernateException e) {
			rollback();
			throw new OperateException("Could not get user " + bloodType, e);
		}
	}
	
	public List<Blood> getBloodList(BloodBank bloodBank) throws OperateException {
		try {
			begin();
			Query q = getSession().createQuery("from Blood where bloodBank = :bloodBank");
			q.setEntity("bloodBank", bloodBank);		
			List<Blood> bloodlist = q.list();
			commit();
			return bloodlist;
		} catch (HibernateException e) {
			rollback();
			throw new OperateException("Could not get user " + bloodBank.getOrganName(), e);
		}
	}
	
	public List<VitalSign> getVitalSignList(Person person) throws OperateException {
		try {
			begin();
			Query q = getSession().createQuery("from VitalSign where person = :person");
			q.setEntity("person", person);		
			List<VitalSign> vitalSignlist = q.list();
			commit();
			return vitalSignlist;
		} catch (HibernateException e) {
			rollback();
			throw new OperateException("Could not get vitalSignlist ", e);
		}
	}
	
	public List<Blood> getBloodList(String bloodType, BloodBank bloodBank) throws OperateException {
		try {
			begin();
			Query q = getSession().createQuery("from Blood where bloodBank = :bloodBank and bloodType = :bloodType");
			q.setEntity("bloodBank", bloodBank);
			q.setString("bloodType", bloodType);
			List<Blood> bloodlist = q.list();
			commit();
			return bloodlist;
		} catch (HibernateException e) {
			rollback();
			throw new OperateException("Could not get user " + bloodBank.getOrganName(), e);
		}
	}

	public void createBlood(Blood blood) throws OperateException {
		try {
			begin();
			getSession().save(blood);
			commit();
		} catch (HibernateException e) {
			rollback();
			System.out.println(e);
			throw new OperateException("Could not save blood ", e);
		}
	}
	
	public void createVitalSign(VitalSign vitalSign) throws OperateException {
		try {
			begin();
			getSession().save(vitalSign);
			commit();
		} catch (HibernateException e) {
			rollback();
			System.out.println(e);
			throw new OperateException("Could not save vitalSign ", e);
		}
	}
	
	public void update(Blood blood) throws OperateException {
		try {
			begin();
			getSession().merge(blood);
			commit();
		} catch (HibernateException e) {
			rollback();
			System.out.println(e);
			throw new OperateException("Could not update blood ", e);
		}
	}

	public void delete(Blood blood) throws OperateException {
		try {
			begin();
			getSession().delete(blood);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new OperateException("Could not delete blood ", e);
		}
	}
}