package com.globant.glow.staffing.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.globant.glow.core.domain.staffing.StaffingColumn;


@Repository
@Transactional
public class GlobersDaoImpl implements GlobersDao{

	private static final Logger LOGGER = LoggerFactory.getLogger(GlobersDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() throws Exception {
	    return sessionFactory.getCurrentSession();
	}

	/**
	 * Get the user information along with its default role details
	 * @param userName
	 * @return userInfo object
	 */
	@Override
	public List<StaffingColumn> getStaffingColumnList(String columnFor, boolean isActive) throws Exception {
		LOGGER.info("Inside getUserInfoWithDefaultRole method of UsersDaoImpl");
		String hql = "select sc from StaffingColumn sc where sc.columnFor=:columnFor and sc.isActive=:isActive";
		Query query = getSession().createQuery(hql);
		query.setString("columnFor", columnFor);
		query.setBoolean("isActive", isActive);
		List<StaffingColumn> staffingColumnList = query.list();
		LOGGER.info("Exit from getUserInfoWithDefaultRole method of UsersDaoImpl");
		return staffingColumnList;
	}

}
