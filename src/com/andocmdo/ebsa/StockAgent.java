package com.andocmdo.ebsa;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class StockAgent implements Individual {

    private List<Gene> dna;

    StockAgent() {

    }

    @Override
    public Individual createNew() {
        return new StockAgent();
    }

    @Override
    public void mutate(Double mutRate) {

    }

    @Override
    public Individual crossover(Individual partner) {
        return this;
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
    public List getDNA() {
        return dna;
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
        return this.getDNA().equals(ind.getDNA());
    }

}
