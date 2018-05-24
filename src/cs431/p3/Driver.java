package cs431.p3;

import java.util.Scanner;

public class Driver {
	
	public static final Scanner in = new Scanner(System.in);

	public static void main(String[] args) {
		Game g= new Game(in);
		g.start();
		
		//Prototypes.validMoveTest("A1", "A2");
	}

}
