package excelian.maze;

import java.util.ArrayList;
import java.util.List;
import excelian.maze.domain.Coordinate;
import excelian.maze.domain.MazeUnitType;

public class Explorer {

	private List<Coordinate> movement;
	
	public List<Coordinate> exploreMaze(Maze maze) {
		movement = new ArrayList<>();
		explore(maze, maze.getStartPoint());
		
		return movement;
	}
	
	private void explore(Maze maze, Coordinate current) {
		
		if(MazeUnitType.END.equals(maze.getMaze().get(current))) {
			movement.add(current);
			return;
		}
		if (!maze.getMaze().containsKey(current)) {
			return;
		}
		
		movement.add(current);
		
		Coordinate upPoint = new Coordinate(current.getX() - 1, current.getY());
		MazeUnitType up = getComponent(maze, upPoint);
		if (isValidMove(up, upPoint)) {
			explore(maze, upPoint);
		}
		
		Coordinate downPoint = new Coordinate(current.getX() + 1, current.getY());
		MazeUnitType down = getComponent(maze, downPoint);
		if (isValidMove(down, downPoint)) {
			explore(maze, downPoint);
		}
		
		Coordinate leftPoint = new Coordinate(current.getX(), current.getY() - 1);
		MazeUnitType left = getComponent(maze, leftPoint);
		if (isValidMove(left, leftPoint)) {
			explore(maze, leftPoint);
		}
		
		Coordinate rightPoint = new Coordinate(current.getX(), current.getY() + 1);
		MazeUnitType right = getComponent(maze, rightPoint);
		if (isValidMove(right, rightPoint)) {
			explore(maze, rightPoint);
		}
	}
	
	private MazeUnitType getComponent(Maze maze, Coordinate point) {
		return maze.getPoint(point.getX(), point.getY());
	}
	
	private boolean isValidMove(MazeUnitType unitType, Coordinate coordinate) {
		return (MazeUnitType.SPACE.equals(unitType) || MazeUnitType.END.equals(unitType))
				&& !movement.contains(coordinate);
	}

	public List<Coordinate> getMovement() {
		return movement;
	}
}
