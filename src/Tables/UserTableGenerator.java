package Tables;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class UserTableGenerator {

	@SuppressWarnings("unchecked")
	public static TableView<UserTable> generateTable() {
		TableView<UserTable> table = new TableView();
		table.setEditable(true);
		
		TableColumn<UserTable, String> usernameCol = new TableColumn<>("User");
		usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
		
		
		TableColumn<UserTable, Integer> regionIDCol = new TableColumn<>("Region");
		regionIDCol.setCellValueFactory(new PropertyValueFactory<>("regionID"));
		
		TableColumn<UserTable, Integer> tradeIndexCol = new TableColumn<>("Trading potential");
		tradeIndexCol.setCellValueFactory(new PropertyValueFactory<>("tradeIndex"));
		
		table.getColumns().addAll(usernameCol, regionIDCol, tradeIndexCol);

		return table;
	}
}