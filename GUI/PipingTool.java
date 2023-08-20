package GUI;

import Objects.List;
import Objects.Repertoire;
import Objects.Tune;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The type Piping tool. Represents the program window.
 */
public class PipingTool extends Application {

    private Repertoire rep;

    private Group root;

    private static PipingTool instance;


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

        //load fxml page, display the lists on it and show
        try{

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainPage.fxml"));
            fxmlLoader.setController(new MainPageController(this));
            root = fxmlLoader.load();

            Scene scene = new Scene(root,1500,1000);
            scene.getStylesheets().add("appStyle.css");

            displayLists();

            primaryStage.setTitle("Piping Repertoire tool");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            System.out.println("Error fxml file for main page not found");
            System.out.println(e.getMessage());
        }

    }

    /**
     * Displays the lists in the Repertoire object on the screen
     *
     */
    public void displayLists() {
        //Position of first list
        int x = 10;
        int y = 100;
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
            x += 240;
            listBox.getStyleClass().add("list");
            root.getChildren().add(listBox);

            listBox.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    System.out.println("dragged");
                    listBox.setLayoutX(mouseEvent.getSceneX());
                    listBox.setLayoutY(mouseEvent.getSceneY());

                }
            });
        }
    }

    public void DisplayNewListPage(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NewListWindow.fxml"));
            fxmlLoader.setController(new NewListWindowController(this));
            Parent NewListRoot = fxmlLoader.load();

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("New List");
            stage.setScene(new Scene(NewListRoot, 450, 450));
            stage.show();
        } catch (IOException e) {
            System.out.println("Error fxml file for new list page not found");
            System.out.println(e.getMessage());
        }
    }
    public Repertoire getRep() {
        return rep;
    }
}
