package Data;

import DataProvider.IUserDataProvider;

public final class AppState {

	 private static final AppState instance = new AppState();
	 
	    private User user;
	    private IUserDataProvider db; 
	    
	    public static AppState getInstance() {
	        return instance;
	    }

	    private AppState() {}

	    public void setUser(User user) {
	        this.user = user;
	    }
	    
	    public User getUser() {
	        return this.user;
	    }
	    
	    public boolean isUserLoggedIn() {
	        return (user != null);
	    }
	    
	    public void setDatabase (IUserDataProvider db) {
	        this.db = db;
	    }
	    
	    public IUserDataProvider getDatabase() {
	        return db;
	    }
}
