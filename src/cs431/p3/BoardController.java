package cs431.p3;

import java.util.Random;

public class BoardController {
	Board board;
	Player[] players;
	
	public BoardController(Board b, Player[] players) {
		this.board = b;
		this.players = players;
	}
	
	public Board getBoard() {
		return board;
	}
	
	public boolean isSurrounded() {
		return board.isSurrounded(current().getLocation());
	}
	
	public int surroundedBy() {
		return board.surroundedBy(current().getLocation());
	}
	
	public int opponentSurroundedBy() {
		return board.surroundedBy(waiting().getLocation());
	}
	
	public void promptNextMove() {
		System.out.println(BoardDisplay.display(board, players));
		String move = current().getMove(this);
		while (!board.isValidMove(current().getLocation(), move)) {
			System.out.println("Invalid Move!\n");
			System.out.println(BoardDisplay.display(board, players));
			move = current().getMove(this);
		}
		forceNextMove(move);
	}

	public void forceNextMove(String dest) {
		board.move(current().getLocation(), dest);
		waiting().setLocation(dest);
	}
	
	public String[] getValidMoves() {
		return board.getValidMoves(current().getLocation());
	}
	
	public String[] opponentValidMoves() {
		return board.getValidMoves(waiting().getLocation());
	}
	
	public String getRandomValidMove() {
		Random rand = new Random();
		String[] validMoves = board.getValidMoves(current().getLocation());
		int i = rand.nextInt(validMoves.length);
		return validMoves[i];
	}
	
	public void undo() {
		Player lastMover = waiting();
		String prevPos = board.undo();
		lastMover.setLocation(prevPos);
	}
	
	public Player current() {
		return players[board.totalMoves()%2];
	}
	
	public Player waiting() {
		return players[(board.totalMoves()+1)%2];
	}
	
	public void displayBoard() {
		System.out.println(BoardDisplay.display(board, players));
	}
}
