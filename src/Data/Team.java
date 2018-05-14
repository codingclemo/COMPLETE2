package Data;

public class Team {
	private String name;
	private int teamID;
	private int[] teamMembers;
	
	public Team() {
		name = new String();
		teamID = 0;
		teamMembers = null;
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTeamID() {
		return teamID;
	}
	public void setTeamID(int teamID) {
		this.teamID = teamID;
	}
	public int[] getTeamMembers() {
		return teamMembers;
	}
	public void setTeamMembers(int[] teamMembers) {
		this.teamMembers = teamMembers;
	}
	
	
}
