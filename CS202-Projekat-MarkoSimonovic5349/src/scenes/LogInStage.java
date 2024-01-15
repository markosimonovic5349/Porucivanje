package scenes;

import entities.Korisnik;
import exceptions.NotValidEcveption;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LogInStage extends Application{

    @Override
    public void start(Stage primaryStage) {
        Button nazad = new Button("Nazad");
        nazad.setOnAction((event) -> {
            primaryStage.close();
            new PocetniStage().start(primaryStage);
        });

        Label userNameLabel = new Label("Username:");
        TextField userNameTxt = new TextField();

        Label passwordLabel = new Label("Password:");
        PasswordField passwordTxt = new PasswordField();

        Button btnLogIn = new Button("Log in");

        btnLogIn.setOnAction((event) -> {
            Korisnik k = null;
            try {
                k = db.KorisnikCrud.getKorinsik(userNameTxt.getText().trim());
            } catch (NotValidEcveption ex) {
                Logger.getLogger(LogInStage.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (k != null) {
                if (passwordTxt.getText().trim().equals(k.getPassword())) {
                    primaryStage.close();
                    new OpcijeStage(k).start(primaryStage);
                } else {
                    
                    prikaziAlert("Alert", "Pogresna lozinka!");
                }
            } else {
                prikaziAlert("Alert", "Nevazeci username!");
            }
        });

        
        VBox root = new VBox(10);
        VBox v1 = new VBox(2);
        VBox v2 = new VBox(2);
        v1.getChildren().addAll(userNameLabel,userNameTxt);
        v2.getChildren().addAll(passwordLabel,passwordTxt);
        root.getChildren().addAll(nazad, v1, v2, btnLogIn);

        root.setPadding(new Insets(30));
        Scene scene = new Scene(root, 500 , 350);

        primaryStage.setTitle("Log in");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void prikaziAlert(String naslov, String tekst) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(naslov);
        alert.setHeaderText(null);
        alert.setContentText(tekst);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
