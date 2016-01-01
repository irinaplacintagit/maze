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
		
		if(MazeUnitType.END.equals(current)) {
			movement.add(current);
			System.out.println("found the end");
			return;
		}
		if (!maze.getMaze().containsKey(current)) {
			return;
		}
		
		movement.add(current);
		
		MazeUnitType up = getComponentUp(maze, current);
		if ((MazeUnitType.SPACE.equals(up) || (MazeUnitType.END.equals(up)))  &&
				!movement.contains(new Coordinate(current.getX() - 1, current.getY()))) {
			explore(maze, moveUp(current));
		}
		
		MazeUnitType down = getComponentDown(maze, current);
		if ((MazeUnitType.SPACE.equals(down) || (MazeUnitType.END.equals(down)))  &&
			!movement.contains(new Coordinate(current.getX() + 1, current.getY()))) {
			explore(maze, moveDown(current));
		}
		
		MazeUnitType left = getComponentToLeft(maze, current);
		if ((MazeUnitType.SPACE.equals(left) || (MazeUnitType.END.equals(left)))  &&
			!movement.contains(new Coordinate(current.getX(), current.getY() - 1))) {
			explore(maze, turnLeft(current));
		}
		
		MazeUnitType right = getComponentToRight(maze, current);
		if ((MazeUnitType.SPACE.equals(right) || (MazeUnitType.END.equals(right)))  &&
			!movement.contains(new Coordinate(current.getX(), current.getY() + 1))) {
			explore(maze, turnRight(current));
		}
	}

	private Coordinate moveUp(Coordinate current) {
		Coordinate newPosition = new Coordinate(current.getX() - 1, current.getY());
		return newPosition;
	}
	
	private Coordinate moveDown(Coordinate current) {
		Coordinate newPosition = new Coordinate(current.getX() + 1, current.getY());
		return newPosition;
	}

	private Coordinate turnLeft(Coordinate current) {
		Coordinate newPosition = new Coordinate(current.getX(), current.getY() - 1);
		return newPosition;
	}
	
	private Coordinate turnRight(Coordinate current) {
		Coordinate newPosition = new Coordinate(current.getX(), current.getY() + 1);
		return newPosition;
	}
	
	private MazeUnitType getComponentUp(Maze maze, Coordinate current) {
		return maze.getPoint(current.getX() - 1, current.getY());
	}
	
	private MazeUnitType getComponentDown(Maze maze, Coordinate current) {
		return maze.getPoint(current.getX() + 1, current.getY());
	}
	
	private MazeUnitType getComponentToLeft(Maze maze, Coordinate current) {
		return maze.getPoint(current.getX(), current.getY() - 1);
	}
	
	private MazeUnitType getComponentToRight(Maze maze, Coordinate current) {
		return maze.getPoint(current.getX(), current.getY() + 1);
	}

}
