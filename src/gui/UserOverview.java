package gui;
import javafx.scene.text.Text;
import Data.AppState;
import Data.User;
import complete.CompleteMain;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

public class UserOverview {

//    public MenuBar createMenuBar(Stage stage) {
//
//        // Main screen -> User Overview
//        MenuItem myProfile = new MenuItem("My Profile");
//        MenuItem StickerExchange = new MenuItem("Sticker Exchange");
////        MenuItem exitItem = new MenuItem("Close");
//
//        Menu completeMenu = new Menu("COMPLETE");
//        completeMenu.getItems().add(myProfile);
//        completeMenu.getItems().add(StickerExchange);
////        completeMenu.getItems().add(exitItem);
//
//        
//        StickerExchange.setOnAction( e -> {
////        	StickerExchange se = new StickerExchange();
//        	Scene seScene = new StickerExchange().getScene();
//        	stage.setScene(seScene);
//        });
//        
//        // close the application
////        exitItem.setOnAction(e -> {
////        	Platform.exit();
////            System.exit(0);
////        });
//
//        MenuBar menuBar = new MenuBar();
//        menuBar.getMenus().add(completeMenu);
//
//        return menuBar;
//    }

	Stage UserStage = new Stage();
	
	public Scene overview;
	
	public VBox rootPane;
	public HBox framePane;
	public VBox userInfo;
	public GridPane userDetails;
	public VBox userStatistics;
	public VBox interactionPane;
	
	
	public UserOverview(Stage owner) {
		rootPane = new VBox();
		framePane = new HBox();
		userInfo = new VBox();
		userDetails = new GridPane();
		userStatistics = new VBox();
		interactionPane = new VBox();
		overview = new Scene(rootPane);
		
		// add user information to user Details
		User u = AppState.getInstance().getUser();
		userDetails.add(new Text(u.getUsername()), 0, 0);
		userDetails.add(new Text("id: " + u.getUserID()), 0, 1);
		userDetails.add(new Text("from: " + u.getRegionID()), 0, 2);
		
		userDetails.setMinWidth(100);
		userDetails.setPadding(new Insets(50, 100, 50, 100));
		
		// add ListView to display Stickers that are available for trading
		ListView<String> availableStickers = new ListView<String>();
		
		// get userData for stickers
		ObservableList<String> items = FXCollections.observableArrayList(
				"Ronaldo", "Pele", "Alaba", "Hummels", "Arnautovic");
		availableStickers.setItems(items);
		availableStickers.setMinSize(500, 150);
		availableStickers.setMaxSize(500, 150);
		
		interactionPane.getChildren().add(availableStickers);
		
		userInfo.getChildren().add(userDetails);
		userInfo.getChildren().add(userStatistics);
		framePane.getChildren().add(userInfo);
		framePane.getChildren().add(interactionPane);
        
		
		
		
		 // Create MenuBar and add to Scene
		MenuBar menuBar = new MainMenu(UserStage).getMenuBar();
//		MenuBar menuBar = createMenuBar(UserStage);
		((VBox) overview.getRoot()).getChildren().addAll(menuBar);
		
		
		rootPane.getChildren().add(framePane);
		
		UserStage.setScene(overview);
		UserStage.setMinHeight(500);
		UserStage.setMinWidth(800);
		UserStage.initOwner(owner);
        UserStage.setResizable(false);
        
        
	}
	
	public void show() {
		UserStage.show();
	}
	
	public Stage getStage() {
		return UserStage;
	}
	
	public Scene getScene() {
		return overview;
	}
	
}
