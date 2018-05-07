package gui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class UserProfile extends Pane {
	public HBox framePane;
	public VBox userInfo;
	public HBox userDetails;
	public VBox userStatistics;
	public VBox interactionPane;
	
	private static final UserProfile instance = new UserProfile (); // singleton
	
	public static UserProfile getInstance() {
		return instance;
	}
	
	public UserProfile() {
		framePane = new HBox();
		userInfo = new VBox();
		userDetails = new HBox();
		userStatistics = new VBox();
		interactionPane = new VBox();
		
		// add ListView to display Stickers that are available for trading
		ListView<String> availableStickers = new ListView<String>();
		ObservableList<String> items = FXCollections.observableArrayList(
				"Ronaldo", "Pele", "Alaba", "Hummels", "Arnautovic");
		availableStickers.setItems(items);
		availableStickers.setMinSize(500, 150);
		availableStickers.setMaxSize(500, 150);
		
		interactionPane.getChildren().add(availableStickers);
		
		// add user information to user Details

		userInfo.getChildren().add(userDetails);
		userInfo.getChildren().add(userStatistics);
		framePane.getChildren().add(userInfo);
		framePane.getChildren().add(interactionPane);
	}
	
	public Pane getUserProfile() {
		return framePane;
	}
}
