package gui;

import javafx.scene.text.Text;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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


public class LoginDialog implements EventHandler<ActionEvent> {

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
	
	public LoginDialog(Stage owner) {
		
		window = owner;
		
		
		// add buttons
		loginButton = createTextButton("button-login", "Login");
        loginButton.setOnAction(this);
        
        registerButton = createTextButton("button-register", "Register");   
        
        VBox buttonPane = new VBox();
        buttonPane.setId("button-pane");
        buttonPane.getChildren().add(loginButton);
        buttonPane.getChildren().add(registerButton);
        buttonPane.setAlignment(Pos.CENTER);
        buttonPane.setSpacing(10);
        buttonPane.setBackground(new Background ( new BackgroundFill(Color.BURLYWOOD, null, null)));
        
        Label usernameLabel = new Label("Username");
        usernameLabel.setAlignment(Pos.CENTER_LEFT);
        TextField usernameTextField = new TextField ();
        usernameTextField.setPromptText("enter e-mail adress");
        VBox usernameInput = new VBox();
        usernameInput.getChildren().addAll(usernameLabel, usernameTextField);
        usernameInput.setPadding(new Insets(5, 0, 10, 0));
        usernameInput.setMaxWidth(200);
        
        Label passwordLabel = new Label("Password");
        passwordLabel.setAlignment(Pos.CENTER_LEFT);
        PasswordField passwordTextField = new PasswordField();
        passwordTextField.setPromptText("enter password");
        VBox pwInput = new VBox();
        pwInput.getChildren().addAll(passwordLabel, passwordTextField);
        pwInput.setPadding(new Insets(5, 0, 10, 0));
        pwInput.setMaxWidth(200);
        
        credentialsPane = new VBox();
        credentialsPane.setId("credentials-pane");
        
        credentialsPane.getChildren().addAll(usernameInput, pwInput, buttonPane);
//        credentialsPane.getChildren().add(pwInput);
//        credentialsPane.getChildren().add(buttonPane);
        
        credentialsPane.setBackground( new Background( new BackgroundFill(Color.CORNSILK, null, null)));
        credentialsPane.setAlignment(Pos.CENTER);
//        credentialsPane.setMaxWidth(200);
		
		Scene loginScene = new Scene(credentialsPane);
		
		LoginStage.setScene(loginScene);
        LoginStage.initModality(Modality.WINDOW_MODAL);
        LoginStage.initStyle(StageStyle.UTILITY);
        LoginStage.setMinHeight(400);
        LoginStage.setMinWidth(400);
        LoginStage.initOwner(window);
        LoginStage.setResizable(false);
	}
	
	public void show() {
		LoginStage.show();
	}
	
	
//	UserOverview ov = new UserOverview(window);
//    ov.show();

	@Override
	public void handle(ActionEvent event) {
		try {
			if ( ((Button) event.getSource()).getId().equals("button-login") ) {
				UserOverview ov = new UserOverview(window);
			    ov.show();
			    LoginStage.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	//	@Override
//    public void handle(ActionEvent event) {
//        try {
//            if (((Button) event.getSource()).getId().equals("ok-button")) {
//                ICompleteDataProvider db = AppState.getInstance().getDatabase();
//                if (db.authenticateUser(usernameField.getText(), passwordField.getText())) {
//                    User u = db.getUserByUsername(usernameField.getText());
//                    System.out.println("'LoginDialog', login succeesful with username = " + usernameField.getText() + "   password = " + passwordField.getText());
//
//                    AppState.getInstance().setUser(u);
//                    loginStage.hide();
//                } else {
//                    System.out.println("'LoginDialog', login failed with username = " + usernameField.getText() + "   password = " + passwordField.getText());
//
//                }
//            }
//            
//        } catch (NumberFormatException ex) {
//            System.out.println("'LoginDialog', login failed with username = " + usernameField.getText() + "   password = " + passwordField.getText());
////            segmentLengthField
////                    .setStyle("-fx-border-color: red; " + "-fx-border-width: 2px; " + "-fx-border-radius: 3px;");
//        }
//
//    }
}
