package gui;

import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class MainMenu {
	MenuBar menuBar;
	
	public MainMenu(Stage stage) {
	    // Main screen -> User Overview
	    MenuItem myProfile = new MenuItem("My Profile");
	    MenuItem StickerExchange = new MenuItem("Sticker Exchange");
	
	    Menu completeMenu = new Menu("COMPLETE");
	    completeMenu.getItems().add(myProfile);
	    completeMenu.getItems().add(StickerExchange);
	
	
	    myProfile.setOnAction( e -> {
	    	Scene uoScene = new UserOverview(stage).getScene();
	    	stage.setScene(uoScene);
	    	System.out.println("Set scene My Profile");
	    });
	    
	    StickerExchange.setOnAction( e -> {
	    	Scene seScene = new StickerExchange(stage).getScene();
	    	stage.setScene(seScene);
	    	System.out.println("Set scene Sticker Exchange");
	    });
	    
	    // close the application
//	    exitItem.setOnAction(e -> {
//	    	Platform.exit();
//	        System.exit(0);
//	    });
	
	    menuBar = new MenuBar();
	    menuBar.getMenus().add(completeMenu);
	}

	public MenuBar getMenuBar() {
		return menuBar;
	}

}
//
//public MenuBar createMenuBar(Stage stage) {
//
//    // Main screen -> User Overview
//    MenuItem myProfile = new MenuItem("My Profile");
//    MenuItem StickerExchange = new MenuItem("Sticker Exchange");
////    MenuItem exitItem = new MenuItem("Close");
//
//    Menu completeMenu = new Menu("COMPLETE");
//    completeMenu.getItems().add(myProfile);
//    completeMenu.getItems().add(StickerExchange);
////    completeMenu.getItems().add(exitItem);
//
//    
//    StickerExchange.setOnAction( e -> {
////    	StickerExchange se = new StickerExchange();
//    	Scene seScene = new StickerExchange().getScene();
//    	stage.setScene(seScene);
//    });
//    
//    // close the application
////    exitItem.setOnAction(e -> {
////    	Platform.exit();
////        System.exit(0);
////    });
//
//    MenuBar menuBar = new MenuBar();
//    menuBar.getMenus().add(completeMenu);
//
//    return menuBar;
//}