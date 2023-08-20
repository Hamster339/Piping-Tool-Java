package tests;

import GUI.PipingTool;
import Objects.Repertoire;
import javafx.scene.Group;
import javafx.scene.layout.VBox;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PipingToolTest {

    Group root;
    Repertoire rep;

    @Before
    public void setup(){
        root = new Group();
        rep = new Repertoire();
        RepertoireTest.inputData(rep);

        PipingTool p = new PipingTool();
        p.displayLists();
    }

    @Test
    public void testPipingTool_CreateLists (){
        Assert.assertEquals(2,root.getChildren().size());
    }

    @Test
    public void testPipingTool_numOfVBoxes1 (){
        VBox list = ((VBox) root.getChildren().get(0));
        Assert.assertEquals(3,list.getChildren().size());
    }

    @Test
    public void testPipingTool_numOfVBoxes2 (){
        VBox list = ((VBox) root.getChildren().get(1));
        Assert.assertEquals(3,list.getChildren().size());
    }


}
