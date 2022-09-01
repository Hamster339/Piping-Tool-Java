package Objects;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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

        //check if directory exists,
        //if not skip loading
        File dir = new File("Repertoire");
        if (!dir.isDirectory()){
            return;
        }

        //load Master File
        try {
            Scanner myReader = new Scanner(new File("Repertoire/MASTER.prl"));
            if (myReader.nextLine().equals("MASTER")){
                while (myReader.hasNextLine()) {
                    String[] data = myReader.nextLine().split(",");
                    this.masterList.add(new Tune(data[0],Style.convert(data[1]),Timesig.convert(data[2]),data[3]));
                }

            } else{
                throw new FileNotFoundException("master file exits but is malformed");
            }
            myReader.close();
        } catch (FileNotFoundException e) {
                System.out.println("Master File not found");
                System.out.println(e.getMessage());
                return;
        }

        //load Lists
        File[] files = dir.listFiles();
        if (files != null){
            for (File file:files){
                String name = file.getName();

                //if prl file, processes it, if not skip
                if (name.endsWith(".prl")){

                    //if master file skip
                    if (name.equals("MASTER.prl")){
                        continue;
                    }

                    Scanner myReader = new Scanner(file);
                    List list = new List(myReader.nextLine(),new ArrayList<>());
                    while (myReader.hasNextLine()) {
                        try {
                            String[] data = myReader.nextLine().split(",");
                            if (data.length!=4){
                                System.out.println(data.length);
                                throw new IndexOutOfBoundsException("Malformed File");
                            }

                            for (Tune tune:this.masterList){
                                if (tune.getName().equals(data[0])
                                        && tune.getStyle().equals(Style.convert(data[1]))
                                        && tune.getTimeSignature().equals(Timesig.convert(data[2]))
                                        && tune.getSheetMusicLocation().equals(data[3]))
                                {
                                    list.add(tune);
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("Malformed File detected");
                            System.out.println(e.getMessage());
                            if(file.delete()){
                                System.out.println("Deleted Malformed File");
                            } else {
                                System.out.println("Could not delete malformed file");
                            }
                            break;
                        }
                    }
                    this.lists.add(list);
                }

            }
        }
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
                if(!file.delete()){
                    System.out.println("Failed to remove File");
                }
            }
        }

        //Write master list.
        //list name cannot be "MASTER". must be enforced somewhere
        //no item can include ,
        FileWriter myWriter = new FileWriter("Repertoire/MASTER.prl");
        myWriter.write(String.format("%s\n","MASTER"));
        for (Tune tune:masterList) {
            myWriter.write(String.format("%s,%s,%s,%s\n", tune.getName(),tune.getStyle(),tune.getTimeSignature(),tune.getSheetMusicLocation()));
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
     * @param name  the name of the list
     * @param tunes the tunes to be added
     */
    public void addList(String name, ArrayList<Tune> tunes){
        this.lists.add(new List(name,tunes));
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
