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
import javafx.scene.control.Button;
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

public class RegistrationStage implements EventHandler<ActionEvent> {

	public Stage window;
	Stage registrationStage = new Stage();
	public Scene regScene;
	
	public RegistrationStage(Stage owner) {
		
		window = owner;
		
		Button nextButton;
	    Button backButton;
	     
        VBox registrationPane = new VBox();
        
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
        TextField fnTextField = new TextField ();
        
        Label lnLabel = new Label("Last name");
        TextField lnTextField = new TextField ();
        
        Label usernameLabel = new Label("E-Mail");
        TextField usernameTextField = new TextField ();
        
        Label passwordLabel = new Label("Password");
//	      passwordLabel.setAlignment(Pos.CENTER_LEFT);
        PasswordField passwordTextField = new PasswordField();
//	      passwordTextField.setPromptText("enter password");
        
        Label adressLabel = new Label("Adress");
        TextField adressTextField = new TextField ();
        
        Label cityLabel = new Label("City");
        TextField cityTextField = new TextField (); 
        
        Label regionLabel = new Label("Region");
        TextField regionTextField = new TextField ();   
        
        Label countryLabel = new Label("Country");
        TextField countryTextField = new TextField ();  


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
        backButton = new Button("Back"); backButton.setId("button-back");
        backButton.setOnAction(this);
//        backButton.setOnAction(e -> window.setScene(loginScene));
        
        HBox buttonsPane = new HBox();
        buttonsPane.getChildren().add(backButton);
        buttonsPane.getChildren().add(nextButton);
        buttonsPane.setSpacing(50);
        buttonsPane.setPrefSize(400, 70);
        buttonsPane.setAlignment(Pos.BOTTOM_CENTER);
        buttonsPane.setBackground(new Background ( new BackgroundFill(Color.BLUEVIOLET, null, null)));
        
        registrationPane.setId("registration-pane");
        registrationPane.getChildren().addAll(headerPane);
        registrationPane.getChildren().addAll(registrationForm);
        registrationPane.getChildren().addAll(buttonsPane);
        registrationPane.setAlignment(Pos.TOP_CENTER);
        
        
        regScene = new Scene(registrationPane);
        registrationStage.setResizable(false);
        registrationStage.setScene(regScene);
		
	}
	
	public void show() {
//		window.setTitle("Registration");
		registrationStage.setTitle("Registration");
		registrationStage.show();
	}
	
	
	@Override
	public void handle(ActionEvent event) {
		try {
			if ( ((Button) event.getSource()).getId().equals("button-back") ) {

				
					LoginDialog ld = new LoginDialog(window);
					ld.show();
					registrationStage.close();
					
//					UserOverview ov = new UserOverview(window);
//				    ov.show();
//				    LoginStage.close();
  
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
