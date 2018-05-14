package Tables;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class UserTable {
	
	private SimpleStringProperty username;
	private SimpleIntegerProperty regionID;
	private SimpleIntegerProperty tradeIndex;
	
	public UserTable(String username, int regionID, int tradeIndex) {
		this.username = new SimpleStringProperty(username);
		this.regionID = new SimpleIntegerProperty(regionID);
		this.tradeIndex = new SimpleIntegerProperty(tradeIndex);
	}

	public String getUsername() {
		return username.get();
	}

	public void setUsername(String username) {
		this.username.set(username);
	}

	public int getRegionID() {
		return regionID.get();
	}

	public void setRegionID(int regionID) {
		this.regionID.set(regionID);
	}

	public int getTradeIndex() {
		return tradeIndex.get();
	}

	public void setTradeIndex(int tradeIndex) {
		this.tradeIndex.set(tradeIndex);
	}
	
	
	
}
