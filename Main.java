import Objects.*;

import java.io.IOException;
import java.util.ArrayList;


public class Main {
    
    public static void main(String[] args) {

        ArrayList<Tune> tunes = new ArrayList<>();
        tunes.add(new Tune("Amazing Grace",Style.MARCH,Timesig.FOURFOUR,"Online"));
        tunes.add(new Tune("Highland Cathedral",Style.MARCH,Timesig.FOURFOUR,"Online"));
        tunes.add(new Tune("Itchy Figures",Style.HORNPIPE,Timesig.THREEFOUR,"Online"));
        tunes.add(new Tune("Green Hills of Tyrol",Style.MARCH,Timesig.THREEFOUR,"Online"));

        ArrayList<List> lists = new ArrayList<>();
        lists.add(new List("MASTER",tunes));
        lists.add(new List("In Progress",new ArrayList<>(tunes.subList(0,2))));
        lists.add(new List("Learnt",new ArrayList<>(tunes.subList(2,4))));

        Repertoire repertoire = new Repertoire(tunes,lists);

        try{
            repertoire.save();
            System.out.println("Save Successful");
        } catch (IOException e){
            System.out.println("An error occurred when saving");
            System.out.println(e.getMessage());
        }

    }

}
