package algorithms.search;

public abstract class ASearchingAlgorithm implements  ISearchingAlgorithm{
    static  int count=0;
    protected String name;
    protected  int numberOfNodesEvaluated;

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumOfNode() {
        return numberOfNodesEvaluated;
    }

    // todo: delete?
    public void setNumOfNode(int numOfNode) {
        this.numberOfNodesEvaluated = numOfNode;
    }

    public ASearchingAlgorithm(String name) {
        this.name = name;

    }
}