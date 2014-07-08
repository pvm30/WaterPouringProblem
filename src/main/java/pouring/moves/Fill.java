package pouring.moves;

public class Fill extends AbstractMove{

	private final int[] capacities;

	public Fill(int[] capacities, int glass) {
		super(glass);
		this.capacities = capacities;
	}

	public State change(State state) {
		return state.updated(fromGlass, capacities[fromGlass]);
	}
}