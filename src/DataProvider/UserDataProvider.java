package DataProvider;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import Data.Sticker;
import Data.Stickers;
import Data.User;

public class UserDataProvider implements IUserDataProvider{
	
	int userID = 0;
	TreeMap<String, User> userTable = new TreeMap<>();
	
	Stickers stickerDB = new Stickers();
	
	public UserDataProvider() {
		createDummyData();
	}
	
	@Override
	public User getUserByUsername(String username) {
		if (userTable.containsKey(username)) {
			return userTable.get(username);
		}
		return null;
	}
	
    @Override
    public boolean addUser(String username, String password, int regionId) {
        if (userTable.containsKey(username)) {
            return false; 
        }
        userID++;
        User user = new User(userID, username, password, regionId);
        userTable.put(username, user);
        return true; 
    }
    
    @Override
    public boolean authenticateUser(String username, String password) {
    	// does user exist?
    	if (userTable.containsKey(username)) {
    		// is password correct?
    		if (userTable.get(username).getPassword().equals(password)) {
    			System.out.println( username + " logged in successfully with password: " + password );
    			return true;
    		}
    		System.out.println("Wrong password: " + password + " for user: " + username);
    	} else {
    		System.out.println("user: " + username + " does not exist");
    	}
    	return false;
    }
    
    public void createDummyData() {
    	addUser("Clemens", "a", 0);
    	addUser("Albi", "a", 0);
    	addUser("Moh", "a", 0);
    	addUser("Manuel", "a", 0);
    	addUser("Flo", "a", 0);
    	
    	User dummy = this.getUserByUsername("Moh");
//    	dummy.stickersAvailable.add(0);
//    	dummy.stickersAvailable.add(0);
//    	dummy.stickersAvailable.add(2);
//    	dummy.stickersAvailable.add(2);
//    	dummy.stickersAvailable.add(2);
//    	dummy.stickersAvailable.add(s);
    	
    	Sticker s0 = new Sticker(0, 0, "David Alaba");
    	Sticker s1 = new Sticker(1, 1, "Zlatan Ibrahimovic");
    	Sticker s2 = new Sticker(2, 1, "This Otherguy");
    	Sticker s3 = new Sticker(3, 2, "Player Number3");
    	Sticker s4 = new Sticker(4, 2, "Also Team2");
    	stickerDB.add(s0);
    	stickerDB.add(s1);
    	stickerDB.add(s2);
    	stickerDB.add(s3);
    	stickerDB.add(s4);
    	
    }
	
    public void printData() {
    	System.out.println("db contains: ");
    	for(Map.Entry<String, User> i: userTable.entrySet()) {
    		System.out.println("\tuser: " + i.getValue().getUsername() + " pw: " + i.getValue().getPassword());
    	}
    }
    

	
}
