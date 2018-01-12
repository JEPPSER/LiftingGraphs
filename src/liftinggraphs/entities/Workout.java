package liftinggraphs.entities;

import java.time.LocalDate;
import java.util.ArrayList;

public class Workout {

	private ArrayList<Set> sets;
	private LocalDate date;

	public Workout(LocalDate date) {
		sets = new ArrayList<Set>();
		this.date = date;
	}

	public void addSet(Set set) {
		this.sets.add(set);
	}

	public Set getSet(int index) {
		return this.sets.get(index);
	}

	public int getNumberOfSets() {
		return this.sets.size();
	}

	public int getVolume() {
		int volume = 0;
		for (int i = 0; i < sets.size(); i++) {
			int setVolume = (int) (sets.get(i).getReps() * sets.get(i).getWeight());
			volume += setVolume;
		}
		return volume;
	}

	public LocalDate getDate() {
		return this.date;
	}
}
