package com.globant.glow.staffing.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.globant.glow.core.domain.staffing.StaffingColumn;
import com.globant.glow.core.domain.staffing.StaffingView;


public interface GlobersService {

	public List<StaffingColumn> getStaffingColumnList(String columnFor,boolean isActive) throws Exception;

	public String getStaffingViewList(long userId, String viewFor, boolean isActive) throws Exception;

	public String getGlobersListForView(long viewId) throws Exception;

	public Boolean addNewCustomView(HttpServletRequest request) throws Exception;

}
