package com.globant.glow.staffing.services;

import java.util.List;

import com.globant.glow.staffing.model.Position;



public interface PositionService {

	public List<Position> getAllPositions() throws Exception;

	public Position getPositionById(int id) throws Exception;

	public Position createPosition(Position position) throws Exception;

	public Position updatePosition(Position position) throws Exception;

	public void deletePosition(int id) throws Exception;
}
