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
import com.globant.glow.core.domain.staffing.StaffingView;


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
	 * Get the column list for the globers menu
	 * @param columnFor- column type
	 * @param isActive- Column status
	 * @return columnList json
	 */
	@Override
	public List<StaffingColumn> getStaffingColumnList(String columnFor, boolean isActive) throws Exception {
		LOGGER.info("Inside getStaffingColumnList method of GlobersDaoImpl");
		String hql = "select sc from StaffingColumn sc where sc.columnFor=:columnFor and sc.isActive=:isActive";
		Query query = getSession().createQuery(hql);
		query.setString("columnFor", columnFor);
		query.setBoolean("isActive", isActive);
		List<StaffingColumn> staffingColumnList = query.list();
		LOGGER.info("Exit from getStaffingColumnList method of GlobersDaoImpl");
		return staffingColumnList;
	}

	@Override
	public List<StaffingView> getStaffingViewList(long userId, String viewFor, boolean isActive) throws Exception {
		LOGGER.info("Inside getStaffingViewList method of GlobersDaoImpl");
		String hql = "select sv from StaffingView sv where sv.viewFor=:viewFor and sc.isActive=:isActive";
		Query query = getSession().createQuery(hql);
		query.setLong("userId", userId);
		query.setString("viewFor", viewFor);
		query.setBoolean("isActive", isActive);
		List<StaffingView> staffingViewList = query.list();
		LOGGER.info("Exit from getStaffingViewList method of GlobersDaoImpl");
		return staffingViewList;
	}

}
