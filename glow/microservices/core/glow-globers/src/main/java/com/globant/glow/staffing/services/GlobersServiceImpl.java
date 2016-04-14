package com.globant.glow.staffing.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globant.glow.core.domain.staffing.StaffingColumn;
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


		return null;
	}

}
