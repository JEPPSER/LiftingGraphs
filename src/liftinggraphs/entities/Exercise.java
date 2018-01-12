package liftinggraphs.entities;

import java.io.File;
import java.util.ArrayList;

import liftinggraphs.util.WorkoutParser;

public class Exercise {

	private String name;
	private ArrayList<Workout> workouts;

	public Exercise(String name, File file) {
		WorkoutParser parser = new WorkoutParser();
		this.name = name;
		this.workouts = parser.parse(file);
	}

	public String getName() {
		return this.name;
	}

	public double getRepMax(int reps) {
		double result = 0;
		for (int i = 0; i < workouts.size(); i++) {
			Workout workout = workouts.get(i);
			for (int j = 0; j < workout.getNumberOfSets(); j++) {
				Set set = workouts.get(i).getSet(j);
				if (set.getReps() == reps) {
					if (set.getWeight() > result) {
						result = set.getWeight();
					}
				}
			}
		}
		return result;
	}

	public void addWorkout(Workout workout) {
		workouts.add(workout);
	}

	public Workout getWorkout(int index) {
		return workouts.get(index);
	}
}
