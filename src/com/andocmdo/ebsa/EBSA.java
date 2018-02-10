package com.andocmdo.ebsa;

import org.apache.commons.cli.*;

import java.util.logging.*;

@SuppressWarnings("SpellCheckingInspection")
class EBSA {

    public static void main(String[] args) {

        // Start the logger
        Logger log = Logger.getLogger("mainLog");

        // Read in all the command line arguments
        Options options = new Options();

        /*
        symbol -s
        startDate -f
        endDate -t
        popSize -p
        maxGen -g
        mutRate -m
        outputFile -o
        logfile -l
         */
        // TODO maybe pass in the data as JSON file or XML rather than explicitly look for it here
        Option symbolOpt = new Option("s", "symbol", true, "stock symbol");
        symbolOpt.setRequired(true);
        options.addOption(symbolOpt);

        Option startDateOpt = new Option("f", "startDate", true, "start date for simulation");
        startDateOpt.setRequired(false);
        options.addOption(startDateOpt);

        Option endDateOpt = new Option("t", "endDate", true, "end date for simulation");
        endDateOpt.setRequired(false);
        options.addOption(endDateOpt);

        Option popSizeOpt = new Option("p", "popSize", true, "population size");
        popSizeOpt.setRequired(true);
        options.addOption(popSizeOpt);

        Option maxGenOpt = new Option("g", "maxGen", true, "max number of generations (1 - sim only)");
        maxGenOpt.setRequired(true);
        options.addOption(maxGenOpt);

        Option mutRateOpt = new Option("m", "mutRate", true, "mutation rate ex: 0.02");
        mutRateOpt.setRequired(true);
        options.addOption(mutRateOpt);

        Option outputFileOpt = new Option("o", "outputFile", true, "final output filename");
        outputFileOpt.setRequired(false);
        options.addOption(outputFileOpt);

        Option logfileOpt = new Option("l", "logfile", true, "logfile");
        logfileOpt.setRequired(false);
        options.addOption(logfileOpt);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("java -cp . EBSA ", options);

            System.exit(1);
            return;
        }

        /* How to print out options
        String inputFilePath = cmd.getOptionValue("input");
        String outputFilePath = cmd.getOptionValue("output");
        */

        /*
        symbol -s
        startDate -f
        endDate -t
        popSize -p
        maxGen -g
        mutRate -m
        outputFile -o
        logfile -l
         */
        // pull out command args into corresponding variables
        String symbol = cmd.getOptionValue("symbolOpt");
        String startDate = cmd.getOptionValue("startDateOpt");
        String endDate = cmd.getOptionValue("endDateOpt");
        Integer popSize = Integer.parseInt(cmd.getOptionValue("popSizeOpt"));
        Integer maxGen = Integer.parseInt(cmd.getOptionValue("maxGenOpt"));
        Double mutRate = Double.parseDouble(cmd.getOptionValue("mutRateOpt"));
        String outputFile = cmd.getOptionValue("outputFileOpt");
        String logfile = cmd.getOptionValue("logfileOpt");

        // Give the GA an example individual
        Individual example = new StockAgent();

        // TODO implement builder pattern for GA?
        GeneticAlgorithm ga = new GeneticAlgorithm(popSize, mutRate, example);

        // Run the GA sim, log stats, check for interrupt signals
        for (int i = 0; i < maxGen; i++) {
            ga.nextGen();
            ga.getStats();
            // TODO write stats to log
            // TODO check for fitness limit / end conditions
            // TODO should check for interrupt or stop signal here
        }

        // Cleanup and exit
        System.exit(0);
    }
}
