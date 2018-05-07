package gui;
import javafx.scene.text.Text;
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
	
	public HBox framePane;
	public VBox userInfo;
	public HBox userDetails;
	public VBox userStatistics;
	public VBox interactionPane;
	
	public UserOverview(Window owner) {
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
        
        
		Scene overview = new Scene(framePane);
		
		UserStage.setScene(overview);
//		UserStage.initModality(Modality.WINDOW_MODAL);
//		UserStage.initStyle(StageStyle.UTILITY);
		UserStage.setMinHeight(400);
		UserStage.setMinWidth(400);
		UserStage.initOwner(owner);
//        UserStage.setResizable(false);
	}
	
	public void show() {
		UserStage.show();
	}
	
}
