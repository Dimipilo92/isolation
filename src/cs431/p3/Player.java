package cs431.p3;


public abstract class Player {

	private String name;
	private String location;
	private char symbol;
	
	public Player(String name) {
		this.name = name;
		this.symbol = '?';
		this.location = "A1";
	}
	
	public Player(String name, char symbol, String location) {
		this.name = name;
		this.symbol = symbol;
		this.location = location;
	}

	public String getName() {
		return name;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}
	
	public char getSymbol() {
		return symbol;
	}
	
	public int getRow() {
		return location.charAt(0)-'A';
	}
	
	public int getCol() {
		return location.charAt(1)-'1';
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	public abstract String getMove(BoardController bc);
}
