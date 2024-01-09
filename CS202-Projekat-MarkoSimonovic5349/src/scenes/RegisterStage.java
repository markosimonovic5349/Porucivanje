package scenes;

import scenes.PocetniStage;
import scenes.OpcijeStage;
import entities.Korisnik;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Marko Simonovic
 */
public class RegisterStage extends Application {

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
        TextField passwordTxt = new TextField();

        Label brojTelefonaLabel = new Label("Broj telefona:");
        TextField brojTelefonaTxt = new TextField();

        Label adresaLabel = new Label("Adresa:");
        TextField adresaTxt = new TextField();

        Button btnRegister = new Button("Register");

        btnRegister.setOnAction((event) -> {
            if (userNameTxt.getText().trim().equals("") || passwordTxt.getText().trim().equals("") || brojTelefonaTxt.getText().trim().equals("") || adresaTxt.getText().trim().equals("")) {
                prikaziAlert("Alert", "Sva polja su obavezna!");
            } else {
                Korisnik k = new Korisnik(userNameTxt.getText().trim(), passwordTxt.getText().trim(), brojTelefonaTxt.getText().trim(), adresaTxt.getText().trim());
                db.KorisnikCrud.addKorisnik(k);
                primaryStage.close();
                new OpcijeStage(k).start(primaryStage);
            }
        });

        VBox root = new VBox(10);
        VBox v1 = new VBox(2);
        VBox v2 = new VBox(2);
        VBox v3 = new VBox(2);
        VBox v4 = new VBox(2);
        v1.getChildren().addAll(userNameLabel, userNameTxt);
        v2.getChildren().addAll(passwordLabel, passwordTxt);
        v1.getChildren().addAll(brojTelefonaLabel, brojTelefonaTxt);
        v2.getChildren().addAll(adresaLabel, adresaTxt);
        root.getChildren().addAll(nazad, v1, v2, v3, v4, btnRegister);

        root.setPadding(new Insets(30));
        Scene scene = new Scene(root, 500, 350);

        primaryStage.setTitle("Register");
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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
