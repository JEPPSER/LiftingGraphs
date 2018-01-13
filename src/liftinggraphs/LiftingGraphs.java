package liftinggraphs;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import liftinggraphs.view.LiftingGraphsView;

public class LiftingGraphs extends Application{

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		LiftingGraphsView view = new LiftingGraphsView(primaryStage);
		Scene scene = new Scene(view);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
