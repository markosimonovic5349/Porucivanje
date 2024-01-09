package scenes;

import entities.Porudzbina;
import entities.Proizvod;
import scenes.OpcijeStage;
import entities.Korisnik;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Marko Simonovic
 */
public class PonudaStage extends Application {

    private Korisnik k;

    public PonudaStage(Korisnik k) {
        this.k = k;
    }

    @Override
    public void start(Stage primaryStage) {
        Label usName = new Label( "Username: "+ k.getUserName());
        Button nazad = new Button("Nazad");
        nazad.setOnAction((event) -> {
            primaryStage.close();
            new OpcijeStage(k).start(primaryStage);
        });

        List<Proizvod> proizvodi = (ArrayList<Proizvod>) db.ProizvodCrud.getProizvodi();
        List<TextField> kolicine = new ArrayList<>();

        VBox root = new VBox(10);
        root.getChildren().addAll(usName,nazad);
        VBox v1 = new VBox(10);

        for (Proizvod proizvod : proizvodi) {
            ImageView img = new ImageView("file:src/" + proizvod.getPutanjaSlike() + ".jpg");
            img.setFitHeight(50);
            img.setFitWidth(50);
            Label opis = new Label("Porizvod: " + proizvod.getNaziv() + "\n" + "Cena: " + String.valueOf(proizvod.getCena()));

            Label kolicinaLbl = new Label("Kolicina: ");
            TextField kolicina = new TextField("0");
            kolicina.setPrefWidth(50);

            kolicine.add(kolicina);

            VBox vbox = new VBox();
            HBox hbox = new HBox();
            HBox hb = new HBox();
            hb.getChildren().addAll(kolicinaLbl, kolicina);
            vbox.getChildren().addAll(opis, hb);
            hbox.getChildren().addAll(img, vbox);
            hb.setAlignment(Pos.CENTER_LEFT);

            hbox.setAlignment(Pos.CENTER_LEFT);
            vbox.setAlignment(Pos.CENTER_LEFT);
            hbox.setStyle("-fx-border-color:black");

            v1.getChildren().add(hbox);
        }
        root.getChildren().addAll(v1);

        Button poruci = new Button("Poruci");
        root.getChildren().add(poruci);
        poruci.setOnAction((event) -> {

            Map<Proizvod, Integer> putanjeKolicineMapa = new HashMap<>();
            boolean b = true;
            for (int i = 0; i < proizvodi.size(); i++) {
                try {
                    int kol = Integer.parseInt(kolicine.get(i).getText());
                    if (kol > 0) {
                        putanjeKolicineMapa.put(proizvodi.get(i), kol);
                    } else {
                        if (kol < 0) {
                            if (b) {
                                b = false;
                            }
                        }
                    }
                } catch (NumberFormatException e) {
                    if (b) {
                        b = false;
                    }
                }

            }
            if (b && putanjeKolicineMapa.size() > 0) {
                System.out.println(putanjeKolicineMapa);

                db.PorudzbinaCrud.addPorudzbina(new Porudzbina(k, putanjeKolicineMapa));

                String obavestenje = "UPSESNA PORUDZBINA!" + "\n\n"
                        + "Username: " + k.getUserName() + "\n"
                        + "Broj telefona: " + k.getBrojTelefona() + "\n"
                        + "Adresa: " + k.getAdresa() + "\n"
                        + "Broj telefona: " + k.getBrojTelefona() + "\n";
                double ukCena = 0;
                for (Map.Entry<Proizvod, Integer> entry : putanjeKolicineMapa.entrySet()) {
                    Proizvod p = entry.getKey();
                    int kolicina = entry.getValue();
                    ukCena += (kolicina * p.getCena());
                    obavestenje += "Proizvod: " + p.getNaziv() + " - " + p.getCena() + " x " + kolicina + "\n";
                }
                obavestenje += "Ukupna cena: " + ukCena;
                primaryStage.close();
                prikaziAlert("Porudzbina", obavestenje);
                
                new OpcijeStage(k).start(primaryStage);
            } else {
                prikaziAlert("Greska", "Nevalidan unos kolicina!");
            }
        });

        root.setPadding(new Insets(30));
        Scene scene = new Scene(root, 500 , 350);

        primaryStage.setTitle("Ponuda");
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
