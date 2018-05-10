package Data;

public class User {
	
	private String username;
	private String password;
	int userID;
	int regionID;
	
	private String firstname;
	private String lastname;
	private String adress;
	private String City;
	private String Country;
	
	private int[] stickersAvailable = new int[10];
	private int[] stickersNeeded = new int[10];
	
//	public User() {
//        this.username = null; 
//        this.password = null; // some hash magic needs to be added here
//        this.userID = -1;
//        this.regionID = -1;
//	}
	
    public User(int id, String username, String password, int regionID) {
        this.username = username; 
        this.password = password; // some hash magic needs to be added here
        this.userID = id;
        this.regionID = regionID;
    }
	
    //getters
    public String getUsername() {
    	return username;
    }
    
    public String getPassword() {
    	return password;
    }
    
    public int getUserID() {
    	return userID;
    }
    
    public int getRegionID() {
    	return regionID;
    }
    
    //setters
    public void setUsername(String name) {
    	this.username= name;
    }
    
    public void setPassword(String pw) {
    	this.password = pw;
    }
    
    public void setUserID(int newID) {
    	this.userID = newID;
    }
    
    public void setRegionID(int regionID) {
    	this.regionID = regionID;
    }
    
    public void addStickerAvailable(int stickerID) {
    	stickersAvailable[stickerID]++;
    }
    
	public void removeStickerAvailable(int stickerID) {
		if (stickersAvailable[stickerID] > 0) {
			this.stickersAvailable[stickerID]--;
		} else {
			System.out.println("Stock of sticker No. " + stickerID +  " is already empty.");
		}
	}
	
	public int[] getStickersAvailable() {
		return stickersAvailable;
	}
    
    public void addStickerNeeded(int stickerID) {
    	stickersAvailable[stickerID]++;
    }
    
	public void removeStickerNeeded(int stickerID) {
		if (stickersAvailable[stickerID] > 0) {
			this.stickersAvailable[stickerID]--;
		} else {
			System.out.println("Stock of sticker No. " + stickerID +  " is already empty.");
		}
	}
	
	public int[] getStickersNeeded() {
		return stickersNeeded;
	}
	
	//TODO: Add getters and setters for optional fields (fn, ln, country...)




}