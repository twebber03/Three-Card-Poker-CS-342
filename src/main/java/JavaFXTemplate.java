import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
import javafx.fxml.FXMLLoader;


public class JavaFXTemplate extends Application {

	Button exit=new Button("Exit");
	Button deal=new Button("Deal"), play=new Button("Play"), fold=new Button("Fold"), freshstart=new Button("Fresh Start"), look=new Button("New Look");
	Button sdeal=new Button("Deal"), splay=new Button("Play"), sfold=new Button("Fold");
	TextField title=new TextField(), name1=new TextField(), name2=new TextField(), totalwinning=new TextField(), stotalwinning=new TextField(), dealer=new TextField(), log=new TextField();
    TextField pair=new TextField(), ante=new TextField(), spair=new TextField(), sante=new TextField();
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
		//FXMLLoader loader=new FXMLLoader(getClass().getResource("Example.fxml"));
		//Parent load=loader.load();
		Image picture=new Image("https://upload.wikimedia.org/wikipedia/commons/thumb/e/e2/A_studio_image_of_a_hand_of_playing_cards._MOD_45148377.jpg/220px-A_studio_image_of_a_hand_of_playing_cards._MOD_45148377.jpg");
		Button button2=new Button("Play");
		TextField title=new TextField(), message=new TextField();
		BorderPane layout=new BorderPane();
        button2.setStyle("-fx-background-color: green");
        exit.setStyle("-fx-background-color: red");
		primaryStage.setTitle("Welcome Screen");
		exit.setMinWidth(200);
		button2.setMinWidth(300);
		title.setText("THREE CARD POKER");
		title.setStyle("-fx-font-size: 36px");
		message.setText("Welcome to 3 Card Poker! Get ready to trust your luck and skill! Are you ready to play!");
        message.setStyle("-fx-font-size: 20px");

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
		ImageView view= new ImageView(picture);
		view.setFitWidth(300);
		view.setFitHeight(300);
		VBox pic=new VBox();
		pic.getChildren().add(view);
		HBox buttonbox=new HBox(20, exit, button2);
		VBox titlesection=new VBox(pic, title);
		layout.setTop(titlesection);
		layout.setCenter(message);
		layout.setBottom(buttonbox);
		layout.setStyle("-fx-background-color: lightblue");
		Scene window=new Scene(layout, 700, 700);
		button2.setOnAction(e->{
			primaryStage.setScene(play());
			primaryStage.setTitle("Play Screen");
		});
		exit.setOnAction(e-> primaryStage.close());
		primaryStage.setScene(window);
		primaryStage.show();
	}


	private Scene play(){
		//TextField title=new TextField(), name1=new TextField(), name2=new TextField(), totalwinning=new TextField(), stotalwinning=new TextField(), dealer=new TextField(), log=new TextField();
		BorderPane layout=new BorderPane();
		title.setText("Hi, this is the play screen");
		name1.setText("Player 1");
		name2.setText("Player 2");
		pair.setText("Pair Plus $");
		ante.setText("Ante $ ");
		spair.setText("Pair Plus $");
		sante.setText("Ante $ ");
		dealer.setText("Dealer");
		dealer.setStyle("-fx-font-size: 30px");
		dealer.setMinWidth(400);
		log.setText("Play Log");
		log.setMinSize(200, 300);
		log.setStyle("-fx-font-size: 25px");
		totalwinning.setText("Total Winnings: $");
		stotalwinning.setText("Total Winnings: $");
		HBox buttons=new HBox(deal, play, fold), sbuttons=new HBox(sdeal, splay, sfold);
		VBox player1=new VBox(pair, ante, name1, buttons, totalwinning);
		VBox player2=new VBox(spair, sante, name2, sbuttons, stotalwinning);
		VBox menu=new VBox(exit, freshstart, look);
		HBox Topbar= new HBox(menu, dealer, log);
		layout.setStyle("-fx-background-color: lightblue");
		look.setOnAction(e->layout.setStyle("-fx-background-color: lightgreen"));
		layout.setTop(Topbar);
		layout.setLeft(player1);
		layout.setRight(player2);
		return new Scene(layout, 700, 700);
	}


}
