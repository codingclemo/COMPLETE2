package DataProvider;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import Data.Sticker;
import Data.Stickers;
import Data.User;
import Tables.StickerTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UserDataProvider implements IUserDataProvider{
	
	private int userID = 0;
	private TreeMap<String, User> userTable = new TreeMap<>();
	private ObservableList<StickerTable> dummyStickerData;  // to load in TableView for Stickers
	private ArrayList<StickerTable> stickerDB = new ArrayList<>();

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
    	
    	userTable.get("Moh").addStickerAvailable(1);
//    	userTable.get("Moh").addStickerAvailable(1);
//    	userTable.get("Moh").addStickerAvailable(3);
//    	userTable.get("Moh").addStickerAvailable(4);
//    	userTable.get("Moh").addStickerAvailable(4);
//    	userTable.get("Moh").addStickerAvailable(4);
//    	userTable.get("Moh").addStickerAvailable(9);
    	
    	userTable.get("Albi").addStickerAvailable(1);
    	
    }
    
    public void createStickerDB() {
    	stickerDB.add( new StickerTable(0, "Zero", "Placeholder", 0) );
    	stickerDB.add( new StickerTable(1, "Austria", "Alaba", 0) );
    	stickerDB.add( new StickerTable(2, "Austria", "Arnautovic", 0) );
    	stickerDB.add( new StickerTable(3, "Assabaidschan", "Radovanovic", 0) );
    	stickerDB.add( new StickerTable(4, "Ästland", "Ave", 0) );
    	stickerDB.add( new StickerTable(5, "Fronkreich", "Jean-Paul", 0) );
    	stickerDB.add( new StickerTable(6, "asasfad", "Alaba", 0) );
    	stickerDB.add( new StickerTable(7, "asdasffaa", "Arnautovic", 0) );
    	stickerDB.add( new StickerTable(8, "Assaabaidschan", "Radovanovic", 0) );
    	stickerDB.add( new StickerTable(9, "asd", "Ave", 0) );
//    	stickerDB.add( new StickerTable(10, "fsfa", "Jean-Paul", 0) );
    }
    
    public ObservableList<StickerTable> getObservableStickers(int[] stickerArray) {
    	ObservableList<StickerTable> stickerList = FXCollections.observableArrayList();
    	for (int i = 0; i < stickerArray.length; i++) {
    		if (stickerArray[i] != 0) {
    			StickerTable st = stickerDB.get(i);
//    			st.setAmount(99);
    			st.setAmount(stickerArray[i]);  /// why is it doubled here?
    			stickerList.add(st);
  
    		}
    	}
    	return stickerList;
    }
    
    public ObservableList<StickerTable> getDummyStickerData(){
    	return dummyStickerData;
    }
    
    public void removeSticker(int stickerID) {
    	dummyStickerData.get(stickerID).decreaseAmount();
    }
    
    public void printData() {
    	System.out.println("db contains: ");
    	for(Map.Entry<String, User> i: userTable.entrySet()) {
    		System.out.println("\tuser: " + i.getValue().getUsername() + " pw: " + i.getValue().getPassword());
    	}
    }
    

	
}
