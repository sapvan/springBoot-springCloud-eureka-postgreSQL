package com.globant.glow.staffing.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.globant.glow.core.domain.Glober;
import com.globant.glow.core.domain.staffing.StaffingColumn;
import com.globant.glow.core.domain.staffing.StaffingUserDefaultView;
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

	/**
	 * Get the default view for the user
	 * @param userId- User primary key
	 * @param defaultViewFor- Default View for
	 * @param isActive- Active status
	 * @return defaultView
	 */
	@Override
	public StaffingView getDefaultViewByUserId(long userId,String defaultViewFor,boolean isActive) throws Exception {
		LOGGER.info("Inside getdefaultViewByUserId method of GlobersDaoImpl");
		String hql = "select sudv.staffingView from StaffingUserDefaultView sudv where sudv.defaultViewFor=:defaultViewFor and sudv.isActive=:isActive and "
				+ "sudv.user.id=:userId";
		Query query = getSession().createQuery(hql);
		query.setLong("userId", userId);
		query.setString("defaultViewFor", defaultViewFor);
		query.setBoolean("isActive", isActive);
		StaffingView defaultView = (StaffingView) query.uniqueResult();
		LOGGER.info("Exit from getdefaultViewByUserId method of GlobersDaoImpl");
		return defaultView;
	}


	/**
	 * Get the default view for the user
	 * @param userId- User primary key
	 * @param defaultViewFor- Default View for
	 * @param isActive- Active status
	 * @return defaultView
	 */
	@Override
	public StaffingUserDefaultView getStaffingUserDefaultViewByUserId(long userId,String defaultViewFor,boolean isActive) throws Exception {
		LOGGER.info("Inside getStaffingUserDefaultViewByUserId method of GlobersDaoImpl");
		String hql = "select sudv from StaffingUserDefaultView sudv where sudv.defaultViewFor=:defaultViewFor and sudv.isActive=:isActive and "
				+ "sudv.user.id=:userId";
		Query query = getSession().createQuery(hql);
		query.setLong("userId", userId);
		query.setString("defaultViewFor", defaultViewFor);
		query.setBoolean("isActive", isActive);
		StaffingUserDefaultView staffingUserDefaultView = (StaffingUserDefaultView) query.uniqueResult();
		LOGGER.info("Exit from getStaffingUserDefaultViewByUserId method of GlobersDaoImpl");
		return staffingUserDefaultView;
	}

	/**
	 * Get the staffing views list with system and custom views for user
	 * @param userId- User primary key
	 * @param viewFor- View for
	 * @param isActive- Active status
	 * @return staffingViewList
	 */
	@Override
	public List<StaffingView> getStaffingViewList(long userId, String viewFor, boolean isActive) throws Exception {
		LOGGER.info("Inside getStaffingViewList method of GlobersDaoImpl");
		String hql = "select sv from StaffingView sv where sv.viewFor=:viewFor and sv.isActive=:isActive and "
				+ "(sv.user.id=:userId or sv.user.id is null)";
		Query query = getSession().createQuery(hql);
		query.setLong("userId", userId);
		query.setString("viewFor", viewFor);
		query.setBoolean("isActive", isActive);
		List<StaffingView> staffingViewList = query.list();
		LOGGER.info("Exit from getStaffingViewList method of GlobersDaoImpl");
		return staffingViewList;
	}


	/**
	 * Get the globers list for GlobalTpView who are in TP or release in 21 days
	 * @param viewId- View Id
	 * @return globerList
	 */
	@Override
	public List<Object[]> getGlobersListForGlobalTpView(long viewId,int offset,int limit) throws Exception {
		LOGGER.info("Inside getGlobersListForGlobalTpView method of GlobersDaoImpl");
		String hql = "SELECT g.id, g.username, g.workEmail, g.firstName, g.lastName,si.name, "
				+ "cd.position, cd.seniority, st.name,a.internalAssignmentType,a.startingDate,a.endDate,a.percentage,a.project "
				+ "from Glober g, ContractInformation ci, ContractData cd,Site si, Studio st,Assignment a "
				+ "where ci.id = g.contractInformation.id and ci.lastDate IS NULL "
				+ "and cd.contracInformation.id = g.contractInformation.id and cd.endDate IS NULL "
				+ "and si.id = cd.site.id "
				+ "and a.glober.id=g.id "
				+ "and st.id = g.studio.id "
				+ "and ((a.endDate is null and a.startingDate<=:todayDate and a.internalAssignmentType=:benchProject) "
				+ "or (a.endDate>:todayDate and a.endDate<=:added21DaysByTodayDate)) "
				+ " order by g.username";

		/*String hql = "select g.id,g.username,g.workEmail,g.firstName,g.lastName,si.name,"
					+"cd.position,cd.seniority,st.name,a.internalAssignmentType,a.startingDate,"
					+"a.percentage,ml.mentor.mentor.username,ml.leader.leader.username "
					+"FROM Glober g "
					+"Join g.contractInformation ci "
					+"Join ci.contractsData cd "
					+"Join cd.site si "
					+"Join g.assignments a "
					+"Join g.studio st "
					+"Left Join g.mentorLeaders ml "
					+"WHERE ci.lastDate IS NULL "
					+"AND cd.endDate IS NULL "
					+"and ((a.endDate is null and a.startingDate<=:todayDate) "
					+"or (a.endDate is null and a.startingDate<=:added21DaysByTodayDate and a.startingDate>:todayDate)) "
					+"and a.internalAssignmentType=:benchProject order by g.username";*/

		/*Criteria criteria = getSession().createCriteria(Glober.class, "glober");
		criteria.createAlias("glober.contractInformation", "ci");
		criteria.createAlias("ContractData.contracInformation", "cd");
		criteria.createAlias("Site", "si");
		criteria.createAlias("Assignment.glober", "a");
		criteria.createAlias("Studio", "st");
		criteria.createAlias("MentorLeader.glober", "ml");

		criteria.setFetchMode("ci", FetchMode.JOIN);
		criteria.setFetchMode("cd", FetchMode.JOIN);
		criteria.setFetchMode("si", FetchMode.JOIN);
		criteria.setFetchMode("a", FetchMode.JOIN);
		criteria.setFetchMode("st", FetchMode.JOIN);
		criteria.setFetchMode("ml", FetchMode.JOIN);*/

		/*String sql = "SELECT g.id,g.username,g.work_email,g.first_name,g.last_name,si.name,"
				+" cd.position,cd.seniority,a.internal_assignment_type,a.starting_date,a.percentage,ml.mentor_fk"
				+" FROM globers g"
				+" JOIN contracts_information ci ON ci.id = g.contract_information_fk"
				+" JOIN contracts_data cd ON cd.contract_information_fk = g.contract_information_fk"
				+" JOIN sites si ON si.id = cd.site_fk"
				+" JOIN assignments a on  a.resume_fk = g.id"
				+" Join studios stud on stud.id = g.studio_fk"
				+" Left Join mentor_leader ml on ml.glober_fk = g.id"
				+" WHERE ci.last_date IS NULL"
				+" AND cd.end_date IS NULL"
				+" and ((a.end_date is null and a.starting_date<=:todayDate)"
				   +" or (a.end_date is null and a.starting_date<=:added21DaysByTodayDate and a.starting_date>:todayDate))"
				   +" and a.internal_assignment_type=:benchProject order by g.username";*/

		Query query = getSession().createQuery(hql);
		query.setFirstResult(offset);
		query.setMaxResults(limit);

		Date todayDate = new Date();
		Date added21DaysByTodayDate = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(added21DaysByTodayDate);
		c.add(Calendar.DATE, 21);
		added21DaysByTodayDate = c.getTime();

		query.setDate("todayDate", todayDate);
		query.setDate("added21DaysByTodayDate", added21DaysByTodayDate);
		query.setString("benchProject", "BENCH");
		List<Object[]> globerList = query.list();
		LOGGER.info("Exit from getGlobersListForGlobalTpView method of GlobersDaoImpl");
		return globerList;
	}

	/**
	 * Get the globers list for GlobalTpView who are all billable globers
	 * @param viewId- View Id
	 * @return globerList
	 */
	@Override
	public List<Object[]> getGlobersListForAllGlobersView(long viewId,int offset,int limit) throws Exception {
		LOGGER.info("Inside getGlobersListForGlobalTpView method of GlobersDaoImpl");
		String hql = "SELECT g.id, g.username, g.workEmail, g.firstName, g.lastName,si.name, "
				+ "cd.position, cd.seniority, st.name "
				+ "from Glober g, ContractInformation ci, ContractData cd,Site si, Studio st "
				+ "where ci.id = g.contractInformation.id and ci.lastDate IS NULL "
				+ "and cd.contracInformation.id = g.contractInformation.id and cd.endDate IS NULL "
				+ "and si.id = cd.site.id "
				+ "and st.id = g.studio.id "
				+ "and g.billable=:billableTrue";

		Query query = getSession().createQuery(hql);
		query.setFirstResult(offset);
		query.setMaxResults(limit);

		query.setBoolean("billableTrue", true);

		List<Object[]> globerList = query.list();
		LOGGER.info("Exit from getGlobersListForGlobalTpView method of GlobersDaoImpl");
		return globerList;
	}


	/**
	 * Get the globers list for MyTpView
	 * @param viewId- View Id
	 * @return globerList
	 */
	@Override
	public List<Object[]> getGlobersListForMyTpView(long viewId,int offset,int limit) throws Exception {
		LOGGER.info("Inside getGlobersListForGlobalTpView method of GlobersDaoImpl");
		String hql = "SELECT g.id, g.username, g.workEmail, g.firstName, g.lastName,si.name, "
				+ "cd.position, cd.seniority, st.name "
				+ "from Glober g, ContractInformation ci, ContractData cd, Site si, Studio st "
				+ "where ci.id = g.contractInformation.id and ci.lastDate IS NULL and "
				+ "cd.contracInformation.id = g.contractInformation.id and cd.endDate IS NULL and "
				+ "si.id = cd.site.id and "
				+ "st.id = g.studio.id ";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(offset);
		query.setMaxResults(limit);

		List<Object[]> globerList = query.list();
		LOGGER.info("Exit from getGlobersListForGlobalTpView method of GlobersDaoImpl");
		return globerList;
	}

	/**
	 * Get the globers list for FollowingView who are followed
	 * @param viewId- View Id
	 * @return globerList
	 */
	@Override
	public List<Object[]> getGlobersListForFollowingView(long viewId,int offset,int limit) throws Exception {
		LOGGER.info("Inside getGlobersListForGlobalTpView method of GlobersDaoImpl");
		String hql = "SELECT g.id, g.username, g.workEmail, g.firstName, g.lastName,si.name, "
				+ "cd.position, cd.seniority, st.name "
				+ "from Glober g, ContractInformation ci, ContractData cd, Site si, Studio st "
				+ "where ci.id = g.contractInformation.id and ci.lastDate IS NULL and "
				+ "cd.contracInformation.id = g.contractInformation.id and cd.endDate IS NULL and "
				+ "si.id = cd.site.id and "
				+ "st.id = g.studio.id ";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(offset);
		query.setMaxResults(limit);

		List<Object[]> globerList = query.list();
		LOGGER.info("Exit from getGlobersListForGlobalTpView method of GlobersDaoImpl");
		return globerList;
	}

	/**
	 * Get the globers list for UnassignedView who are new joines
	 * @param viewId- View Id
	 * @return globerList
	 */
	@Override
	public List<Object[]> getGlobersListForUnassignedView(long viewId,int offset,int limit) throws Exception {
		LOGGER.info("Inside getGlobersListForGlobalTpView method of GlobersDaoImpl");
		String hql = "SELECT g.id, g.username, g.workEmail, g.firstName, g.lastName,si.name, "
				+ "cd.position, cd.seniority, st.name "
				+ "from Glober g, ContractInformation ci, ContractData cd, Site si, Studio st "
				+ "where ci.id = g.contractInformation.id and ci.lastDate IS NULL and "
				+ "cd.contracInformation.id = g.contractInformation.id and cd.endDate IS NULL and "
				+ "si.id = cd.site.id and "
				+ "st.id = g.studio.id ";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(offset);
		query.setMaxResults(limit);

		List<Object[]> globerList = query.list();
		LOGGER.info("Exit from getGlobersListForGlobalTpView method of GlobersDaoImpl");
		return globerList;
	}


	/**
	 * Add new staffing view for the user
	 * @param customView
	 * @return customView
	 */
	@Override
	public StaffingView addNewCustomStaffingView(StaffingView customView) throws Exception {
		getSession().save(customView);
		return customView;
	}

	/**
	 * Add new StaffingUserDefaultView
	 * @param staffingUserDefaultView
	 * @return staffingUserDefaultView
	 */
	@Override
	public StaffingUserDefaultView addNewStaffingUserDefaultView(StaffingUserDefaultView staffingUserDefaultView) throws Exception {
		getSession().save(staffingUserDefaultView);
		return staffingUserDefaultView;
	}

	/**
	 * Update staffingUserDefaultView
	 * @param staffingUserDefaultView
	 * @return staffingUserDefaultView
	 */
	@Override
	public StaffingUserDefaultView updateStaffingUserDefaultView(StaffingUserDefaultView staffingUserDefaultView) throws Exception {
		getSession().update(staffingUserDefaultView);
		return staffingUserDefaultView;
	}


}
