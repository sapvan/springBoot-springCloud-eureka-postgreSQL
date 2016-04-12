package com.globant.glow.staffing.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.globant.glow.staffing.services.UsersServiceImpl;


@Repository
@Transactional
public class UsersDaoImpl implements UsersDao{

	private static final Logger LOGGER = LoggerFactory.getLogger(UsersDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() throws Exception {
	    return sessionFactory.getCurrentSession();
	}

	/**
	 * Get the user information along with its default role details
	 * @param userName
	 * @return userInfo object
	 */
	@Override
	public Object[] getUserInfoWithDefaultRole(String userName) throws Exception {
		LOGGER.info("Inside getUserInfoWithDefaultRole method of UsersDaoImpl");
		String hql = "select g.id,g.username,g.firstName,g.lastName,a.id,a.authority "
					 +"from Authority a,UsersAuthority ua,User u, Glober g "
					 +"where a.id=ua.key.authority and ua.key.user=u.id and u.resume.id=g.id and u.username=:userName";
		Query query = getSession().createQuery(hql);
		query.setString("userName", userName);
		Object[] userInformation = (Object[]) query.uniqueResult();
		LOGGER.info("Exit from getUserInfoWithDefaultRole method of UsersDaoImpl");
		return userInformation;
	}


}
