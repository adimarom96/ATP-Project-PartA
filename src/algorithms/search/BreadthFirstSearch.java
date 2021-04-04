package algorithms.search;

import algorithms.mazeGenerators.Position;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch extends ASearchingAlgorithm {
    protected Queue<AState> state_q;

    // constructor
    public BreadthFirstSearch() {
        super("BFS");
        numberOfNodesEvaluated = 0;
        this.state_q = new LinkedList<>();
        isBest = false;
    }

    // constructor for the successor (Best)
    public BreadthFirstSearch(String name, Queue<AState> state_q) {
        super(name);
        numberOfNodesEvaluated = 0;
        this.state_q = state_q;
    }

    @Override
    public int getNumberOfNodesEvaluated() {
        return numberOfNodesEvaluated;
    }

    public Solution solve(ISearchable problem) {
        // if this is not the first try to solve the maze we need to first reset all the PreState adn costs
        if (count != 0) {
            problem.restStates();
        }
        AState start = problem.getStart();
        AState current;
        AState prev = start;
        AState goal = problem.getGoal();
        count++;
        //this.state_q = new LinkedList<>(); // q that will hold all the possible moves that we didn't visited
        ArrayList<AState> neighbors; // all the possible moves for the current state
        Solution sol = new Solution(goal);
        state_q.add(start);
        while (!state_q.isEmpty()) { // while this q is not empty we will keep run
            current = state_q.peek();

            if (current.equals(goal)) { // if we found the wanted state
                goal.setPreState(prev);
                //goal.setCost(goal.getCost()-10); // we want to reduce the price of the first node
                numberOfNodesEvaluated++;
                break;
            }
            prev = state_q.remove();
            neighbors = problem.getAllSuccessors(prev); // get all the neighbors
            for (AState s : neighbors // for each neighbor do:
            ) {// maybe check if already een in the q
                if (s.getPreState() == null && !s.equals(start)) { // check which of the neighbors has not been handle
                    s.setPreState(current);
                    if (s.equals(goal)) { // if we found the wanted state
                        numberOfNodesEvaluated++;
                        goal = s;
                       // goal.setCost(goal.getCost()-10); // we want to reduce the price of the first node
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