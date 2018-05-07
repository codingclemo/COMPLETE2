package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class WelcomePane extends GridPane {
    
    public VBox welcomePane;
    
    public WelcomePane() {
        welcomePane = new VBox();
        VBox contentPane = new VBox();  // probably there is a nicer solution to this
        contentPane.setId("content-pane");
        
        Text t = new Text();
        t.setText("Welcome to COMPLETE");
//        contentPane.add(t, 0, 2);
        contentPane.getChildren().add(t);
        contentPane.setMinSize(400, 100);
        
        welcomePane = new VBox();
        welcomePane.setId("welcome-pane");
        welcomePane.getChildren().addAll(contentPane);
        welcomePane.setAlignment(Pos.CENTER);
        welcomePane.setBackground(new Background ( new BackgroundFill(Color.AQUA, null, null)));
    }
    
    public Pane getPane() {
        return welcomePane;
    }
    
    
}
