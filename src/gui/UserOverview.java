package gui;
import javafx.scene.text.Text;
import Data.AppState;
import Data.User;
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

	Stage UserStage = new Stage();
	
	public Scene overview;
	
	public HBox framePane;
	public VBox userInfo;
	public GridPane userDetails;
	public VBox userStatistics;
	public VBox interactionPane;
	
	public UserOverview(Window owner) {
		framePane = new HBox();
		userInfo = new VBox();
		userDetails = new GridPane();
		userStatistics = new VBox();
		interactionPane = new VBox();
		
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
        
        
		overview = new Scene(framePane);
		
		UserStage.setScene(overview);
		UserStage.setMinHeight(500);
		UserStage.setMinWidth(800);
		UserStage.initOwner(owner);
        UserStage.setResizable(false);
	}
	
	public void show() {
		UserStage.show();
	}
	
}
