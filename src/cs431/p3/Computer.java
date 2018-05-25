package cs431.p3;


public class Computer extends Player{
	
	public Computer(String name) {
		super(name);
	}
	
	public Computer(String name, char symbol, String location) {
		super(name, symbol, location);
	}

	@Override
	public String getMove(BoardController bc) {
		return search(bc);
	}
	
	private String search(BoardController bc) {
		long start = System.nanoTime();
		while ((System.nanoTime() - start)/1000000000.0 < 29.0) {
			
		}
		
		return null;
	}

}
