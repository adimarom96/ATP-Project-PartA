package algorithms.search;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch extends ASearchingAlgorithm {
    protected Queue<AState> state_q;

    public BreadthFirstSearch() {
        super("BFS");
        numberOfNodesEvaluated = 0;
        this.state_q = new LinkedList<>();
    }

    public BreadthFirstSearch(String name, Queue<AState> state_q) { // constrctuor for the sucsccsor
        super(name);
        numberOfNodesEvaluated = 0;
        this.state_q = state_q;
    }

    @Override
    public int getNumberOfNodesEvaluated() {
        return numberOfNodesEvaluated;
    }
    public Solution solve(ISearchable problem) {
        if(count != 0){
            problem.restStates();
        }
        AState start = problem.getStart();
        AState current;
        AState prev = start;
        AState goal = problem.getGoal();
        count++;
        this.state_q = new LinkedList<>(); // q that will hold all the possible moves that we didnot visted
        ArrayList<AState> neighbors; // all the possible moves for the current state
        Solution sol = new Solution(goal);
        state_q.add(start);
        while (!state_q.isEmpty()) { // while this q is not empty we will keep run
            current = state_q.peek();
            if (current.equals(goal)) { // if we found the wanted state
                goal.setPreState(prev);
                numberOfNodesEvaluated++;
                break;
            }
            prev = state_q.remove();
            neighbors = problem.getAllPossible(prev); // get all the neighbors
            for (AState s : neighbors
            ) {
                if (s.getPreState() == null) { // check which of the neighbors hasnot been handle
                    s.setPreState(current);
                    if (s.equals(goal)) {
                        numberOfNodesEvaluated++;
                        goal = s;
                        return new Solution(goal);
                    }
                    state_q.add(s);
                }
            }
            numberOfNodesEvaluated++;
        }
        return sol;
    }

}
