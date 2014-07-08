package pouring;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import pouring.moves.Empty;
import pouring.moves.Fill;
import pouring.moves.Move;
import pouring.moves.Path;
import pouring.moves.Pour;
import pouring.moves.State;

public class Pouring {

	// All the possible moves in any moment are these
	private final List<Move> moves;

	// The sequence of all possible movements and the final states they lead to.
	// We consider only Paths that produce different final states. We store
	// in this List of List the shorter Paths before
	private final List<List<Path>> pathSets;

	// Constructor.. This 'capacities' array represents the different available glasses and their sizes...
	public Pouring(int[] capacities) {

		// All possible moves
		moves = getAllPossibleMoves(capacities);

		// ititialState = all are glasses empty
		State initialState = new State(new int[capacities.length]);

		List<Path> initialPaths = new ArrayList<Path>();
		initialPaths.add(new Path(new ArrayList<Move>(), initialState));

		Set<State> explored = new HashSet<State>();
		explored.add(initialState);

		pathSets = fromInitialPaths(initialPaths, explored);
	}

	private List<List<Path>> fromInitialPaths(List<Path> initialPaths,	Set<State> explored) {
		
		if (initialPaths.isEmpty()) {
			// Using Collections.EMPTY_LIST was throwing a UnsupportedOperationException
			// when prepending
			return new ArrayList<List<Path>>();
		}
		else {
			// This 'more' gives us the new set of paths after adding all
			// possible moves to all pre-existing paths not yet explored...
			List<Path> more = new ArrayList<Path>();
			for (Path path : initialPaths) {
				for (Move move : moves) {
					Path next = path.extend(move);
					if (!explored.contains(next.endState)) {
						more.add(next);
					}
				}
			}
			
			// Calling recursively and prepending...
			List<List<Path>> newPaths = fromInitialPaths(more, addToExplored(explored, more));
			newPaths.add(0, initialPaths);
			
			return newPaths;
		}
	}

	private Set<State> addToExplored(Set<State> explored, List<Path> more) {
		Set<State> newExplored = new HashSet<State>(explored);

		for (Path path : more)
			newExplored.add(path.endState);

		return newExplored;
	}

	private List<Move> getAllPossibleMoves(int[] capacities) {
		List<Move> moves = new ArrayList<Move>();

		for (int glass = 0; glass < capacities.length; glass++) {
			moves.add(new Empty(glass));
			moves.add(new Fill(capacities, glass));
		}

		for (int from = 0; from < capacities.length; from++)
			for (int to = 0; to < capacities.length; to++)
				if (to != from)
					moves.add(new Pour(capacities, from, to));

		return moves;
	}

	public Path getShortestSolution(int target) {
		for (List<Path> pathSet : pathSets) {
			for (Path path : pathSet) {
				if (path.endState.contains(target)) {
					return path;
				}
			}
		}

		return null;
	}
}
