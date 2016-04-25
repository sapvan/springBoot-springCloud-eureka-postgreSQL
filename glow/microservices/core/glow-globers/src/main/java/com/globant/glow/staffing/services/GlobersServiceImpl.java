package com.globant.glow.staffing.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globant.glow.core.domain.Glober;
import com.globant.glow.core.domain.InternalAssignmentType;
import com.globant.glow.core.domain.staffing.StaffingColumn;
import com.globant.glow.core.domain.staffing.StaffingUserDefaultView;
import com.globant.glow.core.domain.staffing.StaffingView;
import com.globant.glow.staffing.dao.GlobersDao;



@Service
public class GlobersServiceImpl implements GlobersService {

	private static final Logger LOGGER = LoggerFactory.getLogger(GlobersServiceImpl.class);

	@Autowired
	GlobersDao globersDao;

	/**
	 * Get the column list for the globers menu
	 * @param columnFor- column type
	 * @param isActive- Column status
	 * @return columnList json
	 */
	@Override
	public List<StaffingColumn> getStaffingColumnList(String columnFor, boolean isActive) throws Exception {
		LOGGER.info("Inside getStaffingColumnList method of GlobersServiceImpl");
		List<StaffingColumn> staffingColumnList = globersDao.getStaffingColumnList(columnFor,isActive);
		LOGGER.info("Exit from getStaffingColumnList method of GlobersServiceImpl");
		return staffingColumnList;
	}

	/**
	 * Get the staffing view list for the user id
	 * @param userId- User primary key
	 * @param viewFor- view type
	 * @param isActive- View status
	 * @return viewList and default view json
	 */
	@Override
	public String getStaffingViewList(long userId, String viewFor, boolean isActive) throws Exception {
		LOGGER.info("Inside getStaffingViewList method of GlobersServiceImpl");
		JSONObject viewObj = new JSONObject();

		StaffingView masterDefaultView = null;
		JSONArray staffingViewArr = new JSONArray();
		List<StaffingView> staffingViewList = globersDao.getStaffingViewList(userId, viewFor, isActive);
		if(staffingViewList!=null) {
			for(StaffingView staffingView: staffingViewList) {
				long id = staffingView.getId();
				String name = staffingView.getName();
				Glober user = staffingView.getUser();
				String columns = staffingView.getColumns();
				String filterCriteria = staffingView.getFilterCriteria();
				boolean isMasterDefault = staffingView.isMasterDefault();
				long sharedViewId = staffingView.getShareViewId();
				Date createdDate = staffingView.getCreatedDate();
				Date updatedDate = staffingView.getUpdatedDate();
				viewFor = staffingView.getViewFor();
				isActive = staffingView.isActive();

				JSONObject staffingViewObj = new JSONObject();
				staffingViewObj.put("id", id);
				staffingViewObj.put("name", name);
				if(columns!=null && columns.trim().isEmpty()==false) {
					JSONArray columnsArr = new JSONArray(columns);
					staffingViewObj.put("columns", columnsArr);
				}
				else {
					staffingViewObj.put("columns", "");
				}

				if(filterCriteria!=null) {
					staffingViewObj.put("filterCriteria", filterCriteria);
				}
				else {
					staffingViewObj.put("filterCriteria", "");
				}

				if(user!=null) {
					staffingViewObj.put("customView", true);
				}
				else {
					staffingViewObj.put("customView", false);
				}
				if(sharedViewId!=0) {
					staffingViewObj.put("sharedView", true);
				}
				else {
					staffingViewObj.put("sharedView", false);
				}
				staffingViewObj.put("createdDate", createdDate);
				staffingViewObj.put("updatedDate", updatedDate);
				staffingViewObj.put("viewFor", viewFor);
				staffingViewObj.put("isActive", isActive);

				staffingViewArr.put(staffingViewObj);

				//HardCoded master default view
				if(isMasterDefault==true) {
					masterDefaultView = staffingView;
				}
			}
			viewObj.put("viewsList", staffingViewArr);

			StaffingView defaultView = globersDao.getDefaultViewByUserId(userId, viewFor, isActive);
			if(defaultView==null && masterDefaultView!=null) {
				defaultView = masterDefaultView;
			}

			long id = defaultView.getId();
			String name = defaultView.getName();
			Glober user = defaultView.getUser();
			String columns = defaultView.getColumns();
			String filterCriteria = defaultView.getFilterCriteria();
			boolean isMasterDefault = defaultView.isMasterDefault();
			long sharedViewId = defaultView.getShareViewId();
			Date createdDate = defaultView.getCreatedDate();
			Date updatedDate = defaultView.getUpdatedDate();
			viewFor = defaultView.getViewFor();
			isActive = defaultView.isActive();

			JSONObject defaultViewObj = new JSONObject();
			defaultViewObj.put("id", id);
			defaultViewObj.put("name", name);
			if(columns!=null && columns.trim().isEmpty()==false) {
				JSONArray columnsArr = new JSONArray(columns);
				defaultViewObj.put("columns", columnsArr);
			}
			else {
				defaultViewObj.put("columns", "");
			}

			if(filterCriteria!=null) {
				defaultViewObj.put("filterCriteria", filterCriteria);
			}
			else {
				defaultViewObj.put("filterCriteria", "");
			}

			if(user!=null) {
				defaultViewObj.put("customView", true);
			}
			else {
				defaultViewObj.put("customView", false);
			}
			if(sharedViewId!=0) {
				defaultViewObj.put("sharedView", true);
			}
			else {
				defaultViewObj.put("sharedView", false);
			}
			defaultViewObj.put("createdDate", createdDate);
			defaultViewObj.put("updatedDate", updatedDate);
			defaultViewObj.put("viewFor", viewFor);
			defaultViewObj.put("isActive", isActive);

			viewObj.put("defaultView", defaultViewObj);
		}
		LOGGER.info("Exit from getStaffingViewList method of GlobersServiceImpl");
		return viewObj.toString();
	}

	/**
	 * Get the globers list for the selected view id
	 * @param viewId- View primary key
	 * @return globersList json
	 */
	@Override
	public String getGlobersListForView(long viewId) throws Exception {
		LOGGER.info("Inside getGlobersListForView method of GlobersServiceImpl");
		JSONArray globerArr = new JSONArray();
		if(viewId!=0) {
			List<Object[]> globerList = null;
			if(viewId==1) {			//My TP
				globerList = globersDao.getGlobersListForMyTpView(viewId);

				if(globerList!=null) {
					for(Object[] glober: globerList) {
						long id = (Long) glober[0];
						String userName = (String) glober[1];
						String workEmail = (String) glober[2];
						String firstName = (String) glober[3];
						String lastName = (String) glober[4];
						String location = (String) glober[5];
						String position = (String) glober[6];
						String seniority = (String) glober[7];
						String studio = (String) glober[8];

						String benchStartDateStr = "1-3-2016";
						SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

						JSONObject globerObj = new JSONObject();
						globerObj.put("id", id);
						globerObj.put("name", firstName+" "+lastName);
						globerObj.put("email", workEmail);
						globerObj.put("position", position);
						globerObj.put("seniority", seniority);
						globerObj.put("skills", "");
						globerObj.put("availablity", "100");
						globerObj.put("benchStartDate", benchStartDateStr);
						globerObj.put("studio", studio);
						globerObj.put("location", location);
						globerObj.put("leader", "");
						globerObj.put("handler", "");
						globerObj.put("status", "");
						globerArr.put(globerObj);
					}
				}
			}
			else if(viewId==2) {	//Global TP
				globerList = globersDao.getGlobersListForGlobalTpView(viewId);

				if(globerList!=null) {
					for(Object[] glober: globerList) {
						long id = (Long) glober[0];
						String userName = (String) glober[1];
						String workEmail = (String) glober[2];
						String firstName = (String) glober[3];
						String lastName = (String) glober[4];
						String location = (String) glober[5];
						String position = (String) glober[6];
						String seniority = (String) glober[7];
						String studio = (String) glober[8];
						InternalAssignmentType project = (InternalAssignmentType) glober[9];
						Date benchStartDate = (Date) glober[10];
						int availablity = (int) glober[11];

						String benchStartDateStr = "";
						SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
						if(benchStartDate!=null) {
							benchStartDateStr = formatter.format(benchStartDate);
						}

						JSONObject globerObj = new JSONObject();
						globerObj.put("id", id);
						globerObj.put("name", firstName+" "+lastName);
						globerObj.put("email", workEmail);
						globerObj.put("position", position);
						globerObj.put("seniority", seniority);
						globerObj.put("skills", "");
						globerObj.put("availablity", availablity);
						globerObj.put("benchStartDate", benchStartDateStr);
						globerObj.put("studio", studio);
						globerObj.put("location", location);
						globerObj.put("leader", "");
						globerObj.put("handler", "");
						globerObj.put("status", "");
						globerArr.put(globerObj);
					}
				}
			}
			else if(viewId==3) {	//Following TP
				globerList = globersDao.getGlobersListForFollowingView(viewId);

				if(globerList!=null) {
					for(Object[] glober: globerList) {
						long id = (Long) glober[0];
						String userName = (String) glober[1];
						String workEmail = (String) glober[2];
						String firstName = (String) glober[3];
						String lastName = (String) glober[4];
						String location = (String) glober[5];
						String position = (String) glober[6];
						String seniority = (String) glober[7];
						String studio = (String) glober[8];

						String benchStartDateStr = "1-3-2016";
						SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

						JSONObject globerObj = new JSONObject();
						globerObj.put("id", id);
						globerObj.put("name", firstName+" "+lastName);
						globerObj.put("email", workEmail);
						globerObj.put("position", position);
						globerObj.put("seniority", seniority);
						globerObj.put("skills", "");
						globerObj.put("availablity", "100");
						globerObj.put("benchStartDate", benchStartDateStr);
						globerObj.put("studio", studio);
						globerObj.put("location", location);
						globerObj.put("leader", "");
						globerObj.put("handler", "");
						globerObj.put("status", "");
						globerArr.put(globerObj);
					}
				}
			}
			else if(viewId==4) {	//Un-assigned BU/Studio
				globerList = globersDao.getGlobersListForUnassignedView(viewId);

				if(globerList!=null) {
					for(Object[] glober: globerList) {
						long id = (Long) glober[0];
						String userName = (String) glober[1];
						String workEmail = (String) glober[2];
						String firstName = (String) glober[3];
						String lastName = (String) glober[4];
						String location = (String) glober[5];
						String position = (String) glober[6];
						String seniority = (String) glober[7];
						String studio = (String) glober[8];

						String benchStartDateStr = "1-3-2016";
						SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

						JSONObject globerObj = new JSONObject();
						globerObj.put("id", id);
						globerObj.put("name", firstName+" "+lastName);
						globerObj.put("email", workEmail);
						globerObj.put("position", position);
						globerObj.put("seniority", seniority);
						globerObj.put("skills", "");
						globerObj.put("availablity", "100");
						globerObj.put("benchStartDate", benchStartDateStr);
						globerObj.put("studio", studio);
						globerObj.put("location", location);
						globerObj.put("leader", "");
						globerObj.put("handler", "");
						globerObj.put("status", "");
						globerArr.put(globerObj);
					}
				}
			}
			else if(viewId==5) {	//All Globers
				globerList = globersDao.getGlobersListForAllGlobersView(viewId);

				if(globerList!=null) {
					for(Object[] glober: globerList) {
						long id = (Long) glober[0];
						String userName = (String) glober[1];
						String workEmail = (String) glober[2];
						String firstName = (String) glober[3];
						String lastName = (String) glober[4];
						String location = (String) glober[5];
						String position = (String) glober[6];
						String seniority = (String) glober[7];
						String studio = (String) glober[8];

						String benchStartDateStr = "";
						SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

						JSONObject globerObj = new JSONObject();
						globerObj.put("id", id);
						globerObj.put("name", firstName+" "+lastName);
						globerObj.put("email", workEmail);
						globerObj.put("position", position);
						globerObj.put("seniority", seniority);
						globerObj.put("skills", "");
						globerObj.put("availablity", "100");
						globerObj.put("benchStartDate", "2-2-2016");
						globerObj.put("studio", studio);
						globerObj.put("location", location);
						globerObj.put("leader", "");
						globerObj.put("handler", "");
						globerObj.put("status", "");
						globerArr.put(globerObj);
					}
				}
			}
			else {					//Custom Views
				//Get filter criteria

				//Get actual data
			}
		}

		LOGGER.info("Exit from getGlobersListForView method of GlobersServiceImpl");
		return globerArr.toString();
	}

	@Override
	public Boolean addNewCustomView(HttpServletRequest request) throws Exception {

		String viewFor = "globers";
		boolean isActive = true;
		String name = request.getParameter("name");
		String userIdStr = request.getParameter("userId");
		String columns = request.getParameter("columns");
		String filterCriteria = request.getParameter("filterCriteria");
		String isDefaultView = request.getParameter("isDefaultView");

		long userId = 0;
		if(userIdStr!=null && userIdStr.trim().isEmpty()==false) {
			userId = Long.parseLong(userIdStr);
		}

		Glober user = null;
		if(userId!=0) {
			user = new Glober();
			user.setId(userId);
		}

		StaffingView customView = new StaffingView();
		customView.setName(name);
		customView.setColumns(columns);
		customView.setFilterCriteria(filterCriteria);

		customView.setUser(user);
		customView.setMasterDefault(false);
		customView.setShareViewId((long) 0);;
		Date todayDate = new Date();
		customView.setCreatedDate(todayDate);
		customView.setUpdatedDate(todayDate);
		customView.setActive(isActive);
		customView.setViewFor(viewFor);

		customView = globersDao.addNewCustomStaffingView(customView);
		if(customView!=null) {
			if(isDefaultView!=null && isDefaultView.trim().equals("true")) {
				StaffingUserDefaultView staffingUserDefaultView = globersDao.getStaffingUserDefaultViewByUserId(userId, viewFor, isActive);
				if(staffingUserDefaultView==null) {
					staffingUserDefaultView = new StaffingUserDefaultView();

					staffingUserDefaultView.setActive(true);
					staffingUserDefaultView.setDefaultViewFor(viewFor);
					staffingUserDefaultView.setStaffingView(customView);
					staffingUserDefaultView.setUser(user);

					globersDao.addNewStaffingUserDefaultView(staffingUserDefaultView);
				}
				else {
					staffingUserDefaultView.setActive(true);
					staffingUserDefaultView.setDefaultViewFor(viewFor);
					staffingUserDefaultView.setStaffingView(customView);
					staffingUserDefaultView.setUser(user);

					globersDao.updateStaffingUserDefaultView(staffingUserDefaultView);
				}
			}
			return true;
		}
		return false;
	}

}
