import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Objects;

public class JavaFXTemplate extends Application {
	private Stage primaryStage;
	private static final String WINDOW_TITLE = "Three Card Poker";
	private static final double WINDOW_WIDTH = 800;
	private static final double WINDOW_HEIGHT = 600;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle(WINDOW_TITLE);
		this.primaryStage.setWidth(WINDOW_WIDTH);
		this.primaryStage.setHeight(WINDOW_HEIGHT);

		try {
			// Display a simple label
			// showGamePlayScreen();
			showWelcomeScreen();
		} catch (Exception e) {
			System.out.println("Error starting application: " + e.getMessage());
			Platform.exit();
		}
	}

	public void showWelcomeScreen() throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("welcome.fxml"));
		Scene scene = new Scene(loader.load());
		scene.getStylesheets().add("welcome.css");

		WelcomeController controller = loader.getController();
		controller.setMainApp(this);

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void showGamePlayScreen() throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml"));
		Scene scene = new Scene(loader.load());
		//scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("resources/game.css")).toExternalForm());
		scene.getStylesheets().add("styles.css");
		GameController controller = loader.getController();
		controller.setMainApp(this);

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}