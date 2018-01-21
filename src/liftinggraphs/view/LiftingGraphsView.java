package liftinggraphs.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import liftinggraphs.entities.Exercise;
import liftinggraphs.entities.Week;
import liftinggraphs.entities.Workout;
import liftinggraphs.util.ExerciseLoader;

public class LiftingGraphsView extends ScrollPane {

	private Stage primaryStage;

	public LiftingGraphsView(Stage primaryStage) {
		super.setPrefHeight(500);
		this.primaryStage = primaryStage;
		updateView();
	}

	private void updateView() {
		ExerciseLoader loader = new ExerciseLoader();
		ArrayList<Exercise> exercises = loader.load();

		VBox vbox = new VBox();

		HBox menu = new HBox();
		menu.setPrefHeight(50);
		menu.setPrefWidth(500);
		Button addButton = new Button("Add Exercise");
		addButton.setOnAction(e -> {
			onAddButtonClicked();
		});
		addButton.setTranslateY(12);
		addButton.setTranslateX(10);
		menu.getChildren().add(addButton);
		vbox.getChildren().add(menu);

		TabPane tv = new TabPane();
		for (int i = 0; i < exercises.size(); i++) {
			Tab tab = new Tab();
			tab.setText(exercises.get(i).getName());
			tab.setClosable(false);
			VBox root = new VBox();
			root.getChildren().add(createDailyVolumeLineChart(exercises.get(i)));
			root.getChildren().add(createWeekVolumeLineChart(exercises.get(i)));
			root.getChildren().add(createRepMaxLineChart(exercises.get(i)));
			root.getChildren().add(this.createCommonRepLineChart(exercises.get(i)));
			tab.setContent(root);
			tv.getTabs().add(tab);
		}
		vbox.getChildren().add(tv);
		super.setContent(vbox);
	}

	private LineChart<String, Number> createDailyVolumeLineChart(Exercise exercise) {
		CategoryAxis xAxis = new CategoryAxis();
		xAxis.setLabel("Workouts");
		NumberAxis yAxis = new NumberAxis(0, exercise.getMaxVolume() + 1000, 1000);
		yAxis.setLabel("Volume");

		LineChart<String, Number> lineChart = new LineChart<String, Number>(xAxis, yAxis);

		lineChart.setTitle("Daily Volume Line Chart");
		XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
		series.setName("Work Volume");
		for (int i = 0; i < exercise.getNumberOfWorkouts(); i++) {
			Workout w = exercise.getWorkout(i);
			series.getData().add(new XYChart.Data<String, Number>(w.getDate().toString(), w.getVolume()));
		}
		lineChart.getData().add(series);
		return lineChart;
	}

	private LineChart<String, Number> createWeekVolumeLineChart(Exercise exercise) {
		CategoryAxis xAxis = new CategoryAxis();
		xAxis.setLabel("Weeks");
		int maxWeekVolume = 0;
		ArrayList<Week> weeks = exercise.getWeekVolumes();
		for (int i = 0; i < weeks.size(); i++) {
			if (weeks.get(i).getVolume() > maxWeekVolume) {
				maxWeekVolume = weeks.get(i).getVolume();
			}
		}
		NumberAxis yAxis = new NumberAxis(0, maxWeekVolume + 2000, 2000);
		yAxis.setLabel("Volume");

		LineChart<String, Number> lineChart = new LineChart<String, Number>(xAxis, yAxis);

		lineChart.setTitle("Weekly Volume Line Chart");
		XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
		series.setName("Volume");
		for (int i = 0; i < weeks.size(); i++) {
			series.getData().add(new XYChart.Data<String, Number>(String.valueOf(weeks.get(i).getWeekNumber()),
					weeks.get(i).getVolume()));
		}
		lineChart.getData().add(series);
		return lineChart;
	}

	private LineChart<Number, Number> createRepMaxLineChart(Exercise exercise) {
		NumberAxis xAxis = new NumberAxis(1, 12, 1);
		xAxis.setLabel("Reps");
		NumberAxis yAxis = new NumberAxis(0, exercise.getMaxWeight() + 10, 10);
		yAxis.setLabel("Weight");

		LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);

		lineChart.setTitle("Rep Max Line Chart");
		XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>();
		series.setName("Weight");
		for (int i = 1; i < 12; i++) {
			if (exercise.getRepMax(i) != 0) {
				series.getData().add(new XYChart.Data<Number, Number>(i, exercise.getRepMax(i)));
			}
		}
		lineChart.getData().add(series);
		return lineChart;
	}
	
	private LineChart<String, Number> createCommonRepLineChart(Exercise exercise){
		CategoryAxis xAxis = new CategoryAxis();
		xAxis.setLabel("Workouts");
		NumberAxis yAxis = new NumberAxis(0, exercise.getMaxWeight() + 10, 10);
		yAxis.setLabel("Weight");

		LineChart<String, Number> lineChart = new LineChart<String, Number>(xAxis, yAxis);
		int mostCommonReps = exercise.getMostCommonReps();

		lineChart.setTitle(mostCommonReps + " Rep Weight Progression");
		XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
		series.setName("Weight");
		for (int i = 0; i < exercise.getNumberOfWorkouts(); i++) {
			Workout workout = exercise.getWorkout(i);
			if (workout.containsReps(mostCommonReps)) {
				series.getData().add(new XYChart.Data<String, Number>(workout.getDate().toString(), workout.getHeaviestWeight(mostCommonReps)));
			}
		}
		lineChart.getData().add(series);
		return lineChart;
	}

	private void onAddButtonClicked() {
		FileChooser fc = new FileChooser();
		File file = fc.showOpenDialog(primaryStage);
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(new File("./exercises.txt"), true));
			pw.println(file.getAbsolutePath());
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		updateView();
	}
}
