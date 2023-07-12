package GUI;

import Objects.List;
import Objects.Repertoire;
import Objects.Tune;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * The type Piping tool. Represents the program window.
 */
public class PipingTool extends Application {

    private Repertoire rep;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
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

        displayLists(root,rep);

        primaryStage.setTitle("Piping Repertoire tool");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /**
     * Displays the lists in the Repertoire object on the screen
     *
     * @param root the root object of the window
     * @param rep the Repertoire that contains the lists to be displayed
     */
    public void displayLists (Group root, Repertoire rep) {
        //Position of first list
        int x = 10;
        int y = 10;
        //iterate over the lists to display them
        for (List list : rep.getLists()) {
            VBox listBox = new VBox();


            VBox titleBox = new VBox();
            titleBox.getStyleClass().add("title-box");

            Text nameText = new Text(list.getName());
            nameText.getStyleClass().add("list-title");
            titleBox.getChildren().add(nameText);

            listBox.getChildren().add(titleBox);

            //iterate over the tunes in each list to display them
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
    }
}
