package tests;

import Objects.List;
import Objects.Style;
import Objects.Timesig;
import Objects.Tune;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class ListTest {

    List list = null;
    ArrayList<Tune> tunes = null;
    @Before
    public void setup(){
        Tune tune1 = new Tune("Amazing Grace", Style.MARCH, Timesig.THREEFOUR,"online");
        Tune tune2 = new Tune("Highland Harry", Style.STRATHSPAY, Timesig.TWOFOUR,null);
        Tune tune3 = new Tune("Jig", Style.JIG, Timesig.SIXEIGHT,"in book");

        tunes = new ArrayList<>();
        tunes.add(tune1);
        tunes.add(tune2);
        tunes.add(tune3);

        list = new List("new list",tunes);
    }

    @Test
    public void testList_toString(){

        Assert.assertEquals(list.toString(),"new list");
    }

    @Test
    public void testList_getName(){

        Assert.assertEquals(list.getName(),"new list");
    }

    @Test
    public void testList_setName(){

        list.setName("old list");

        Assert.assertEquals(list.getName(),"old list");
    }

    @Test
    public void testList_getTunes(){

        Assert.assertEquals(list.getTunes(),tunes);
    }

    @Test
    public void testList_add(){

        Tune newTune = new Tune("new", Style.MARCH, Timesig.THREEFOUR,"online");
        list.add(newTune);

        Assert.assertEquals(list.getTunes().get(list.getTunes().size()-1),newTune);
    }

    @Test
    public void testList_remove(){
        Tune tuneToRemove = list.getTunes().get(2);
        list.remove(2);

        Assert.assertEquals(-1,list.getTunes().indexOf(tuneToRemove));
        Assert.assertEquals(2, list.getTunes().size());
    }

    @Test
    public void testList_swap(){

        list.swap(0,2);


        Assert.assertEquals(tunes.get(0),list.getTunes().get(2));
        Assert.assertEquals(tunes.get(2),list.getTunes().get(0));
    }
}
