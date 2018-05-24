package cs431.p3;


public abstract class Player {

	private String name;
	private String location;
	private char symbol;
	
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
	
	public char getSymbol() {
		return symbol;
	}
	
	public int getRow() {
		return location.charAt(0)-'A';
	}
	
	public int getCol() {
		return location.charAt(1)-'1';
	}
	
	public abstract String move(Board b);
}
