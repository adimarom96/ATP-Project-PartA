package algorithms.search;

public abstract class ASearchingAlgorithm implements  ISearchingAlgorithm{
    protected String name;
    protected  int numOfNode;

    @Override
    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public int getNumOfNode() {
        return numOfNode;
    }

    public void setNumOfNode(int numOfNode) {
        this.numOfNode = numOfNode;
    }



    // constrctuor

}
