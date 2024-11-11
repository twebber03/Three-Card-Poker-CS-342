
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

// WelcomeController.java
public class WelcomeController {
    private JavaFXTemplate mainApp;

    @FXML
    private void handleStartGame() {
        try {
            mainApp.showGamePlayScreen();  // Use the proper navigation method
        } catch (Exception e) {
            System.err.println("Error loading game screen: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void handleExit() {
        Platform.exit();  // Proper JavaFX exit method
    }

    public void setMainApp(JavaFXTemplate mainApp) {
        this.mainApp = mainApp;
    }
}