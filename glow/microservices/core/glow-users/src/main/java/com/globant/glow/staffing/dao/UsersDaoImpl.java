package com.globant.glow.staffing.dao;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
@Transactional
public class UsersDaoImpl implements UsersDao{

	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() throws Exception {
	    return sessionFactory.getCurrentSession();
	}


}
