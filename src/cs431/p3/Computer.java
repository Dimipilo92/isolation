package cs431.p3;

import java.util.Random;

public class Computer extends Player{
	
	private class Result {
		private int value;
		private String action;
		
		public Result() {
			this.action = "-";
			this.value = 0;
		}
		public Result(String action, int value) {
			this.action = action;
			this.value = value;
		}
		
		public String getAction() {return action;};
		public boolean isCutOff() {return value != Integer.MAX_VALUE;};
	}
	
	private int timeConstraint;
	
	public Computer(String name, int timeConstraint) {
		super(name);
		this.timeConstraint = timeConstraint;
	}
	
	public Computer(String name, char symbol, String location, int timeConstraint) {
		super(name, symbol, location);
		this.timeConstraint = timeConstraint;
	}

	@Override
	public String getMove(BoardController bc) {
		System.out.println(getName() + " is deciding next move...");
		String move = iterativeABSearch(bc);
		System.out.println(getName() + " selected " + move +"\n");
		return move;
	}
	
	private String iterativeABSearch(BoardController bc){
		int depth = 2;
		Result result = new Result();
		Result lastResult = null;
		long timeLimit = getTimeLimit(timeConstraint);
		while (!(result == null) || (lastResult == null)) { // result is maximum not cutoff
			lastResult = result;
			result = limitedABSearch(bc, depth,timeLimit);
			depth++;
		}
		return lastResult.getAction();
	}

	private Result limitedABSearch(BoardController bc, int depthLimit, long time) {
		String[] validMoves = bc.getValidMoves();
		
		int bestValue = Integer.MIN_VALUE;
		int beta = Integer.MAX_VALUE;
		String bestAction = "Nothing";
		for (String location : validMoves){
			bc.forceNextMove(location);
			int v = minValue(bc, bestValue, beta, depthLimit-1, time);
			bc.undo();
			if (outOfTime(time)) {
				return null;
			}
			if (v > bestValue){
				bestValue = v;
				bestAction = location;
			}
			if (v == bestValue) {// randomness?
				Random rand = new Random();
				if (rand.nextDouble() > 0.5) {
					bestValue = v;
					bestAction = location;
				}
			}
		}
		return new Result(bestAction, bestValue);
	}
	
	private int maxValue(BoardController bc, int alpha, int beta, int depth, long time) {
		if (cutoff(bc, depth, time)) {
			return evaluate(bc);
		}
		int v = Integer.MIN_VALUE;
		for (String location : bc.getValidMoves()) {
			bc.forceNextMove(location);
			v = Math.max(v, minValue(bc, alpha, beta, depth-1, time));
			bc.undo();
			if (outOfTime(time)) {
				return 0;
			}
			if (v >= beta) {
				return v;
			}
			alpha = Math.max(alpha, v);
		}
		return v;
	}
	
	private int minValue(BoardController bc, int alpha, int beta, int depth, long time) {
		if (cutoff(bc, depth, time)) {
			return evaluate(bc);
		}
		int v = Integer.MAX_VALUE;
		for (String location : bc.getValidMoves()) {
			bc.forceNextMove(location);
			v = Math.min(v, maxValue(bc, alpha, beta, depth-1, time));
			bc.undo();
			if (outOfTime(time)) {
				return 0;
			}
			if (v <= alpha) {
				return v;
			}
			beta = Math.min(beta, v);
		}
		return v;
	}
	
	private long getTimeLimit(int sec) {
		return System.currentTimeMillis() + sec*1000;
	}
	
	private boolean cutoff(BoardController bc, int depth, long time) {
		return (bc.isSurrounded() || depth <= 0 || outOfTime(time));
	}
	
	private int evaluate(BoardController bc){
		int evaluation = 0;
		if (bc.isSurrounded()){
			evaluation = Integer.MIN_VALUE;
		}
		if (bc.getValidMoves().length > 5) {
			evaluation = bc.getValidMoves().length - bc.opponentValidMoves().length;
		}
		if (bc.getValidMoves().length <= 5) {
			evaluation += bc.surroundedBy();
			evaluation -= bc.opponentSurroundedBy();
		}
		return evaluation;
	}
	
	private boolean outOfTime(long time) {
		return System.currentTimeMillis() >= time;
	}
}
