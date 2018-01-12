package liftinggraphs.entities;

import java.util.ArrayList;

public class Exercise {

	private String name;
	private ArrayList<Workout> workouts;
	
	public Exercise(){
		workouts = new ArrayList<Workout>();
	}
	
	public void setName(String name){
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
	
	public int getNumberOfWorkouts(){
		return workouts.size();
	}

	public void addWorkout(Workout workout) {
		workouts.add(workout);
	}

	public Workout getWorkout(int index) {
		return workouts.get(index);
	}
}
