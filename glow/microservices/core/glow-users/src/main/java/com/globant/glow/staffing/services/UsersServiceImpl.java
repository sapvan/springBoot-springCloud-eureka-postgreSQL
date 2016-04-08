package com.globant.glow.staffing.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globant.glow.staffing.dao.UsersDao;



@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	UsersDao usersDao;


}
