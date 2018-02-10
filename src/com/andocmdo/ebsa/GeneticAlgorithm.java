package com.andocmdo.ebsa;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.*;

class GeneticAlgorithm {

    // Passed in parameters
    private final Integer popSize;
    private final Double mutRate;
    private final Individual chickenOrEgg;    // TODO there must be a better way to generate new specific Individuals

    // Internals
    private final Logger log;
    private Double averageFitness;
    private final Integer poolMultiplier;     // dependent on the way we score fitness TODO finalize scoring/fitness
    private final ArrayList<Individual> population;
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

    void nextGen() {

        Double sum = 0.0;   // used to calculate average population fitness

        // Generate a population if none exists (first generation) and score for fitness
        if (gen == 0) {

            // create population
            for (int i = 0; i < popSize; i++) {
                population.add(chickenOrEgg.createNew());
            }

            // Score fitness // TODO use multithreading for large populations?
            for (Individual individual : population) {
                sum = sum + individual.calculateFitness();
                // check if fitness is good enough to add to high scores
                // then add to high scores
            }
            averageFitness = sum / population.size();
            return;
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

        for (Individual individual : population) {
            // Since we are looping through might as well calculate our stats
            Double fitness = individual.calculateFitness();
            sum = sum + fitness;
            // check if fitness is good enough to add to high scores
            // then add to high scores
        }
        averageFitness = sum / population.size();

        gen++;
    }

    // TODO implement a GAStats class to pass back runtime stats
    void getStats() {
        // TODO return a set of hashmaps (Integer, String, Double) for values?
    }
}
