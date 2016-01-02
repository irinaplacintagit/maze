package excelian.maze.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import excelian.maze.Maze;
import excelian.maze.domain.Coordinate;
import excelian.maze.domain.MazeUnitType;

public class MazeLoader {

	public static Maze loadFromFile(File file) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(file));
		Map<Coordinate, MazeUnitType> mazeMap = new HashMap<>();
		String row;
		int y = 1;
		while ((row = reader.readLine()) != null) {
			for (int x = 1; x <= row.length(); x++) {
				switch (row.charAt(x-1)) {
					case 'X': 	mazeMap.put(new Coordinate(x, y), MazeUnitType.WALL);
								break;
					case ' ': 	mazeMap.put(new Coordinate(x, y), MazeUnitType.SPACE);
								break;
					case 'S': 	mazeMap.put(new Coordinate(x, y), MazeUnitType.START);
								break;
					case 'F': 	mazeMap.put(new Coordinate(x, y), MazeUnitType.END);
								break;			
				}
			}
			y++;
		}
		reader.close();
		return new Maze(mazeMap);
	}
}
