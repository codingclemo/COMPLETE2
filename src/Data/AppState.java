package Data;

//import DataProvider.IUserDataProvider;
import DataProvider.ImysqlDataProvider;
import DataProvider.mysqlDataProvider;

public final class AppState {
	
	 private static final AppState instance = new AppState();
	 
	    private User user;
	    private ImysqlDataProvider db; 
	    
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
	    
	    public void setDatabase (mysqlDataProvider mysqldp) {
	        this.db = mysqldp;
	    }
	    
	    public ImysqlDataProvider getDatabase() {
	        return db;
	    }
}
