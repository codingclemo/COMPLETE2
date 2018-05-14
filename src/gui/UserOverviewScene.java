package gui;

import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Data.AppState;
import Data.User;
import DataProvider.UserDataProvider;
import Tables.StickerTable;
import Tables.StickerTableGenerator;
import complete.CompleteMain;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView.TableViewSelectionModel;
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

public class UserOverviewScene {

//	Stage UserStage = new Stage();
	
	private Scene scene;
	private TableView<StickerTable> duplicatesTable;
	private ArrayList<String> selectedDuplicateIDs = new ArrayList<String>();
	private ArrayList<String> selectedRequiredIDs = new ArrayList<String>();
	
//	private Text stickerTextID = new Text();
	
//	public VBox rootPane;
//	private HBox framePane;
//	private VBox userInfo;
//	private GridPane userDetails;
//	private VBox userStatistics;
//	private VBox interactionPane;
	
	
	public UserOverviewScene(Stage stage) {
		VBox rootPane = new VBox();
		scene = new Scene(rootPane);
		MenuBar menuBar = new MainMenu(stage).getMenuBar();
		((VBox) scene.getRoot()).getChildren().addAll(menuBar);
		
		HBox framePane = new HBox();
			VBox userInfo = new VBox();
				GridPane userDetails = new GridPane();
				VBox userStatistics = new VBox();
			VBox interactionPane = new VBox();
				VBox inputPane = new VBox();
				HBox showPane = new HBox();
					VBox viewListPane = new VBox();
					VBox removeButtonPane = new VBox();
		
		
					
		// add textfield for adding Stickers
		
		TextField stickersTextField = new TextField();
		stickersTextField.setPromptText("Enter Sticker ID numbers (i.e. 001, 035, 212, ..)");
		HBox buttonPane = new HBox();
		Button addToDuplicates = new Button("Add to duplicates");
		Button addToRequired = new Button("Add to required");
		buttonPane.getChildren().addAll(addToDuplicates, addToRequired);
		inputPane.getChildren().addAll(stickersTextField, buttonPane);
		inputPane.setPadding(new Insets(50,0,30,0));
		
		addToDuplicates.setOnAction( e -> {
			int[] inputArray = getArrayFromInput(stickersTextField.getText());
			for (int i = 0; i < inputArray.length; i++) {
				if(inputArray[i] > 0 && inputArray[i] < 10 ) {    ///check if stickerID is valid
					System.out.print( Integer.toString(inputArray[i]) + " ");
					AppState.getInstance().getUser().addStickerAvailable(inputArray[i]);
					
				}
			}
			System.out.print("\n");
		});
		
		// add user information to user Details
		try {
			User u = AppState.getInstance().getUser();

			userDetails.add(new Text(u.getUsername()), 0, 0);
			userDetails.add(new Text("id: " + u.getUserID()), 0, 1);
			userDetails.add(new Text("from: " + u.getRegionID()), 0, 2);
			String stickerList = new String();
			stickerList = "av. Stickers:";
			int[] stickerArray = u.getStickersAvailable();
			for (int i = 0; i < stickerArray.length; i++) {
				if (stickerArray[i] != 0) {
					stickerList += i + " ";
				}
				
			}
			userDetails.add(new Text(stickerList), 0, 3);
			
		} catch (NullPointerException e) {
			// TODO: handle exception
		}

		userDetails.setMinWidth(100);
		userDetails.setPadding(new Insets(50, 100, 50, 100));
		
		// add ListView to display Stickers that are available for trading
		ListView<String> duplicateStickers = new ListView<String>();
		ListView<String> requiredStickers = new ListView<String>();
		
		// show duplicate stickers
		Label duplicatesLabel = new Label("My duplicates");
		duplicatesLabel.setPadding(new Insets(0, 0, 5, 0));
		
		// add duplicates Table
//		duplicatesTable = StickerTableGenerator.generateTable();		
//		AppState.getInstance().getDatabase().createDummyStickerData();
//		duplicatesTable.setItems(AppState.getInstance().getDatabase().getDummyStickerData());
//		duplicatesTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
//		addListenersForTable(duplicatesTable);
		
		duplicatesTable = StickerTableGenerator.generateTable();
		try {
			User u = AppState.getInstance().getUser();
			
			UserDataProvider udp = (UserDataProvider) AppState.getInstance().getDatabase();
//	    	udp.getObservableStickers(u.getStickersAvailable());
//	    	AppState.getInstance().getDatabase().getObservableStickers(u.getStickersAvailable());

			duplicatesTable.setItems(AppState.getInstance().getDatabase().getObservableStickers(u.getStickersAvailable()));
//			duplicatesTable.setItems( udp.getObservableStickers(u.getStickersAvailable()) );
		} catch (NullPointerException e) {

		}
		duplicatesTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		addListenersForTable(duplicatesTable);
		
		
		
		
		
		
		
		// show required stickers
		Label requiredLabel = new Label("My needs");
		requiredLabel.setPadding(new Insets(20, 0, 5, 0));
		ObservableList<String> requiredItems = FXCollections.observableArrayList(
				"Hans", "Petee", "Albi", "Hum", "Radovanovic", "Dum1", "Dum2", "Dum3");
		requiredStickers.setItems(requiredItems);
		requiredStickers.setMinSize(500, 150);
		requiredStickers.setMaxSize(500, 150);
		requiredStickers.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		// add remove Button
		Button removeButton = new Button("Remove");
		removeButtonPane.getChildren().add(removeButton);
//		removeButtonPane.getChildren().add(stickerTextID);  
		removeButtonPane.setAlignment(Pos.CENTER_RIGHT);
		removeButtonPane.setPadding(new Insets(0, 0, 0, 20));
		
		
//		removeButton.setOnAction( e -> {
//			int stickerID = 1;
			
//			for (String stickerID: selectedDuplicateIDs) {
//				// remove stickerID from User.duplicatesArray
//				AppState.getInstance().getUser().removeStickerAvailable(Integer.parseInt(stickerID));
//			}
//			AppState.getInstance().getUser().removeStickerAvailable(Integer.parseInt(stickerID));
//			AppState.getInstance().getDatabase().removeSticker(stickerID);
//		});
		
		
	
//		removeButtonPane.setBackground(new Background ( new BackgroundFill(Color.BURLYWOOD, null, null)));
		viewListPane.getChildren().addAll(duplicatesLabel, duplicatesTable, requiredLabel, requiredStickers);
		
		showPane.getChildren().addAll(viewListPane, removeButtonPane);
		
		interactionPane.getChildren().addAll(inputPane, showPane);
		
		userInfo.getChildren().add(userDetails);
		userInfo.getChildren().add(userStatistics);
		framePane.getChildren().add(userInfo);
		framePane.getChildren().add(interactionPane);

		rootPane.getChildren().add(framePane);
        stage.setMinHeight(600);
        stage.setMinWidth(1000);
        stage.setResizable(false);
	}

	public Scene getScene() {
		return scene;
	}
	
	@SuppressWarnings("unused")
	private void addListenersForTable(TableView<StickerTable> stTable) {
		stTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
			@Override
			public void changed(ObservableValue<?> observableValue, Object oldValue, Object newValue) {
				if (stTable.getSelectionModel().getSelectedItem() != null) {
					TableViewSelectionModel<StickerTable> selection = stTable.getSelectionModel();
					
//					StickerTable st = (StickerTable) selection.getSelectedItem();
					ObservableList<StickerTable> selectedItems = stTable.getSelectionModel().getSelectedItems();
					selectedDuplicateIDs.clear();
//					ArrayList<String> selectedIDs = new ArrayList<String>();
					for (StickerTable row: selectedItems) {
						selectedDuplicateIDs.add( Integer.toString(row.getID()) );
					}
					System.out.println(selectedDuplicateIDs);
					
				}
			}
		});
	}
	
	private int[] getArrayFromInput(String s) {
		int[] result = new int[200];
		int i = 0;
		s = s.replace(" ", "");
		Scanner scanner = new Scanner(s);
		scanner.useDelimiter(",");
		while (scanner.hasNextInt()) {
		    result[i] = scanner.nextInt();
		    i++;
		}
		return result;
	};
}
