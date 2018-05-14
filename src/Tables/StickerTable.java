package Tables;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class StickerTable {
	private SimpleIntegerProperty ID;
	private SimpleStringProperty teamName;
	private SimpleStringProperty name;
	private SimpleIntegerProperty amount;
	
	public StickerTable(int id, String teamName, String name, int amount) {
		this.ID = new SimpleIntegerProperty(id);
		this.teamName = new SimpleStringProperty(teamName);
		this.name = new SimpleStringProperty(name);
		this.amount = new SimpleIntegerProperty(amount);
	}

	public int getID() {
		return ID.get();
	}
	
	public String getTeamName() {
		return teamName.get();
	}
	
	public String getName() {
		return name.get();
	}
	
	public int getAmount() {
		return amount.get();
	}
	
	public void setID(int i) {
		ID.set(i);
	}
	
	public void setTeamName(String s) {
		teamName.set(s);
	}
	
	public void setName(String s) {
		name.set(s);
	}
	
	public void setAmount(int i) {
		amount.set(i);
	}
	
	public void increaseAmount() {
		amount.set(amount.get() + 1);
	}
	
	public void decreaseAmount() {
		if (amount.get() > 0)
			amount.set(amount.get() - 1);
	}
}
