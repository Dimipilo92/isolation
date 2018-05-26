package cs431.p3;

import java.util.Scanner;

public class Game {
	BoardController bc;
	
	Scanner in;
	
	public Game(Scanner in) {
		Player[] players;
		this.in = in;
		players = new Player[]{new Human("Player1", in), new Human("Player2", in)};
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
