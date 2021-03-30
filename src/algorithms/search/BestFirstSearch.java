package algorithms.search;

import java.util.PriorityQueue;
import java.util.Queue;

public class BestFirstSearch extends BreadthFirstSearch {
    // private function that we send with the queue so it know how to compare between 2 elements
    private static int comp(AState s1, AState s2){
        if(s1.getCost()>s2.getCost()){
            return 1;
        }
        if(s1.getCost()<s2.getCost()){
            return -1;
        }
        return 0;
    }
    // constructor
    public BestFirstSearch() {
        super("BestFS", new PriorityQueue<AState>((AState a1, AState a2) -> Double.compare(a2.getCost(), a1.getCost())));//TODO: FIX the priority q
        //super("BestFS", new PriorityQueue<AState>(BestFirstSearch::comp));
    }
}