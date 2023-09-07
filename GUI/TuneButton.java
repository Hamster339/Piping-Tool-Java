package GUI;

import Objects.List;
import javafx.scene.control.Button;

public class TuneButton extends Button {

    int tune;
    List list;

    public TuneButton (String label, int tune, List list){
        super(label);
        this.tune = tune;
        this.list = list;
    }
}
