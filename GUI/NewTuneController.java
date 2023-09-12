package GUI;

import Objects.List;
import Objects.Tune;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class NewTuneController {
    private final PipingTool mainController;
    private final List list;

    @FXML
    private ComboBox<Tune> selector;
    @FXML Button addTuneButton;
    @FXML Text message;


    public NewTuneController(PipingTool m, List list){
        mainController = m;
        this.list = list;
    }

    @FXML protected void addTune(ActionEvent event) {

        if (selector.getValue() != null) {
            list.add(selector.getValue());

            mainController.displayLists();
            try {
                mainController.getRep().save();
            } catch (IOException e) {
                System.out.println("problem Saving");
                System.out.println(e.getMessage());
            }

            Stage stage = (Stage) addTuneButton.getScene().getWindow();
            stage.close();
        } else {
            message.setText("Please select a tune or create one");
        }
    }

    @FXML protected void createTune(ActionEvent event) {
        mainController.DisplayCreateTunePage(this);
    }

    //refreshes the list of available tunes
    protected void refresh(){
        selector.setItems(FXCollections.observableList(mainController.getRep().getMasterList()));
    }

}