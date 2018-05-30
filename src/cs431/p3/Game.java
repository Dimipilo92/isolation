package cs431.p3;

import java.util.Scanner;

public class Game {
	BoardController bc;
	
	Scanner in;
	
	public Game(Scanner in, int mode, int timeConstraint) {
		Player[] players = {};
		this.in = in;
		if (mode == 1) {
			players = new Player[] {new Computer("PC1",timeConstraint), new Human("Opponent", in)};
		} else if (mode == 2) {
			players = new Player[] {new Human("Opponent", in), new Computer("PC1",timeConstraint)};
		} else {
			System.out.println("Invalid choice.");
		}
//		players = new Player[]{new Human("Player1", in), new Human("Player2", in)};
		bc = new BoardController(Board.createBoard(players),players);
	}
	
	public void start() {
		while (!bc.isSurrounded()) {
			bc.promptNextMove();
		}
		bc.displayBoard();
		System.out.println(bc.waiting().getName() + " wins!");
	}
}
