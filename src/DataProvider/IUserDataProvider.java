package DataProvider;

import Data.User;

public interface IUserDataProvider {

	// user
	public User getUserByUsername(String username);
    public boolean addUser(String userame, String password, int regionId);
    public boolean authenticateUser(String username, String password);
    
    public void createDummyData();
    public void printData();
}
