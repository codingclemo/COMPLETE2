package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.Window;

public class LoginPane extends Pane {

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
    
    

    
    public LoginPane() {
        loginButton = createTextButton("button-login", "Login");
        registerButton = createTextButton("button-register", "Register");
//        registerButton.setOnAction(e -> window.setScene(registrationScene));
//        loginButton.setOnAction( e-> window.setScene(UserProfile) );
        
        
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
        
        Label passwordLabel = new Label("Password");
        passwordLabel.setAlignment(Pos.CENTER_LEFT);
        PasswordField passwordTextField = new PasswordField();
        passwordTextField.setPromptText("enter password");
//      TextField passwordTextField = new TextField ();
        VBox pwInput = new VBox();
        pwInput.getChildren().addAll(passwordLabel, passwordTextField);
        pwInput.setPadding(new Insets(5, 0, 10, 0));
        
        credentialsPane = new VBox();
        credentialsPane.setId("credentials-pane");
        
        credentialsPane.getChildren().addAll(usernameInput, pwInput, buttonPane);
//        credentialsPane.getChildren().add(pwInput);
//        credentialsPane.getChildren().add(buttonPane);
        
        credentialsPane.setBackground( new Background( new BackgroundFill(Color.CORNSILK, null, null)));
        credentialsPane.setAlignment(Pos.CENTER);

        
    }
    
    public void setRegisterButton(Stage window, Scene registrationScene) {
//    	registerButton.setOnAction(e -> window.setScene(registrationScene));
    	Node nodeOut = credentialsPane.getChildren().get(2);
    	if (nodeOut instanceof VBox) {
    		for (Node nodeIn: ((VBox) nodeOut).getChildren() ) {
    			if (nodeIn instanceof Button) {
    				if (nodeIn.getId() == "button-register") {
    					((Button) nodeIn).setOnAction(e -> window.setScene(registrationScene));
    				}
    			}
    		}
    	}
    }
    
    public Pane getPane() {
    	return credentialsPane;
    }

}
