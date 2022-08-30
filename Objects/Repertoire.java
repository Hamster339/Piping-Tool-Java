package Objects;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The type Repertoire. Represents all the tunes and lists in the users repertoire
 */
public class Repertoire {

    ArrayList<Tune> masterList;
    ArrayList<List> lists;

    /**
     * Instantiates a new Repertoire.
     *
     * @param masterList the master list
     * @param lists      the lists
     */
    public Repertoire(ArrayList<Tune> masterList, ArrayList<List> lists){
        this.masterList = masterList;
        this.lists = lists;

    }

    /**
     * Loads The repertoire from disk
     *
     * @throws IOException the io exception
     */
    public void load() throws IOException{

    }

    /**
     * Saves the repertoire to the disk
     *
     * @throws IOException the io exception
     */
    public void save() throws IOException {

        //empty directory
        File dir = new File("Repertoire");
        File[] filesList = dir.listFiles();
        if (filesList!=null) {
            for (File file : filesList) {
                file.delete();
            }
        }

        //Write master list.
        //list name cannot be "MASTER". must be enforced somewhere
        FileWriter myWriter = new FileWriter("Repertoire/MASTER.prl");
        myWriter.write(String.format("N:<%s>\n","MASTER"));
        for (Tune tune:masterList) {
            myWriter.write(String.format("N:<%s>T:<%s>S:<%s>L<%s>\n", tune.getName(),tune.getTimeSignature(),tune.getStyle(),tune.getSheetMusicLocation()));
        }
        myWriter.close();

        //write all other lists
        for (List list: this.lists) {
            myWriter = new FileWriter(String.format("Repertoire/%s.prl", list.getName()));
            myWriter.write(String.format("N:<%s>\n", list.getName()));
            for (Tune tune : list.getTunes()) {
                myWriter.write(String.format("N:<%s>T:<%s>S:<%s>L<%s>\n", tune.getName(), tune.getTimeSignature(), tune.getStyle(), tune.getSheetMusicLocation()));
            }
            myWriter.close();
        }

    }

    /**
     * Gets master list.
     *
     * @return the master list
     */
    public ArrayList<Tune> getMasterList() {
        return masterList;
    }

    /**
     * Gets other lists.
     *
     * @return the lists
     */
    public ArrayList<List> getLists() {
        return lists;
    }
}
