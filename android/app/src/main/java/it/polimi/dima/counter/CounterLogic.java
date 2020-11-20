package it.polimi.dima.counter;

public class CounterLogic {

    private int total = 0;
    private int current = 0;

    public int getCurrent() {
        return current;
    }

    public int getTotal() {
        return total;
    }

    public void addOne() {
        current += 1;
        total += 1;
    }

    public void zero() {
        current = 0;
    }
}
