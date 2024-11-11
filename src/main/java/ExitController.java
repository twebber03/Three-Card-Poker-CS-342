import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.fxml.FXML;

public class ExitController {

    @FXML
    private void handleexit() {
        System.exit(0);
    }

    @FXML
    private void handleResumeGame(ActionEvent event) {
        // Get the current stage
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // Close the exit confirmation window
        stage.close();
    }
}