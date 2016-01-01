package excelian.maze;

import java.util.Map;

import excelian.maze.domain.Coordinate;
import excelian.maze.domain.MazeUnitType;

public class Maze {
	
	private Map<Coordinate, MazeUnitType> maze;
	private int walls;
	private int spaces;
	private Coordinate startPoint; 
	
	public Maze(Map<Coordinate, MazeUnitType> maze) {
		this.maze = maze;
		walls = 0;
		spaces = 0;
		computeMazeSettings();
	}
	
	public Map<Coordinate, MazeUnitType> getMaze() {
		return maze;
	}

	public void setMaze(Map<Coordinate, MazeUnitType> maze) {
		this.maze = maze;
	}
	
	public int getWalls() {
		return walls;
	}

	public int getSpaces() {
		return spaces;
	}
	
	public Coordinate getStartPoint() {
		return startPoint;
	}

	private void computeMazeSettings() {
		for (Coordinate point : maze.keySet()) {
				MazeUnitType type = maze.get(point);
				switch(type) {
					case WALL: 	walls++; break;
					case SPACE: spaces++; break;
					case START: startPoint = point;
								break;
					default:
			}
		}
	}
	
	public MazeUnitType getPoint(int x, int y) {
		return maze.get(new Coordinate(x, y));
	}
}
