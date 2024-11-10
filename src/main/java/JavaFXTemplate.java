import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.util.Duration;


public class JavaFXTemplate extends Application {

//	Button button1=new Button("Exit"), button2=new Button("Play");
//	TextField title=new TextField(), message=new TextField();
//    BorderPane layout=new BorderPane();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Button button1=new Button("Exit"), button2=new Button("Play");
		TextField title=new TextField(), message=new TextField();
		BorderPane layout=new BorderPane();

		primaryStage.setTitle("Welcome Screen");
		button1.setMinWidth(200);
		button2.setMinWidth(200);
		title.setText("THREE CARD POKER");
		title.setMinWidth(600);
		message.setText("Welcome to 3 Card Poker! Get ready to trust your luck and skill! Are you ready to play!");
        message.setMinWidth(400);

//		primaryStage.setTitle("Welcome to JavaFX");
//
//		 Rectangle rect = new Rectangle (100, 40, 100, 100);
//	     rect.setArcHeight(50);
//	     rect.setArcWidth(50);
//	     rect.setFill(Color.VIOLET);
//
//	     RotateTransition rt = new RotateTransition(Duration.millis(5000), rect);
//	     rt.setByAngle(270);
//	     rt.setCycleCount(4);
//	     rt.setAutoReverse(true);
//	     SequentialTransition seqTransition = new SequentialTransition (
//	         new PauseTransition(Duration.millis(500)),
//	         rt
//	     );
//	     seqTransition.play();
//
//	     FadeTransition ft = new FadeTransition(Duration.millis(5000), rect);
//	     ft.setFromValue(1.0);
//	     ft.setToValue(0.3);
//	     ft.setCycleCount(4);
//	     ft.setAutoReverse(true);
//
//	     ft.play();
//	     BorderPane root = new BorderPane();
//	     root.setCenter(rect);
//
//	     Scene scene = new Scene(root, 700,700);
//			primaryStage.setScene(scene);
//			primaryStage.show();
		HBox buttonbox=new HBox(20, button1, button2);
		layout.setTop(title);
		layout.setCenter(message);
		layout.setBottom(buttonbox);
		Scene window=new Scene(layout, 700, 700);

		button2.setOnAction(e->{
			primaryStage.setScene(play());
			primaryStage.setTitle("Loading...");
			primaryStage.setTitle("Play Screen");
		});
		button1.setOnAction(e-> primaryStage.close());
		primaryStage.setScene(window);
		primaryStage.show();
	}

//	private Scene welcome(){
//		button1.setMinWidth(200);
//		button2.setMinWidth(200);
//		title.setText("THREE CARD POKER");
//		title.setMinWidth(600);
//		message.setText("Welcome to 3 Card Poker! Get ready to trust your luck and skill! Are you ready to play!");
//		message.setMinWidth(400);
//		HBox buttonbox=new HBox(20, button1, button2);
//		layout.setTop(title);
//		layout.setCenter(message);
//		layout.setBottom(buttonbox);
//		return new Scene(layout, 700, 700);
//	}

	private Scene play(){
		TextField title=new TextField(), name1=new TextField(), name2=new TextField(), totalwinning=new TextField(), stotalwinning=new TextField(), dealer=new TextField(), log=new TextField();
		BorderPane layout=new BorderPane();
		title.setText("Hi, this is the play screen");
		name1.setText("Player 1");
		name2.setText("Player 2");
		dealer.setText("Dealer");
		log.setText("Log Info");
		totalwinning.setText("Total Winnings: $");
		stotalwinning.setText("Total Winnings: $");
		Button button3=new Button("Deal"), button4=new Button("Play"), button5=new Button("Fold"), exit=new Button("Exit"), freshstart=new Button("Fresh Start"), look=new Button("New Look");
		Button sbutton3=new Button("Deal"), sbutton4=new Button("Play"), sbutton5=new Button("Fold");
		HBox buttons=new HBox(button3, button4, button5), sbuttons=new HBox(sbutton3, sbutton4, sbutton5);
		VBox player1=new VBox(name1, buttons, totalwinning);
		VBox player2=new VBox(name2, sbuttons, stotalwinning);
		VBox menu=new VBox(exit, freshstart, look);
		HBox Topbar= new HBox(menu, dealer, log);
		layout.setTop(Topbar);
		layout.setLeft(player1);
		layout.setRight(player2);
		return new Scene(layout, 700, 700);
	}


}
