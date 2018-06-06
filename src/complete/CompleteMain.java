package complete;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import Data.AppState;
//import DataProvider.UserDataProvider;
import DataProvider.mysqlDataProvider;
//import gui.LoginDialog;
import gui.LoginScene;
import gui.StickerExchange;
//import gui.UserOverview;
import gui.UserOverviewScene;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


public class CompleteMain extends Application {

	public Stage window;
    public Scene loginScene;
    public Scene registrationScene;
    
    @Override
    public void start(Stage primaryStage) throws Exception { // changed to priStage from primaryStage
        
    	primaryStage.setScene(new UserOverviewScene(primaryStage).getScene());
    	primaryStage.setScene(new LoginScene(primaryStage).getScene());
        primaryStage.setMinWidth(400);
        primaryStage.setMinHeight(400);
        primaryStage.show();
    }

    public static void main(String[] args) {
    	
    	// set the global application database and state
    	mysqlDataProvider mysqldp = new mysqlDataProvider();

    	AppState.getInstance().setDatabase(mysqldp);
    	mysqldp.createDummyData();
    	mysqldp.createStickerDB();
    	mysqldp.printData();
//    	udp.authenticateUser("Moh", "a");
    	
		
        launch(args);
    }
}
