package com.zagurskaya.ferry.state.impl;

import com.zagurskaya.ferry.entity.Car;
import com.zagurskaya.ferry.state.CarState;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ComeState implements CarState {
    private static Logger logger = LogManager.getLogger();

    @Override
    public void come(Car car) {
        logger.log(Level.INFO, "Car {} come", car.getName());
        car.setState(new EnterState());
    }

    @Override
    public void enter(Car car) {
        logger.log(Level.INFO, "state of car {} is - come (enter)", car.getName());
    }

    @Override
    public void leave(Car car) {
        logger.log(Level.INFO, "state of car {} is - come (leave)", car.getName());
    }

    @Override
    public void depart(Car car) {
        logger.log(Level.INFO, "state of car {} is - come (depart)", car.getName());

    }
}
