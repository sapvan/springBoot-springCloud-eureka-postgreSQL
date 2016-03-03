package com.globant.glow.staffing.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.globant.glow.staffing.model.Position;


@Repository
@Transactional
public class PositionDaoImpl implements PositionDao{

	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
	    return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Position> getAllPositions() {
		return getSession().createQuery("from Position").list();
	}

	@Override
	public Position getPositionById(int id) {
		 return (Position) getSession().load(Position.class, id);
	}

	@Override
	public Position createPosition(Position position) {
		getSession().save(position);
		return position;
	}

	@Override
	public Position updatePosition(Position position) {
		getSession().update(position);
		return position;
	}

	@Override
	public void deletePosition(Position position) {
		getSession().delete(position);
	}

}
