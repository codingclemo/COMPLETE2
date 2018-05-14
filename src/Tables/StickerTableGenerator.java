package Tables;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class StickerTableGenerator {
	
	@SuppressWarnings("unchecked")
	public static TableView<StickerTable> generateTable() {
		TableView<StickerTable> table = new TableView<>();
		table.setEditable(true);

		TableColumn<StickerTable, Integer> idCol = new TableColumn<>("No.");
		idCol.setCellValueFactory(new PropertyValueFactory<>("ID"));

		TableColumn<StickerTable, String> teamCol = new TableColumn<>("Team");
		teamCol.setCellValueFactory(new PropertyValueFactory<>("teamName"));
		
		TableColumn<StickerTable, String> nameCol = new TableColumn<>("Name");
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		TableColumn<StickerTable, Integer> amountCol = new TableColumn<>("Amount");
		amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));

		table.getColumns().addAll(idCol, teamCol, nameCol, amountCol);

		return table;
	}
}
