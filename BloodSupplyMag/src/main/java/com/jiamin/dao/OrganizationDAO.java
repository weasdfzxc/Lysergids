package com.jiamin.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.jiamin.exception.OperateException;
import com.jiamin.pojo.Blood;
import com.jiamin.pojo.BloodBank;
import com.jiamin.pojo.BloodManageCenter;
import com.jiamin.pojo.Clinic;
import com.jiamin.pojo.Organization;
import com.jiamin.tools.BloodBankInventory;
import com.jiamin.tools.BloodBankStatus;

public class OrganizationDAO extends DAO {

	public OrganizationDAO() {
	}
	
	public List<BloodBankInventory> bbiList(Set<BloodBank> bankList) {
		List<BloodBankInventory> bbiList = new ArrayList<BloodBankInventory>();
		int atypea = 0;
		int atypeb = 0;
		int atypeab = 0;
		int atypeo = 0;
		BloodBankInventory abbi = new BloodBankInventory();
		bbiList.add(abbi);
		for (BloodBank bank : bankList) {
			int typea = 0;
			int typeb = 0;
			int typeab = 0;
			int typeo = 0;
			for (Blood blood : bank.getBloodList()) {
				if (blood.getBloodType().equals("A")) {
					typea += blood.getVolum();
					atypea += blood.getVolum();
				}else if (blood.getBloodType().equals("B")) {
					typeb += blood.getVolum();
					atypeb += blood.getVolum();
				}else if (blood.getBloodType().equals("AB")) {
					typeab += blood.getVolum();
					atypeab += blood.getVolum();
				}else if (blood.getBloodType().equals("O")) {
					typeo += blood.getVolum();
					atypeo += blood.getVolum();
				}
			}
			BloodBankInventory bbi = new BloodBankInventory();
			bbi.setBbID(bank.getOid());
			bbi.setBbName(bank.getOrganName());
			bbi.setTypea(typea);
			bbi.setTypeab(typeab);
			bbi.setTypeb(typeb);
			bbi.setTypeo(typeo);
			bbi.initratio();
			bbiList.add(bbi);
		}
		abbi.setBbID(0);
		abbi.setBbName("The whole area");
		abbi.setTypea(atypea);
		abbi.setTypeab(atypeab);
		abbi.setTypeb(atypeb);
		abbi.setTypeo(atypeo);
		abbi.initratio();
		return bbiList;
	}
	
	public List<BloodBankStatus> bbsList(Set<BloodBank> bankList, String bType) {
		List<BloodBankStatus> bbsList = new ArrayList<BloodBankStatus>();
		for (BloodBank bank : bankList) {
			int amount = 0;
			for (Blood blood : bank.getBloodList()) {
				if (blood.getBloodType().equals(bType)) {
					amount += blood.getVolum();
				}
			}
			BloodBankStatus bbs = new BloodBankStatus();
			bbs.setBbID(bank.getOid());
			bbs.setBbName(bank.getOrganName());
			bbs.setBloodType(bType);
			bbs.setQuantity(amount);
			bbsList.add(bbs);
		}
		return bbsList;
	}

	public List<BloodBankStatus> bbsList(Set<BloodBank> bankList, String bType, int quntities) {
		List<BloodBankStatus> bbsList = new ArrayList<BloodBankStatus>();
		for (BloodBank bank : bankList) {
			int amount = 0;
			for (Blood blood : bank.getBloodList()) {
				if (blood.getBloodType().equals(bType)) {
					amount += blood.getVolum();
				}
			}
			if (amount >= quntities) {
				BloodBankStatus bbs = new BloodBankStatus();
				bbs.setBbID(bank.getOid());
				bbs.setBbName(bank.getOrganName());
				bbs.setBloodType(bType);
				bbs.setQuantity(amount);
				bbsList.add(bbs);
			}
		}
		return bbsList;
	}

	public List<Organization> organList(String type) throws OperateException {
		try {
			begin();
			Query q = getSession().createQuery("from Organization where organType = :type");
			q.setString("type", type);
			List<Organization> organList = q.list();
			commit();
			return organList;
		} catch (HibernateException e) {
			rollback();
			throw new OperateException("Could not get organList " + type, e);
		}
	}
	
	public List<Organization> organList() throws OperateException {
		try {
			begin();
			Query q = getSession().createQuery("from Organization");
			List<Organization> organList = q.list();
			commit();
			return organList;
		} catch (HibernateException e) {
			rollback();
			throw new OperateException("Could not get organList", e);
		}
	}
	
	public Organization getOrgan(String ogranname) throws OperateException {
		try {
			begin();
			Query q = getSession().createQuery("from Organization where organName = :name");
			q.setString("name", ogranname);		
			Organization organization = (Organization) q.uniqueResult();
			commit();
			return organization;
		} catch (HibernateException e) {
			rollback();
			throw new OperateException("Could not get Organization " + ogranname, e);
		}
	}
	
	public Organization getOrgan(int organId) throws OperateException {
		try {
			begin();
			Query q = getSession().createQuery("from Organization where oid= :organId");
			q.setInteger("organId", organId);		
			Organization organization = (Organization) q.uniqueResult();
			commit();
			return organization;
		} catch (HibernateException e) {
			rollback();
			throw new OperateException("Could not get Organization " + organId, e);
		}
	}
	
	public Clinic getClinic(String ogranname) throws OperateException {
		try {
			begin();
			Query q = getSession().createQuery("from Clinic where organName = :name");
			q.setString("name", ogranname);		
			Clinic organization = (Clinic) q.uniqueResult();
			commit();
			return organization;
		} catch (HibernateException e) {
			rollback();
			throw new OperateException("Could not get Clinic " + ogranname, e);
		}
	}
	
	public Clinic getClinic(int organId) throws OperateException {
		try {
			begin();
			Query q = getSession().createQuery("from Clinic where oid= :organId");
			q.setInteger("organId", organId);		
			Clinic organization = (Clinic) q.uniqueResult();
			commit();
			return organization;
		} catch (HibernateException e) {
			rollback();
			throw new OperateException("Could not get Clinic " + organId, e);
		}
	}
	public BloodBank getBloodBank(String ogranname) throws OperateException {
		try {
			begin();
			Query q = getSession().createQuery("from BloodBank where organName = :name");
			q.setString("name", ogranname);		
			BloodBank organization = (BloodBank) q.uniqueResult();
			commit();
			return organization;
		} catch (HibernateException e) {
			rollback();
			throw new OperateException("Could not get BloodBank " + ogranname, e);
		}
	}
	
	public BloodBank getBloodBank(int organId) throws OperateException {
		try {
			begin();
			Query q = getSession().createQuery("from BloodBank where oid= :organId");
			q.setInteger("organId", organId);		
			BloodBank organization = (BloodBank) q.uniqueResult();
			commit();
			return organization;
		} catch (HibernateException e) {
			rollback();
			throw new OperateException("Could not get BloodBank " + organId, e);
		}
	}
	
	public BloodManageCenter getBMC(String ogranname) throws OperateException {
		try {
			begin();
			Query q = getSession().createQuery("from BloodManageCenter where organName = :name");
			q.setString("name", ogranname);		
			BloodManageCenter organization = (BloodManageCenter) q.uniqueResult();
			commit();
			return organization;
		} catch (HibernateException e) {
			rollback();
			throw new OperateException("Could not get BloodManageCenter " + ogranname, e);
		}
	}
	
	public BloodManageCenter getBMC(int organId) throws OperateException {
		try {
			begin();
			Query q = getSession().createQuery("from BloodManageCenter where oid= :organId");
			q.setInteger("organId", organId);		
			BloodManageCenter organization = (BloodManageCenter) q.uniqueResult();
			commit();
			return organization;
		} catch (HibernateException e) {
			rollback();
			throw new OperateException("Could not get BloodManageCenter " + organId, e);
		}
	}

	public Organization createOrganization(Organization o)
			throws OperateException {
		try {
			begin();

			Organization organ = new Organization();
			organ.setOrganName(o.getOrganName());
			organ.setOrganType(o.getOrganType());
			
			getSession().save(organ);
			commit();
			return organ;

		} catch (HibernateException e) {
			rollback();
			throw new OperateException("Exception while creating Organization: " + e.getMessage());
		}
	}
	
	public Clinic createClinic(Clinic o)
			throws OperateException {
		try {
			begin();

			Clinic organ = new Clinic();
			BloodBank bb = o.getBloodBank();
			organ.setBloodBank(bb);
			organ.setOrganName(o.getOrganName());
			organ.setOrganType(o.getOrganType());
			//bb.getClinicSet().add(organ);
			
			getSession().save(organ);
			commit();
			return organ;

		} catch (HibernateException e) {
			rollback();
			throw new OperateException("Exception while creating Clinic: " + e.getMessage());
		}
	}
	
	public BloodBank createBloodBank(BloodBank o)
			throws OperateException {
		try {
			begin();

			BloodBank organ = new BloodBank();
			BloodManageCenter bmc = o.getBmc();
			organ.setBmc(bmc);
			organ.setOrganName(o.getOrganName());
			organ.setOrganType(o.getOrganType());
			//bmc.getBloodBankSet().add(organ);
			
			getSession().save(organ);
			commit();
			return organ;

		} catch (HibernateException e) {
			rollback();
			throw new OperateException("Exception while creating BloodBank: " + e.getMessage());
		}
	}
	
	public BloodManageCenter createBMC(BloodManageCenter o)
			throws OperateException {
		try {
			begin();

			BloodManageCenter organ = new BloodManageCenter();
			organ.setOrganName(o.getOrganName());
			organ.setOrganType(o.getOrganType());
			
			getSession().save(organ);
			commit();
			return organ;

		} catch (HibernateException e) {
			rollback();
			throw new OperateException("Exception while creating BloodManageCenter: " + e.getMessage());
		}
	}

	public void delete(Organization organ) throws OperateException {
		try {
			begin();
			getSession().delete(organ);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new OperateException("Could not delete Organization " + organ.getOrganName(), e);
		}
	}
}