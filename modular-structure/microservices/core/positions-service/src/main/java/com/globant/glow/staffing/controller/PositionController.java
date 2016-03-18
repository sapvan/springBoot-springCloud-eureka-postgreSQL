package com.globant.glow.staffing.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.globant.glow.staffing.model.Position;
import com.globant.glow.staffing.services.PositionService;



@RestController
public class PositionController {

	@RequestMapping("/")
	public String hello() {
		return "Hello";
	}


	@Autowired
	PositionService positionService;


    private static final Logger LOG = LoggerFactory.getLogger(PositionController.class);

	@RequestMapping(value="/positions", method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Position>> getAllPositions() {
		List<Position> positionsList = null;
		try {
			positionsList = positionService.getAllPositions();
		}
		catch(Exception e) {
			return new ResponseEntity<List<Position>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<Position>>(positionsList, HttpStatus.OK);
	}


	@RequestMapping(value="/position/{id}", method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Position> getPositionById(@PathVariable int id) {
		Position greeting = null;
		try {
			greeting = positionService.getPositionById(id);
		}
		catch(Exception e) {
			return new ResponseEntity<Position>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Position>(greeting, HttpStatus.OK);
	}

	@RequestMapping(value="/position", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Position> createPosition(@RequestBody Position position) {
		try {
			position = positionService.createPosition(position);
		}
		catch(Exception e) {
			return new ResponseEntity<Position>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Position>(position, HttpStatus.OK);
	}


	@RequestMapping(value="/position", method=RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Position> updatePosition(@RequestBody Position position) {
		Position updatedPosition = null;
		try {
			updatedPosition = positionService.updatePosition(position);
		}
		catch(Exception e) {
			return new ResponseEntity<Position>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Position>(updatedPosition, HttpStatus.OK);
	}

	@RequestMapping(value="/position/{id}", method=RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE, produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> deletePosition(@PathVariable int id) {
		try {
			positionService.deletePosition(id);
		}
		catch(Exception e) {
			return new ResponseEntity<Boolean>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Boolean>(HttpStatus.OK);
	}
}
