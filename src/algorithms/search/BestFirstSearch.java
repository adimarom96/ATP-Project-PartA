package algorithms.search;

import java.util.PriorityQueue;
import java.util.Queue;

public class BestFirstSearch extends BreadthFirstSearch {

/*    // private function that we send with the queue so it know how to compare between 2 elements
    private static int comp(AState s1, AState s2){
        return (int)(s1.getCost() - s2.getCost());

        if(s1.getCost()>s2.getCost()){
            return 1;
        }
        if(s1.getCost()<s2.getCost()){
            return -1;
        }
        return 0;
    }*/

    // constructor
    public BestFirstSearch() {
        //TODO: FIX the priority q
        super("BestFirstSearch", new PriorityQueue<AState>(2, new costCompare()));
        //super("BestFS", new PriorityQueue<AState>((AState a1, AState a2) -> Double.compare(a2.getCost(), a1.getCost())));
        //super("BestFS", new PriorityQueue<AState>(BestFirstSearch::comp));
        isBest = true;
    }
}