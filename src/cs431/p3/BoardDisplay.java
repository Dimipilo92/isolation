package cs431.p3;

public class BoardDisplay {
	public static String display(Board b, Player[] p) {
		StringBuilder sb = new StringBuilder();
		String[] historyList = displayableHistoryList(b);
		sb.append("  ");
		for (int i = 1; i <= b.getBoard().length; i++) {
			sb.append(i+" ");
		}
		sb.append("\t" + p[0].getName() + " vs. " + p[1].getName() +"\n");
		for (int i = 0; i < b.getBoard().length; i++) {
			sb.append( (char)('A'+i) + " " );
			for (int j = 0; j < b.getBoard().length; j++) {
				sb.append(b.getBoard()[i][j] + " ");
			}
			if (i < historyList.length) {
				sb.append("\t"+(i+1)+". "+historyList[i]);
			}
			sb.append("\n");
		}
		if (historyList.length >= b.getBoard().length) {
			for (int i = b.getBoard().length; i < historyList.length; i++) {
				sb.append("\t\t\t"+(i+1)+". "+historyList[i]+"\n");
			}
		}
		if (historyList.length>0) {
			sb.append("\n"+p[(b.getHistory().length+1)%2] + "'s Move is: "
					+ b.getLastMove() + "\n");
		}
		
		return sb.toString();
	}
	
	private static String[] displayableHistoryList(Board b){
		String[] history = b.getHistory();
		int historyListSize = (int)Math.ceil((double)(history.length)/2);
		String[] historyList = new String[historyListSize];
		for (int i = 0; i < historyList.length; i++) {
			historyList[i] = "";
		}
		
		for (int i = 0; i < history.length; i++) {
			historyList[i/2]+=history[i]+" ";
		}
		
		return historyList;
	}
}
