package com.globant.glow.staffing.services;

import java.util.List;

import com.globant.glow.core.domain.staffing.StaffingColumn;


public interface GlobersService {

	public List<StaffingColumn> getStaffingColumnList(String columnFor,boolean isActive) throws Exception;

	public String getStaffingViewList(long userId, String viewFor, boolean isActive) throws Exception;

	public String getGlobersListForView(long viewId) throws Exception;



}
