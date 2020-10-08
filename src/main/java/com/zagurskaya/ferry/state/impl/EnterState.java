package com.zagurskaya.ferry.state.impl;

import com.zagurskaya.ferry.entity.Car;
import com.zagurskaya.ferry.entity.Ferryboat;
import com.zagurskaya.ferry.state.CarState;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EnterState implements CarState {
    private static Logger logger = LogManager.getLogger();


    @Override
    public void come(Car car) {
        logger.log(Level.INFO, "state of car {} is - enter (come)", car.getName());
    }

    @Override
    public void enter(Car car) {
        Ferryboat ferryboat = Ferryboat.getInstance();
        ferryboat.download(car);
        logger.log(Level.INFO, "car {} is entering", car.getName());
        car.setState(new LeaveState());
    }

    @Override
    public void leave(Car car) {
        logger.log(Level.INFO, "state of car {} is - enter (leave)", car.getName());
    }

    @Override
    public void depart(Car car) {
        logger.log(Level.INFO, "state of car {} is - enter (depart)", car.getName());
    }
}
