package com.andocmdo.ebsa;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.*;

class GeneticAlgorithm {

    // Passed in parameters
    private Integer popSize;
    private Double mutRate;
    private Individual chickenOrEgg;    // TODO there must be a better way to generate new specific Individuals

    // Internals
    private Logger log;
    private Double averageFitness;
    private Integer poolMultiplier;     // dependent on the way we score fitness TODO finalize scoring/fitness
    private ArrayList<Individual> population;
    private Integer gen;

    GeneticAlgorithm(Integer popSize, Double mutRate, Individual individual) {

        this.popSize = popSize;
        this.mutRate = mutRate;
        this.chickenOrEgg = individual;

        // Get the logger handle
        log = Logger.getLogger("mainLog");

        // init some hardcoded defaults
        averageFitness = 0.0;
        poolMultiplier = popSize / 10;   // TODO someday maybe better way to do this, or parameterize
        population = new ArrayList<>();
        gen = 0;
    }

    boolean nextGen() {

        // Generate a population if none exists (first generation) and score for fitness
        if (gen == 0) {

            // create population
            for (int i = 0; i < popSize; i++) {
                population.add(chickenOrEgg.createNew());
            }

            // Score fitness // TODO use multithreading for large populations?
            for (Individual individual : population) {
                // We will only calc high scores and stats on later runs, not first generation
                individual.calculateFitness();
            }
            return true;
        }

        // If not first generation:
        // Crossover scored population from last run/generation
        ArrayList<Individual> pool = new ArrayList<>();   // create a mating pool
        for (Individual individual : population) {
            // add copies of individual based on fitness, with minimum of one copy added
            for (int j = 0; j < (int) ((individual.getFitness() * poolMultiplier) + 1); j++) {
                pool.add(individual);
            }
        }
        // now pick random indices from the pool and crossover to make new population
        for (int i = 0; i < population.size(); i++) {
            population.set(i, pool.get(
                    ThreadLocalRandom.current().nextInt(0, pool.size()))
                    .crossover(pool.get(ThreadLocalRandom.current().nextInt(0, pool.size())))
            );
        }

        // Mutate
        for (Individual individual : population) {
            individual.mutate(mutRate);
        }


        // Score fitness TODO add multithreading for large populations?
        Double sum = 0.0;
        for (Individual individual : population) {
            // Since we are looping through might as well calculate our stats
            sum = sum + individual.calculateFitness();
        }

        gen++;
        return true;
    }

    // TODO implement a GAStats class to pass back runtime stats
    void getStats() {
        // TODO return a set of hashmaps (Integer, String, Double) for values?
    }
}
