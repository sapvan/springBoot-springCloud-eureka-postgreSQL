package com.globant.glow.staffing.services;

import java.util.Date;
import java.util.List;

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
		LOGGER.info("Inside getStaffingColumnList method of GlobersServiceImpl");
		return staffingColumnList;
	}

	@Override
	public String getStaffingViewList(long userId, String viewFor, boolean isActive) throws Exception {
		StaffingView defaultView = globersDao.getdefaultViewByUserId(userId, viewFor, isActive);
		boolean isDefault = false;
		if(defaultView!=null) {

		}

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
				staffingViewObj.put("columns", columns);
				staffingViewObj.put("filterCriteria", filterCriteria);

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
			}
		}

		return staffingViewArr.toString();
	}

}
