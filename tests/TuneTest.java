package tests;
import Objects.Style;
import Objects.Timesig;
import Objects.Tune;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * The test class for the Tune object.
 */
public class TuneTest {

    private Tune tune = null;

    /**
     * Setup objects.
     */
    @Before
    public void setup(){
        tune = new Tune("Amazing Grace", Style.MARCH, Timesig.THREEFOUR,"online");
    }

    /**
     * Test tune create.
     */
    @Test
    public void testTune_create(){
        Assert.assertNotEquals(tune,null);
    }

    /**
     * Test tune toString method.
     */
    @Test
    public void testTune_toString(){
        Assert.assertEquals(tune.toString(),"Amazing Grace");
    }

    /**
     * Test tune get name method.
     */
    @Test
    public void testTune_getName(){
        Assert.assertEquals(tune.getName(),"Amazing Grace");
    }

    /**
     * Test tune set name method.
     */
    @Test
    public void testTune_setName(){

        tune.setName("new name");

        Assert.assertEquals(tune.getName(),"new name");
    }

    /**
     * Test tune get style method.
     */
    @Test
    public void testTune_getStyle(){
        Assert.assertEquals(tune.getStyle(),Style.MARCH);
    }

    /**
     * Test tune set style method.
     */
    @Test
    public void testTune_setStyle(){

        tune.setStyle(Style.OTHER);

        Assert.assertEquals(tune.getStyle(),Style.OTHER);
    }

    /**
     * Test tune get time sig method.
     */
    @Test
    public void testTune_getTimeSig(){
        Assert.assertEquals(tune.getTimeSignature(),Timesig.THREEFOUR);
    }

    /**
     * Test tune set time sig method.
     */
    @Test
    public void testTune_setTimeSig(){

        tune.setTimeSignature(Timesig.SIXEIGHT);

        Assert.assertEquals(tune.getTimeSignature(),Timesig.SIXEIGHT);
    }

    /**
     * Test tune get sheet music location method.
     */
    @Test
    public void testTune_getSheetMusicLocation(){
        Assert.assertEquals(tune.getSheetMusicLocation(),"online");
    }

    /**
     * Test tune set sheet music location method.
     */
    @Test
    public void testTune_setSheetMusicLocation(){

        tune.setSheetMusicLocation("in folder");

        Assert.assertEquals(tune.getSheetMusicLocation(),"in folder");
    }
}
