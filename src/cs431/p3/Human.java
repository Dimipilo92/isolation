package cs431.p3;

import java.util.Scanner;

public class Human extends Player {
	Scanner in;
	public Human(String name, char symbol, String location, Scanner in) {
		super(name, symbol, location);
		this.in = in;
	}

	@Override
	public String getMove(Board b) {
		
		System.out.println("Enter "+ getName() +"'s ("+getSymbol() + ") move: ");
		String move = in.next();
		
		return move;
	}
}
