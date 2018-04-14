package com.andocmdo.ebsa;

import java.util.List;

interface Individual {

    Individual createNew();

    void mutate(Double mutRate);

    Individual crossover(Individual partner);

    Double calculateFitness();

    Double getFitness();

    List getDNA();

    boolean equals(Object obj);



}
