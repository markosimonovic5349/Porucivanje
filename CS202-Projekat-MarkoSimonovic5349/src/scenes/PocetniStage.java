package scenes;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PocetniStage extends Application {

    @Override
    public void start(Stage primaryStage) {
        Button logIn = new Button("Log in");
        Button register = new Button("Register");
        Button exit = new Button("Exit");

        logIn.setOnAction((event) -> {
            primaryStage.close();
            new LogInStage().start(primaryStage);
        });

        register.setOnAction((event) -> {
            primaryStage.close();
            new RegisterStage().start(primaryStage);
        });

        exit.setOnAction((event) -> {
            primaryStage.close();
        });

        VBox root = new VBox(30);
        root.getChildren().addAll(logIn, register, exit);
        root.setAlignment(Pos.CENTER);

        root.setPadding(new Insets(30));
        Scene scene = new Scene(root, 500 , 350);

        primaryStage.setTitle("Pocetni Stage");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
