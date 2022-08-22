package Objects;

import java.util.ArrayList;

/**
 * The type List. Represents a list of tunes
 */
public class List {

    private String name;
    private ArrayList<Tune> tunes;

    /**
     * Instantiates a new List.
     *
     * @param name  the name
     * @param tunes ArrayList of tunes
     */
    public List(String name, ArrayList<Tune> tunes) {
        this.name = name;
        this.tunes = tunes;
    }

    /**
     * Add a tune to the list.
     *
     * @param tune the tune to add
     * @return true if successful, false if not
     */
    public boolean add(Tune tune){
        return this.tunes.add(tune);
    }

    /**
     * Remove a tune from the list
     *
     * @param pos the position of the tune to remove
     * @return true if successful, false if not
     */
    public boolean remove(int pos){
        return this.tunes.remove(pos) != null;
    }

    /**
     * Swap the position of two tunes in the list.
     *
     * @param pos1 the position of one tune
     * @param pos2 the position of the other
     * @return true if successful, false if not
     */
    public boolean swap(int pos1, int pos2){
        try {
            Tune temp = this.tunes.get(pos1);
            this.tunes.set(pos1, this.tunes.get(pos2));
            this.tunes.set(pos2, temp);
            return true;
        } catch (Exception e){
            return false;
        }

    }

    @Override
    public String toString() {
        return "name";
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
     * Gets ArrayList of tunes.
     *
     * @return the tunes
     */
    public ArrayList<Tune> getTunes() {
        return tunes;
    }

    /**
     * Sets ArrayList of tunes.
     *
     * @param tunes the tunes
     */
    public void setTunes(ArrayList<Tune> tunes) {
        this.tunes = tunes;
    }


}
