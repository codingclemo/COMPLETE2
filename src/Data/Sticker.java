package Data;

public class Sticker {
	
	private int ID;
	private int teamID;
	private String name = new String();
	
	public Sticker(int id, int teamID, String name) {
		this.ID = id;
		this.teamID = teamID;
		this.name = name;
	}
	
	public void setID(int id) { 		this.ID = id; }
	public void setTeamID(int id) {		this.teamID = id;	}
	public void setName(String name) {	this.name = name;	}
	public int getID() { 				return ID; }
	public int getTeamID() { 			return teamID; }
	public String getName() { 			return name;	}
	
}