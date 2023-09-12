package GUI;

import Objects.Tune;
import Objects.Style;
import Objects.Timesig;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import javafx.event.ActionEvent;
import java.io.IOException;
import java.util.Objects;

public class EditTuneController {

    private PipingTool mainController;

    private Tune tune;

    @FXML private TextField nameField;
    @FXML private ComboBox <Style> styleSelector;
    @FXML private ComboBox <Timesig> timeSigSelector;
    @FXML private TextField notesField;
    @FXML private Label helpText;
    @FXML private Button createButton;


    public EditTuneController(PipingTool m, Tune t){

        mainController = m;
        tune = t;
    }

    @FXML protected void handleCreateOrEditTune(ActionEvent event){

        if (Objects.equals(nameField.getText(), "")){
            helpText.setText("Tune names cannot be blank");
        } else if (Objects.equals(styleSelector.getValue(), null)) {
            helpText.setText("You must Select a tune style");
        } else if (Objects.equals(timeSigSelector.getValue(), null)) {
            helpText.setText("You must Select a time signature");
        } else {
            //delete old Tune and add new one
            tune.setName(nameField.getText());
            tune.setStyle(styleSelector.getValue());
            tune.setTimeSignature(timeSigSelector.getValue());
            tune.setSheetMusicLocation(notesField.getText());

            Stage stage = (Stage) createButton.getScene().getWindow();

            try {
                mainController.getRep().save();
            } catch (IOException e) {
                System.out.println("problem Saving");
                System.out.println(e.getMessage());
            }
            mainController.displayLists();
            stage.close();
        }

    }
}
