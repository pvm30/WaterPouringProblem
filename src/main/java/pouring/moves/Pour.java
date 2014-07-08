package pouring.moves;

import java.util.Arrays;

public class Pour extends AbstractMove {

	private final int[] capacities;
	private final int to;

	public Pour(int[] capacities, int from, int to) {
		super(from);
		this.capacities = capacities;
		this.to = to;
	}

	public State change(State state) {
		// We are moving this amount of water: the minimum of the contents
		// of the 'from' glass and the empty space in the 'to' glass...
		int amount = Math.min(state.getGlassState(fromGlass), capacities[to]
				- state.getGlassState(to));
		return state.updated(fromGlass, state.getGlassState(fromGlass) - amount)
				.updated(to, state.getGlassState(to) + amount);
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "(" + fromGlass + "," + to	+ ")";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Arrays.hashCode(capacities);
		result = prime * result + to;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pour other = (Pour) obj;
		if (!Arrays.equals(capacities, other.capacities))
			return false;
		if (to != other.to)
			return false;
		return true;
	}

}