package cs431.p3;

public class BoardController {
	Board board;
	Player[] players;
	
	public BoardController(Board b, Player[] players) {
		this.board = b;
		this.players = players;
	}
	
	public Board getBoard() {
		return board;
	}

	public void move(Player p, String dest) {
		board.move(p.getLocation(), dest);
		p.setLocation(dest);
	}
	
	public void undo() {
		Player lastMover = waiting();
		String prevPos = board.undo();
		lastMover.setLocation(prevPos);
	}
	
	public Player current() {
		return players[board.totalMoves()%2];
	}
	
	public Player waiting() {
		return players[(board.totalMoves()+1)%2];
	}
}
