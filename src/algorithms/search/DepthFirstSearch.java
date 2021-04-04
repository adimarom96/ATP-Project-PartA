package algorithms.search;
import java.util.ArrayList;
import java.util.Stack;

public class DepthFirstSearch extends ASearchingAlgorithm {
    public DepthFirstSearch() {
        super("DFS");
        isBest = false;
    }

    @Override
    public int getNumberOfNodesEvaluated() {
        return numberOfNodesEvaluated;
    }

    @Override
    public Solution solve(ISearchable domain) {
        // if this is not the first try to solve the maze we need to first reset all the PreState
        if(count != 0){
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
            neighbors = domain.getAllSuccessors(prev); // get all the neighbors
            for (AState s : neighbors  // for each neighbor do:
            ) {
                if (s.getPreState() == null) { // check which of the neighbors has not been handle
                    s.setPreState(current);
                    if (s.equals(goal)) { // if we found the wanted state
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