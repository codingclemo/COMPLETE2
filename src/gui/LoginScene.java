package gui;

import javafx.scene.text.Text;
import Data.AppState;
import Data.User;
import DataProvider.IUserDataProvider;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

public class LoginScene {
//public class LoginDialog implements EventHandler<ActionEvent> {

	Stage LoginStage = new Stage();
	
	public VBox credentialsPane;
	private Button loginButton;
    private Button registerButton;
    private Button createTextButton(String id, String caption) {
        Button button = new Button(caption);
        button.setId(id);
        button.setPadding(new Insets(5));
        button.setPrefSize(100, 30);
        return button;
    }
    
    public Stage window;
	
    private TextField usernameTextField;
    private PasswordField passwordTextField;
    
    private Scene scene;
    
	public LoginScene(Stage stage) {
		
//		window = stage;
		VBox rootPane = new VBox();
		VBox welcomePane = new VBox();
        VBox contentPane = new VBox();  // probably there is a nicer solution to this
        contentPane.setId("content-pane");
        rootPane.getChildren().addAll(welcomePane, contentPane);
        scene = new Scene(rootPane);		
        
        Text t = new Text();
        t.setText("Welcome to COMPLETE");
//	        contentPane.add(t, 0, 2);
        contentPane.getChildren().add(t);
        contentPane.setMinSize(400, 100);
        
//        welcomePane = new VBox();
        welcomePane.setId("welcome-pane");
        welcomePane.getChildren().addAll(contentPane);
        welcomePane.setAlignment(Pos.CENTER);
        welcomePane.setBackground(new Background ( new BackgroundFill(Color.AQUA, null, null)));

	
		// add buttons
		loginButton = createTextButton("button-login", "Login");
//        loginButton.setOnAction(this);
	    loginButton.setOnAction( e -> {
	    	System.out.println("Login button pressed");
			IUserDataProvider db = AppState.getInstance().getDatabase();
			
			if (db.authenticateUser(usernameTextField.getText(), passwordTextField.getText())) {
				User u = db.getUserByUsername(usernameTextField.getText());
				AppState.getInstance().setUser(u);
//				UserOverview ov = new UserOverview(stage);
				stage.setScene( new UserOverview(stage).getScene());
			    if (AppState.getInstance().isUserLoggedIn())
			    	System.out.println("hes logged in...OMG");
			}
	    });
        
        
        registerButton = createTextButton("button-register", "Register");   
        registerButton.setOnAction( e -> {
	    	System.out.println("Login button pressed");
			IUserDataProvider db = AppState.getInstance().getDatabase();
			
			stage.setScene( new RegisterScene(stage).getScene());
	    });
        
        VBox buttonPane = new VBox();
        buttonPane.setId("button-pane");
        buttonPane.getChildren().add(loginButton);
        buttonPane.getChildren().add(registerButton);
        buttonPane.setAlignment(Pos.CENTER);
        buttonPane.setSpacing(10);
        buttonPane.setBackground(new Background ( new BackgroundFill(Color.BURLYWOOD, null, null)));
        
        Label usernameLabel = new Label("Username");
        usernameLabel.setAlignment(Pos.CENTER_LEFT);
        usernameTextField = new TextField ();
        usernameTextField.setPromptText("enter e-mail adress");
        VBox usernameInput = new VBox();
        usernameInput.getChildren().addAll(usernameLabel, usernameTextField);
        usernameInput.setPadding(new Insets(5, 0, 10, 0));
        usernameInput.setMaxWidth(200);
        
        Label passwordLabel = new Label("Password");
        passwordLabel.setAlignment(Pos.CENTER_LEFT);
        passwordTextField = new PasswordField();
        passwordTextField.setPromptText("enter password");
        VBox pwInput = new VBox();
        pwInput.getChildren().addAll(passwordLabel, passwordTextField);
        pwInput.setPadding(new Insets(5, 0, 10, 0));
        pwInput.setMaxWidth(200);
        
        credentialsPane = new VBox();
        credentialsPane.setId("credentials-pane");
        
        credentialsPane.getChildren().addAll(usernameInput, pwInput, buttonPane);
        
        credentialsPane.setBackground( new Background( new BackgroundFill(Color.CORNSILK, null, null)));
        credentialsPane.setAlignment(Pos.CENTER);
		contentPane.getChildren().add(credentialsPane);
		
		
//		LoginStage.setScene(loginScene);
//        LoginStage.initModality(Modality.WINDOW_MODAL);
//        LoginStage.initStyle(StageStyle.UTILITY);
//        LoginStage.setMinHeight(400);
//        LoginStage.setMinWidth(400);
//        LoginStage.initOwner(stage);
//        LoginStage.setResizable(false);
//        
//        window = stage;
	}
	
	
	public Scene getScene() {
		return scene;
	}
}
	
//
//	@Override
//	public void handle(ActionEvent event) {
//		try {
//			if ( ((Button) event.getSource()).getId().equals("button-login") ) {
////				System.out.println("Login button pressed");
////				IUserDataProvider db = AppState.getInstance().getDatabase();
////				
////				if (db.authenticateUser(usernameTextField.getText(), passwordTextField.getText())) {
////					User u = db.getUserByUsername(usernameTextField.getText());
////					AppState.getInstance().setUser(u);
////					UserOverview ov = new UserOverview(window);
////					
////				    ov.show();
////				    LoginStage.close();
////				    if (AppState.getInstance().isUserLoggedIn())
////				    	System.out.println("hes logged in...OMG");
////				}
//			    
//			} else if ( ((Button) event.getSource()).getId().equals("button-register") ) {
//				System.out.println("Register button pressed");
//				RegistrationStage rs = new RegistrationStage(window);
//				rs.show();
//				LoginStage.close();
//			
//			}
//		} catch (Exception e) {
//
//		}
//	}
//}
