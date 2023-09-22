package GUI;

import Objects.Tune;
import javafx.event.ActionEvent;

import java.io.IOException;

public class TuneManagerController {
    private PipingTool mainController;


    public TuneManagerController(PipingTool m){

        mainController = m;
    }

    protected void handleEditTune(ActionEvent actionEvent){
        TuneButton source = (TuneButton) actionEvent.getSource();
        mainController.displayEditWindow(source.tune);
    }

    protected void handleDeleteTune(ActionEvent actionEvent)  {
        TuneButton source = (TuneButton) actionEvent.getSource();
        mainController.getRep().delTune(source.tune);

        try {
            mainController.getRep().save();
            mainController.displayTuneManager();
        } catch (IOException e){
            System.out.println("error Saving");
            System.out.println(e.getMessage());
        }
    }

    protected void refresh(){
        mainController.displayTuneManager();
    }
}
