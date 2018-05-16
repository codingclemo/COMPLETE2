package gui;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Map;

import Data.AppState;
import Data.User;
import DataProvider.UserDataProvider;
import Tables.UserTable;
import Tables.StickerTable;
import Tables.StickerTableGenerator;
import Tables.TransactionTable;
import Tables.TransactionTableGenerator;
import Tables.UserTable;
import Tables.UserTableGenerator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StickerExchange {
	
	private Scene scene;
	private TableView<UserTable> usersTable;
	private TableView<StickerTable> otherAvailableTable;
	private TableView<StickerTable> otherRequiresTable;
	private TableView<StickerTable> ownAvailableTable;
	private TableView<StickerTable> ownRequiresTable;
	private TableView<TransactionTable> transactionTable;
	private User selectedUser;
	private User currentUser;
	
	private ArrayList<String> selectedOtherAvailables = new ArrayList<String>();
	private ArrayList<String> selectedOwnAvailables = new ArrayList<String>();
	private int[] stickersArray;
	
	public StickerExchange(Stage stage) {
		VBox rootPane = new VBox();
		scene = new Scene(rootPane);
		MenuBar menuBar = new MainMenu(stage).getMenuBar();
		((VBox) scene.getRoot()).getChildren().addAll(menuBar);

		VBox framePane = new VBox();
			VBox UserListPane = new VBox();
			VBox TradePane = new VBox();
				HBox TradePaneTop = new HBox();
					VBox TradeTopLeft = new VBox();
					VBox TradeTopMiddle = new VBox();
					VBox TradeTopRight = new VBox();
				HBox TradePaneBottom = new HBox();
					VBox TradeBottomLeft = new VBox();
					VBox TradeBottomMiddle = new VBox();
					VBox TradeBottomRight = new VBox();
		
		rootPane.getChildren().add(framePane);
		framePane.getChildren().addAll(UserListPane, TradePane); 
		TradePane.getChildren().addAll(TradePaneTop, TradePaneBottom);
		TradePaneTop.getChildren().addAll(TradeTopLeft, TradeTopMiddle, TradeTopRight);
		TradePaneBottom.getChildren().addAll(TradeBottomLeft, TradeBottomMiddle, TradeBottomRight);
		
		
		rootPane.setBackground( new Background( new BackgroundFill(Color.AQUAMARINE, null, null)));
		framePane.setAlignment(Pos.CENTER);
		TradePane.setAlignment(Pos.CENTER);
		TradePaneTop.setAlignment(Pos.CENTER);
		TradePaneBottom.setAlignment(Pos.CENTER);
		TradePane.setPadding(new Insets(20,0,20,0));
		TradePaneBottom.setPadding(new Insets(20,0,20,0));
		
		currentUser = AppState.getInstance().getUser();
		
		// add table to show other users
		usersTable = UserTableGenerator.generateTable();
		try {
//			User u = AppState.getInstance().getUser();
			UserDataProvider udp = (UserDataProvider) AppState.getInstance().getDatabase();
			usersTable.setItems(AppState.getInstance().getDatabase().getObservableUsers());
		} catch (NullPointerException e) {}
		usersTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		usersTable.setMaxSize(500,150);
		usersTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		addListenersForUserTable(usersTable);
		Label userTableLabel = new Label("Users in your region:");
		UserListPane.getChildren().addAll(userTableLabel, usersTable);
		UserListPane.setMaxWidth(500);
		UserListPane.setAlignment(Pos.TOP_LEFT);
		UserListPane.setPadding(new Insets(50,0,20,0));
		
		
		// add tables for TradePaneTop
//		selectedUser = AppState.getInstance().getDatabase().getUserByUsername("Clemens");
		
		otherAvailableTable = StickerTableGenerator.generateTable(); 
		try {
			UserDataProvider udp = (UserDataProvider) AppState.getInstance().getDatabase();
			otherAvailableTable.setItems(AppState.getInstance().getDatabase().getObservableStickers(selectedUser.getStickersAvailable()));
		} catch (NullPointerException e) {}
		otherAvailableTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		addListenersForStickerTable(otherAvailableTable);
		otherAvailableTable.setMinSize(350,100);
		otherAvailableTable.setMaxSize(500,150);
		otherAvailableTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		Label otherAvailableLabel = new Label("Other user offers:");
		
		
		otherRequiresTable = StickerTableGenerator.generateTable();
		otherRequiresTable = StickerTableGenerator.generateTable();
		try {
			User u = AppState.getInstance().getUser();
			UserDataProvider udp = (UserDataProvider) AppState.getInstance().getDatabase();
			otherRequiresTable.setItems(AppState.getInstance().getDatabase().getObservableStickers(selectedUser.getStickersNeeded()));
		} catch (NullPointerException e) {}
		otherRequiresTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		otherRequiresTable.setMinSize(350,100);
		otherRequiresTable.setMaxSize(500,150);
		otherRequiresTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		otherRequiresTable.setSelectionModel(null);
		Label otherRequiresLabel = new Label("Other user needs:");
		
		
		
		ownRequiresTable = StickerTableGenerator.generateTable();
		try {
			User u = AppState.getInstance().getUser();
			UserDataProvider udp = (UserDataProvider) AppState.getInstance().getDatabase();
			ownRequiresTable.setItems(AppState.getInstance().getDatabase().getObservableStickers(u.getStickersNeeded()));
		} catch (NullPointerException e) {}
		ownRequiresTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		ownRequiresTable.setMinSize(350,100);
		ownRequiresTable.setMaxSize(500,150);
		ownRequiresTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		ownRequiresTable.setSelectionModel(null);
		Label ownRequiresLabel = new Label("Your needs:");
		
		
		ownAvailableTable = StickerTableGenerator.generateTable();
		try {
			User u = AppState.getInstance().getUser();
			UserDataProvider udp = (UserDataProvider) AppState.getInstance().getDatabase();
			ownAvailableTable.setItems(AppState.getInstance().getDatabase().getObservableStickers(u.getStickersAvailable()));
		} catch (NullPointerException e) {}
		ownAvailableTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		addListenersForStickerTable(ownAvailableTable);	
		ownAvailableTable.setMinSize(350,100);
		ownAvailableTable.setMaxSize(500,150);
		ownAvailableTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		Label ownAvailableLabel = new Label("Your offer:");
		
		
//		ArrayList<TransactionTable> transDB = AppState.getInstance().getDatabase().
		
		transactionTable = TransactionTableGenerator.generateTable();
		ObservableList<TransactionTable> transactionsDB = FXCollections.observableArrayList();
		try {
//			transactionsDB = AppState.getInstance().getDatabase().getObservableTransactions(currentUser);
			transactionTable.setItems(transactionsDB);
		} catch (NullPointerException e) {}
		transactionTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		transactionTable.setMinSize(350,100);
		transactionTable.setMaxSize(500,150);
		transactionTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		transactionTable.getColumns().remove(0); // hide transactionID
		Label transactionLabel = new Label("Exchange proposal:");

		// configure buttons
		Button toRight = new Button("->");
		toRight.setOnAction( e -> {
			int[] inputArray = getArrayFromArrayList(selectedOtherAvailables);
			int[] selectedUserArray = selectedUser.getStickersAvailable();
			int[] currentUserArray = currentUser.getStickersNeeded();
			for (int i = 0; i < inputArray.length; i++) {
				if (inputArray[i] != 0) {
					selectedUser.removeStickerAvailable(inputArray[i]);
					if (currentUserArray[inputArray[i]] != 0) {
						currentUser.removeStickerNeeded(inputArray[i]);
					} else {
						currentUser.addStickerAvailable(inputArray[i]);
					}
					AppState.getInstance().getDatabase().addTransaction(selectedUser, currentUser, inputArray[i]);	
				}
			}
			//refresh Tables
			otherAvailableTable.setItems(AppState.getInstance().getDatabase().getObservableStickers(selectedUserArray));
			ownRequiresTable.setItems(AppState.getInstance().getDatabase().getObservableStickers(currentUserArray));
			transactionTable.setItems(AppState.getInstance().getDatabase().getObservableTransactions(currentUser));
			otherAvailableTable.refresh();
			ownRequiresTable.refresh();
			transactionTable.refresh();
		});
		
		Button toLeft = new Button("<-");
		toLeft.setOnAction( e -> {
			int[] inputArray = getArrayFromArrayList(selectedOwnAvailables);
			int[] selectedUserArray = selectedUser.getStickersNeeded();
			int[] currentUserArray = currentUser.getStickersAvailable();
			for (int i = 0; i < inputArray.length; i++) {
				if (inputArray[i] != 0) {
					currentUser.removeStickerAvailable(inputArray[i]);
					if (currentUserArray[inputArray[i]] != 0) {
						selectedUser.removeStickerNeeded(inputArray[i]);
					} else {
						selectedUser.addStickerAvailable(inputArray[i]);
					}
					AppState.getInstance().getDatabase().addTransaction(currentUser, selectedUser, inputArray[i]);	
				}
			}
			//refresh Tables
			ownAvailableTable.setItems(AppState.getInstance().getDatabase().getObservableStickers(currentUserArray));
			otherRequiresTable.setItems(AppState.getInstance().getDatabase().getObservableStickers(selectedUserArray));
			transactionTable.setItems(AppState.getInstance().getDatabase().getObservableTransactions(currentUser));
			ownAvailableTable.refresh();
			otherRequiresTable.refresh();
			transactionTable.refresh();
		});

		
		TradeTopLeft.getChildren().addAll(otherAvailableLabel, otherAvailableTable);
		TradeTopMiddle.getChildren().add(toRight); TradeTopMiddle.setAlignment(Pos.CENTER);
		TradeTopRight.getChildren().addAll(ownRequiresLabel, ownRequiresTable);
		TradeBottomLeft.getChildren().addAll(otherRequiresLabel, otherRequiresTable);
		TradeBottomMiddle.getChildren().add(toLeft); TradeBottomMiddle.setAlignment(Pos.CENTER);
		TradeBottomRight.getChildren().addAll(ownAvailableLabel, ownAvailableTable);

		TradePane.getChildren().addAll(transactionLabel, transactionTable);
		
		
	}
	
	public Scene getScene() {
		return this.scene;
	}
	

	@SuppressWarnings("unused")
	private void addListenersForUserTable(TableView<UserTable> usersTable) {
		usersTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
			@Override
			public void changed(ObservableValue<?> observableValue, Object oldValue, Object newValue) {
				if (usersTable.getSelectionModel().getSelectedItem() != null) {
					TableViewSelectionModel<UserTable> selection = usersTable.getSelectionModel();
					
//					StickerTable st = (StickerTable) selection.getSelectedItem();
					ObservableList<UserTable> selectedItems = usersTable.getSelectionModel().getSelectedItems();
					
					String un = selectedItems.get(0).getUsername();
					selectedUser = AppState.getInstance().getDatabase().getUserByUsername(un);
					System.out.println(selectedUser.getUsername() + " is now selected");
					otherAvailableTable.setItems(AppState.getInstance().getDatabase().getObservableStickers(selectedUser.getStickersAvailable()));
					otherRequiresTable.setItems(AppState.getInstance().getDatabase().getObservableStickers(selectedUser.getStickersNeeded()));
//					otherAvailableTable.refresh();
//					otherRequiresTable.refresh();
				}
			}
		});
	}
	
	@SuppressWarnings("unused")
	private void addListenersForStickerTable(TableView<StickerTable> stTable) {
		stTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
			@Override
			public void changed(ObservableValue<?> observableValue, Object oldValue, Object newValue) {
				if (stTable.getSelectionModel().getSelectedItem() != null) {
					TableViewSelectionModel<StickerTable> selection = stTable.getSelectionModel();
					
//					otherAvailableTable.setItems(
//						AppState.getInstance().getDatabase().getObservableStickers(
//							selectedUser.getStickersAvailable()));
					
					
//					StickerTable st = (StickerTable) selection.getSelectedItem();
					ObservableList<StickerTable> selectedItems = stTable.getSelectionModel().getSelectedItems();
					selectedOtherAvailables.clear();
					selectedOwnAvailables.clear(); // was added later
					
//					selectedUser.
					
//					ArrayList<String> selectedIDs = new ArrayList<String>();
					for (StickerTable row: selectedItems) {
//						row.setAmount(i);
						selectedOtherAvailables.add( Integer.toString(row.getID()) );
						selectedOwnAvailables.add( Integer.toString(row.getID()) ); // was added later
					}
					System.out.println("selectedOthersAvailables:" + selectedOtherAvailables);
					System.out.println("selectedOwnAvailables:" + selectedOwnAvailables);// was added later
				}
			}
		});
	}
	
	private int[] getArrayFromArrayList(ArrayList<String> inputArray) {
		int[] result = new int[200];
		for (int i = 0; i < inputArray.size(); i++) {
			result[i] = Integer.parseInt(inputArray.get(i));
		}
		return result;
	}
	
}
