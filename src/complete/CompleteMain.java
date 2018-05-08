package complete;

import Data.AppState;
import DataProvider.UserDataProvider;
import gui.LoginDialog;
import gui.LoginPane;
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
    
    
    
    private MenuBar createMenuBar(Stage stage) {
        // PreferencesDialog dialog = new PreferencesDialog(stage);

        // my cards
        MenuItem myCardsItem = new MenuItem("My Cards");
        MenuItem marketplaceItem = new MenuItem("Marketplace");
        // prefItem.setOnAction(e -> mycards.show());
        MenuItem exitItem = new MenuItem("Exit ");

        Menu completeMenu = new Menu("Complete");
        completeMenu.getItems().add(myCardsItem);
        completeMenu.getItems().add(marketplaceItem);
        completeMenu.getItems().add(exitItem);

        // close the application when exit is clicked
        exitItem.setOnAction(e -> {
            Platform.exit();
            System.exit(0);
        });

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(completeMenu);

        return menuBar;
    }
    
    
    @Override
    public void start(Stage primaryStage) throws Exception { // changed to priStage from primaryStage
        
        window = primaryStage;
        window.setMinWidth(400);
        window.setMinHeight(400);
        
        window.setTitle("Complete");
        
        LoginDialog loginDialog = new LoginDialog(window);
        loginDialog.show();
 
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
