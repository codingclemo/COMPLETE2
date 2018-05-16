package Tables;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TransactionTableGenerator {

		@SuppressWarnings("unchecked")
		public static TableView<TransactionTable> generateTable() {
			TableView<TransactionTable> table = new TableView();
			table.setEditable(true);
			
			TableColumn<TransactionTable, Integer> IDCol = new TableColumn<>("TransactionID");
			IDCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
			
			TableColumn<TransactionTable, String> userGiverCol = new TableColumn<>("Giver");
			userGiverCol.setCellValueFactory(new PropertyValueFactory<>("userGiver"));
			
			TableColumn<TransactionTable, String> userTakerCol = new TableColumn<>("Taker");
			userTakerCol.setCellValueFactory(new PropertyValueFactory<>("userTaker"));		
			
			TableColumn<TransactionTable, Integer> stickerIDCol = new TableColumn<>("StickerID");
			stickerIDCol.setCellValueFactory(new PropertyValueFactory<>("stickerID"));
			
			TableColumn<TransactionTable, String> stickerNameCol = new TableColumn<>("Name");
			stickerNameCol.setCellValueFactory(new PropertyValueFactory<>("stickerName"));	
			
			TableColumn<TransactionTable, String> statusCol = new TableColumn<>("Status");
			statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
			
			table.getColumns().addAll(IDCol, userGiverCol, userTakerCol, stickerIDCol, stickerNameCol, statusCol);

			return table;
		}
}
