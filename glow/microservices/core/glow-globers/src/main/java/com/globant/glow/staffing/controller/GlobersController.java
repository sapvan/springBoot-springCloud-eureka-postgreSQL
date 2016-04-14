package com.globant.glow.staffing.controller;

import java.util.List;

import javax.ws.rs.QueryParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.globant.glow.core.domain.staffing.StaffingColumn;
import com.globant.glow.staffing.services.GlobersService;



@RestController
public class GlobersController {

	@RequestMapping("/")
	public String hello() {
		return "Hello";
	}


	@Autowired
	GlobersService globersService;

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobersController.class);


    /**
	 * Get the column list for the globers menu
	 * @param columnFor- column type
	 * @param isActive- Column status
	 * @return columnList json
	 */
	@RequestMapping(value="/globers/columns", method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<StaffingColumn>> getStaffingColumnList(@QueryParam("columnFor") String columnFor,@QueryParam("isActive") boolean isActive) {
		LOGGER.info("Inside getStaffingColumnList method of GlobersController");
		List<StaffingColumn> staffingColumnList = null;
		try {
			staffingColumnList = globersService.getStaffingColumnList(columnFor,isActive);
		}
		catch(Exception e) {
			LOGGER.error("Exception in getStaffingColumnList method of GlobersController: ",e);
			return new ResponseEntity<List<StaffingColumn>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOGGER.info("Exit from getStaffingColumnList method of GlobersController");
		return new ResponseEntity<List<StaffingColumn>>(staffingColumnList, HttpStatus.OK);
	}


	/**
	 * Get the view list for the globers menu
	 * @param columnFor- column type
	 * @param isActive- Column status
	 * @return columnList json
	 */
	@RequestMapping(value="/globers/views", method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getStaffingViewList(@QueryParam("userId") long userId,@QueryParam("viewFor") String viewFor,@QueryParam("isActive") boolean isActive) {
		LOGGER.info("Inside getStaffingViewList method of GlobersController");
		String staffingViewList = null;
		try {
			staffingViewList = globersService.getStaffingViewList(userId,viewFor,isActive);
		}
		catch(Exception e) {
			LOGGER.error("Exception in getStaffingViewList method of GlobersController: ",e);
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOGGER.info("Exit from getStaffingViewList method of GlobersController");
		return new ResponseEntity<String>(staffingViewList, HttpStatus.OK);
	}
}
