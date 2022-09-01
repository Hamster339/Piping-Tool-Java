package tests;

import Objects.Style;
import Objects.Timesig;
import org.junit.Assert;
import org.junit.Test;

/**
 * The test class for enums Style and Timesig.
 */
public class EnumTest {

    /**
     * Test timesig conversion from string to enum is correct.
     */
    @Test
    public void testTimesigConversion(){

        Assert.assertEquals(Timesig.TWOTWO,Timesig.convert("2/2"));
        Assert.assertEquals(Timesig.TWOFOUR,Timesig.convert("2/4"));
        Assert.assertEquals(Timesig.THREEFOUR,Timesig.convert("3/4"));
        Assert.assertEquals(Timesig.FOURFOUR,Timesig.convert("4/4"));
        Assert.assertEquals(Timesig.SIXEIGHT,Timesig.convert("6/8"));
        Assert.assertEquals(Timesig.NINEEIGHT,Timesig.convert("9/8"));
        Assert.assertEquals(Timesig.TWELVEEIGHT,Timesig.convert("12/8"));

    }

    /**
     * Test style conversion from string to enum is correct
     */
    @Test
    public void testStyleConversion(){

        Assert.assertEquals(Style.MARCH,Style.convert("March"));
        Assert.assertEquals(Style.STRATHSPAY,Style.convert("Strathspay"));
        Assert.assertEquals(Style.REEL,Style.convert("Reel"));
        Assert.assertEquals(Style.WALTZ,Style.convert("Waltz"));
        Assert.assertEquals(Style.JIG,Style.convert("Jig"));
        Assert.assertEquals(Style.SLOWAIR,Style.convert("Slow Air"));
        Assert.assertEquals(Style.HORNPIPE,Style.convert("Hornpipe"));
        Assert.assertEquals(Style.PIBROCH,Style.convert("Pibroch"));
        Assert.assertEquals(Style.OTHER,Style.convert("Other"));

    }

    /**
     * Test timesig toString method is correct.
     */
    @Test
    public void testTimesigToString(){

        Assert.assertEquals("2/2",Timesig.TWOTWO.toString());
        Assert.assertEquals("2/4",Timesig.TWOFOUR.toString());
        Assert.assertEquals("3/4",Timesig.THREEFOUR.toString());
        Assert.assertEquals("4/4",Timesig.FOURFOUR.toString());
        Assert.assertEquals("6/8",Timesig.SIXEIGHT.toString());
        Assert.assertEquals("9/8",Timesig.NINEEIGHT.toString());
        Assert.assertEquals("12/8",Timesig.TWELVEEIGHT.toString());

    }

    /**
     * Test style toString method is correct.
     */
    @Test
    public void testStyleToString(){

        Assert.assertEquals("March",Style.MARCH.toString());
        Assert.assertEquals("Strathspay",Style.STRATHSPAY.toString());
        Assert.assertEquals("Reel",Style.REEL.toString());
        Assert.assertEquals("Waltz",Style.WALTZ.toString());
        Assert.assertEquals("Jig",Style.JIG.toString());
        Assert.assertEquals("Slow Air",Style.SLOWAIR.toString());
        Assert.assertEquals("Hornpipe",Style.HORNPIPE.toString());
        Assert.assertEquals("Pibroch",Style.PIBROCH.toString());
        Assert.assertEquals("Other",Style.OTHER.toString());

    }
}
