package cs431.p3;

import java.util.Scanner;

public class Driver {

	public static final Scanner in = new Scanner(System.in);

	public static void main(String[] args) {
		// For testing purposes
//		Game g = new Game(in);
//
//		System.out.println("Isolation Game \nSelect a mode to play: ");
//		System.out.println("1. Player vs. Player \n2. Computer vs. Player " 
//				+ "\n3. Computer vs. Computer");
//		int mode = in.nextInt();
//		switch(mode) {
//		case 1:
//			g.start();
//			break;
//		case 2:
//			Player[] p = new Player[] {new Computer("PC1"), new Human("Dimitri", in)};
//			BoardController bc = new BoardController(Board.createBoard(p),p);
//			while(!bc.isSurrounded()) {
//				bc.promptNextMove();
//			}
//			System.out.println("Game Over");
//			break;
//		case 3:
//			Prototypes.computerTest(in);
//			break;
//		default:
//			System.out.println("Invalid choice.");
//			break;
//		}
		
		// Meets project requirements
		System.out.println("Isolation Game");
		System.out.println("Who goes first? \n1. Computer \n2. Opponent");
		int mode = in.nextInt();
		if (mode == 1) {
			Player[] p = new Player[] {new Computer("PC1"), new Human("Opponent", in)};
			BoardController bc = new BoardController(Board.createBoard(p),p);
			while(!bc.isSurrounded()) {
				bc.promptNextMove();
			}
			System.out.println("Game Over");
		}
		else if (mode == 2) {
			Player[] p = new Player[] {new Human("Opponent", in), new Computer("PC1")};
			BoardController bc = new BoardController(Board.createBoard(p),p);
			while(!bc.isSurrounded()) {
				bc.promptNextMove();
			}
			System.out.println("Game Over");
		}
		else {
			System.out.println("Invalid choice.");
		}
	}
}
