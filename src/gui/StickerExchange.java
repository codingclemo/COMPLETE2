package gui;

import Data.AppState;
import Data.User;
import DataProvider.UserDataProvider;
import Tables.UserTable;
import Tables.StickerTableGenerator;
import Tables.UserTable;
import Tables.UserTableGenerator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
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
	
	public StickerExchange(Stage stage) {
		VBox rootPane = new VBox();
		scene = new Scene(rootPane);
		MenuBar menuBar = new MainMenu(stage).getMenuBar();
		((VBox) scene.getRoot()).getChildren().addAll(menuBar);

		VBox framePane = new VBox();
			VBox UserListPane = new VBox();
			VBox TradePane = new VBox();
				HBox TradePaneTop = new HBox();
				HBox TradePaneBottom = new HBox();
		
		rootPane.getChildren().add(framePane);
		framePane.getChildren().addAll(UserListPane, TradePane);
		TradePane.getChildren().addAll(TradePaneTop, TradePaneBottom);
		rootPane.setBackground( new Background( new BackgroundFill(Color.AQUAMARINE, null, null)));
		
		
		// add table to show other users
		usersTable = UserTableGenerator.generateTable();
		try {
			User u = AppState.getInstance().getUser();
			UserDataProvider udp = (UserDataProvider) AppState.getInstance().getDatabase();
//			usersTable.setItems(AppState.getInstance().getDatabase().getObservableStickers(stickerArray));
		} catch (NullPointerException e) {}
		usersTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		addListenersForTable(usersTable);
		Label userTableLabel = new Label("Users in your region:");
		UserListPane.getChildren().addAll(userTableLabel, usersTable);
		UserListPane.setMaxWidth(500);
	}
	
	public Scene getScene() {
		return this.scene;
	}
	

	@SuppressWarnings("unused")
	private void addListenersForTable(TableView<UserTable> usersTable) {
		usersTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
			@Override
			public void changed(ObservableValue<?> observableValue, Object oldValue, Object newValue) {
				if (usersTable.getSelectionModel().getSelectedItem() != null) {
					TableViewSelectionModel<UserTable> selection = usersTable.getSelectionModel();
					
//					StickerTable st = (StickerTable) selection.getSelectedItem();
					ObservableList<UserTable> selectedItems = usersTable.getSelectionModel().getSelectedItems();
					
				}
			}
		});
	}
}
