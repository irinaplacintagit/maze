package excelian.maze;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import excelian.maze.domain.Coordinate;
import excelian.maze.domain.MazeUnitType;

public class MazeTest {

	private Maze maze;
	
	@Before
	public void init() {
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
		maze = new Maze(mazeMap);
	}
	
	@Test
	public void testComputeWallsAndSpaces() throws Exception {
		assertEquals(2, maze.getSpaces());
		assertEquals(8, maze.getWalls());
		Collection<MazeUnitType> values = maze.getMaze().values();
		List<MazeUnitType> start = values
				.stream()
				.filter(m -> m.equals(MazeUnitType.START))
				.collect(Collectors.toList());
		assertEquals(1, start.size());
		List<MazeUnitType> end = values
				.stream()
				.filter(m -> m.equals(MazeUnitType.END))
				.collect(Collectors.toList());
		assertEquals(1, end.size());
	}
	
	@Test
	public void testGetPoint() {
		assertEquals(MazeUnitType.WALL, maze.getPoint(1, 1));
		assertEquals(MazeUnitType.START, maze.getPoint(1, 2));
		assertEquals(MazeUnitType.SPACE, maze.getPoint(2, 2));
		assertEquals(MazeUnitType.END, maze.getPoint(4, 2));
	}
	
	@Test
	public void testGetPointInvalid() {
		assertNull(maze.getPoint(0, 1));
		assertNull(maze.getPoint(1, 5));
	}
	
	@Test
	public void getStartPoint() {
		assertEquals(new Coordinate(1,2), maze.getStartPoint());
	}
}
