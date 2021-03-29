package algorithms.search;

import java.util.ArrayList;

public class Solution {
    private AState goal;

    public Solution(AState goal) {
        this.goal = goal;
    }
    public ArrayList<AState> getSolutionPath(){
        AState cur = goal;
        ArrayList<AState> path = new ArrayList<AState>();
        while(cur.getPreState()!= null){
            path.add(cur);
            cur= cur.getPreState();

        }
        path.add(cur);
        path = revPath(path);
        return  path;
    }
    private  ArrayList<AState> revPath(ArrayList<AState> path){
        ArrayList<AState> revPath =  new ArrayList<AState>();
        for (int i = path.size()-1; i >= 0  ; i--) {
            revPath.add(path.get(i));
        }
        return  revPath;
    }


}
