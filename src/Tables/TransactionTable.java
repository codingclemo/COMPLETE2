package Tables;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TransactionTable {
	private SimpleIntegerProperty ID;
	private SimpleStringProperty userGiver;
	private SimpleStringProperty userTaker;
	private SimpleIntegerProperty stickerID;
	private SimpleStringProperty stickerName;
	private SimpleStringProperty status;
	
	public TransactionTable(int ID, String userGiver, String userTaker, int stickerID, String stickerName) {
		this.ID = new SimpleIntegerProperty(ID);
		this.userGiver = new SimpleStringProperty(userGiver);
		this.userTaker = new SimpleStringProperty(userTaker);
		this.stickerID = new SimpleIntegerProperty(stickerID);
		this.stickerName = new SimpleStringProperty(stickerName);
		this.status = new SimpleStringProperty("offering");  // change to "changed", "accepted" or "declined"
	}
	
	public int getID() {
		return ID.get();
	}
	
	public String getUserGiver() {
		return userGiver.get();
	}
	
	public String getUserTaker() {
		return userTaker.get();
	}
	
	public int getStickerID() {
		return stickerID.get();
	}
	
	public String getStickerName() {
		return stickerName.get();
	}
	
	public String getStatus() {
		return status.get();
	}
	
	public void setID(int i) {
		this.ID.set(i);
	}
	
	public void setUserGiver(String i) {
		this.userGiver.set(i);
	}
	
	public void setUserTaker(String i) {
		this.userTaker.set(i);
	}
	
	public void setStickerID(int i) {
		this.stickerID.set(i);
	}
	
	public void setStickerName(String s) {
		this.stickerName.set(s);
	}
	
	public void setStatus(String s) {
		this.status.set(s);
	}
	
}
