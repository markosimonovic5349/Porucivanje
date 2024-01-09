package scenes;

import entities.Korisnik;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

/**
 *
 * @author Marko Simonovic
 */
public class PamukStage extends Application {

    private Korisnik k;

    public PamukStage(Korisnik k) {
        this.k = k;
    }

    @Override
    public void start(Stage primaryStage) {
        //Korisnik k = db.KorisnikCrud.getKorinsik("Marko03");
        Label usName = new Label("Username: " + k.getUserName());
        Button nazad = new Button("Nazad");
        nazad.setOnAction((event) -> {
            primaryStage.close();
            new OpcijeStage(k).start(primaryStage);
        });

       
        

        Text text = new Text(vratiText(Arrays.asList("1", "2", "3","19", "20")));
        text.setWrappingWidth(425);
            
        ScrollPane scrollPane = new ScrollPane(text);      
        VBox root = new VBox(10);
        root.getChildren().addAll(usName, nazad, scrollPane);

        root.setPadding(new Insets(30));
        Scene scene = new Scene(root, 500, 350);

        primaryStage.setTitle("Pamuk");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static String vratiText(List<String> s){
        String text = "";
        try {
            Document doc = Jsoup.connect("https://robertobaressi.rs/blog/pamuk-sve-sto-treba-da-znamo-o-ovom-materijalu/").get();
            Elements t = doc.select("body > div.wrap > main.main > div.container > div.page-content > p:nth-child(1)");
            
            for (String string : s) {
            t = doc.select("body > div.wrap > main.main > div.container > div.page-content > p:nth-child("+string+")");
            text += t.text().trim() + "\n\n";
        }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
        return text;
    }
    
    public static void main(String[] args) {
        launch(args);
    }

}
