package GUI;

import Objects.List;
import Objects.Tune;
import javafx.scene.control.Button;

public class TuneButton extends Button {

    int tuneNum;

    Tune tune;
    List list;

    public TuneButton (String label, int tune, List list){
        super(label);
        this.tuneNum = tune;
        this.list = list;
    }

    public TuneButton (String label, Tune tune){
        super(label);
        this.tune = tune;
    }
}
