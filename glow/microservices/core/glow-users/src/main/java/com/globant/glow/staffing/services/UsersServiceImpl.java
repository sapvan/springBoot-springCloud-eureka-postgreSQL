package com.globant.glow.staffing.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globant.glow.staffing.dao.UsersDao;



@Service
public class UsersServiceImpl implements UsersService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UsersServiceImpl.class);

	@Autowired
	UsersDao usersDao;

	/**
	 * Get the user information along with its default role details
	 * @param userName
	 * @return userInfo object
	 */
	@Override
	public Object[] getUserInfoWithDefaultRole(String userName) throws Exception {
		LOGGER.info("Inside getUserInfoWithDefaultRole method of UserServiceImpl");
		Object[] userInformation = usersDao.getUserInfoWithDefaultRole(userName);
		LOGGER.info("Inside getUserInfoWithDefaultRole method of UserServiceImpl");
		return userInformation;
	}


}
