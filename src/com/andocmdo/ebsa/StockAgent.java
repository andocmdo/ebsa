package com.andocmdo.ebsa;

import java.util.ArrayList;

public class StockAgent implements Individual {

    private ArrayList<Gene> dna;

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
    public ArrayList getDNA() {
        return dna;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Individual)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        Individual ind = (Individual) obj;
        if (this.getDNA().equals(ind.getDNA())) return true;
        return false;
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
