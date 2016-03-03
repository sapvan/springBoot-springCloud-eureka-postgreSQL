package com.globant.glow.staffing.services;

import java.util.List;

import com.globant.glow.staffing.model.Position;



public interface PositionService {

	public List<Position> getAllPositions();

	public Position getPositionById(int id);

	public Position createPosition(Position position);

	public Position updatePosition(Position position);

	public void deletePosition(int id);
}
