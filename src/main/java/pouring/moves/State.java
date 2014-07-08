package pouring.moves;

import java.util.Arrays;


/**
 * Class describing glasses contents at any time... 
 *
 */
public class State {

	private final int[] state;

	public State(int[] state) {
		this.state = state;
	}

	State updated(int glass, int newValue) {
		// This method mimics 'updated' Scala method and creates a copy of this State with one single replaced element.
		int[] copy = new int[state.length];
		System.arraycopy(state, 0, copy, 0, state.length);
		copy[glass] = newValue;

		return new State(copy);
	}

	int getGlassState(int glass) {
		return state[glass];
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(state);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		State other = (State) obj;
		if (!Arrays.equals(state, other.state))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "(" + getListString() + ")";
	}

	private String getListString() {
		StringBuffer stringBuffer = new StringBuffer();

		for (int i = 0; i < state.length; i++) {
			if (i == state.length - 1) {
				stringBuffer.append(state[i]);
			} else {
				stringBuffer.append(state[i] + ",");
			}
		}

		return stringBuffer.toString();
	}

	// Puff, you have to do this because of being using a primitive types array:
	// http://stackoverflow.com/questions/12020361/java-simplified-check-if-int-array-contains-int
	public boolean contains(int target) {
		for (int i : state) {
			if (i == target) {
				return true;
			}
		}

		return false;
	}
}