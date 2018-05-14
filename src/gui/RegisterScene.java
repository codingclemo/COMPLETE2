package gui;


import javafx.scene.text.Text;
import Data.AppState;
import Data.User;
import DataProvider.IUserDataProvider;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

public class RegisterScene{

	public Stage window;
	Stage registrationStage = new Stage();
	
	public Scene scene;
	
	String username;
	String pw;
	int regionID;
	
	
    TextField fnTextField;
    TextField lnTextField;
    TextField usernameTextField;
    PasswordField passwordTextField;
    TextField adressTextField;
    TextField cityTextField;
    TextField regionTextField;
    TextField countryTextField;  
	
    
	public RegisterScene(Stage stage) {
		
		
		Button nextButton;
	    Button backButton;
	     
        VBox rootPane = new VBox();
        
        GridPane headerPane = new GridPane();  // probably there is a nicer solution to this
        headerPane.setId("header-pane");
        headerPane.setAlignment(Pos.CENTER);
        headerPane.setBackground(new Background ( new BackgroundFill(Color.AQUA, null, null)));
        
        Text t = new Text();
        t.setText("Registration - Onboard the Panini exchange train");
        headerPane.add(t, 0, 2);
        headerPane.setMinHeight(100);
        
        GridPane registrationForm = new GridPane();
        registrationForm.setId("registration-form");
        registrationForm.setMinHeight(50);
        registrationForm.setBackground(new Background ( new BackgroundFill(Color.BEIGE, null, null)));
    
        Label fnLabel = new Label("First name");
        fnTextField = new TextField ();
        
        Label lnLabel = new Label("Last name");
        lnTextField = new TextField ();
        
        Label usernameLabel = new Label("E-Mail");
        usernameTextField = new TextField ();
        
        Label passwordLabel = new Label("Password");
        passwordTextField = new PasswordField();
        
        Label adressLabel = new Label("Adress");
        adressTextField = new TextField ();
        
        Label cityLabel = new Label("City");
        cityTextField = new TextField (); 
        
        Label regionLabel = new Label("Region");
        regionTextField = new TextField ();   
        
        Label countryLabel = new Label("Country");
        countryTextField = new TextField ();  

        
        

        registrationForm.add(fnLabel,           0, 0);
        registrationForm.add(fnTextField,       1, 0);
        registrationForm.add(lnLabel,           0, 1);
        registrationForm.add(lnTextField,       1, 1);
        registrationForm.add(usernameLabel,     0, 2);
        registrationForm.add(usernameTextField, 1, 2);
        
        registrationForm.add(adressLabel,       0, 3);
        registrationForm.add(adressTextField,   1, 3);
        registrationForm.add(cityLabel,         0, 4);
        registrationForm.add(cityTextField,     1, 4);
        registrationForm.add(regionLabel,       0, 5);
        registrationForm.add(regionTextField,   1, 5);
        registrationForm.add(countryLabel,      0, 6);
        registrationForm.add(countryTextField,  1, 6);
        registrationForm.add(passwordLabel,     0, 8);
        registrationForm.add(passwordTextField,     1, 8);
        
        registrationForm.setVgap(5);
        registrationForm.setHgap(20);       
//	      registrationForm.setGridLinesVisible(true);
        registrationForm.setHalignment(fnLabel, HPos.RIGHT);
        registrationForm.setHalignment(lnLabel, HPos.RIGHT);
        registrationForm.setHalignment(usernameLabel, HPos.RIGHT);
        registrationForm.setHalignment(adressLabel, HPos.RIGHT);
        registrationForm.setHalignment(cityLabel, HPos.RIGHT);
        registrationForm.setHalignment(regionLabel, HPos.RIGHT);
        registrationForm.setHalignment(countryLabel, HPos.RIGHT);

        registrationForm.setAlignment(Pos.CENTER);
        registrationForm.setMaxWidth(400);
        
        nextButton = new Button("Next"); nextButton.setId("button-next"); 
        nextButton.setOnAction( e -> {
			System.out.println("Next button pressed");
			
			IUserDataProvider db = AppState.getInstance().getDatabase();
			
			// check if user exists
			// if user exists -> error
			// else
			// add new user to db
			// set user to this new user //get by username
			try {
				if (db.getUserByUsername(usernameTextField.getText()) != null) {
					//error
					Alert alert = new Alert(AlertType.ERROR, "username already taken", ButtonType.CANCEL);
					alert.showAndWait();
				} else if (usernameTextField.getText() == "" || passwordTextField.getText() == "" || regionTextField.getText() == "") {
					Alert alert = new Alert(AlertType.ERROR, "username is empty", ButtonType.CANCEL);
					alert.showAndWait();
				} else {
					db.addUser(usernameTextField.getText(), passwordTextField.getText(), Integer.parseInt(regionTextField.getText()));
					User u = db.getUserByUsername(usernameTextField.getText());
					AppState.getInstance().setUser(u);
					db.printData();
					stage.setScene(new UserOverviewScene(stage).getScene());
				}
			} catch (NumberFormatException e2) {
				// TODO: handle exception
			}

			
        });
        
        
        
        backButton = new Button("Back"); backButton.setId("button-back");
        backButton.setOnAction( e -> {
        	stage.setScene(new LoginScene(stage).getScene()); 
        });
        
        HBox buttonsPane = new HBox();
        buttonsPane.getChildren().add(backButton);
        buttonsPane.getChildren().add(nextButton);
        buttonsPane.setSpacing(50);
        buttonsPane.setPrefSize(400, 70);
        buttonsPane.setAlignment(Pos.BOTTOM_CENTER);
        buttonsPane.setBackground(new Background ( new BackgroundFill(Color.BLUEVIOLET, null, null)));
        
        rootPane.setId("registration-pane");
        rootPane.getChildren().addAll(headerPane);
        rootPane.getChildren().addAll(registrationForm);
        rootPane.getChildren().addAll(buttonsPane);
        rootPane.setAlignment(Pos.TOP_CENTER);
        
        
        scene = new Scene(rootPane);
        registrationStage.setResizable(false);
        registrationStage.setScene(scene);
		
	}
	
	
	public Scene getScene() {
		return scene;
	}
	
	private void registerUser() {
		
	}
	
//	@Override
//	public void handle(ActionEvent event) {
//		try {
//			if ( ((Button) event.getSource()).getId().equals("button-back") ) {
//					LoginDialog ld = new LoginDialog(window);
//					ld.show();
//					registrationStage.close();
//			} else if ( ((Button) event.getSource()).getId().equals("button-next") ) {
//		
//				
////				// TODO: addUser to db
//				System.out.println("Next button pressed");
//				
//				IUserDataProvider db = AppState.getInstance().getDatabase();
//				
//				if (db.authenticateUser("Moh", "a")) {
//					User u = db.getUserByUsername("Moh");
//					AppState.getInstance().setUser(u);
//					UserOverview ov = new UserOverview(window);
//				    ov.show();
//				    registrationStage.close();
//				    if (AppState.getInstance().isUserLoggedIn())
//				    	System.out.println("hes logged in...OMG");
//				}
//				
//				UserOverview uo = new UserOverview(window);
//				window.setScene(uo.getScene());
////				registrationStage.close();
//				
//				
////				IUserDataProvider db = AppState.getInstance().getDatabase();
////				
////				User newUser = db.getUserByUsername(usernameTextField.getText());
////				if (newUser != null) {
////					System.out.println("User '"+ usernameTextField.getText() + "' already exists. Please use a different username.");
////				} else {
////
////					if ( db.addUser(usernameTextField.getText(), passwordTextField.getText(), Integer.parseInt(regionTextField.getText())) ) {
////						System.out.println("New user '" + usernameTextField.getText() + "' registered.");
////						
////						User u = db.getUserByUsername(usernameTextField.getText());
////						AppState.getInstance().setUser(u);
////						
////						UserOverview ov = new UserOverview(window);
////					    ov.show();
////					    registrationStage.close();
////					    if (AppState.getInstance().isUserLoggedIn())
////					    	System.out.println("hes registered and logged in...OMG");
////						
////					} else {
////						System.out.println("User '" + usernameTextField.getText() + "' could not be registered.");
////					}
////				}
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//	}
}
