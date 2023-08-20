package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.text.Text;


public class MainPageController {
    private final PipingTool mainController;

    public MainPageController(PipingTool m){
        mainController = m;
    }


    @FXML protected void openNewListWindow(ActionEvent event) {
        mainController.DisplayNewListPage();
    }

}

