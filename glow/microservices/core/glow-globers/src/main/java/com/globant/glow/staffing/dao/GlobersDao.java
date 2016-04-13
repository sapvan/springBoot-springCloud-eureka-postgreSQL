package com.globant.glow.staffing.dao;

import java.util.List;

import com.globant.glow.core.domain.staffing.StaffingColumn;


public interface GlobersDao {

	public List<StaffingColumn> getStaffingColumnList(String columnFor,boolean isActive) throws Exception;
}
