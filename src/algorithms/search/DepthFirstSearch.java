package algorithms.search;

import java.util.ArrayList;
import java.util.Stack;

public class DepthFirstSearch extends ASearchingAlgorithm {
    public DepthFirstSearch() {
        super("DFS");
    }

    @Override
    public int getNumberOfNodesEvaluated() {
        return numberOfNodesEvaluated;
    }

    @Override
    public Solution solve(ISearchable domain) {
        if(count != 0){ // if we have solves alredy on maze and we want to restrat the mazestate pre state
            domain.restStates();
        }
        AState start = domain.getStart();
        AState current;
        AState prev = start;
        AState goal = domain.getGoal();
        Solution sol = new Solution(goal);
        Stack<AState> stack = new Stack<>();
        stack.push(start);
        ArrayList<AState> neighbors; // all the possible moves for the current state

        while (!stack.empty()) {
            current = stack.peek();
            if (current.equals(goal)) { // if we found the wanted state
                goal.setPreState(prev);
                numberOfNodesEvaluated++;
                break;
            }
            prev = stack.pop();
            neighbors = domain.getAllPossible(prev); // get all the neighbors
            for (AState s : neighbors
            ) {
                if (s.getPreState() == null) { // check which of the neighbors hasnot been handle
                    s.setPreState(current);
                    if (s.equals(goal)) {
                        numberOfNodesEvaluated++;
                        goal = s;
                        return new Solution(goal);
                    }
                    stack.push(s);
                }
                numberOfNodesEvaluated++;
            }
        }
        return sol;

    }

}
