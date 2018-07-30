package com.jiamin.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;

import com.jiamin.exception.OperateException;
import com.jiamin.pojo.User;
import com.jiamin.pojo.WorkReqDonate;
import com.jiamin.pojo.WorkReqUse;
import com.jiamin.pojo.WorkRequest;

public class WorkRequestDAO extends DAO {

	public WorkRequestDAO() {
	}
	
	public List<WorkRequest> wrListByStaDes(String status, String destination) throws OperateException {
		try {
			begin();
			Query q = getSession().createQuery("from WorkRequest where status = :status and destination = :destination");
			q.setString("status", status);
			q.setString("destination", destination);
			List<WorkRequest> wrList = q.list();
			commit();
			return wrList;
		} catch (HibernateException e) {
			rollback();
			throw new OperateException("Could not get wrdList " + status, e);
		}
	}
	
	public List<WorkRequest> wrListByBiSta(String status1, String status2) throws OperateException {
		try {
			begin();
			Query q = getSession().createQuery("from WorkRequest where status = :status1 or status = :status2");
			q.setString("status1", status1);
			q.setString("status2", status2);
			List<WorkRequest> wrList = q.list();
			commit();
			return wrList;
		} catch (HibernateException e) {
			rollback();
			throw new OperateException("Could not get wrdList " + status1, e);
		}
	}
	
	public List<WorkRequest> wrListByStaDesUser(String status, String destination, User user) throws OperateException {
		try {
			begin();
			Query q = getSession().createQuery("from WorkRequest as wr inner join fetch wr.userSet as user where wr.status = :status and wr.destination = :destination and user.pID = :pid");
			q.setString("status", status);
			q.setString("destination", destination);
			q.setLong("pid", user.getpID());
			List<WorkRequest> wrList = q.list();
			System.out.println(wrList.size());
			commit();
			return wrList;
		} catch (HibernateException e) {
			rollback();
			throw new OperateException("Could not get wrdList " + status, e);
		}
	}
	
	
	public List<WorkReqDonate> wrdList() throws OperateException {
		try {
			begin();
			Query q = getSession().createQuery("from WorkReqDonate");
			List<WorkReqDonate> wrdList = q.list();
			commit();
			return wrdList;
		} catch (HibernateException e) {
			rollback();
			throw new OperateException("Could not get wrdList ", e);
		}
	}
	
	public List<WorkReqDonate> wrdListByMsg(String message) throws OperateException {
		try {
			begin();
			Query q = getSession().createQuery("from WorkReqDonate where message = :message");
			q.setString("message", message);
			List<WorkReqDonate> wrdList = q.list();
			commit();
			return wrdList;
		} catch (HibernateException e) {
			rollback();
			throw new OperateException("Could not get wrdList " + message, e);
		}
	}
	public List<WorkReqDonate> wrdListByDes(String destination) throws OperateException {
		try {
			begin();
			Query q = getSession().createQuery("from WorkReqDonate where destination = :destination");
			q.setString("destination", destination);
			List<WorkReqDonate> wrdList = q.list();
			commit();
			return wrdList;
		} catch (HibernateException e) {
			rollback();
			throw new OperateException("Could not get wrdList " + destination, e);
		}
	}
	public List<WorkReqDonate> wrdListBySta(String status) throws OperateException {
		try {
			begin();
			Query q = getSession().createQuery("from WorkReqDonate where status = :status");
			q.setString("status", status);
			List<WorkReqDonate> wrdList = q.list();
			commit();
			return wrdList;
		} catch (HibernateException e) {
			rollback();
			throw new OperateException("Could not get wrdList " + status, e);
		}
	}
	
	public List<WorkReqDonate> wrdListByStaDes(String status, String destination) throws OperateException {
		try {
			begin();
			Query q = getSession().createQuery("from WorkReqDonate where status = :status and destination = :destination");
			q.setString("status", status);
			q.setString("destination", destination);
			List<WorkReqDonate> wrdList = q.list();
			commit();
			return wrdList;
		} catch (HibernateException e) {
			rollback();
			throw new OperateException("Could not get wrdList " + status, e);
		}
	}
	
	public List<WorkReqDonate> wrdListByStaDesUser(String status, String destination, User user) throws OperateException {
		try {
			begin();
			Query q = getSession().createQuery("from WorkReqDonate as wrd inner join fetch wrd.userSet as user where wrd.status = :status and wrd.destination = :destination and user.pID = :pid");
			q.setString("status", status);
			q.setString("destination", destination);
			q.setLong("pid", user.getpID());
			List<WorkReqDonate> wrdList = q.list();
			commit();
			return wrdList;
		} catch (HibernateException e) {
			rollback();
			throw new OperateException("Could not get wrdList " + status, e);
		}
	}
	
	public List<WorkReqDonate> wrdListByBiStaDesUser(String status1,String status2, String destination, User user) throws OperateException {
		try {
			begin();
			Query q = getSession().createQuery("from WorkReqDonate as wrd inner join fetch wrd.userSet as user where (wrd.status = :status1 or wrd.status = :status2) and wrd.destination = :destination and user.pID = :pid");
			q.setString("status1", status1);
			q.setString("status2", status2);
			q.setString("destination", destination);
			q.setLong("pid", user.getpID());
			List<WorkReqDonate> wrdList = q.list();
			commit();
			return wrdList;
		} catch (HibernateException e) {
			rollback();
			throw new OperateException("Could not get wrdList " + status2 + e.getMessage(), e);
		}
	}
	
	public List<WorkReqUse> wruList() throws OperateException {
		try {
			begin();
			Query q = getSession().createQuery("from WorkReqUse");
			List<WorkReqUse> wruList = q.list();
			commit();
			return wruList;
		} catch (HibernateException e) {
			rollback();
			throw new OperateException("Could not get wruList ", e);
		}
	}
	
	public List<WorkReqUse> wruListByMsg(String message) throws OperateException {
		try {
			begin();
			Query q = getSession().createQuery("from WorkReqUse where message = :message");
			q.setString("message", message);
			List<WorkReqUse> wruList = q.list();
			commit();
			return wruList;
		} catch (HibernateException e) {
			rollback();
			throw new OperateException("Could not get wruList " + message, e);
		}
	}
	
	public List<WorkReqUse> wruListByStaDesUser(String status, String destination, User user) throws OperateException {
		try {
			begin();
			Query q = getSession().createQuery("from WorkReqUse as wrd inner join fetch wrd.userSet as user where wrd.status = :status and wrd.destination = :destination and user.pID = :pid");
			q.setString("status", status);
			q.setString("destination", destination);
			q.setLong("pid", user.getpID());
			List<WorkReqUse> wrdList = q.list();
			commit();
			return wrdList;
		} catch (HibernateException e) {
			rollback();
			throw new OperateException("Could not get wruList " + status, e);
		}
	}
	
	public List<WorkReqUse> wruListByStaTri(String status1, String status2, String status3, User user) throws OperateException {
		try {
			begin();
			//Query q = getSession().createQuery("from WorkReqUse where status = :status1 or status = :status2 or status = :status3");
			//q.setString("status1", status1);
			//q.setString("status2", status2);
			Criteria crit = getSession().createCriteria(WorkReqUse.class);
			Criteria userCrit = crit.createCriteria("userSet");
			userCrit.add(Restrictions.eq("pID",user.getpID()));
			Criterion shortage = Restrictions.ilike("status", status1);
			Criterion delivered = Restrictions.ilike("status", status2);
			Criterion waiting = Restrictions.ilike("status", status3);
			Disjunction disjunction = Restrictions.disjunction();
			disjunction.add(shortage);
			disjunction.add(delivered);
			disjunction.add(waiting);
			crit.add(disjunction);
			List<WorkReqUse> wruList = crit.list();
			commit();
			return wruList;
		} catch (HibernateException e) {
			rollback();
			throw new OperateException("Could not get wruList " + status1+status2+status3, e);
		}
	}
	
	public List<WorkReqDonate> wrdListByStaSi(String status1, User user) throws OperateException {
		try {
			begin();
			Criteria crit = getSession().createCriteria(WorkReqDonate.class);
			Criteria userCrit = crit.createCriteria("userSet");
			userCrit.add(Restrictions.eq("pID",user.getpID()));
			Criterion waiting = Restrictions.ilike("status", status1);
			crit.add(waiting);
			List<WorkReqDonate> wrdList = crit.list();
			commit();
			return wrdList;
		} catch (HibernateException e) {
			rollback();
			throw new OperateException("Could not get wruList " + status1, e);
		}
	}
	
	public List<WorkReqUse> wruListByStaSi(String status1, User user) throws OperateException {
		try {
			begin();
			Criteria crit = getSession().createCriteria(WorkReqUse.class);
			Criteria userCrit = crit.createCriteria("userSet");
			userCrit.add(Restrictions.eq("pID",user.getpID()));
			Criterion waiting = Restrictions.ilike("status", status1);
			crit.add(waiting);
			List<WorkReqUse> wruList = crit.list();
			commit();
			return wruList;
		} catch (HibernateException e) {
			rollback();
			throw new OperateException("Could not get wruList " + status1, e);
		}
	}
	public List<WorkReqUse> wruListByDes(String destination) throws OperateException {
		try {
			begin();
			Query q = getSession().createQuery("from WorkReqUse where destination = :destination");
			q.setString("destination", destination);
			List<WorkReqUse> wruList = q.list();
			commit();
			return wruList;
		} catch (HibernateException e) {
			rollback();
			throw new OperateException("Could not get wruList " + destination, e);
		}
	}
	public List<WorkReqUse> wruListBySta(String status) throws OperateException {
		try {
			begin();
			Query q = getSession().createQuery("from WorkReqUse where status = :status");
			q.setString("status", status);
			List<WorkReqUse> wruList = q.list();
			commit();
			return wruList;
		} catch (HibernateException e) {
			rollback();
			throw new OperateException("Could not get wruList " + status, e);
		}
	}
	public List<WorkReqUse> wruListByStaEes(String status, String destination) throws OperateException {
		try {
			begin();
			Query q = getSession().createQuery("from WorkReqUse where status = :status and destination = :destination");
			q.setString("status", status);
			q.setString("destination", destination);
			List<WorkReqUse> wruList = q.list();
			commit();
			return wruList;
		} catch (HibernateException e) {
			rollback();
			throw new OperateException("Could not get wruList " + status, e);
		}
	}
	public WorkRequest getWr(int wrId) throws OperateException {
		try {
			begin();
			Query q = getSession().createQuery("from WorkRequest where wrId= :wrId");
			q.setInteger("wrId", wrId);		
			WorkRequest wr = (WorkRequest) q.uniqueResult();
			commit();
			return wr;
		} catch (HibernateException e) {
			rollback();
			throw new OperateException("Could not get WorkReqUse " + wrId, e);
		}
	}
	
	public WorkReqUse getWru(int wruId) throws OperateException {
		try {
			begin();
			Query q = getSession().createQuery("from WorkReqUse where wruId= :wruId");
			q.setInteger("wruId", wruId);		
			WorkReqUse wru = (WorkReqUse) q.uniqueResult();
			commit();
			return wru;
		} catch (HibernateException e) {
			rollback();
			throw new OperateException("Could not get WorkReqUse " + wruId, e);
		}
	}
	
	public WorkReqDonate getWrd(int wrdId) throws OperateException {
		try {
			begin();
			Query q = getSession().createQuery("from WorkReqDonate where wrdId= :wrdId");
			q.setInteger("wrdId", wrdId);		
			WorkReqDonate wrd = (WorkReqDonate) q.uniqueResult();
			commit();
			return wrd;
		} catch (HibernateException e) {
			rollback();
			throw new OperateException("Could not get WorkReqDonate " + wrdId, e);
		}
	}

	public WorkReqDonate createWrd(WorkReqDonate wrd)
			throws OperateException {
		try {
			begin();
			System.out.println("inside DAO");
 			
			getSession().save(wrd);
			commit();
			return wrd;

		} catch (HibernateException e) {
			rollback();
			throw new OperateException("Exception while creating WorkReqDonate: " + e.getMessage());
		}
	}
	
	public WorkReqUse createWru(WorkReqUse wru)
			throws OperateException {
		try {
			begin();
			System.out.println("inside DAO");
 			
			getSession().save(wru);
			commit();
			return wru;

		} catch (HibernateException e) {
			rollback();
			throw new OperateException("Exception while creating WorkReqUse: " + e.getMessage());
		}
	}
	
	public WorkRequest updateWr(WorkRequest wr)
			throws OperateException {
		try {
			begin();
			System.out.println("inside DAO");
 			
			getSession().merge(wr);
			commit();
			return wr;

		} catch (HibernateException e) {
			rollback();
			throw new OperateException("Exception while updating WorkRequest: " + e.getMessage());
		}
	}
	
	public WorkReqDonate updateWrd(WorkReqDonate wrd)
			throws OperateException {
		try {
			begin();
			System.out.println("inside DAO");
 			
			getSession().merge(wrd);
			commit();
			return wrd;

		} catch (HibernateException e) {
			rollback();
			throw new OperateException("Exception while updating WorkReqDonate: " + e.getMessage());
		}
	}
	
	public WorkReqUse updateWru(WorkReqUse wru)
			throws OperateException {
		try {
			begin();
			System.out.println("inside DAO");
 			
			getSession().merge(wru);
			commit();
			return wru;

		} catch (HibernateException e) {
			rollback();
			throw new OperateException("Exception while updating WorkReqUse: " + e.getMessage());
		}
	}
}