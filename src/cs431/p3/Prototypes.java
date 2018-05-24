package cs431.p3;

public class Prototypes {
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
