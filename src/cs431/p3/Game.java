package cs431.p3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
	List<String> history;
	Board board;
	int turns;
	Player[] players;
	Scanner in;
	
	public Game(Scanner in) {
		this.in = in;
		history = new ArrayList<String>();
		turns = 0;
	}
	
	public void start() {
		players = new Player[]{new Human("Player1", 'X', "A1", in), 
				new Human("Player2", 'O', "H8", in)};
		board = new Board(players);
		
		//while (currentPlayer().hasMoves(board)) {
		while(true) {
			System.out.println(displayBoard());
			String move = currentPlayer().move(board);
			history.add(move);
			turns++;
		}
	}
	
	private String displayBoard() {
		StringBuilder sb = new StringBuilder();
		sb.append("  ");
		for (int i = 1; i <= board.getBoard().length; i++) {
			sb.append(i+" ");
		}
		sb.append("\t" + players[0].getName() + " vs. " + players[1].getName() +"\n");
		for (int i = 0; i < board.getBoard().length; i++) {
			sb.append( (char)('A'+i) + " " );
			for (int j = 0; j < board.getBoard().length; j++) {
				sb.append(board.getBoard()[i][j] + " ");
			}
			if (i < history.size()) {
				sb.append("\t"+(i+1)+". "+history.get(i));
			}
			sb.append("\n");
		}
		if (history.size()>0) {
			sb.append("\n"+otherPlayer().getName() + "'s Move is: "
					+ history.get(history.size() - 1) + "\n");
		}
		
		if (history.size() > board.getBoard().length) {
			for (int i = board.getBoard().length; ; i++) {
				sb.append("\t\t\t\t\t"+i+". "+history.get(i)+"\n");
			}
		}
		
		return sb.toString();
	}
	
	private Player currentPlayer() {
		return players[turns%2];
	}
	
	private Player otherPlayer() {
		return players[(turns+1)%2];
	}
	
	private int round() {
		return turns/2;
	}
}
