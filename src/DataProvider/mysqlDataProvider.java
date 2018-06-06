package DataProvider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import Data.AppState;
import Data.Sticker;
import Data.Stickers;
import Data.User;
import Tables.StickerTable;
import Tables.TransactionTable;
import Tables.UserTable;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
//import swe4.jdbc.dal.Person;
//import swe4.jdbc.dal.DataAccessException;
//import swe4.jdbc.dal.Person;
import DataProvider.DataAccessException;



public class mysqlDataProvider implements ImysqlDataProvider{
	  
	private static final String CONNECTION_STRING = "jdbc:mysql://localhost/complete";
	private static final String USER_NAME = "root";
	private static final String PASSWORD = null;
	
	  private Connection connection;
	  private String connectionString;
	  private String userName;
	  private String password;
	
	private int userID = 0;
	private TreeMap<String, User> userTable = new TreeMap<>();
	private ObservableList<StickerTable> dummyStickerData;  // to load in TableView for Stickers
	private ArrayList<StickerTable> stickerDB = new ArrayList<>();
	private ArrayList<TransactionTable> transactionsDB = new ArrayList<>();
	private int transactionID = 0;

	public mysqlDataProvider() {
//		createDummyData();
		connectionString = CONNECTION_STRING;
		userName = USER_NAME;
		password = PASSWORD;
	}
	
  public Connection getConnection() throws DataAccessException {
	    try {
	      if (connection == null)
	        connection = DriverManager.getConnection(connectionString, userName,
	            password);
	      return connection;
	    } catch (SQLException ex) {
	      throw new DataAccessException(
	          "Can't establish connection to database. SQLException: "
	              + ex.getMessage());
	    }
	  }

	
	private Collection<User> getUserWhere(String query, Object... args)
	      throws DataAccessException {
	    Collection<User> c = new ArrayList<User>();
	    try (PreparedStatement statement = getConnection().prepareStatement(
	        "SELECT * FROM users " + query)) {
	      for (int i = 0; i < args.length; i++)
	        statement.setObject(i + 1, args[i]);
	      try (ResultSet resultSet = statement.executeQuery()) {
	        while (resultSet.next()) {
	          c.add(new User(
	              resultSet.getInt("id"),
	              resultSet.getString("username"),
	              resultSet.getString("password"),
	              resultSet.getInt("regionId")));
	        }
	      } // includes finally resultSet.close();
	    } catch (SQLException ex) {
	      throw new DataAccessException("SQLException: " + ex.getMessage());
	    } // includes finally statement.close()
	    return c;
	  }

	@Override
	public User getUserByUsername(String username) throws DataAccessException {
		
		try (CompleteDao completeDao = new CompleteDaoJdbc(CONNECTION_STRING, USER_NAME, PASSWORD)) {
			Collection<User> c = getUserWhere("WHERE username = ?", username);
		    Iterator<User> it = c.iterator();
		    return it.hasNext() ? it.next() : null;
	    } catch (SQLException e) {
	      while (e != null) {
	        System.out.println(e);
	        e = e.getNextException();
	      }
	    } catch (Exception e) {
	      e.printStackTrace();
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
    	addUser("Albi", "a", 1);
    	addUser("Moh", "a", 1);
    	addUser("Manuel", "a", 1);
    	addUser("Flo", "a", 0);
    	
    	userTable.get("Moh").addStickerAvailable(1);
    	userTable.get("Moh").addStickerAvailable(1);
    	userTable.get("Moh").addStickerAvailable(3);
    	userTable.get("Moh").addStickerAvailable(4);
    	userTable.get("Moh").addStickerAvailable(4);
    	userTable.get("Moh").addStickerNeeded(5);
    	userTable.get("Moh").addStickerNeeded(8);
    	userTable.get("Moh").addStickerNeeded(9);
    	
    	userTable.get("Albi").addStickerAvailable(3);
    	userTable.get("Albi").addStickerAvailable(5);
    	userTable.get("Albi").addStickerAvailable(7);
    	userTable.get("Albi").addStickerAvailable(7);
    	userTable.get("Albi").addStickerAvailable(9);
    	userTable.get("Albi").addStickerNeeded(1);
    	userTable.get("Albi").addStickerNeeded(2);
    	userTable.get("Albi").addStickerNeeded(4);
    	
    	userTable.get("Clemens").addStickerAvailable(7);
    	userTable.get("Clemens").addStickerAvailable(9);
    	userTable.get("Clemens").addStickerAvailable(9);
    	userTable.get("Clemens").addStickerNeeded(1);
    	userTable.get("Clemens").addStickerNeeded(3);
    	userTable.get("Clemens").addStickerNeeded(8);
    	
    	transactionsDB.add( new TransactionTable(666, "moh", "albi", 2, this.getStickerName(2)));
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
    
    public String getStickerName(int ID) {
    	try {
    		if (ID > 0 && ID <= 682)
    			return stickerDB.get(ID).getName();
		} catch (IndexOutOfBoundsException e) {
			
		}
    	return "unkown";
    }
    
    public ObservableList<StickerTable> getObservableStickers(int[] stickerArray) {
    	ObservableList<StickerTable> stickerList = FXCollections.observableArrayList();
    	for (int i = 0; i < stickerArray.length; i++) {
    		if (stickerArray[i] != 0 && stickerArray[i] <= 682) {
    			StickerTable st = stickerDB.get(i);
    			st.setAmount(stickerArray[i]); 
    			stickerList.add(st);
    		}
    	}
    	return stickerList;
    }
    
    public ObservableList<UserTable> getObservableUsers() {
    	ObservableList<UserTable> userList = FXCollections.observableArrayList();
    	String username;
		int regionID;
		int tradeIndex;
		User currentUser = AppState.getInstance().getUser();
    	for(Map.Entry<String, User> i: userTable.entrySet()) {
    		
    		tradeIndex = calculateTradeIndex(i.getValue(), currentUser);
    		if (tradeIndex != 0) {
    			username = i.getValue().getUsername();
        		regionID = i.getValue().getRegionID();
        		
        		userList.add(new UserTable(username, regionID, tradeIndex));
    		}
    	}
    	
    	return userList;
    }
    
    private int calculateTradeIndex(User giver, User taker) {
    	int matchCount = 0;
    	int[] giverStickers = giver.getStickersAvailable();
    	int[] takerStickers = taker.getStickersNeeded();
    	
    	for (int i = 0; i < giver.getStickersAvailable().length; i++) {
			if (giverStickers[i] != 0 && takerStickers[i] != 0)
				matchCount++;
		}
    	return matchCount;
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
    
    public void addTransaction(User Giver, User Taker, int stickerID) {
    	transactionsDB.add( new TransactionTable(transactionID, Giver.getUsername(), Taker.getUsername(), stickerID, this.getStickerName(stickerID)));
    	transactionID++;
    }
    
    public ObservableList<TransactionTable> getObservableTransactions(User u) {
    	ObservableList<TransactionTable> transactionList = FXCollections.observableArrayList();
    	for (TransactionTable tt : transactionsDB) {
    		
    		// TODO: This selection is not working and I dont know why
//			if (tt.getUserGiver().equals(u.getUsername()) ) { // ||  tt.getUserTaker().equals(u.getUsername()) ) {
//				transactionList.add(tt);
//			}
			transactionList.add(tt);
		}
    	
    	return transactionList;
    }
}
