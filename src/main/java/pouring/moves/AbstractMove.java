package pouring.moves;

public abstract class AbstractMove implements Move {

	// All 'Move' classes have a fromGlass...
	protected final int fromGlass;

	protected AbstractMove(int fromGlass) {
		this.fromGlass = fromGlass;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "(" + fromGlass + ")";
	}

	// I need this two auto-generated methods for Hamcrest to behave OK in tests
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + fromGlass;
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
		AbstractMove other = (AbstractMove) obj;
		if (fromGlass != other.fromGlass)
			return false;
		return true;
	}

}
