import Objects.*;

import java.io.IOException;
import java.util.ArrayList;


public class Main {
    
    public static void main(String[] args) {
        //initialise with empty rep
        Repertoire repertoire = new Repertoire(new ArrayList<>(), new ArrayList<>());

        //load tunes from file
        try{
            repertoire.load();
        } catch (IOException e){
            System.out.println("An error occurred when loading");
        }


        repertoire.addTune(new Tune("Highland Harry",Style.STRATHSPAY,Timesig.SIXEIGHT,"Book"));


        //save back to file
        try{
            repertoire.save();
            System.out.println("Save Successful");
        } catch (IOException e){
            System.out.println("An error occurred when saving");
            System.out.println(e.getMessage());
        }


    }

}
