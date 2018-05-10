package gui;

import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StickerExchange {
	Scene scene;
	
	public StickerExchange(Stage stage) {
		
		VBox rootPane = new VBox();
		scene = new Scene(rootPane);
		
		// add menu
		MenuBar menuBar = new MainMenu(stage).getMenuBar();
		((VBox) scene.getRoot()).getChildren().addAll(menuBar);
		
		// add content
		rootPane.getChildren().add(new Text("Welcome to the Sticker Exchange"));
		
		
	}
	
	public Scene getScene() {
		return this.scene;
	}
}
