package liftinggraphs.entities;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.IsoFields;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Locale;

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
	
	public int getNumberOfWeeks(){
		int weeks = (int) ChronoUnit.WEEKS.between(workouts.get(0).getDate(), workouts.get(workouts.size() - 1).getDate());
		return weeks;
	}

	public ArrayList<Week> getWeekVolumes(){
		ArrayList<Week> list = new ArrayList<Week>();
		WeekFields weekFields = WeekFields.of(Locale.getDefault());
		int currentWeek = workouts.get(0).getDate().get(weekFields.weekOfWeekBasedYear());
		for(int i=0; i<workouts.size(); i++){
			int weekNumber = workouts.get(i).getDate().get(weekFields.weekOfWeekBasedYear());
			int weekVolume = 0;
			while(weekNumber == currentWeek && i < workouts.size()){
				weekVolume += workouts.get(i).getVolume();
				if(i >= workouts.size() - 1){
					weekNumber = workouts.get(i).getDate().get(weekFields.weekOfWeekBasedYear()) + 1;
					break;
				}
				i++;
				weekNumber = workouts.get(i).getDate().get(weekFields.weekOfWeekBasedYear());
			}
			int prevWeek = weekNumber - 1;
			if(prevWeek == 0){
				prevWeek = 52;
			}
			Week week = new Week(prevWeek, weekVolume);
			list.add(week);
			currentWeek = workouts.get(i).getDate().get(weekFields.weekOfWeekBasedYear());
			if(i >= workouts.size() - 1){
				break;
			}
			i--;
		}
		return list;
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
