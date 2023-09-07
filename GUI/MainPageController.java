package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.text.Text;

import java.io.IOException;


public class MainPageController {
    private final PipingTool mainController;

    public MainPageController(PipingTool m){
        mainController = m;
    }


    @FXML protected void openNewListWindow(ActionEvent event) {
        mainController.DisplayNewListPage();
    }

    @FXML protected void handleDeleteList(ActionEvent event) {
        ListButton closeButton = (ListButton) event.getSource();
        mainController.AreYouSureWindowDelList(closeButton.list);
    }

    @FXML protected  void handleNewTune(ActionEvent event) {
        ListButton closeButton = (ListButton) event.getSource();
        mainController.DisplayNewTunePage(closeButton.list);
    }

    @FXML protected void handleDeleteTune(ActionEvent event) {
        // get list and tune and remove it
        TuneButton tuneButton = (TuneButton) event.getSource();
        tuneButton.list.remove(tuneButton.tune);

        //refresh page
        mainController.displayLists();

        //save changes
        try {
            mainController.getRep().save();
        } catch (IOException e) {
            System.out.println("problem Saving");
            System.out.println(e.getMessage());
        }
    }

}

