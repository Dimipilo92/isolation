package cs431.p3;

import java.util.Scanner;

public class Game {
	Board board;
	Player[] players;
	Scanner in;
	
	public Game(Scanner in) {
		this.in = in;
		players = new Player[]{new Human("Player1", in), new Human("Player2", in)};
		board = Board.createBoard(players);
	}
	
	public void start() {
		BoardController bc = new BoardController(board,players);
		while (!board.isSurrounded(bc.current())) {
			System.out.println(BoardDisplay.display(board, players));
			String move = bc.current().getMove(bc);
			while (!board.isValidMove(bc.current(), move)) {
				System.out.println("\nInvalid Move!\n");
				System.out.println(BoardDisplay.display(board, players));
				move = bc.current().getMove(bc);
			}
			bc.move(bc.current(), move);
		}
		System.out.println(BoardDisplay.display(board, players));
		System.out.println(bc.waiting().getName() + " wins!");
	}
}
