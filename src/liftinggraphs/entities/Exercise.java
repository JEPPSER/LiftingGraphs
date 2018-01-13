package liftinggraphs.entities;

import java.util.ArrayList;

public class Exercise {

	private String name;
	private ArrayList<Workout> workouts;

	public Exercise() {
		workouts = new ArrayList<Workout>();
	}

	public void setName(String name) {
		this.name = name;
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

	public double getMaxWeight() {
		double result = 0;
		for (int i = 0; i < workouts.size(); i++) {
			Workout workout = workouts.get(i);
			for (int j = 0; j < workout.getNumberOfSets(); j++) {
				Set set = workouts.get(i).getSet(j);
				if (set.getWeight() > result) {
					result = set.getWeight();
				}
			}
		}
		return result;
	}
	
	public int getMaxVolume() {
		int result = 0;
		for(int i=0; i<workouts.size(); i++){
			if(workouts.get(i).getVolume() > result){
				result = workouts.get(i).getVolume();
			}
		}
		return result;
	}

	public int getNumberOfWorkouts() {
		return workouts.size();
	}

	public void addWorkout(Workout workout) {
		workouts.add(workout);
	}

	public Workout getWorkout(int index) {
		return workouts.get(index);
	}

	public String toString() {
		String result = "";
		result += this.getName() + "\n";
		for (int i = 0; i < this.getNumberOfWorkouts(); i++) {
			result += this.getWorkout(i).getDate().toString() + ": ";
			for (int j = 0; j < this.getWorkout(i).getNumberOfSets(); j++) {
				Set set = this.getWorkout(i).getSet(j);
				result += set.getReps() + "x" + set.getWeight() + "kg, ";
			}
			result += "Volume: " + this.getWorkout(i).getVolume() + "\n";
		}
		return result;
	}
}
