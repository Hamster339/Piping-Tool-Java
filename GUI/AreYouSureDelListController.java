package GUI;

import Objects.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;


public class AreYouSureDelListController {

    @FXML Button CanButton;
    private final PipingTool mainController;
    private final List list;

    public AreYouSureDelListController(PipingTool m, List list){
        mainController = m;
        this.list = list;
    }


    @FXML protected void delete(ActionEvent event) {
        mainController.getRep().DelList(list);

        mainController.displayLists();
        try {
            mainController.getRep().save();
        } catch (IOException e) {
            System.out.println("problem Saving");
            System.out.println(e.getMessage());
        }

        Stage stage = (Stage) CanButton.getScene().getWindow();
        stage.close();
    }

    @FXML protected void cancelDelete(ActionEvent event) {
        Stage stage = (Stage) CanButton.getScene().getWindow();
        stage.close();
    }

}

