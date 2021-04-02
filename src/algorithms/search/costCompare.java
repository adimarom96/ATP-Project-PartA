package algorithms.search;

import java.util.Comparator;

public class costCompare implements Comparator<AState> {
    @Override
    public int compare(AState t0, AState t1) {
        if (t0.getCost() > t1.getCost())
            return 1;
        else if (t0.getCost() < t1.getCost())
            return -1;
        return 0;
    }
}