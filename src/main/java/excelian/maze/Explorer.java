package excelian.maze;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import excelian.maze.domain.Coordinate;
import excelian.maze.domain.Direction;
import excelian.maze.domain.MazeUnitType;

public class Explorer {
	
	private static final Logger logger = LogManager.getLogger(Explorer.class);
	private List<Coordinate> movement;
	private Maze maze;
	private boolean foundExit;
	
	public Explorer(Maze maze) {
		this.maze = maze;
	}

	public void dropIn() {
		foundExit = false;
		movement = new ArrayList<>();
		Coordinate start = maze.getStartPoint();
		movement.add(start);
		logger.info("Dropped into start: " + start.toString());
	}
	
	public Coordinate whatIsThere(Coordinate current, Direction direction) {
		Coordinate coordinate;
		switch(direction) {
			case UP: coordinate = new Coordinate(current.getX() - 1, current.getY());
					 break;
			case DOWN: 	coordinate = new Coordinate(current.getX() + 1, current.getY());
						break;
			case LEFT: 	coordinate = new Coordinate(current.getX(), current.getY() - 1);
						break;
			case RIGHT: coordinate = new Coordinate(current.getX(), current.getY() + 1);
						break;
			default: coordinate = null;
		}
		if (maze.getMaze().containsKey(coordinate)) {
			return coordinate;
		}
		return null;
	}
	
	private boolean isValidMove(Coordinate point) {
		if (point == null) {
			return false;
		}
		MazeUnitType unitType = maze.getPoint(point.getX(), point.getY());
		return (MazeUnitType.SPACE.equals(unitType) || MazeUnitType.END.equals(unitType))
			   && !movement.contains(point);
	}
	
	public void move(Coordinate current, Direction direction) {
		Coordinate newPosition = whatIsThere(current, direction);
		logger.info("Moving to: " + newPosition.toString());
		movement.add(newPosition);
	}
	
	public List<Coordinate> exploreMaze() {
		dropIn();
		explore(maze.getStartPoint());	
		return movement;
	}
	
	private void explore(Coordinate current) {
		
		if(MazeUnitType.END.equals(maze.getMaze().get(current))) {
			logger.info("Found the exit: " + current.toString());
			foundExit = true;
			return;
		}
				
		for (Direction direction : Direction.values()) {
			if (isValidMove(whatIsThere(current, direction))
					&& !foundExit) {
				move(current, direction);
				explore(whatIsThere(current, direction));
			}
		}
	}

	public List<Coordinate> getMovement() {
		return movement;
	}

	public Maze getMaze() {
		return maze;
	}
}
