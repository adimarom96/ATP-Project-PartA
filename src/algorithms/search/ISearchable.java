package algorithms.search;
import java.util.ArrayList;

public interface ISearchable {
    AState getStart();
    AState getGoal();
    ArrayList<AState> getAllPossible(AState state);
    void restStates();
}