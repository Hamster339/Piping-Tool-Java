package tests;
import Objects.Style;
import Objects.Timesig;
import Objects.Tune;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class TuneTest {
    Tune tune = null;
    @Before
    public void setup(){
        tune = new Tune("Amazing Grace", Style.MARCH, Timesig.THREEFOUR,"online");
    }

    @Test
    public void testTune_create(){
        Assert.assertNotEquals(tune,null);
    }

    @Test
    public void testTune_toString(){
        Assert.assertEquals(tune.toString(),"Amazing Grace");
    }

    @Test
    public void testTune_getName(){
        Assert.assertEquals(tune.getName(),"Amazing Grace");
    }

    @Test
    public void testTune_setName(){

        tune.setName("new name");

        Assert.assertEquals(tune.getName(),"new name");
    }

    @Test
    public void testTune_getStyle(){
        Assert.assertEquals(tune.getStyle(),Style.MARCH);
    }

    @Test
    public void testTune_setStyle(){

        tune.setStyle(Style.OTHER);

        Assert.assertEquals(tune.getStyle(),Style.OTHER);
    }

    @Test
    public void testTune_getTimeSig(){
        Assert.assertEquals(tune.getTimeSignature(),Timesig.THREEFOUR);
    }

    @Test
    public void testTune_setTimeSig(){

        tune.setTimeSignature(Timesig.SIXEIGHT);

        Assert.assertEquals(tune.getTimeSignature(),Timesig.SIXEIGHT);
    }

    @Test
    public void testTune_getSheetMusicLocation(){
        Assert.assertEquals(tune.getSheetMusicLocation(),"online");
    }

    @Test
    public void testTune_setSheetMusicLocation(){

        tune.setSheetMusicLocation("in folder");

        Assert.assertEquals(tune.getSheetMusicLocation(),"in folder");
    }
}
