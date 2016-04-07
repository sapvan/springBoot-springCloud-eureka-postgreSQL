package com.globant.glow.staffing.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.globant.glow.core.domain.LocationOffice;
import com.globant.glow.staffing.model.Position;


@Repository
@Transactional
public class PositionDaoImpl implements PositionDao{

	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() throws Exception {
	    return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LocationOffice> getAllPositions() throws Exception {
		return getSession().createQuery("from LocationOffice").list();
	}

	@Override
	public Position getPositionById(int id) throws Exception {
		 return (Position) getSession().get(Position.class, id);
	}

	@Override
	public Position createPosition(Position position) throws Exception {
		getSession().save(position);
		return position;
	}

	@Override
	public Position updatePosition(Position position) throws Exception {
		getSession().update(position);
		return position;
	}

	@Override
	public void deletePosition(Position position) throws Exception {
		getSession().delete(position);
	}

}
