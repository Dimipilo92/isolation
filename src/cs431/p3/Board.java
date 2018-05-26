package cs431.p3;

import java.util.ArrayList;
import java.util.List;


public class Board {
	
	private class Coord {
		int row;
		int col;
		
		public Coord() {};
		public Coord(String location) {set(location);}
		
		public void set(int row, int col) {
			this.row = row;
			this.col = col;
		}
		
		public void set(String location) {
			if (isValidLocation(location)) {
				this.row = location.charAt(0)-'A';
				this.col = location.charAt(1)-'1';
			}
		}
		
		public int getRow(){return row;}
		public int getCol(){return col;}
		
		private boolean isValidLocation(String location){
			return (location.length() == 2
					&& location.charAt(0) >= 'A' 
					&& location.charAt(0) < board.length+'A'
					&& location.charAt(1) >= '1'
					&& location.charAt(1) < board.length+'1');
		}
		
		public boolean isEmpty() {
			return isEmpty(row,col);
		}

		public boolean isEmpty(int row, int col) {
			return isValidLocation(row,col) && (board[row][col] == '-');
		}
		
		public boolean isValidLocation() {
			return isValidLocation(row,col);
		}
		public boolean isValidLocation(int row, int col) {
			return (row >= 0
					&& row < board.length
					&& col >= 0
					&& col < board.length);
		}
		
		public void goUp() {row--;}
		public void goDown() {row++;}
		public void goLeft() {col--;}
		public void goRight() {col++;}
		
		
		public String getLocation() {
			 return String.valueOf((char)(row+'A'))+String.valueOf((char)(col+'1'));
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
		String lastMoveMoveMade = history.get(history.size()-1);
		history.remove(history.size()-1);
		String prevPosition = history.get(history.size()-2);
		Coord src = new Coord(lastMoveMoveMade);
		Coord dst = new Coord(prevPosition);
		
		char symbol = board[src.getRow()][src.getCol()];
		board[dst.getRow()][dst.getCol()] = symbol;
		board[src.getRow()][src.getCol()] = '-';
		return prevPosition;
	}
	
	public int surroundedBy(String loc) {
		
		int total = 0;
		Coord c = new Coord(loc);

		int i = c.getRow();
		int j = c.getCol();
		
		if (!c.isEmpty(i,j-1)) {
			total++;
		}
		if (!c.isEmpty(i,j+1)) {
			total++;
		}
		if (!c.isEmpty(i-1,j)) {
			total++;
		}
		if (!c.isEmpty(i+1,j)) {
			total++;
		}
		if (!c.isEmpty(i+1,j+1)) {
			total++;
		}
		if (!c.isEmpty(i-1,j-1)) {
			total++;
		}
		if (!c.isEmpty(i+1,j-1)) {
			total++;
		}
		if (!c.isEmpty(i-1,j+1)){
			total++;
		}
		
		return total;
	}
	
	public boolean isSurrounded(String loc) {
		return surroundedBy(loc) == 8;
	}
	
	
	public String[] getValidMoves(String loc) {
		List<String> validMoves = new ArrayList<String>();

		Coord c = new Coord(loc);
		int i = c.getRow();
		int j = c.getCol();
		
		c.set(i-1,j);
		while (c.isEmpty()) {
			validMoves.add(c.getLocation());
			c.goUp();
		}
		c.set(i+1,j);
		while (c.isEmpty()) {
			validMoves.add(c.getLocation());
			c.goDown();
		}
		c.set(i,j-1);
		while (c.isEmpty()) {
			validMoves.add(c.getLocation());
			c.goLeft();
		}
		c.set(i,j+1);
		while (c.isEmpty()) {
			validMoves.add(c.getLocation());
			c.goRight();
		}
		c.set(i-1,j+1);
		while (c.isEmpty()) {
			validMoves.add(c.getLocation());
			c.goUp();
			c.goRight();
		}
		c.set(i+1,j-1);
		while (c.isEmpty()) {
			validMoves.add(c.getLocation());
			c.goDown();
			c.goLeft();
		}
		c.set(i-1,j-1);
		while (c.isEmpty()) {
			validMoves.add(c.getLocation());
			c.goUp();
			c.goLeft();
		}
		c.set(i+1,j+1);
		while (c.isEmpty()) {
			validMoves.add(c.getLocation());
			c.goDown();
			c.goRight();
		}
		
		return validMoves.toArray(new String[0]);
	}
	
	public boolean isValidMove(String loc1, String loc2) {
		
		boolean isValid = false;
		Coord src = new Coord(loc1);
		Coord dest = new Coord(loc2);

		if (!dest.isValidLocation()) {
			return false;
		}
		
		int vertDist = dest.getRow()-src.getRow();
		int horDist = dest.getCol()-src.getCol();
		if (horDist == 0 && vertDist == 0) {
			return false;
		} 
		else if (horDist == 0) { // up & down
			int direction = Integer.signum(vertDist);
			for (int i = src.getRow()+direction; i != dest.getRow()+direction; i+=direction){
				if (board[i][dest.getCol()] != '-') {
					return false;
				}
			}
			isValid = true;
		}
		else if (vertDist == 0) { // left & right
			int direction = Integer.signum(horDist);
			for (int i = src.getCol()+direction; i != dest.getCol()+direction; i+=direction){
				if (board[dest.getRow()][i] != '-') {
					return false;
				}
			}
			isValid = true;
		}
		else if (horDist == vertDist) { // along upwards diagonal
			int direction = Integer.signum(vertDist);
			for (int i = 1; i <= Math.abs(vertDist); i++){
				if (board[src.getRow()+(direction*i)][src.getCol()+(direction*i)] != '-') {
					return false;
				}
			}
			isValid = true;
		}
		else if (horDist == -vertDist) { // along downwards diagonal
			int direction = Integer.signum(vertDist);
			for (int i = 1; i <= Math.abs(vertDist); i++){
				if (board[src.getRow()+(direction*i)][src.getCol()-(direction*i)] != '-') {
					return false;
				}
			}
			isValid = true;
		}
		return isValid;
	}

}
