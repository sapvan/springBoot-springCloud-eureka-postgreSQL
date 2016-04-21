package com.globant.glow.staffing.services;

import java.util.Date;
import java.util.List;

import jdk.nashorn.internal.scripts.JS;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globant.glow.core.domain.Glober;
import com.globant.glow.core.domain.staffing.StaffingColumn;
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

	@Override
	public String getGlobersListForView(long viewId) throws Exception {
		LOGGER.info("Inside getGlobersListForView method of GlobersServiceImpl");
		JSONArray globerArr = new JSONArray();
		if(viewId!=0) {
			List<Object[]> globerList = null;
			if(viewId==1) {			//My TP
				globerList = globersDao.getGlobersListForMyTpView(viewId);
			}
			else if(viewId==2) {	//Global TP
				globerList = globersDao.getGlobersListForGlobalTpView(viewId);
			}
			else if(viewId==3) {	//Following TP
				globerList = globersDao.getGlobersListForFollowingView(viewId);
			}
			else if(viewId==4) {	//Un-assigned BU/Studio
				globerList = globersDao.getGlobersListForUnassignedView(viewId);
			}
			else if(viewId==5) {	//All Globers
				globerList = globersDao.getGlobersListForAllGlobersView(viewId);
			}
			else {					//Custom Views

			}

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

					JSONObject globerObj = new JSONObject();
					globerObj.put("id", id);
					globerObj.put("name", firstName+" "+lastName);
					globerObj.put("email", workEmail);
					globerObj.put("position", position);
					globerObj.put("seniority", seniority);
					globerObj.put("skills", "");
					globerObj.put("availablity", "");
					globerObj.put("benchStartDate", "");
					globerObj.put("studio", studio);
					globerObj.put("location", location);
					globerObj.put("leader", "");
					globerObj.put("handler", "");
					globerObj.put("status", "");
					globerArr.put(globerObj);
				}
			}
		}

		LOGGER.info("Exit from getGlobersListForView method of GlobersServiceImpl");
		return globerArr.toString();
	}

}
