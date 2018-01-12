package liftinggraphs.entities;

public class Set {

	private int reps;
	private double weight;

	public Set(int reps, double weight) {
		this.reps = reps;
		this.weight = weight;
	}

	public int getReps() {
		return this.reps;
	}

	public double getWeight() {
		return this.weight;
	}
}
