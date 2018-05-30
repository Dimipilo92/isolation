package cs431.p3;

public class BoardDisplay {
	
	public static String display(Board b, Player[] p) {
		StringBuilder sb = new StringBuilder();
		String[] historyList = displayableHistoryList(b);
		sb.append("  ");
		
		// above the header (history > 9)
		int h = 0;
		if (historyList.length > b.getBoard().length) {
			sb.append("\t\t\t" + p[0].getName() + " vs. " + p[1].getName() +"\n");
			for (; h <= historyList.length-b.getBoard().length-2; h++) {
				sb.append("\t\t\t"+(h+1)+". "+historyList[h]+"\n");
			}
		}
		
		// header
		//sb.append("  ");
		for (int i = 1; i <= b.getBoard().length; i++) {
			sb.append(i+" ");
		}
		if (historyList.length <= b.getBoard().length) {
			sb.append("\t" + p[0].getName() + " vs. " + p[1].getName() +"\n");
		}
		else {
			sb.append("\t"+(h+1)+". "+historyList[h]+"\n");
			h++;
		}
		
		// below the header
		for (int i = 0; i < b.getBoard().length; i++) {
			sb.append( (char)('A'+i) + " " );
			for (int j = 0; j < b.getBoard().length; j++) {
				sb.append(b.getBoard()[i][j] + " ");
			}
			if (h < historyList.length) {
				sb.append("\t"+(h+1)+". "+historyList[h]);
				h++;
			}
			sb.append("\n");
		}
		
		// below the board
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
