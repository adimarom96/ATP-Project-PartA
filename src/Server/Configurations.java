package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.search.BreadthFirstSearch;
import algorithms.search.ISearchingAlgorithm;

import java.io.OutputStream;
import java.util.Properties;

public class Configurations {
    private static Configurations instance = null;
    private Properties prop;// = new Properties();
    //private int NThreads; // how many threads to use.
    //private AMazeGenerator generator; // which generator to use.(empty/simple/My)
    //private ISearchingAlgorithm search; // which searching algorithm to use.(BFS/DFS/Best)
    //private OutputStream stream; // which compress algorithm to use (simple/My)

    public Properties getProp() {
        return prop;
    }

    private Configurations() {
        prop = new Properties();
    }

    public static Configurations getInstance() {
        if (instance == null)
            instance = new Configurations();
        return instance;
    }

    //----------------getters---------------------


    //----------------setters---------------------
    // todo: need to gets strings as parameters !!!
    //need also to write to the file and update the props.
    public void setNThreads(String str , int n) {
        if (n > 0)
            this.prop.setProperty(str, String.valueOf(n));
    }

    public void setGenerator(String str , String generator) {
        this.prop.setProperty(str, generator);
    }

    public void setSearch(String str , String search) {
        this.prop.setProperty(str, search);
    }
}