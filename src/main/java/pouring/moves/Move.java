package pouring.moves;

/**
 * A Move alters a State
 *
 */
public interface Move {
	State change(State state);
}