package com.globant.glow.staffing.dao;

import java.util.List;

import com.globant.glow.staffing.model.Position;

public interface PositionDao {

	public List<Position> getAllPositions();

	public Position getPositionById(int id);

	public Position createPosition(Position greeting);

	public Position updatePosition(Position greeting);

	public void deletePosition(Position greeting);
}
