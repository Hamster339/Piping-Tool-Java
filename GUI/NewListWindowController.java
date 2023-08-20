package GUI;

import com.sun.jdi.request.ClassPrepareRequest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Objects;

public class NewListWindowController {
    //represents the user input of the name of a new list
    @FXML private TextField listName;

    //represents the help text in the new list window
    @FXML private Text HelpText;

    // represents the submit button
    @FXML private Text addButton;

    private final PipingTool mainController;

    public NewListWindowController(PipingTool m){
        mainController = m;
    }

    @FXML protected void handleAddNewList(ActionEvent event) {
        if (Objects.equals(listName.getText(), "")){
            HelpText.setText("List names cannot be blank");
        } else if (Objects.equals(listName.getText(), "MASTER")) {
            HelpText.setText("List names cannot 'MASTER' as that word is reserved");
        } else {
            mainController.getRep().addList(listName.getText());

            Stage stage = (Stage) addButton.getScene().getWindow();
            mainController.displayLists();
            stage.close();
        }
    }

}