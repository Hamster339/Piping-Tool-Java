package GUI;

import Objects.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * The type Piping tool. Represents the program window.
 */
public class PipingTool extends Application {

    private Repertoire rep;

    private Group root;

    private Scene scene;

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

            scene = new Scene(root,1500,1000);
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
        //clear scene
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainPage.fxml"));
            fxmlLoader.setController(new MainPageController(this));
            root = fxmlLoader.load();
            scene.setRoot(root); }
        catch (IOException e) {
            System.out.println("Error fxml file for main page not found");
            System.out.println(e.getMessage());
        }


        //Position of first list
        int x = 10;
        int y = 100;
        //iterate over the lists to display them
        for (List list : rep.getLists()) {
            VBox listBox = new VBox();

            HBox titleBox = new HBox();
            titleBox.getStyleClass().add("title-box");

            Text nameText = new Text(list.getName());
            nameText.getStyleClass().add("list-title");
            titleBox.getChildren().add(nameText);

            //Add close button
            ListButton close = new ListButton("x",list);
            // it works, but now we have two controllers? setting controler for button
            close.setOnAction(new MainPageController(this)::handleDeleteList);
            titleBox.getChildren().add(close);

            listBox.getChildren().add(titleBox);

            //iterate over the tunes in each list to display them
            int count = 0;
            for(Tune t : list.getTunes()) {
                //create box for each tune
                HBox tuneBox = new HBox();
                tuneBox.getStyleClass().add("tune-box");

                //get and add text for each tune
                Text tuneText = new Text(t.getName());
                tuneText.getStyleClass().add("tune-text");
                tuneBox.getChildren().add(tuneText);

                //add del tune button
                TuneButton tuneDelButton = new TuneButton("x",count,list);
                tuneDelButton.setOnAction(new MainPageController(this)::handleDeleteTune);
                tuneBox.getChildren().add(tuneDelButton);

                //event handler for opening edit page which tune clicked on
                tuneText.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        displayEditWindow(t);
                    }
                });

                listBox.getChildren().add(tuneBox);

                count++;
            }

            //add new tune button
            ListButton newTune = new ListButton("+",list);
            newTune.setOnAction(new MainPageController(this)::handleNewTune);
            listBox.getChildren().add(newTune);

            //add list to screeen
            listBox.setLayoutX(x);
            listBox.setLayoutY(y);
            x += 300;
            listBox.getStyleClass().add("list");
            root.getChildren().add(listBox);

            //add dragqbility with event manager
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

    public void DisplayNewListPage() {
        try {
            FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("NewListWindow.fxml"));
            fxmlLoader2.setController(new NewListWindowController(this));
            GridPane NewListRoot = fxmlLoader2.load();

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("New List");
            stage.setScene(new Scene(NewListRoot, 450, 450));
            stage.show();
        } catch (IOException e) {
            System.out.println("Error fxml file for new list page not found or error");
            System.out.println(e.getMessage());

        }
    }

    public void AreYouSureWindowDelList(List list) {
        try {
            FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("areYouSureDelList.fxml"));
            fxmlLoader2.setController(new AreYouSureDelListController(this,list));
            GridPane NewListRoot = fxmlLoader2.load();

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("New List");
            stage.setScene(new Scene(NewListRoot, 400, 200));
            stage.show();
        } catch (IOException e) {
            System.out.println("Error fxml file for new list page not found or error");
            System.out.println(e.getMessage());

        }
    }

    public void DisplayNewTunePage(List list) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NewTune.fxml"));
            fxmlLoader.setController(new NewTuneController(this,list));
            GridPane AddTuneRoot = fxmlLoader.load();

            ComboBox <Tune> selector = (ComboBox) AddTuneRoot.lookup("#selector");
            selector.setItems(FXCollections.observableList(getRep().getMasterList()));

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Add Tune");
            stage.setScene(new Scene(AddTuneRoot, 400, 200));
            stage.show();
        } catch (IOException e) {
            System.out.println("Error fxml file for new list page not found or error");
            System.out.println(e.getMessage());

        }
    }

    public void DisplayCreateTunePage(NewTuneController parentController){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CreateOrEditTune.fxml"));
            fxmlLoader.setController(new CreateTuneController(this,parentController));
            GridPane CreateTuneRoot = fxmlLoader.load();

            ComboBox <Style> StyleSelector = (ComboBox) CreateTuneRoot.lookup("#styleSelector");
            StyleSelector.setItems(FXCollections.observableList(new ArrayList<Style>(java.util.List.of(Style.values()))));

            ComboBox <Timesig> timeSigSelector = (ComboBox) CreateTuneRoot.lookup("#timeSigSelector");
            timeSigSelector.setItems(FXCollections.observableList(new ArrayList<Timesig>(java.util.List.of(Timesig.values()))));

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Create New Tune");
            stage.setScene(new Scene(CreateTuneRoot, 400, 200));
            stage.show();
        } catch (IOException e) {
            System.out.println("Error fxml file for create tune page not found or error");
            System.out.println(e.getMessage());

        }
    }

    public void displayEditWindow(Tune t) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CreateOrEditTune.fxml"));
            fxmlLoader.setController(new EditTuneController(this, t));
            GridPane EditTuneRoot = fxmlLoader.load();

            ComboBox <Style> StyleSelector = (ComboBox) EditTuneRoot.lookup("#styleSelector");
            StyleSelector.setItems(FXCollections.observableList(new ArrayList<Style>(java.util.List.of(Style.values()))));

            ComboBox <Timesig> timeSigSelector = (ComboBox) EditTuneRoot.lookup("#timeSigSelector");
            timeSigSelector.setItems(FXCollections.observableList(new ArrayList<Timesig>(java.util.List.of(Timesig.values()))));

            //rename things in reused fxml file
            Button updateButton = (Button) EditTuneRoot.lookup("#createButton");
            updateButton.setText("Update");

            Text title = (Text) EditTuneRoot.lookup("#Title");
            title.setText("Edit Tune");

            //Set current values
            TextField nameField = (TextField) EditTuneRoot.lookup("#nameField");
            nameField.setText(t.getName());

            StyleSelector.setValue(t.getStyle());
            timeSigSelector.setValue(t.getTimeSignature());

            TextField notesField = (TextField) EditTuneRoot.lookup("#notesField");
            notesField.setText(t.getSheetMusicLocation());


            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Edit Tune");
            stage.setScene(new Scene(EditTuneRoot, 400, 300));
            stage.show();
        } catch (IOException e) {
            System.out.println("Error fxml file for edit tune page not found or error");
            System.out.println(e.getMessage());

        }
    }
    public Repertoire getRep() {
        return rep;
    }
}
