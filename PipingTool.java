import Objects.List;
import Objects.Repertoire;
import Objects.Tune;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class PipingTool extends Application {

    private Repertoire rep;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init(){
        rep = new Repertoire();
        rep.load();
    }

    @Override
    public void start(Stage primaryStage) {

        Group root = new Group();
        Scene scene = new Scene(root,1500,1000);
        scene.getStylesheets().add("appStyle.css");

        int x = 10;
        int y = 10;
        for (List list : rep.getLists()) {
            VBox listBox = new VBox();


            VBox titleBox = new VBox();
            titleBox.getStyleClass().add("title-box");

            Text nameText = new Text(list.getName());
            nameText.getStyleClass().add("list-title");
            titleBox.getChildren().add(nameText);

            listBox.getChildren().add(titleBox);


            for(Tune t : list.getTunes()) {
                VBox tuneBox = new VBox();
                tuneBox.getStyleClass().add("tune-box");

                Text tuneText = new Text(t.getName());
                tuneText.getStyleClass().add("tune-text");
                tuneBox.getChildren().add(tuneText);

                listBox.getChildren().add(tuneBox);
            }

            listBox.setLayoutX(x);
            listBox.setLayoutY(y);
            x += 220;
            listBox.getStyleClass().add("list");
            root.getChildren().add(listBox);
        }




        primaryStage.setTitle("Piping Repertoire tool");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
