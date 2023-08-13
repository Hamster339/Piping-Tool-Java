package tests;

import Objects.*;
import org.junit.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 * The test class for the repertoire object.
 */
public class RepertoireTest {


    private Repertoire rep;

    @BeforeClass
    public static void saveFiles() throws IOException {
        File repDir = new File("/C:/Users/Work/Documents/Git projects/Pipeing Project/Piping_Tune_List/Repertoire");
        if (repDir.exists()){
            for (File f: Objects.requireNonNull(repDir.listFiles())) {
                Scanner r = new Scanner(f);
                new File("/C:/Users/Work/Documents/Git projects/Pipeing Project/Piping_Tune_List//temp/").mkdir();
                FileWriter w = new FileWriter(String.format("/C:/Users/Work/Documents/Git projects/Pipeing Project/Piping_Tune_List//temp/%s",f.getName()));
                while (r.hasNextLine()){
                    w.write(r.nextLine()+"\n");
                }
                w.close();
                r.close();
            }
        }
    }

    /**
     * Initialises repertoire object and clears save files before each test.
     */
    @Before
    public void setup(){
        rep = new Repertoire();

        File repDir = new File("/C:/Users/Work/Documents/Git projects/Pipeing Project/Piping_Tune_List/Repertoire");
        if (repDir.exists()){
            for (File f: Objects.requireNonNull(repDir.listFiles())) {
                f.delete();
            }
            repDir.delete();
        }
    }

    @AfterClass
    public static void restoreFiles() throws IOException {
        File tempDir = new File("/C:/Users/Work/Documents/Git projects/Pipeing Project/Piping_Tune_List/temp");
        if (tempDir.exists()){
            for (File f: Objects.requireNonNull(tempDir.listFiles())) {
                Scanner r = new Scanner(f);
                new File("/C:/Users/Work/Documents/Git projects/Pipeing Project/Piping_Tune_List/Repertoire/").mkdir();
                FileWriter w = new FileWriter(String.format("/C:/Users/Work/Documents/Git projects/Pipeing Project/Piping_Tune_List/Repertoire/%s",f.getName()));
                while (r.hasNextLine()){
                    w.write(r.nextLine()+"\n");
                }
                w.close();
                r.close();
                f.delete();
            }
            tempDir.delete();
        }
    }


    /**
     * Test repertoire get master list method.
     */
    @Test
    public void testRepertoire_GetMasterList(){
        Assert.assertEquals(rep.getMasterList(),new ArrayList<>());
    }

    /**
     * Test repertoire get lists method.
     */
    @Test
    public void testRepertoire_GetLists() {
        Assert.assertEquals(rep.getLists(),new ArrayList<>());

    }

    /**
     * Test repertoire add tune method.
     */
    @Test
    public void testRepertoire_AddTune(){
        Tune t = new Tune("testTune", Style.MARCH, Timesig.FOURFOUR,"online");
        ArrayList<Tune> l = new ArrayList<>();
        l.add(t);

        rep.addTune(t);

        Assert.assertEquals(rep.getMasterList(),l);
    }

    /**
     * Test repertoire add empty list method.
     */
    @Test
    public void testRepertoire_AddEmptyList(){
        rep.addList("test");

        Assert.assertEquals(1,rep.getLists().size());
        Assert.assertEquals("test",rep.getLists().get(0).getName());
        Assert.assertEquals(0,rep.getLists().get(0).getTunes().size());
    }

    /**
     * Test repertoire add list method.
     */
    @Test
    public void testRepertoire_AddList(){
        Tune t = new Tune("test",Style.MARCH,Timesig.FOURFOUR,"testLocation");
        ArrayList<Tune> tunes = new ArrayList<>();
        tunes.add(t);
        List l = new List("test",tunes);

        rep.addList(l);

        Assert.assertEquals(1,rep.getLists().size());
        Assert.assertEquals("test",rep.getLists().get(0).getName());
        Assert.assertEquals(1,rep.getLists().get(0).getTunes().size());
        Assert.assertEquals(t,rep.getLists().get(0).getTunes().get(0));

    }


    /**
     * Tests save() creates files if they don't already exist.
     *
     * @throws IOException the io exception
     */
    @Test
    public void testRepertoire_Save_TestCreatesFiles() throws IOException {
        inputData(rep);

        rep.save();

        File dir = new File("/C:/Users/Work/Documents/Git projects/Pipeing Project/Piping_Tune_List/Repertoire");
        Assert.assertTrue(dir.isDirectory());
        Assert.assertEquals(3, dir.listFiles().length);

        for (File file: Objects.requireNonNull(dir.listFiles())){
            Assert.assertTrue(file.getName().equals("In progress.prl")
                    || file.getName().equals("Learnt.prl")
                    || file.getName().equals("MASTER.prl"));
        }


    }

    /**
     * Tests save() saves objects to disk correctly.
     *
     * @throws IOException the io exception
     */
    @Test
    public void testRepertoire_Save_TestCorrectContent() throws IOException {
        inputData(rep);
        rep.save();

        TestFiles("MASTER.prl");
        TestFiles("In progress.prl");
        TestFiles("Learnt.prl");
    }

    /**
     * Tests save() clears unnecessary files from directory.
     *
     * @throws IOException the io exception
     */
    @Test
    public void testRepertoire_Save_TestDirCleared() throws IOException {

        boolean b = new File("C:/Users/Work/Documents/Git projects/Pipeing Project/Piping_Tune_List/Repertoire").mkdir();

        FileWriter writer = new FileWriter("C:/Users/Work/Documents/Git projects/Pipeing Project/Piping_Tune_List/Repertoire/test.txt");
        writer.close();

        rep.save();

        File dir = new File("C:/Users/Work/Documents/Git projects/Pipeing Project/Piping_Tune_List/Repertoire/");


        Assert.assertFalse(new File("C:/Users/Work/Documents/Git projects/Pipeing Project/Piping_Tune_List/Repertoire/test.txt").exists());
    }

    /**
     * Test load() behaves correctly when loading nothing.
     *
     * @throws IOException the io exception
     */
    @Test
    public void testRepertoire_Load_Initial() throws IOException {
        rep.load();

        Assert.assertEquals(0,rep.getMasterList().size());
        Assert.assertEquals(0,rep.getLists().size());
    }

    /**
     * Tests load() loads data from disk correctly.
     *
     * @throws IOException the io exception
     */
    @Test
    public void testRepertoire_Load_WithData() throws IOException {

        inputData(rep);
        rep.save();
        rep = new Repertoire();

        rep.load();

        Assert.assertEquals(4,rep.getMasterList().size());
        Assert.assertEquals(2,rep.getLists().size());
        Assert.assertEquals(2,rep.getLists().get(0).getTunes().size());
        Assert.assertEquals(2,rep.getLists().get(1).getTunes().size());

        for (Tune t:rep.getMasterList()){
            Assert.assertTrue(t.getName().equals("Amazing Grace")
                    ||t.getName().equals("When the battle's over")
                    || t.getName().equals("Itchy figures")
                    || t.getName().equals("Highland Harry") );
        }

        for (Tune t:rep.getLists().get(0).getTunes()){
            Assert.assertTrue((t.getName().equals("Amazing Grace")
                    ||t.getName().equals("When the battle's over"))

                    || (t.getName().equals("Itchy figures")
                    || t.getName().equals("Highland Harry")));
        }

        for (Tune t:rep.getLists().get(1).getTunes()){
            Assert.assertTrue((t.getName().equals("Amazing Grace")
                    ||t.getName().equals("When the battle's over"))

                    || (t.getName().equals("Itchy figures")
                    || t.getName().equals("Highland Harry")));
        }

    }

    /**
     * Tests if load() removes malformed files.
     *
     * @throws IOException the io exception
     */
    @Test
    public void testRepertoire_Load_malformedFileRemoved() throws IOException {

        inputData(rep);
        rep.save();

        FileWriter writer = new FileWriter("/C:/Users/Work/Documents/Git projects/Pipeing Project/Piping_Tune_List/Repertoire/test.prl");
        writer.write("hel,o\nh,ey,y");
        writer.close();

        rep.load();

        Assert.assertFalse(new File("C:/Users/Work/Documents/Git projects/Pipeing Project/Piping_Tune_List/Repertoire/test.prl").exists());
    }

    /**
     * Tests if load() removed lists that contain tunes not in master list
     *
     * @throws IOException the io exception
     */
    @Test
    public void testRepertoire_Load_ListTuneNotInMasterListRemoved() throws IOException {
        inputData(rep);
        rep.getLists().get(0).add(new Tune("test",Style.MARCH,Timesig.FOURFOUR,"test"));
        rep.save();

        rep.load();

        Assert.assertFalse(new File(String.format("/C:/Users/Work/Documents/Git projects/Pipeing Project/Piping_Tune_List/Repertoire/%s.prl",rep.getLists().get(0).getName())).exists());
    }

    //helper function
    private  void TestFiles(String name) throws FileNotFoundException {
        File file = new File(String.format("C:/Users/Work/Documents/Git projects/Pipeing Project/Piping_Tune_List/Repertoire/%s",name));
        Scanner reader = new Scanner(file);
        String actualData = "";
        while (reader.hasNextLine()){
            actualData = actualData + reader.nextLine();
        }

        file = new File(String.format("C:/Users/Work/Documents/Git projects/Pipeing Project/Piping_Tune_List/tests/testFiles/%s",name));
        reader = new Scanner(file);
        String expectedData = "";
        while (reader.hasNextLine()){
            expectedData = expectedData + reader.nextLine();
        }

        Assert.assertEquals(expectedData,actualData);

        reader.close();
    }

    //helper function
    protected static void inputData(Repertoire rep)  {
        rep.addList("In progress");
        rep.addList("Learnt");

        rep.addTune(new Tune("Amazing Grace", Style.MARCH, Timesig.FOURFOUR,"online"));
        rep.addTune(new Tune("When the battle's over",Style.MARCH,Timesig.THREEFOUR,"online"));
        rep.addTune(new Tune("Itchy figures",Style.HORNPIPE,Timesig.SIXEIGHT,"online"));
        rep.addTune(new Tune("Highland Harry",Style.STRATHSPAY,Timesig.FOURFOUR,"online"));

        rep.getLists().get(0).add(rep.getMasterList().get(0));
        rep.getLists().get(0).add(rep.getMasterList().get(1));

        rep.getLists().get(1).add(rep.getMasterList().get(2));
        rep.getLists().get(1).add(rep.getMasterList().get(3));
    }


}
