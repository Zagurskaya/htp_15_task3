package com.zagurskaya.ferry.main;


import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Main {
    private static Logger logger = LogManager.getLogger();

    public static void main(String[] args) {

//        ExecutorService es = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
//        List<Ship> ships = new ArrayList<>();
//        List<Future<Ship>> result = new ArrayList<>();
//

//        try {
//            List<String> shipsData = DataReader.getInstance().readFile("input/ships.txt");
//            ships = DataParser.getInstance().parseShips(shipsData);
//            ships.forEach(ship -> logger.log(Level.INFO, ship));
//        } catch (ProjectException e) {
//            logger.log(Level.ERROR, "Error in time of reading file. ", e);
//        }
//        for (Ship ship : ships) {
//            result.add(es.submit(ship));
//        }
//
//        for (Future<Ship> ship : result) {
//            try {
//                TimeUnit.MILLISECONDS.sleep(200);
//                logger.log(Level.INFO, ship.get());
//            } catch (InterruptedException | ExecutionException e) {
//                logger.log(Level.WARN, "Error. ", e);
//            }
//        }
//        es.shutdown();
    }
}
