package Server;

import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.search.BreadthFirstSearch;
import algorithms.search.ISearchingAlgorithm;

import java.io.OutputStream;

public class Configurations {
    private static Configurations instance = null;
    private int NThreads; // how many threads to use.
    private AMazeGenerator generator; // which generator to use.(empty/simple/My)
    private OutputStream stream; // which compress algorithm to use (simple/My)
    private ISearchingAlgorithm search; // which searching algorithm to use.(BFS/DFS/Best)

    private Configurations() {

    }

    public static Configurations getInstance() {
        if (instance == null)
            instance = new Configurations();
        return instance;
    }

    //----------------getters---------------------
    public int getNThreads() {
        return NThreads;
    }

    public AMazeGenerator getGenerator() {
        return generator;
    }

    public OutputStream getStream() {
        return stream;
    }

    public ISearchingAlgorithm getSearch() {
        return search;
    }

    //----------------setters---------------------
    public void setNThreads(int NThreads) {
        if (NThreads > 0)
            this.NThreads = NThreads;
    }

    public void setGenerator(AMazeGenerator generator) {
        if (generator != null)
            this.generator = generator;
    }

    public void setStream(OutputStream stream) {
        if (stream != null)
            this.stream = stream;
    }

    public void setSearch(ISearchingAlgorithm search) {
        if (search != null)
            this.search = search;
    }
}