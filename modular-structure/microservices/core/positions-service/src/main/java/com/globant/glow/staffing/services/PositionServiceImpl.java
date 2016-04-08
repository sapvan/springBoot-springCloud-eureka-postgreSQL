package com.globant.glow.staffing.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globant.glow.core.domain.BloodInformation;
import com.globant.glow.core.domain.LocationOffice;
import com.globant.glow.staffing.dao.PositionDao;
import com.globant.glow.staffing.model.Position;



@Service
public class PositionServiceImpl implements PositionService {

	@Autowired
	PositionDao positionDao;

	public List<BloodInformation> getAllPositions() throws Exception{
		List<BloodInformation> positionList = positionDao.getAllPositions();
		return positionList;
	}

	@Override
	public Position getPositionById(int id) throws Exception {
		Position position = positionDao.getPositionById(id);
		return position;
	}

	@Override
	public Position createPosition(Position position) throws Exception {
		position = positionDao.createPosition(position);
		return position;
	}

	@Override
	public Position updatePosition(Position position) throws Exception {
		position = positionDao.updatePosition(position);
		return position;
	}

	@Override
	public void deletePosition(int id) throws Exception {
		Position position = new Position(id);
		positionDao.deletePosition(position);
	}

}
