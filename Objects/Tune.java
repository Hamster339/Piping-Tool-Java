package Objects;

/**
 * The type Objects.Tune. Represents a bagpipe tune
 */
public class Tune {

    private String name;
    private Style style;
    private Timesig timeSignature;
    private String notes;

    /**
     * Instantiates a new Objects.Tune.
     *
     * @param name               the name
     * @param style              the style
     * @param timeSignature      the time signature
     * @param notes              Notes as a string
     */
    public Tune(String name,Style style,Timesig timeSignature,String notes){
        this.name = name;
        this.style = style;
        this.timeSignature = timeSignature;
        this.notes = notes;
    }

    @Override
    public String toString() {
        return name;
    }

    /**
     * Gets the name of the tune.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the tune.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the style of the tune.
     *
     * @return the style
     */
    public Style getStyle() {
        return style;
    }

    /**
     * Sets the style of the tune.
     *
     * @param style the style
     */
    public void setStyle(Style style) {
        this.style = style;
    }

    /**
     * Gets the time signature of the tune.
     *
     * @return the time signature
     */
    public Timesig getTimeSignature() {
        return timeSignature;
    }

    /**
     * Sets the time signature of the tune.
     *
     * @param timeSignature the time signature
     */
    public void setTimeSignature(Timesig timeSignature) {
        this.timeSignature = timeSignature;
    }

    /**
     * Gets the sheet music location of the tune.
     *
     * @return the sheet music location
     */
    public String getSheetMusicLocation() {
        return notes;
    }

    /**
     * Sets the sheet music location of the tune.
     *
     * @param sheetMusicLocation the sheet music location
     */
    public void setSheetMusicLocation(String sheetMusicLocation) {
        this.notes = sheetMusicLocation;
    }
}
