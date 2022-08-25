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
        this.tunes = new ArrayList<>();
        this.tunes.addAll(tunes);
    }

    /**
     * Add a tune to the list.
     *
     * @param tune the tune to add
     */
    public void add(Tune tune){
        this.tunes.add(tune);
    }

    /**
     * Remove a tune from the list
     *
     * @param pos the position of the tune to remove
     */
    public void remove(int pos){

        this.tunes.remove(pos);
    }

    /**
     * Swap the position of two tunes in the list.
     *
     * @param pos1 the position of one tune
     * @param pos2 the position of the other
     */
    public void swap(int pos1, int pos2){
        Tune temp = this.tunes.get(pos1);
        this.tunes.set(pos1, this.tunes.get(pos2));
        this.tunes.set(pos2, temp);

    }

    @Override
    public String toString() {
        return this.name;
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


}
