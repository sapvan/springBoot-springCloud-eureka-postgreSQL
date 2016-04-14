package com.globant.glow.staffing.dao;

import java.util.List;

import com.globant.glow.core.domain.staffing.StaffingColumn;
import com.globant.glow.core.domain.staffing.StaffingView;


public interface GlobersDao {

	public List<StaffingColumn> getStaffingColumnList(String columnFor,boolean isActive) throws Exception;

	public StaffingView getdefaultViewByUserId(long userId,String defaultViewFor,boolean isActive) throws Exception ;

	public List<StaffingView> getStaffingViewList(long userId, String viewFor, boolean isActive) throws Exception;
}
