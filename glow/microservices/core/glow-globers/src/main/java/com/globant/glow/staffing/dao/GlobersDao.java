package com.globant.glow.staffing.dao;

import java.util.List;

import com.globant.glow.core.domain.staffing.StaffingColumn;
import com.globant.glow.core.domain.staffing.StaffingView;


public interface GlobersDao {

	public List<StaffingColumn> getStaffingColumnList(String columnFor,boolean isActive) throws Exception;

	public StaffingView getDefaultViewByUserId(long userId,String defaultViewFor,boolean isActive) throws Exception ;

	public List<StaffingView> getStaffingViewList(long userId, String viewFor, boolean isActive) throws Exception;

	public List<Object[]> getGlobersListForGlobalTpView(long viewId) throws Exception;

	public List<Object[]> getGlobersListForFollowingView(long viewId) throws Exception;

	public List<Object[]> getGlobersListForUnassignedView(long viewId) throws Exception;

	public List<Object[]> getGlobersListForAllGlobersView(long viewId) throws Exception;
}
