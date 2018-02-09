package com.andocmdo.ebsa;

public class StockAgent implements Individual {

    @Override
    public Individual createNew() {
        return null;
    }

    @Override
    public void mutate(Double mutRate) {
        //
    }

    @Override
    public Individual crossover(Individual partner) {
        return null;
    }

    @Override
    public Double calculateFitness() {
        return 0.1;
    }

    @Override
    public Double getFitness() {
        return 0.1;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
