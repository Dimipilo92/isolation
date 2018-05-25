package cs431.p3;

public class Board {
	public class Coord {
		String location;
		int row;
		int col;
		public Coord() {};
		public Coord(String location) {set(location);}
		public Coord(int row, int col) {set(row, col);}
		
		public void set(int row, int col) {
			this.location = String.valueOf(row+'A')+String.valueOf(col+'1');
			this.row = row;
			this.col = col;
			/*
			if (!isValidLocation()) {
				throw new RuntimeException("Location not on board.");
			}
			*/
		}
		
		public void set(String location) {
			this.location = location;
			if (isValidLocation()) {
				this.row = location.charAt(0)-'A';
				this.col = location.charAt(1)-'1';
			}
			/*
			else {
				throw new RuntimeException("Location not on board.");
			}*/
		}
		
		public int getRow(){return row;}
		public int getCol(){return col;}

		public boolean isEmpty() {
			return isValidLocation() && !(board[row][col] == '#');
		}
		
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
		
		private boolean isValidLocation(String loc) {
			return (loc.length() == 2
					&& loc.charAt(0) >= 'A' 
					&& loc.charAt(0) < board.length+'A'
					&& loc.charAt(1) >= '1'
					&& loc.charAt(1) < board.length+'1');
		}
		
		public boolean isValidLocation(int row, int col) {
			return (row >= 0
					&& row < board.length
					&& col >= 0
					&& col < board.length);
		}
	}
	
	private static final int BOARD_LENGTH = 8;
	private char[][] board;
	
	public Board(Player[] players){
		initBoard(players);
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
	
	public void move(Player p, String dest) {
		board[p.getRow()][p.getCol()] = '#';
		p.setLocation(dest);
		board[p.getRow()][p.getCol()] = p.getSymbol();
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
	
	/*
	public boolean getValidMoves() {
		
	}
	*/
	
	public boolean isValidMove(Player p, String loc) {
		
		boolean isValid = false;
		Coord dest = new Coord(loc);

		if (!dest.isValidLocation()) {
			return false;
		}
		
		int vertDist = dest.getRow()-p.getRow();
		int horDist = dest.getCol()-p.getCol();
		if (horDist == 0 && vertDist == 0) {
			isValid = true;;
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
