package cs431.p3;

import java.util.Scanner;

public class Prototypes {
	public static void computerTest(Scanner in) {
		Player[] p = new Player[] {new Computer("PC1"), new Computer("PC2")};
		BoardController bc = new BoardController(Board.createBoard(p),p);
		while(!bc.isSurrounded()) {
			bc.promptNextMove();
		}
		System.out.println("Game Over");
	}
	
	public static void pVpTest(Scanner in) {
		Player[] p = new Player[] {new Human("Dimitri", in), new Human("Vivian", in)};
		BoardController bc = new BoardController(Board.createBoard(p),p);
		while(!bc.isSurrounded()) {
			bc.promptNextMove();
		}
		System.out.println("Game Over");
	}
	
	public static void timeLimitTest() {
		int sec = 3;
		long cur = System.currentTimeMillis();
		long result = System.currentTimeMillis() + sec*1000;
		
		while(System.currentTimeMillis() < result) {
			System.out.println(System.currentTimeMillis());
		}
		System.out.println(cur);
		System.out.println(result);
		System.out.println(cur/1000);
		System.out.println(result/1000);
	}
	public static void testValidMoves(Scanner in) {
		Player[] p = new Player[] {new Human("Dimitri",in), new Human("Vivian",in)};
		Board b = Board.createBoard(p);
		
		String[] validMoves = b.getValidMoves(p[0].getLocation());
		for (String s : validMoves) {
			System.out.println(s);
		}
		
		System.out.println(BoardDisplay.display(b, p));
	}
	
	public static void testUndo(Scanner in) {
		Player[] p = new Player[] {new Human("Dimitri",in), new Human("Vivian",in)};
		Board b = Board.createBoard(p);
		
		BoardController bc = new BoardController(b,p);
		for (int i = 0; i < 3; i++) {
			bc.forceNextMove(bc.getRandomValidMove());
		}
		System.out.println(BoardDisplay.display(b, p));
		for (int i = 0; i < 3; i++) {
			bc.undo();
		}
		System.out.println(BoardDisplay.display(b, p));
		System.out.println(bc.current());
	}
	
	public static boolean isValidLocationTest(String location) {
		return (location.length() == 2
				&& location.charAt(0) >= 'A' 
				&& location.charAt(0) < 8+'A'
				&& location.charAt(1) >= '1'
				&& location.charAt(1) < 8+'1');
	}

	public static void validMoveTest(String src, String dst) {
		
		int srcRow = src.charAt(0)-'A';
		int srcCol = src.charAt(1)-'1';
		int dstCol = dst.charAt(0)-'A';
		int dstRow = dst.charAt(1)-'1';
		
		int vertDist = dstRow-srcRow;
		int horDist = dstCol-srcCol;
		if (horDist == 0 && vertDist == 0) {
			System.out.println("Same");
		} 
		else if (horDist == 0) { // up & down
			System.out.print("Horizontal: ");
			int direction = Integer.signum(vertDist);
			for (int i = srcRow+direction; i != dstRow+direction; i+=direction){
				System.out.print("("+i+",0)");
			}
			System.out.println("");
		}
		else if (vertDist == 0) { // left & right
			System.out.print("Vertical: ");
			int direction = Integer.signum(horDist);
			for (int i = srcCol+direction; i != dstCol+direction; i+=direction){
				System.out.print("(0,"+i+")");
			}
			System.out.println("");
		}
		else if (horDist == vertDist) { // along upwards diagonal
			System.out.println("Diagonal Up");
			int direction = Integer.signum(vertDist);
			for (int i = 1; i <= Math.abs(vertDist); i++){
				System.out.print("("+(srcRow+(direction*i))+","+(srcCol+(direction*i))+")");
			}
		}
		else if (horDist == -vertDist) { // along downwards diagonal
			System.out.println("Diagonal Down");
			int direction = Integer.signum(vertDist);
			for (int i = 1; i <= Math.abs(vertDist); i++){
				System.out.print("("+(srcRow+(direction*i))+","+(srcCol-(direction*i))+")");
			}
		}
		else {
			System.out.println("No Match!");
		}
	}
}
