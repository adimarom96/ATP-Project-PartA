package algorithms.search;

public abstract class AState {
    private double cost;
    private  AState PreState;

    public AState(double cost, AState preState) {
        this.cost = cost;
        PreState = preState;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public AState getPreState() {
        return PreState;
    }

    public void setPreState(AState preState) {
        PreState = preState;
    }

}
