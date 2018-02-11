package com.andocmdo.ebsa;

import org.apache.commons.cli.*;

import java.util.logging.*;

@SuppressWarnings("SpellCheckingInspection")
class EBSA {

    public static void main(String[] args) {

        // Start the logger
        Logger log = Logger.getLogger("mainLog");
        log.log(Level.INFO, "Logger started");

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
        // TODO for Agent specific data pass in as JSON/XML/file
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
        String symbol = cmd.getOptionValue("symbol");   // should be individual specific, pass in through stdin or file
        String startDate = cmd.getOptionValue("startDate"); // should be individual specific, pass in through stdin or file
        String endDate = cmd.getOptionValue("endDate"); // should be individual specific, pass in through stdin or file
        Integer popSize = Integer.parseInt(cmd.getOptionValue("popSize"));
        Integer maxGen = Integer.parseInt(cmd.getOptionValue("maxGen"));
        Double mutRate = Double.parseDouble(cmd.getOptionValue("mutRate"));
        String outputFile = cmd.getOptionValue("outputFile");
        String logfile = cmd.getOptionValue("logfile");
        log.log(Level.INFO, "symbol: {0}, startDate: {1}, endDate: {2}, popSize: {3}, maxGen: {4}, " +
                "mutRate: {5}, outputFile: {6}, logfile: {7} ",
                new Object[]{ symbol, startDate, endDate, popSize, maxGen, mutRate, outputFile, logfile } );

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
