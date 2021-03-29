package algorithms.search;

import java.util.Objects;

public abstract class AState {
    private double cost;
    private  AState PreState;

    public AState(double cost, AState preState) {
        this.cost = cost;
        this.PreState = preState;
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
        this.PreState = preState;
    }


    @Override
    public abstract boolean equals(Object obj);


    @Override
    public abstract String toString();


}
