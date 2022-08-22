package Objects;

/**
 * The type Objects.Tune. Represents a bagpipe tune
 */
public class Tune {

    private String name;
    private Style style;
    private Timesig timeSignature;
    private String sheetMusicLocation;

    /**
     * Instantiates a new Objects.Tune.
     *
     * @param name               the name
     * @param style              the style
     * @param timeSignature      the time signature
     * @param sheetMusicLocation the sheet music location
     */
    public Tune(String name,Style style,Timesig timeSignature,String sheetMusicLocation){
        this.name = name;
        this.style = style;
        this.timeSignature = timeSignature;
        this.sheetMusicLocation = sheetMusicLocation;
    }

    @Override
    public String toString() {
        return name;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets style.
     *
     * @return the style
     */
    public Style getStyle() {
        return style;
    }

    /**
     * Sets style.
     *
     * @param style the style
     */
    public void setStyle(Style style) {
        this.style = style;
    }

    /**
     * Gets time signature.
     *
     * @return the time signature
     */
    public Timesig getTimeSignature() {
        return timeSignature;
    }

    /**
     * Sets time signature.
     *
     * @param timeSignature the time signature
     */
    public void setTimeSignature(Timesig timeSignature) {
        this.timeSignature = timeSignature;
    }

    /**
     * Gets sheet music location.
     *
     * @return the sheet music location
     */
    public String getSheetMusicLocation() {
        return sheetMusicLocation;
    }

    /**
     * Sets sheet music location.
     *
     * @param sheetMusicLocation the sheet music location
     */
    public void setSheetMusicLocation(String sheetMusicLocation) {
        this.sheetMusicLocation = sheetMusicLocation;
    }
}
