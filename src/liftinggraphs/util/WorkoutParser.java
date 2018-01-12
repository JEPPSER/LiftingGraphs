package liftinggraphs.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.Scanner;

import liftinggraphs.entities.Exercise;
import liftinggraphs.entities.Set;
import liftinggraphs.entities.Workout;

public class WorkoutParser {
	
	public Exercise parse(File file) {
		Exercise exercise = new Exercise();

		try {
			Scanner scan = new Scanner(file);
			exercise.setName(scan.nextLine());
			
			DateTimeFormatter germanFormatter = DateTimeFormatter.ofLocalizedDate(
			        FormatStyle.MEDIUM).withLocale(Locale.GERMAN);
			
			while(scan.hasNextLine()){
				String str = scan.nextLine();
				String y = str.split("-")[0];
				String m = str.split("-")[1];
				String d = str.split("-")[2].substring(0, 2);
				
				LocalDate date = LocalDate.parse(d + "." + m + "." + y, germanFormatter);
				Workout workout = new Workout(date);
				String[] sets = str.split(": ")[1].split(";");
				
				for(int i=0; i<sets.length; i++){
					int numberOfSets = Integer.parseInt(sets[i].split("x")[0]);
					int reps = Integer.parseInt(sets[i].split("x")[1].split(",")[0]);
					double weight = Double.parseDouble(sets[i].split(" ")[1].replaceAll("kg", ""));
					for(int j=0; j<numberOfSets; j++){
						Set set = new Set(reps, weight);
						workout.addSet(set);
					}
				}
				exercise.addWorkout(workout);
			}
	
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return exercise;
	}
}
