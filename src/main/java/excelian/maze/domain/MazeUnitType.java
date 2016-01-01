package excelian.maze.domain;

public enum MazeUnitType {
	
	WALL("X"),
	SPACE(" "),
	START("S"),
	END("F");
	
	private String value;
	private MazeUnitType(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return this.value;
	}
}
