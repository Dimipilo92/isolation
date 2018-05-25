package cs431.p3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Board {
	
	private class Coord {
		String location;
		int row;
		int col;
		public Coord() {};
		public Coord(String location) {set(location);}
		
		public void set(String location) {
			this.location = location;
			if (isValidLocation()) {
				this.row = location.charAt(0)-'A';
				this.col = location.charAt(1)-'1';
			}
		}
		
		public int getRow(){return row;}
		public int getCol(){return col;}
		
		private boolean isValidLocation(){
			return (location.length() == 2
					&& location.charAt(0) >= 'A' 
					&& location.charAt(0) < board.length+'A'
					&& location.charAt(1) >= '1'
					&& location.charAt(1) < board.length+'1');
		}

		public boolean isEmpty(int row, int col) {
			return isValidLocation(row,col) && (board[row][col] == '-');
		}
		
		public boolean isValidLocation(int row, int col) {
			return (row >= 0
					&& row < board.length
					&& col >= 0
					&& col < board.length);
		}
	}

	private static final int BOARD_LENGTH = 8;
	
	private List<String> history;
	private char[][] board;
	
	public Board(Player[] players){
		history = new ArrayList<String>();
		history.add(players[0].getLocation());
		history.add(players[1].getLocation());
		initBoard(players);
	}
	
	public static Board createBoard(Player[] p) {
		p[0].setSymbol('X');
		p[0].setLocation("A1");
		p[1].setSymbol('O');
		p[1].setLocation("H8");
		return new Board(p);
	}
	
	private void initBoard(Player[] p) {
		board = new char[BOARD_LENGTH][BOARD_LENGTH];
		for (int i = 0; i < BOARD_LENGTH; i++) {
			for (int j = 0; j < BOARD_LENGTH; j++) {
				board[i][j] = '-';
			}
		}
		board[p[0].getRow()][p[0].getCol()] = p[0].getSymbol();
		board[p[1].getRow()][p[1].getCol()] = p[1].getSymbol();
	}
	
	public char[][] getBoard() {
		return board;
	}
	
	
	public String[] getHistory() {
		return history.subList(2, history.size()).toArray(new String[0]);
	}
	
	public int totalMoves() {
		return history.size()-2;
	}
	
	public boolean isNewGame() {
		return history.size() > 0;
	}
	
	public String getLastMove() {
		return history.get(history.size()-1);
	}

	public void move(String l1, String l2) {
		Coord src = new Coord(l1);
		Coord dst = new Coord(l2);
		board[dst.getRow()][dst.getCol()] = board[src.getRow()][src.getCol()];
		board[src.getRow()][src.getCol()] = '#';
		history.add(l2);
	}
	
	public String undo() {
		String lastMove = history.get(history.size()-1);
		history.remove(history.size()-1);
		String prevPosition = history.get(history.size()-2);
		Coord src = new Coord(lastMove);
		Coord dst = new Coord(prevPosition);
		
		char symbol = board[src.getRow()][src.getCol()];
		board[dst.getRow()][dst.getCol()] = symbol;
		board[src.getRow()][src.getCol()] = '-';
		return prevPosition;
	}
	
	public boolean isSurrounded(Player p) {
		int i = p.getRow();
		int j = p.getCol();
		
		Coord c = new Coord();
		
		return (!c.isEmpty(i,j-1)
				&& !c.isEmpty(i,j+1)
				&& !c.isEmpty(i-1,j)
				&& !c.isEmpty(i+1,j)
				&& !c.isEmpty(i+1,j+1)
				&& !c.isEmpty(i-1,j-1)
				&& !c.isEmpty(i+1,j-1)
				&& !c.isEmpty(i-1,j+1));
	}
	
	public boolean isValidMove(Player p, String loc) {
		
		boolean isValid = false;
		Coord dest = new Coord(loc);

		if (!dest.isValidLocation()) {
			return false;
		}
		
		int vertDist = dest.getRow()-p.getRow();
		int horDist = dest.getCol()-p.getCol();
		if (horDist == 0 && vertDist == 0) {
			return false;
		} 
		else if (horDist == 0) { // up & down
			int direction = Integer.signum(vertDist);
			for (int i = p.getRow()+direction; i != dest.getRow()+direction; i+=direction){
				if (board[i][dest.getCol()] != '-') {
					return false;
				}
			}
			isValid = true;
		}
		else if (vertDist == 0) { // left & right
			int direction = Integer.signum(horDist);
			for (int i = p.getCol()+direction; i != dest.getCol()+direction; i+=direction){
				if (board[dest.getRow()][i] != '-') {
					return false;
				}
			}
			isValid = true;
		}
		else if (horDist == vertDist) { // along upwards diagonal
			int direction = Integer.signum(vertDist);
			for (int i = 1; i <= Math.abs(vertDist); i++){
				if (board[p.getRow()+(direction*i)][p.getCol()+(direction*i)] != '-') {
					return false;
				}
			}
			isValid = true;
		}
		else if (horDist == -vertDist) { // along downwards diagonal
			int direction = Integer.signum(vertDist);
			for (int i = 1; i <= Math.abs(vertDist); i++){
				if (board[p.getRow()+(direction*i)][p.getCol()-(direction*i)] != '-') {
					return false;
				}
			}
			isValid = true;
		}
		return isValid;
	}

}
