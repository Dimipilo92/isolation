package cs431.p3;

import java.util.Scanner;

public class Driver {

	public static final Scanner in = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("Isolation Game");
		System.out.println("Who goes first? \n1. Computer \n2. Opponent");
		int mode = in.nextInt();
		System.out.println("Enter the time limit in seconds: ");
		int timeLimit = in.nextInt();
		Game g = new Game(in, mode, timeLimit);
		g.start();
		in.close();
	}
}
