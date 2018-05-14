package gui;

import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StickerExchange {
	private Scene scene;
	
	public StickerExchange(Stage stage) {
		
		VBox rootPane = new VBox();
		scene = new Scene(rootPane);
		
		// add menu
		MenuBar menuBar = new MainMenu(stage).getMenuBar();
		((VBox) scene.getRoot()).getChildren().addAll(menuBar);
		
		// add content
		rootPane.getChildren().add(new Text("Welcome to the Sticker Exchange"));
		rootPane.setBackground( new Background( new BackgroundFill(Color.AQUAMARINE, null, null)));
		
	}
	
	public Scene getScene() {
		return this.scene;
	}
}
