package com.globant.glow.staffing.dao;

import java.util.List;

import com.globant.glow.core.domain.staffing.StaffingColumn;
import com.globant.glow.core.domain.staffing.StaffingUserDefaultView;
import com.globant.glow.core.domain.staffing.StaffingView;


public interface GlobersDao {

	public List<StaffingColumn> getStaffingColumnList(String columnFor,boolean isActive) throws Exception;

	public StaffingView getDefaultViewByUserId(long userId,String defaultViewFor,boolean isActive) throws Exception ;

	public StaffingUserDefaultView getStaffingUserDefaultViewByUserId(long userId,String defaultViewFor,boolean isActive) throws Exception ;

	public List<StaffingView> getStaffingViewList(long userId, String viewFor, boolean isActive) throws Exception;

	public List<Object[]> getGlobersListForMyTpView(long viewId,int offset,int limit) throws Exception;

	public List<Object[]> getGlobersListForGlobalTpView(long viewId,int offset,int limit) throws Exception;

	public List<Object[]> getGlobersListForFollowingView(long viewId,int offset,int limit) throws Exception;

	public List<Object[]> getGlobersListForUnassignedView(long viewId,int offset,int limit) throws Exception;

	public List<Object[]> getGlobersListForAllGlobersView(long viewId,int offset,int limit) throws Exception;

	public StaffingView addNewCustomStaffingView(StaffingView customView) throws Exception;

	public StaffingUserDefaultView addNewStaffingUserDefaultView(StaffingUserDefaultView staffingUserDefaultView) throws Exception;

	public StaffingUserDefaultView updateStaffingUserDefaultView(StaffingUserDefaultView staffingUserDefaultView) throws Exception;
}
