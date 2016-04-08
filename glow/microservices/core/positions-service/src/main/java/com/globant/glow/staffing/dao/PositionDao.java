package com.globant.glow.staffing.dao;

import java.util.List;

import com.globant.glow.core.domain.BloodInformation;
import com.globant.glow.staffing.model.Position;

public interface PositionDao {

	public List<BloodInformation> getAllPositions() throws Exception;

	public Position getPositionById(int id) throws Exception;

	public Position createPosition(Position greeting) throws Exception;

	public Position updatePosition(Position greeting) throws Exception;

	public void deletePosition(Position greeting) throws Exception;
}
