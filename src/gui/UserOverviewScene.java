package gui;

import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Data.AppState;
import Data.User;
import DataProvider.mysqlDataProvider;
import Tables.StickerTable;
import Tables.StickerTableGenerator;
import Tables.TransactionTable;
import Tables.TransactionTableGenerator;
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

	private Scene scene;
	private TableView<StickerTable> duplicatesTable;
	private TableView<StickerTable> requiredTable;
	private TableView<TransactionTable> myTransactionsTable;
	private ArrayList<String> selectedDuplicateIDs = new ArrayList<String>();
	private ArrayList<String> selectedRequiredIDs = new ArrayList<String>();
	private ArrayList<String> selectedTransactions = new ArrayList<String>();

	private User currentUser;

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
		VBox transactionsPane = new VBox();
		HBox transButtonPane = new HBox();

		currentUser = AppState.getInstance().getUser();

		// framePane.setAlignment(Pos.CENTER);

		// add textfield for adding Stickers
		TextField stickersTextField = new TextField();
		stickersTextField.setPromptText("Enter Sticker ID numbers (i.e. 001, 035, 212, ..)");
		HBox buttonPane = new HBox();
		Button addToDuplicates = new Button("Add to duplicates");
		Button addToRequired = new Button("Add to required");
		buttonPane.getChildren().addAll(addToDuplicates, addToRequired);
		inputPane.getChildren().addAll(stickersTextField, buttonPane);
		inputPane.setPadding(new Insets(50, 0, 30, 0));

		addToDuplicates.setOnAction(e -> {
			int[] inputArray = getArrayFromInput(stickersTextField.getText());
			for (int i = 0; i < inputArray.length; i++) {
				if (inputArray[i] > 0 && inputArray[i] < 682) { /// check if stickerID is valid
					try {
						// System.out.print( Integer.toString(inputArray[i]) + " ");
						currentUser.addStickerAvailable(inputArray[i]);
					} catch (IndexOutOfBoundsException e2) {
					}
				}
			}
			System.out.print("\n");
			// get updated data from db
			duplicatesTable.setItems(
					AppState.getInstance().getDatabase().getObservableStickers(currentUser.getStickersAvailable()));
			duplicatesTable.refresh();

			System.out.print("\n");
			int[] stickArray = currentUser.getStickersAvailable();
			System.out.print("After pressing AvailabeStickersInDB: ");
			for (int i = 0; i < stickArray.length; i++) {
				if (stickArray[i] != 0)
					System.out.print(stickArray[i] + " ");
			}
		});

		addToRequired.setOnAction(e -> {
			int[] inputArray = getArrayFromInput(stickersTextField.getText());
			for (int i = 0; i < inputArray.length; i++) {
				if (inputArray[i] > 0 && inputArray[i] < 682) { /// check if stickerID is valid
					System.out.print(Integer.toString(inputArray[i]) + " ");
					currentUser.addStickerNeeded(inputArray[i]);
				}
			}
			System.out.print("\n");
			// get updated data from db
			requiredTable.setItems(
					AppState.getInstance().getDatabase().getObservableStickers(currentUser.getStickersNeeded()));
			requiredTable.refresh();

			System.out.print("\n");
			int[] stickArray = currentUser.getStickersNeeded();
			System.out.print("After pressing NeededStickersInDB: ");
			for (int i = 0; i < stickArray.length; i++) {
				if (stickArray[i] != 0)
					System.out.print(stickArray[i] + " ");
			}
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

		// show duplicate stickers
		Label duplicatesLabel = new Label("My duplicates");
		duplicatesLabel.setPadding(new Insets(0, 0, 5, 0));

		// add duplicates Table
		duplicatesTable = StickerTableGenerator.generateTable();
		try {
			// User u = AppState.getInstance().getUser();
			mysqlDataProvider udp = (mysqlDataProvider) AppState.getInstance().getDatabase();
			duplicatesTable.setItems(
					AppState.getInstance().getDatabase().getObservableStickers(currentUser.getStickersAvailable()));
		} catch (NullPointerException e) {
		}
		duplicatesTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		duplicatesTable.setMinSize(350, 100);
		duplicatesTable.setMaxSize(500, 150);
		duplicatesTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		addListenersForTable(duplicatesTable);

		// add required Table
		requiredTable = StickerTableGenerator.generateTable();
		try {
			// User u = AppState.getInstance().getUser();
			mysqlDataProvider udp = (mysqlDataProvider) AppState.getInstance().getDatabase();
			System.out.println(" is this here?" + currentUser.getStickersNeeded().toString());
			requiredTable.setItems(
					AppState.getInstance().getDatabase().getObservableStickers(currentUser.getStickersNeeded()));
		} catch (NullPointerException e) {
		}
		requiredTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		requiredTable.setMinSize(350, 100);
		requiredTable.setMaxSize(500, 150);
		requiredTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		addListenersForTable(requiredTable);
		Label requiredLabel = new Label("My needs");
		requiredLabel.setPadding(new Insets(20, 0, 5, 0));

		// ArrayList<TransactionTable> transDB = AppState.getInstance().getDatabase().

		myTransactionsTable = TransactionTableGenerator.generateTable();
		ObservableList<TransactionTable> transactionsDB = FXCollections.observableArrayList();
		try {
			// transactionsDB =
			// AppState.getInstance().getDatabase().getObservableTransactions(currentUser);
			myTransactionsTable.setItems(transactionsDB);
			myTransactionsTable.setItems(AppState.getInstance().getDatabase().getObservableTransactions(currentUser));
		} catch (NullPointerException e) {
		}
		myTransactionsTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		myTransactionsTable.setMinSize(350, 100);
		myTransactionsTable.setMaxSize(500, 150);
		myTransactionsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		// myTransactionsTable.getColumns().remove(0); // hide transactionID
		Label myTransactionsLabel = new Label("My trades:");

		// add remove Button
		Button removeButtonAvailable = new Button("Remove");
		Button removeButtonNeeded = new Button("Remove");
		removeButtonPane.getChildren().addAll(removeButtonAvailable, removeButtonNeeded);
		removeButtonPane.setAlignment(Pos.CENTER_RIGHT);
		removeButtonPane.setPadding(new Insets(0, 0, 0, 20));

		removeButtonAvailable.setOnAction(e -> {
			int[] inputArray = getArrayFromArrayList(selectedDuplicateIDs);

			for (int i = 0; i < inputArray.length; i++) {
				if (inputArray[i] > 0 && inputArray[i] < 682) { /// check if stickerID is valid
					System.out.print(Integer.toString(inputArray[i]) + " ");
					currentUser.removeStickerAvailable(inputArray[i]);
				}
			}
			System.out.print("\n");
			// get updated data from db
			duplicatesTable.setItems(
					AppState.getInstance().getDatabase().getObservableStickers(currentUser.getStickersAvailable()));
			duplicatesTable.refresh();

			System.out.print("\n");
			int[] stickArray = currentUser.getStickersAvailable();
			System.out.print("After pressing AvailabeStickersInDB: ");
			for (int i = 0; i < stickArray.length; i++) {
				if (stickArray[i] != 0)
					System.out.print(stickArray[i] + " ");
			}
		});

		// add remove Button
		removeButtonNeeded.setOnAction(e -> {
			int[] inputArray = getArrayFromArrayList(selectedRequiredIDs);

			for (int i = 0; i < inputArray.length; i++) {
				if (inputArray[i] > 0 && inputArray[i] < 682) { /// check if stickerID is valid
					System.out.print(Integer.toString(inputArray[i]) + " ");
					currentUser.removeStickerNeeded(inputArray[i]);
				}
			}
			System.out.print("\n");
			// get updated data from db
			requiredTable.setItems(
					AppState.getInstance().getDatabase().getObservableStickers(currentUser.getStickersNeeded()));
			requiredTable.refresh();

			System.out.print("\n");
			int[] stickArray = currentUser.getStickersNeeded();
			System.out.print("After pressing NeededStickersInDB: ");
			for (int i = 0; i < stickArray.length; i++) {
				if (stickArray[i] != 0)
					System.out.print(stickArray[i] + " ");
			}
		});

		// add accept Button
		Button acceptButton = new Button("Accept Trade");
		acceptButton.setOnAction(e -> {
			// TransactionTable tt = AppState.getInstance().getDatabase().// get transaction
			for (int i = 0; i < selectedTransactions.size(); i++) {

			}
		});

		// add decline Button
		Button declineButton = new Button("Decline Trade");

		// removeButtonPane.setBackground(new Background ( new
		// BackgroundFill(Color.BURLYWOOD, null, null)));
		HBox duplicatesPane = new HBox();
		HBox requiredPane = new HBox();
		duplicatesPane.getChildren().addAll(duplicatesTable, removeButtonAvailable);
		duplicatesPane.setAlignment(Pos.CENTER);
		requiredPane.getChildren().addAll(requiredTable, removeButtonNeeded);
		requiredPane.setAlignment(Pos.CENTER);

		viewListPane.getChildren().addAll(duplicatesLabel, duplicatesPane, requiredLabel, requiredPane);
		// viewListPane.getChildren().addAll(duplicatesLabel, duplicatesTable,
		// requiredLabel, requiredTable);
		showPane.getChildren().addAll(viewListPane, removeButtonPane);
		interactionPane.getChildren().addAll(inputPane, showPane);
		transButtonPane.getChildren().addAll(acceptButton, declineButton);

		userInfo.getChildren().addAll(userDetails, userStatistics);
		framePane.getChildren().addAll(userInfo, interactionPane, transactionsPane);
		transactionsPane.getChildren().addAll(myTransactionsLabel, myTransactionsTable, transButtonPane);
		transactionsPane.setAlignment(Pos.CENTER);

		rootPane.getChildren().add(framePane);
		stage.setMinHeight(800);
		stage.setMinWidth(1400);
		stage.setResizable(false);
		stage.setTitle("COMPLETE");
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

					// StickerTable st = (StickerTable) selection.getSelectedItem();
					ObservableList<StickerTable> selectedItems = stTable.getSelectionModel().getSelectedItems();
					selectedDuplicateIDs.clear();
					selectedRequiredIDs.clear(); // was added later
					// ArrayList<String> selectedIDs = new ArrayList<String>();
					for (StickerTable row : selectedItems) {
						// row.setAmount(i);
						selectedDuplicateIDs.add(Integer.toString(row.getID()));
						selectedRequiredIDs.add(Integer.toString(row.getID())); // was added later
					}
					System.out.println("DupID:" + selectedDuplicateIDs);
					System.out.println("ReqID:" + selectedDuplicateIDs);// was added later
				}
			}
		});
	}

	@SuppressWarnings("unused")
	private void addListenersForTransaction(TableView<TransactionTable> ttTable) {
		ttTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
			@Override
			public void changed(ObservableValue<?> observableValue, Object oldValue, Object newValue) {
				if (ttTable.getSelectionModel().getSelectedItem() != null) {
					TableViewSelectionModel<TransactionTable> selection = ttTable.getSelectionModel();

					ObservableList<TransactionTable> selectedItems = ttTable.getSelectionModel().getSelectedItems();
					selectedTransactions.clear();

					for (TransactionTable row : selectedItems) {
						// row.setAmount(i);
						selectedTransactions.add(Integer.toString(row.getID()));
					}
				}
			}
		});
	}

	private int[] getArrayFromInput(String s) {
		int[] result = new int[682];
		int i = 0;
		int dummy = 0;
		s = s.replace(" ", "");
		Scanner scanner = new Scanner(s);
		scanner.useDelimiter(",");
		while (scanner.hasNextInt()) {
			dummy = scanner.nextInt();
			if (dummy != 0 && dummy <= 682) { // check here for in bounds of Sticker Array
				result[i] = dummy;
				i++;
			}
		}
		return result;
	};

	private int[] getArrayFromArrayList(ArrayList<String> inputArray) {
		int[] result = new int[682];
		for (int i = 0; i < inputArray.size(); i++) {
			result[i] = Integer.parseInt(inputArray.get(i));
		}
		return result;
	}
}
