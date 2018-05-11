package complete;

import Data.AppState;
import DataProvider.UserDataProvider;
import gui.LoginDialog;
import gui.LoginScene;
import gui.StickerExchange;
import gui.UserOverview;
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
        
//        window = primaryStage;
//        window.setMinWidth(400);
//        window.setMinHeight(400);
//        
//        window.setTitle("Complete");
//        
//        LoginDialog loginDialog = new LoginDialog(window);
//        loginDialog.show();

    	primaryStage.setScene(new LoginScene(primaryStage).getScene());
        primaryStage.setMinWidth(400);
        primaryStage.setMinHeight(400);
        primaryStage.show();
    }

    public static void main(String[] args) {
    	
    	// set the global application database and state
    	UserDataProvider udp = new UserDataProvider();

    	AppState.getInstance().setDatabase(udp);
    	udp.createDummyData();
    	udp.printData();
    	
        launch(args);
    }
}
