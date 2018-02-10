package com.andocmdo.ebsa;

import java.util.ArrayList;

interface Individual {

    Individual createNew();

    void mutate(Double mutRate);

    Individual crossover(Individual partner);

    Double calculateFitness();

    Double getFitness();

    ArrayList getDNA();

    boolean equals(Object obj);



}
