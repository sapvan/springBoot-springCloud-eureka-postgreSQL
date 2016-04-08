package com.globant.glow.staffing.controller;

import java.util.List;

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


}
