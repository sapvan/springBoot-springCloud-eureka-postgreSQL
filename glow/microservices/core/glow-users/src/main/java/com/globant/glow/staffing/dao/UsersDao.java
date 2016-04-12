package com.globant.glow.staffing.dao;

import java.util.List;


public interface UsersDao {

	public Object[] getUserInfoWithDefaultRole(String userName) throws Exception;
}
