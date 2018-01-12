package liftinggraphs;

import java.util.ArrayList;

import liftinggraphs.entities.Exercise;
import liftinggraphs.util.ExerciseLoader;

public class LiftingGraphs {

	public static void main(String[] args) {
		ExerciseLoader loader = new ExerciseLoader();
		ArrayList<Exercise> exercises = loader.load();
		for (int i = 0; i < exercises.size(); i++) {
			System.out.println(exercises.get(i));
		}
	}
}
