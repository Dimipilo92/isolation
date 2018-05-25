package cs431.p3;

public class Computer extends Player{
	
	public Computer(String name, char symbol, String location) {
		super(name, symbol, location);
	}

	@Override
	public String getMove(Board b) {
		String move = "H8";
		System.out.println(getName() + "moves to " + move+"\n");
		return move;
	}

}
