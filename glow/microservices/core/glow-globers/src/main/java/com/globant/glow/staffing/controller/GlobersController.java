package com.globant.glow.staffing.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import com.globant.glow.core.domain.staffing.StaffingView;
import com.globant.glow.staffing.services.GlobersService;



@RestController
public class GlobersController {

	@RequestMapping("/")
	public String hello() {
		return "Globers service";
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
	public ResponseEntity<List<StaffingColumn>> getStaffingColumnList() {
		LOGGER.info("Inside getStaffingColumnList method of GlobersController");
		String columnFor = "globers";
		boolean isActive = true;
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
	public ResponseEntity<String> getStaffingViewList(@QueryParam("userId") long userId) {
		LOGGER.info("Inside getStaffingViewList method of GlobersController");
		String viewFor = "globers";
		boolean isActive = true;
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


	/**
	 * Get the globers list for the selected view
	 * @param viewId- View primary key
	 * @return globersList json
	 */
	@RequestMapping(value="/globers", method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getGlobersListForView(@QueryParam("viewId") Long viewId,@QueryParam("offset") Integer offset,
			@QueryParam("limit") Integer limit,@QueryParam("sortingCriteria") String sortingCriteria,
			@QueryParam("filterCriteria") String filterCriteria) {
		LOGGER.info("Inside getGlobersListForView method of GlobersController");
		String globersList = null;
		try {
			if(viewId==null){
				viewId = (long) 0;
			}
			if(offset==null){
				offset = (int) 0;
			}
			if(limit==null){
				limit = (int) 0;
			}
			globersList = globersService.getGlobersListForView(viewId,offset,limit,sortingCriteria,filterCriteria);
		}
		catch(Exception e) {
			LOGGER.error("Exception in getGlobersListForView method of GlobersController: ",e);
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOGGER.info("Exit from getGlobersListForView method of GlobersController");
		return new ResponseEntity<String>(globersList, HttpStatus.OK);
	}

	/**
	 * Create new custom view
	 * @param httpServletRequest- Form data
	 * @return globersList json
	 */
	@RequestMapping(value="/globers/customviews", method=RequestMethod.POST, produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> addNewCustomView(HttpServletRequest request) {
		LOGGER.info("Inside addNewCustomView method of GlobersController");
		boolean customViewCreated = false;
		try {
			customViewCreated = globersService.addNewCustomView(request);
		}
		catch(Exception e) {
			LOGGER.error("Exception in addNewCustomView method of GlobersController: ",e);
			return new ResponseEntity<Boolean>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOGGER.info("Exit from addNewCustomView method of GlobersController");
		return new ResponseEntity<Boolean>(customViewCreated, HttpStatus.OK);
	}
}
