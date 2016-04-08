package com.globant.glow.staffing.controller;

import java.util.List;

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

import com.globant.glow.core.domain.BloodInformation;
import com.globant.glow.staffing.services.UsersService;



@RestController
public class UsersController {

	@RequestMapping("/")
	public String hello() {
		return "Hello";
	}


	@Autowired
	UsersService usersService;


    private static final Logger LOG = LoggerFactory.getLogger(UsersController.class);

	@RequestMapping(value="/positions", method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BloodInformation>> getAllPositions() {
		List<BloodInformation> positionsList = null;
		try {

		}
		catch(Exception e) {
			return new ResponseEntity<List<BloodInformation>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<BloodInformation>>(positionsList, HttpStatus.OK);
	}

	@RequestMapping(value="/users", method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getLoggedInUserDetails() {
		String userDetails = "";
		try {
			JSONObject userObj = new JSONObject();

			//User Details JSONObject creation
			JSONObject userDetailsObj = new JSONObject();
			userDetailsObj.put("id", "");
			userDetailsObj.put("name", "");
			userDetailsObj.put("roleId", "");
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
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>(userDetails, HttpStatus.OK);
	}
}
