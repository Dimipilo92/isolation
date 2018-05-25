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
		
		while (!board.isSurrounded(currentPlayer())) {
			System.out.println(displayBoard());
			
			
			//**** clean
			String move = currentPlayer().getMove(board);
			while (!board.isValidMove(currentPlayer(), move)) {
				System.out.println("\nInvalid Move!\n");
				System.out.println(displayBoard());
				move = currentPlayer().getMove(board);
			}
			board.move(currentPlayer(), move);
			
			//**** end clean
			history.add(move);
			turns++;
		}
		System.out.println(displayBoard());
		System.out.println(otherPlayer() + "wins!");
	}
	
	private String displayBoard() {
		StringBuilder sb = new StringBuilder();
		String[] historyList = displayableHistoryList();
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
			if (i < historyList.length) {
				sb.append("\t"+(i+1)+". "+historyList[i]);
			}
			sb.append("\n");
		}
		if (historyList.length >= board.getBoard().length) {
			for (int i = board.getBoard().length; i < historyList.length; i++) {
				sb.append("\t\t\t"+(i+1)+". "+historyList[i]+"\n");
			}
		}
		
		if (historyList.length>0) {
			sb.append("\n"+otherPlayer().getName() + "'s Move is: "
					+ history.get(history.size() - 1) + "\n");
		}
		
		
		return sb.toString();
	}
	
	private String[] displayableHistoryList(){
		int historyListSize = (int)Math.ceil((double)(history.size())/2);
		String[] historyList = new String[historyListSize];
		for (int i = 0; i < historyList.length; i++) {
			historyList[i] = "";
		}
		
		for (int i = 0; i < history.size(); i++) {
			historyList[i/2]+=history.get(i)+" ";
		}
		
		return historyList;
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
