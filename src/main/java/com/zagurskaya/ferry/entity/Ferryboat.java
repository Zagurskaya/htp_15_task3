package com.zagurskaya.ferry.entity;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Ferryboat {
    private static final Logger logger = LogManager.getLogger();
    private static Ferryboat instance;

    private static final int AREA = 100; // площадь
    private AtomicInteger occupiedAreaInFerryboat = new AtomicInteger();
    private static final int CARRYING_CAPACITY = 100; // грузоподъемность
    private AtomicInteger occupiedCapacityInFerryboat = new AtomicInteger();
    private static AtomicBoolean isCreated = new AtomicBoolean(false);
    private static Lock lock = new ReentrantLock();
    private static Lock lock1 = new ReentrantLock();
    private static Lock lock2 = new ReentrantLock();

    private static Queue<Car> usedPlaces;

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
        return !isFull()
                && (AREA - occupiedAreaInFerryboat.get() >= car.getArea())
                && (CARRYING_CAPACITY - occupiedCapacityInFerryboat.get() >= car.getCapacity());
    }

    public boolean isFull() {
        return occupiedAreaInFerryboat.get() == AREA && occupiedCapacityInFerryboat.get() == CARRYING_CAPACITY;
    }

    public boolean isEmpty() {
        return (occupiedAreaInFerryboat.get() <= 0) && (occupiedCapacityInFerryboat.get() <= 0);
    }

    public void unload() {
        try {
            lock1.lock();
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
                logger.log(Level.INFO, "Ferryboat is full.");
            }

        } finally {
            lock1.unlock();
        }
    }

    public void download(Car car) {
        try {
            lock2.lock();
            boolean isNotFull = isAddAuto(car);
            if (isNotFull) {
                usedPlaces.offer(car);
                occupiedAreaInFerryboat.getAndAdd(car.getArea());
                occupiedCapacityInFerryboat.getAndAdd(car.getCapacity());
                logger.log(Level.INFO, "Added to ferry {}, All_Area - {}, AlldCapacity - {}",
                        car, occupiedAreaInFerryboat, occupiedCapacityInFerryboat);
            }
            if (!isNotFull) {
                logger.log(Level.INFO, "Ferryboat is full.");
            }
        } finally {
            lock2.unlock();
        }
    }

    public int decrement(AtomicInteger count) {
        return count.decrementAndGet();
    }
}
