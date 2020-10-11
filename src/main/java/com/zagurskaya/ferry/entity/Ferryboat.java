package com.zagurskaya.ferry.entity;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Ferryboat {
    private static final Logger logger = LogManager.getLogger();
    private static Ferryboat instance;

    private static final int MAX_AREA = 100; // площадь
    private AtomicInteger occupiedAreaInFerryboat = new AtomicInteger();
    private static final int MAX_CARRYING_CAPACITY = 100; // грузоподъемность
    private AtomicInteger occupiedCapacityInFerryboat = new AtomicInteger();
    private static AtomicBoolean isCreated = new AtomicBoolean(false);
    private static Lock lock = new ReentrantLock();
    private static PriorityQueue<Car> usedPlaces;

    private Ferryboat() {
    }

    public static Ferryboat getInstance() {
        if (!isCreated.get()) {
            try {
                lock.lock();
                if (instance == null) {
                    instance = new Ferryboat();
                    usedPlaces = new PriorityQueue<>();
                    isCreated.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public boolean isAddAuto(Car car) {
        return (MAX_AREA - occupiedAreaInFerryboat.get() >= car.getArea())
                && (MAX_CARRYING_CAPACITY - occupiedCapacityInFerryboat.get() >= car.getCapacity());
    }

    public boolean isFull(Car car) {
        return (MAX_AREA - occupiedAreaInFerryboat.get() < car.getArea())
                && (MAX_CARRYING_CAPACITY - occupiedCapacityInFerryboat.get() < car.getCapacity());
    }

    public boolean isEmpty() {
        return (occupiedAreaInFerryboat.get() == 0) && (occupiedCapacityInFerryboat.get() == 0);
    }

    public void unload() {
        try {
            lock.lock();
            while (!isEmpty()) {
                Car newCar = usedPlaces.poll();
                for (int i = 0; i < newCar.getArea(); i++) {
                    decrement(occupiedAreaInFerryboat);
                }
                for (int i = 0; i < newCar.getCapacity(); i++) {
                    decrement(occupiedCapacityInFerryboat);
                }
                logger.log(Level.INFO, "Auto left ferry {}, AllArea - {}, AllCapacity - {}",
                        newCar, occupiedAreaInFerryboat, occupiedCapacityInFerryboat);
            }
            if (isEmpty()) {
                logger.log(Level.INFO, "Ferryboat is empty.");
            }

        } finally {
            lock.unlock();
        }
    }

    public void download(Car car) {
        try {
            if (isFull(car)) {
                logger.log(Level.INFO, "Ferryboat is full.");
            }

            lock.lock();
            boolean isNotFull = isAddAuto(car);
            if (isNotFull) {
                usedPlaces.offer(car);
                occupiedAreaInFerryboat.getAndAdd(car.getArea());
                occupiedCapacityInFerryboat.getAndAdd(car.getCapacity());
                logger.log(Level.INFO, "Added to ferry {}, All_Area - {}, AlldCapacity - {}",
                        car, occupiedAreaInFerryboat, occupiedCapacityInFerryboat);
            }
        } finally {
            lock.unlock();
        }
    }

    public int decrement(AtomicInteger count) {
        return count.decrementAndGet();
    }
}
