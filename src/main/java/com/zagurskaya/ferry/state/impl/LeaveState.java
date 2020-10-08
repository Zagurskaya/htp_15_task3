package com.zagurskaya.ferry.state.impl;

import com.zagurskaya.ferry.entity.Car;
import com.zagurskaya.ferry.entity.Ferryboat;
import com.zagurskaya.ferry.state.CarState;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LeaveState implements CarState {
    private static Logger logger = LogManager.getLogger();

    @Override
    public void come(Car car) {
        logger.log(Level.INFO, "state of car {} is - leave (come)", car.getName());
    }

    @Override
    public void enter(Car car) {
        logger.log(Level.INFO, "state of car {} is - leave (enter)", car.getName());
    }

    @Override
    public void leave(Car car) {
        Ferryboat ferryboat = Ferryboat.getInstance();
        ferryboat.unload();
        logger.log(Level.INFO, "state of car {} is - leave", car.getName());
        car.setState(new DepartState());
    }

    @Override
    public void depart(Car car) {
        logger.log(Level.INFO, "state of car {} is - leave (depart)", car.getName());

    }
}
