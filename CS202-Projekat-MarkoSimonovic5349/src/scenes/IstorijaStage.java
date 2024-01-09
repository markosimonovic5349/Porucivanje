/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package scenes;

import entities.Korisnik;
import entities.Porudzbina;
import entities.Proizvod;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Marko Simonovic
 */
public class IstorijaStage extends Application {

    private Korisnik k;

    public IstorijaStage(Korisnik k) {
        this.k = k;
    }

    @Override
    public void start(Stage primaryStage)  {
       //Korisnik k =  db.KorisnikCrud.getKorinsik("Marko03");
        Label usName = new Label("Username: "+ k.getUserName());
        Button nazad = new Button("Nazad");
        nazad.setOnAction((event) -> {
            primaryStage.close();
            new OpcijeStage(k).start(primaryStage);
        });
        String obavestenje = "";
        int num = 1;
        List<Porudzbina> porudzbine = db.PorudzbinaCrud.returnPorudzbineByKorisnik(k);
        for (Porudzbina porudzbina : porudzbine) {
            
            Map<Proizvod, Integer> putanjeKolicineMapa = porudzbina.getMap();
            obavestenje += "PORUDZBINA - "+num + "\n";
                        num++;
                double ukCena = 0;
                for (Map.Entry<Proizvod, Integer> entry : putanjeKolicineMapa.entrySet()) {
                    Proizvod p = entry.getKey();
                    int kolicina = entry.getValue();
                    ukCena += (kolicina * p.getCena());
                    obavestenje += "   -Proizvod: " + p.getNaziv() + " - " + p.getCena() + " x " + kolicina + "\n";
                }
                obavestenje += "Ukupna cena: " + ukCena + "\n\n";
           
            }
        

        Label porudz = new Label(obavestenje);
        
        

        
        VBox container = new VBox(10);
        container.getChildren().addAll(usName, nazad);

        ScrollPane scrollPane = new ScrollPane(porudz);
   


        VBox root = new VBox(10);
        root.getChildren().addAll(container,scrollPane);
        root.setPadding(new Insets(30));

        Scene scene = new Scene(root, 500, 350);

        primaryStage.setTitle("Istorija");
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
