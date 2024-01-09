/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package scenes;

import entities.Korisnik;
import scenes.IstorijaStage;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Marko Simonovic
 */
public class OpcijeStage extends Application {
    private Korisnik k;
    public OpcijeStage(Korisnik k) {
        this.k = k;
    }
    @Override
    public void start(Stage primaryStage) {
        //Korisnik k = db.KorisnikCrud.getKorinsik("Marko03");
        Label usName = new Label("Username: "+ k.getUserName());
        Button nazad = new Button("Odjava");
        nazad.setOnAction((event) -> {
            primaryStage.close();
            new PocetniStage().start(primaryStage);
        });
        
        Button ponuda = new Button("Ponuda");
        Button istorijaPorudzbina = new Button("Istorija porudzbina");
        Button pamuk = new Button("Pamuk");
        
        ponuda.setOnAction((event) -> {
            primaryStage.close();
            new PonudaStage(k).start(primaryStage);
        });
        
        istorijaPorudzbina.setOnAction((event) -> {
            primaryStage.close();
            new IstorijaStage(k).start(primaryStage);
        });
        
        pamuk.setOnAction((event) -> {
            primaryStage.close();
            new PamukStage(k).start(primaryStage);
        });
        
        
        
        VBox root = new VBox(10);
        root.getChildren().addAll(usName,nazad,ponuda,istorijaPorudzbina,pamuk);
        
        root.setPadding(new Insets(30));
        Scene scene = new Scene(root, 500 , 350);
        
        primaryStage.setTitle("Opcije!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
