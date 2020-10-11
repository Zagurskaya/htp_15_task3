package com.zagurskaya.ferry.main;

import com.zagurskaya.ferry.creator.CarCreator;
import com.zagurskaya.ferry.entity.Car;
import com.zagurskaya.ferry.reader.DataReader;
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
    public static void main(String[] args) {
        final Logger logger = LogManager.getLogger(DataReader.class);

        ExecutorService es = Executors.newFixedThreadPool(4);
        List<Car> cars = new ArrayList<>();
        List<Future<Car>> result = new ArrayList<>();
        DataReader dataReader = new DataReader();
        CarCreator carCreator = new CarCreator();

        List<String> rowCarList = dataReader.read("data/data.txt");
        rowCarList.stream().forEach(s -> {
            Car car = carCreator.create(s);
            if (car != null) {
                cars.add(car);
            }
        });
        cars.forEach(ship -> logger.log(Level.INFO, ship));

        for (Car car : cars) {
            result.add(es.submit(car));
        }
        for (Future<Car> car : result) {
            try {
                TimeUnit.MILLISECONDS.sleep(200);
                logger.log(Level.INFO, car.get());
            } catch (InterruptedException | ExecutionException e) {
                logger.log(Level.WARN, "Error. ", e);
            }
        }
        es.shutdown();
    }
}
