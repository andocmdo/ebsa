package com.andocmdo.ebsa;

public interface Individual {

    Individual createNew();

    void mutate(Double mutRate);

    Individual crossover(Individual partner);

    Double calculateFitness();

    Double getFitness();

}
