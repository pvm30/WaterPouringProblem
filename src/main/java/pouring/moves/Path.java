package pouring.moves;

import java.util.ArrayList;
import java.util.List;


/**
 *  Paths: sequences of moves and final state they produce. 
 *  The last move in the path appears first in the history
 *
 */
public class Path {
	
	public final List<Move> history;
	public final State endState;

	public Path(List<Move> history, State endState) {
		this.history = history;
		this.endState = endState;
	}

	// Adding a new Move to the Path...
	public Path extend(Move move) {
		List<Move> extendedHistory = new ArrayList<Move>(history);
		extendedHistory.add(0, move);
		return new Path(extendedHistory, move.change(endState));
	}

	@Override
	public String toString() {

		StringBuffer stringBuffer = new StringBuffer();

		for (int i = history.size() - 1; i >= 0; i--) {
			Move move = history.get(i);
			if (i == 0) {
				stringBuffer.append(move.toString());
			} else {
				stringBuffer.append(move.toString() + " - ");
			}
		}

		return stringBuffer.append(" arrives to " + endState.toString()).toString();
	}
}