package DataProvider;

import Data.User;
import Tables.StickerTable;
import javafx.collections.ObservableList;

public interface IUserDataProvider {

	// user
	public User getUserByUsername(String username);
    public boolean addUser(String userame, String password, int regionId);
    public boolean authenticateUser(String username, String password);
    
    public void createDummyData();
    public void createStickerDB();
    public ObservableList<StickerTable> getDummyStickerData();
    public void removeSticker(int stickerID);  // needs refactoring
    public ObservableList<StickerTable> getObservableStickers(int[] stickerArray);
    
    
    public void printData();
}
