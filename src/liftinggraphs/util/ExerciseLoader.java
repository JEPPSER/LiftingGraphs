package liftinggraphs.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import liftinggraphs.entities.Exercise;

public class ExerciseLoader {

	public ArrayList<Exercise> load() {
		WorkoutParser wp = new WorkoutParser();
		ArrayList<Exercise> exercises = new ArrayList<Exercise>();
		try {
			Scanner scan = new Scanner(new File("./exercises.txt"));
			while (scan.hasNextLine()) {
				Exercise ex = wp.parse(new File(scan.nextLine()));
				exercises.add(ex);
			}
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return exercises;
	}
}
