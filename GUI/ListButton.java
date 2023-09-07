package GUI;

import Objects.List;
import javafx.scene.control.Button;

public class ListButton extends Button {

    List list;

    public ListButton (String label, List list){
        super(label);
        this.list = list;
    }
}
