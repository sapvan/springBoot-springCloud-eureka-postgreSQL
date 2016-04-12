package com.globant.glow.staffing.controller;

import javax.ws.rs.QueryParam;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
	 * Get the user information along with its default role details
	 * @param userName
	 * @return userInfo json
	 */
	@RequestMapping(value="/globers/columns", method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getLoggedInUserDetails(@QueryParam("userName") String userName) {
		LOGGER.info("Inside getLoggedInUserDetails method of UserController");
		String userDetails = "";
		try {
			Object[] userInformation = globersService.getUserInfoWithDefaultRole(userName);

			long globerId = 0;
			String globerName = "";
			long roleId = 0;
			String roleName = "";

			if(userInformation!=null) {
				globerId = (Long) userInformation[0];

				globerName = (String) userInformation[2] +" "+(String) userInformation[3];

				roleId = (long) userInformation[4];

				roleName = (String) userInformation[5];
			}

			JSONObject userObj = new JSONObject();

			//User Details JSONObject creation
			JSONObject userDetailsObj = new JSONObject();
			userDetailsObj.put("id", globerId);
			userDetailsObj.put("name", globerName);
			userDetailsObj.put("roleId", roleId);
			userDetailsObj.put("roleName", roleName);
			userObj.put("userDetails", userDetailsObj);

			//Navigations JSONObject creation
			JSONObject navigationsObj = new JSONObject();

			//GlobalNavigation Array Creation
			JSONArray globalNavArr = new JSONArray();

			//MyInfo Global Navigation
			JSONObject myInfoGlobalNavObj = new JSONObject();
			myInfoGlobalNavObj.put("id", "");
			myInfoGlobalNavObj.put("name", "MyInfo");
			myInfoGlobalNavObj.put("url", "/myinfo");

			JSONArray myInfoPrimaryNavArr = new JSONArray();

			JSONObject myInfoPrimayNavObj = new JSONObject();
			myInfoPrimayNavObj.put("id", "");
			myInfoPrimayNavObj.put("name", "");
			myInfoPrimayNavObj.put("url", "");
			myInfoPrimaryNavArr.put(myInfoPrimayNavObj);

			myInfoGlobalNavObj.put("primaryNavList", myInfoPrimaryNavArr);
			globalNavArr.put(myInfoGlobalNavObj);

			//Financial Global Navigation
			JSONObject financialGlobalNavObj = new JSONObject();
			financialGlobalNavObj.put("id", "");
			financialGlobalNavObj.put("name", "Financial");
			financialGlobalNavObj.put("url", "/financial");

			JSONArray financialPrimaryNavArr = new JSONArray();

			JSONObject financialPrimayNavObj = new JSONObject();
			financialPrimayNavObj.put("id", "");
			financialPrimayNavObj.put("name", "");
			financialPrimayNavObj.put("url", "");
			financialPrimaryNavArr.put(financialPrimayNavObj);

			financialGlobalNavObj.put("primaryNavList", financialPrimaryNavArr);
			globalNavArr.put(financialGlobalNavObj);

			//Staffing Global Navigation
			JSONObject staffingGlobalNavObj = new JSONObject();
			staffingGlobalNavObj.put("id", "");
			staffingGlobalNavObj.put("name", "Staffing");
			staffingGlobalNavObj.put("url", "/staffing");

			JSONArray staffingPrimaryNavArr = new JSONArray();

			JSONObject homeForStaffing = new JSONObject();
			homeForStaffing.put("id", "");
			homeForStaffing.put("name", "Home");
			homeForStaffing.put("url", "/staffing/home");
			staffingPrimaryNavArr.put(homeForStaffing);

			JSONObject staffRequestsForStaffing = new JSONObject();
			staffRequestsForStaffing.put("id", "");
			staffRequestsForStaffing.put("name", "Staff Requests");
			staffRequestsForStaffing.put("url", "/staffing/staffrequests");
			staffingPrimaryNavArr.put(staffRequestsForStaffing);

			JSONObject projectsForStaffing = new JSONObject();
			projectsForStaffing.put("id", "");
			projectsForStaffing.put("name", "Projects");
			projectsForStaffing.put("url", "/staffing/projects");
			staffingPrimaryNavArr.put(projectsForStaffing);

			JSONObject globersForStaffing = new JSONObject();
			globersForStaffing.put("id", "");
			globersForStaffing.put("name", "Globers");
			globersForStaffing.put("url", "/staffing/globers");
			staffingPrimaryNavArr.put(globersForStaffing);

			JSONObject capacityForStaffing = new JSONObject();
			capacityForStaffing.put("id", "");
			capacityForStaffing.put("name", "Capacity");
			capacityForStaffing.put("url", "/staffing/capacity");
			staffingPrimaryNavArr.put(capacityForStaffing);

			staffingGlobalNavObj.put("primaryNavList", staffingPrimaryNavArr);
			globalNavArr.put(staffingGlobalNavObj);

			//Talent Development Global Navigation
			JSONObject talentDevelopmentGlobalNavObj = new JSONObject();
			talentDevelopmentGlobalNavObj.put("id", "");
			talentDevelopmentGlobalNavObj.put("name", "Talent Development");
			talentDevelopmentGlobalNavObj.put("url", "/talentdevelopment");

			JSONArray talentDevelopmentPrimaryNavArr = new JSONArray();

			JSONObject talentDevelopmentPrimayNavObj = new JSONObject();
			talentDevelopmentPrimayNavObj.put("id", "");
			talentDevelopmentPrimayNavObj.put("name", "");
			talentDevelopmentPrimayNavObj.put("url", "");
			talentDevelopmentPrimaryNavArr.put(talentDevelopmentPrimayNavObj);

			talentDevelopmentGlobalNavObj.put("primaryNavList", talentDevelopmentPrimaryNavArr);
			globalNavArr.put(talentDevelopmentGlobalNavObj);

			//Time Tracking Global Navigation
			JSONObject timeTrackingGlobalNavObj = new JSONObject();
			timeTrackingGlobalNavObj.put("id", "");
			timeTrackingGlobalNavObj.put("name", "Time Tracking");
			timeTrackingGlobalNavObj.put("url", "/timetracking");

			JSONArray timeTrackingPrimaryNavArr = new JSONArray();

			JSONObject timeTrackingPrimayNavObj = new JSONObject();
			timeTrackingPrimayNavObj.put("id", "");
			timeTrackingPrimayNavObj.put("name", "");
			timeTrackingPrimayNavObj.put("url", "");
			timeTrackingPrimaryNavArr.put(timeTrackingPrimayNavObj);

			timeTrackingGlobalNavObj.put("primaryNavList", timeTrackingPrimaryNavArr);
			globalNavArr.put(timeTrackingGlobalNavObj);

			navigationsObj.put("globalNavList", globalNavArr);

			userObj.put("navigations", navigationsObj);

			userDetails = userObj.toString();
		}
		catch(Exception e) {
			LOGGER.error("Exception in getLoggedInUserDetails method of UserController: ",e);
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOGGER.info("Exit from getLoggedInUserDetails method of UserController");
		return new ResponseEntity<String>(userDetails, HttpStatus.OK);
	}
}
