package excelian.maze;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import excelian.maze.domain.Coordinate;
import excelian.maze.domain.MazeUnitType;

public class ExplorerTest {

	private Explorer explorer;
	
	@Before
	public void setUp() throws Exception {
		explorer = new Explorer();
	}

	@Test
	public void testExploreMaze1() {
		Map<Coordinate, MazeUnitType> mazeMap = new HashMap<>();
		mazeMap.put(new Coordinate(1,1), MazeUnitType.WALL);
		mazeMap.put(new Coordinate(1,2), MazeUnitType.START);
		mazeMap.put(new Coordinate(1,3), MazeUnitType.WALL);
		mazeMap.put(new Coordinate(2,1), MazeUnitType.WALL);
		mazeMap.put(new Coordinate(2,2), MazeUnitType.SPACE);
		mazeMap.put(new Coordinate(2,3), MazeUnitType.WALL);
		mazeMap.put(new Coordinate(3,1), MazeUnitType.WALL);
		mazeMap.put(new Coordinate(3,2), MazeUnitType.SPACE);
		mazeMap.put(new Coordinate(3,3), MazeUnitType.WALL);
		mazeMap.put(new Coordinate(4,1), MazeUnitType.WALL);
		mazeMap.put(new Coordinate(4,2), MazeUnitType.END);
		mazeMap.put(new Coordinate(4,3), MazeUnitType.WALL);
		Maze maze = new Maze(mazeMap);
		
		explorer.exploreMaze(maze);
		List<Coordinate> path = explorer.getMovement();
		assertEquals(4, path.size());
		assertEquals(new Coordinate(1,2), path.get(0));
		assertEquals(new Coordinate(2,2), path.get(1));
		assertEquals(new Coordinate(3,2), path.get(2));
		assertEquals(new Coordinate(4,2), path.get(3));
	}
	
	@Test
	public void testExploreMaze2() {
		Map<Coordinate, MazeUnitType> mazeMap = new HashMap<>();
		mazeMap.put(new Coordinate(1,1), MazeUnitType.WALL);
		mazeMap.put(new Coordinate(1,2), MazeUnitType.START);
		mazeMap.put(new Coordinate(1,3), MazeUnitType.WALL);
		mazeMap.put(new Coordinate(1,4), MazeUnitType.WALL);
		mazeMap.put(new Coordinate(1,5), MazeUnitType.WALL);
		mazeMap.put(new Coordinate(1,6), MazeUnitType.WALL);
		mazeMap.put(new Coordinate(1,7), MazeUnitType.WALL);
		mazeMap.put(new Coordinate(1,8), MazeUnitType.WALL);

		mazeMap.put(new Coordinate(2,1), MazeUnitType.WALL);
		mazeMap.put(new Coordinate(2,2), MazeUnitType.SPACE);
		mazeMap.put(new Coordinate(2,3), MazeUnitType.WALL);
		mazeMap.put(new Coordinate(2,4), MazeUnitType.SPACE);
		mazeMap.put(new Coordinate(2,5), MazeUnitType.WALL);
		mazeMap.put(new Coordinate(2,6), MazeUnitType.SPACE);
		mazeMap.put(new Coordinate(2,7), MazeUnitType.SPACE);
		mazeMap.put(new Coordinate(2,8), MazeUnitType.END);
		
		mazeMap.put(new Coordinate(3,1), MazeUnitType.WALL);
		mazeMap.put(new Coordinate(3,2), MazeUnitType.SPACE);
		mazeMap.put(new Coordinate(3,3), MazeUnitType.WALL);
		mazeMap.put(new Coordinate(3,4), MazeUnitType.SPACE);
		mazeMap.put(new Coordinate(3,5), MazeUnitType.SPACE);
		mazeMap.put(new Coordinate(3,6), MazeUnitType.SPACE);
		mazeMap.put(new Coordinate(3,7), MazeUnitType.SPACE);
		mazeMap.put(new Coordinate(3,8), MazeUnitType.WALL);
		
		mazeMap.put(new Coordinate(4,1), MazeUnitType.WALL);
		mazeMap.put(new Coordinate(4,2), MazeUnitType.SPACE);
		mazeMap.put(new Coordinate(4,3), MazeUnitType.SPACE);
		mazeMap.put(new Coordinate(4,4), MazeUnitType.SPACE);
		mazeMap.put(new Coordinate(4,5), MazeUnitType.WALL);
		mazeMap.put(new Coordinate(4,6), MazeUnitType.WALL);
		mazeMap.put(new Coordinate(4,7), MazeUnitType.WALL);
		mazeMap.put(new Coordinate(4,8), MazeUnitType.WALL);
		
		mazeMap.put(new Coordinate(5,1), MazeUnitType.WALL);
		mazeMap.put(new Coordinate(5,2), MazeUnitType.WALL);
		mazeMap.put(new Coordinate(5,3), MazeUnitType.WALL);
		
		Maze maze = new Maze(mazeMap);
		explorer.exploreMaze(maze);
		List<Coordinate> path = explorer.getMovement();
		assertEquals(new Coordinate(1,2), path.get(0));
		assertEquals(new Coordinate(2,8), path.get(path.size() - 1));
		assertTrue(path.size() > 5);
		assertTrue(path.contains(new Coordinate(2, 2)));
		assertTrue(path.contains(new Coordinate(3, 2)));
		assertTrue(path.contains(new Coordinate(4, 2)));
	}
}
