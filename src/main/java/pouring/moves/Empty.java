package pouring.moves;

public class Empty extends AbstractMove {

	public Empty(int glass) {
		super(glass);
	}

	public State change(State state) {
		return state.updated(fromGlass, 0);
	}
}