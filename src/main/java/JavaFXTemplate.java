import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import java.io.IOException;

import java.util.Objects;

public class JavaFXTemplate extends Application {
	private Stage primaryStage;
	private static final String WINDOW_TITLE = "Three Card Poker";
	private static final double WINDOW_WIDTH = 800;
	private static final double WINDOW_HEIGHT = 600;

	private static final String DEFAULT_CSS = "/styles.css";
	private static final String NEW_LOOK_CSS = "/newlook.css";

	private boolean isDefaultStyle = true;

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
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/game.fxml"));
		Scene scene = new Scene(loader.load());

		// Load CSS if necessary
		scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles.css")).toExternalForm());

		// Get the GameController and set the main app reference
		GameController controller = loader.getController();
		controller.setMainApp(this);

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void applyNewLook() {
		Scene scene = primaryStage.getScene(); // Get the current scene
		scene.getStylesheets().clear(); // Clear existing stylesheets

		if (isDefaultStyle) {
			// Apply new look CSS
			scene.getStylesheets().add(getClass().getResource(NEW_LOOK_CSS).toExternalForm());
			isDefaultStyle = false; // Set flag to indicate new look is now active
		} else {
			// Revert to default CSS
			scene.getStylesheets().add(getClass().getResource(DEFAULT_CSS).toExternalForm());
			isDefaultStyle = true; // Set flag to indicate default style is now active
		}
	}

	public void showExitScreen() {
		try {
			// Load the FXML
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/exit.fxml"));
			VBox exitScreen = loader.load();

			// Create new stage for exit dialog
			Stage exitStage = new Stage();
			exitStage.setTitle("Exit Confirmation");
			exitStage.initModality(Modality.WINDOW_MODAL); // Makes it modal
			exitStage.initOwner(primaryStage); // Set the owner as the main window

			// Create scene and show
			Scene scene = new Scene(exitScreen);
			scene.getStylesheets().add(getClass().getResource("/exit.css").toExternalForm());
			exitStage.setScene(scene);
			exitStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static void main(String[] args) {
		launch(args);
	}

}