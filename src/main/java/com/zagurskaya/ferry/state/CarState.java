package com.zagurskaya.ferry.state;


import com.zagurskaya.ferry.entity.Car;

public interface CarState {
    void come(Car car);

    void enter(Car car);

    void leave(Car car);

    void depart(Car car);
}
