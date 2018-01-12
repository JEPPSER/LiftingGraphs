package liftinggraphs;

import java.io.File;

import liftinggraphs.entities.Exercise;
import liftinggraphs.entities.Set;
import liftinggraphs.util.WorkoutParser;

public class LiftingGraphs {

	public static void main(String[] args) {
		WorkoutParser wp = new WorkoutParser();
		Exercise ex = wp.parse(new File("C:/Users/JeSpEr/Desktop/Squat Log.txt"));
		
		for(int i=0; i<ex.getNumberOfWorkouts(); i++){
			System.out.print(ex.getWorkout(i).getDate().toString() + ": ");
			for(int j=0; j<ex.getWorkout(i).getNumberOfSets(); j++){
				Set set = ex.getWorkout(i).getSet(j);
				System.out.print(set.getReps() + "x" + set.getWeight() + "kg, ");
			}
			System.out.println();
		}
	}
}
