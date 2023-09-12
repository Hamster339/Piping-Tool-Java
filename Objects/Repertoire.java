package Objects;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 * The type Repertoire. Represents all the tunes and lists in the users repertoire
 */
public class Repertoire {

    /**
     * The Master list.
     */
    ArrayList<Tune> masterList;
    /**
     * The Lists.
     */
    ArrayList<List> lists;

    /**
     * Instantiates a new blank Repertoire.
     *
     */
    public Repertoire(){
        this.masterList =  new ArrayList<>();
        this.lists = new ArrayList<>();

    }

    /**
     * Loads The repertoire from disk
     *
     */
    public void load(){

        //check if directory exists,
        //if not skip loading
        File dir = new File("Repertoire");
        if (!dir.isDirectory()){
            System.out.println("Nothing to load");
            return;
        }

        //if no files to load, skip loading
        File[] files = dir.listFiles();
        if (files == null){
            return;
        }

        //load Master File
        try {
            Scanner myReader = new Scanner(new File("Repertoire/MASTER.prl"));
            if (myReader.nextLine().equals("MASTER")){
                while (myReader.hasNextLine()) {
                    String nextLine = myReader.nextLine();

                    //skip if line empty
                    if (nextLine.equals("")) {
                        continue;
                    }

                    String[] data = nextLine.split(",");
                    String notes = data[3];
                    if (Objects.equals(notes, "None")){
                        notes = "";
                    }
                    this.masterList.add(new Tune(data[0],Style.convert(data[1]),Timesig.convert(data[2]),notes));
                }

            } else{
                myReader.close();
                throw new FileNotFoundException("master file exits but is malformed");
            }
            myReader.close();
        } catch (FileNotFoundException e) {
                System.out.println("Master File not found, nothing to load");
                System.out.println(e.getMessage());
                return;
        }

        //for every file in directory
        for (File file:files){
            String name = file.getName();

            //if prl file, processes it, if not skip
            if (!name.endsWith(".prl")) {
                continue;
            }

            //if master file skip
            if (name.equals("MASTER.prl")){
                continue;
            }

            //read all lines in file
            Scanner myReader;
            try {
                myReader = new Scanner(file);
            } catch (FileNotFoundException e) {
                System.out.printf("error reading file: %s",file.getName());
                continue;
            }

            List list = new List(myReader.nextLine(),new ArrayList<>());
            boolean error = false;
            while (myReader.hasNextLine()) {
                try {
                    String[] data = myReader.nextLine().split(",");
                    //if read data not in correct form, exit out with exception
                    if (data.length!=4){
                        throw new IndexOutOfBoundsException("Malformed File detected");
                    }

                    //find corresponding tune in the master list, and add a copy of that to the list
                    //This way only one tune object is created per tune
                    boolean added = false;
                    for (Tune tune:this.masterList){
                        if (tune.getName().equals(data[0])
                                && tune.getStyle().equals(Style.convert(data[1]))
                                && tune.getTimeSignature().equals(Timesig.convert(data[2]))
                                && tune.getSheetMusicLocation().equals(data[3]))
                        {
                            list.add(tune);
                            added = true;
                            break;
                        }
                    }

                    //if tune in list and not in master list, file is considered malformed. exit out with exception
                    if (!added) {
                        throw new IndexOutOfBoundsException(String.format("Tune in %s not is master list. File malformed",data[0]));
                    }

                // if any errors, deal with them
                } catch (IndexOutOfBoundsException e) {
                    myReader.close();
                    error = true;
                    System.out.println(e.getMessage());
                    System.out.println("deleting Malformed File...");
                    if(file.delete()){
                        System.out.println("Deleted Malformed File");
                    } else {
                        System.out.println("Could not delete malformed file");
                    }
                    break;
                }
            }
            if (!error){
                this.lists.add(list);
            }
            myReader.close();

        }
        System.out.println("load Succeeded");
    }

    /**
     * Saves the repertoire to the disk
     *
     * @throws IOException the io exception
     */
    public void save() throws IOException {

        //ensure Directory exists
        File dir = new File("Repertoire");
        if (!dir.isDirectory()){
            if (!dir.mkdir()){
                throw new IOException("Could not create Repertoire Directory");
            }
        }

        //empty directory
        File[] files = dir.listFiles();
        if (files != null){
            for (File file: files) {
                System.out.println(file.getName());
                if(!file.delete()){
                    System.out.println("Failed to remove File");
                }
            }
        }

        //Write master list.
        //list name cannot be "MASTER". enforces in list creation validation
        FileWriter myWriter = new FileWriter("Repertoire/MASTER.prl");
        myWriter.write(String.format("%s\n","MASTER"));
        for (Tune tune:masterList) {
            String notes = tune.getSheetMusicLocation();
            if (Objects.equals(notes, "")) {
                notes = "None";
            }
            myWriter.write(String.format("%s,%s,%s,%s\n", tune.getName(),tune.getStyle(),tune.getTimeSignature(),notes));
        }
        myWriter.close();

        //write all other lists
        for (List list: this.lists) {
            myWriter = new FileWriter(String.format("Repertoire/%s.prl", list.getName()));
            myWriter.write(String.format("%s\n", list.getName()));
            for (Tune tune : list.getTunes()) {
                myWriter.write(String.format("%s,%s,%s,%s\n", tune.getName(), tune.getStyle(), tune.getTimeSignature(), tune.getSheetMusicLocation()));
            }
            myWriter.close();
        }
        System.out.println("Save Succeeded");
        myWriter.close();

    }

    /**
     * Adds a new tune to the master list of the repertoire.
     *
     * @param tune the tune
     */
    public void addTune(Tune tune){
        this.masterList.add(tune);
    }


    /**
     * Adds a new empty list to the repertoire.
     *
     * @param name the name of the list
     */
    public void addList(String name){
        this.lists.add(new List(name,new ArrayList<>()));
    }

    /**
     * Adds a new list to the repertoire.
     *
     * @param list  the list to be added
     */
    public void addList(List list){
        this.lists.add(list);
    }


    /**
     * Removed A list From the repertoire.
     *
     * @param list  the list to be removed
     */
    public void DelList(List list){
        this.lists.remove(list);
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
