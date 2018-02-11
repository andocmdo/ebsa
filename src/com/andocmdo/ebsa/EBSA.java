package com.andocmdo.ebsa;

import org.apache.commons.cli.*;

import java.nio.file.Paths;
import java.util.logging.*;

@SuppressWarnings("SpellCheckingInspection")
class EBSA {

    public static void main(String[] args) {

        // Start the logger
        Logger log = Logger.getLogger("mainLog");
        log.log(Level.INFO, "Logger started");

        // log our working directory, for debugging purposes only...
        log.log(Level.INFO, "Working directory: " + Paths.get(".").toAbsolutePath().normalize().toString());

        // Read in all the command line arguments
        Options options = new Options();

        Option evolvedClassOpt = new Option("c", "class",
                true, "Java class file for evolved Individual");
        evolvedClassOpt.setRequired(true);
        options.addOption(evolvedClassOpt);

        Option configFileOpt = new Option("f", "configFile",
                true, "evolved Individual specific configuration");
        configFileOpt.setRequired(false);
        options.addOption(configFileOpt);

        Option popSizeOpt = new Option("p", "popSize",
                true, "population size");
        popSizeOpt.setRequired(true);
        options.addOption(popSizeOpt);

        Option maxGenOpt = new Option("g", "maxGen",
                true, "max number of generations");
        maxGenOpt.setRequired(true);
        options.addOption(maxGenOpt);

        Option mutRateOpt = new Option("m", "mutRate",
                true, "mutation rate ex: 0.02");
        mutRateOpt.setRequired(true);
        options.addOption(mutRateOpt);

        Option outputFileOpt = new Option("o", "outputFile",
                true, "final output filename");
        outputFileOpt.setRequired(false);
        options.addOption(outputFileOpt);

        Option logFileOpt = new Option("l", "logFile",
                true, "logfile");
        logFileOpt.setRequired(false);
        options.addOption(logFileOpt);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("java -jar FIXME.jar [ <args> ] ", options);
            System.exit(1);
            return;
        }

        // Parse the command line args
        String classFile = cmd.getOptionValue("classFile");
        String configFile = cmd.getOptionValue("configFile");
        Integer popSize = Integer.parseInt(cmd.getOptionValue("popSize"));
        Integer maxGen = Integer.parseInt(cmd.getOptionValue("maxGen"));
        Double mutRate = Double.parseDouble(cmd.getOptionValue("mutRate"));
        String outputFile = cmd.getOptionValue("outputFile");
        String logFile = cmd.getOptionValue("logFile");
        log.log(Level.INFO, "classFile: {0}, configFile: {1}, popSize: {2}, maxGen: {3}, mutRate: {4}, " +
                        "outputFile: {5}, logFile: {6} ",
                new Object[]{ classFile, configFile, popSize, maxGen, mutRate, outputFile, logFile } );

        // Give the GA an example individual
        // TODO need to find a better way to decide which class of Agent to create
        // maybe classloader? or enum then case switch in GA?
        Individual example = new StockAgent();

        // TODO implement builder pattern for GA?
        GeneticAlgorithm ga = new GeneticAlgorithm(popSize, mutRate, example);

        // Run the GA sim, log stats, check for interrupt signals
        for (int i = 0; i < maxGen; i++) {
            ga.nextGen();
            ga.getStats();
            // TODO write stats to log
            log.log(Level.INFO, "Generation: {0}", i);

            // TODO check for fitness limit / end conditions

            // TODO should check for interrupt or stop signal here

        }

        // Cleanup and exit
        System.exit(0);
    }
}
