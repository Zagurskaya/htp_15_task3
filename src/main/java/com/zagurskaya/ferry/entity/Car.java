package com.zagurskaya.ferry.entity;

import com.zagurskaya.ferry.state.CarState;
import com.zagurskaya.ferry.state.impl.ComeState;

import java.util.concurrent.Callable;

public class Car implements Callable<Car> ,Comparable<Car> {
    private String name;
    private int area;
    private int capacity;
    private CarState state;

    public Car(String name, int area, int capacity) {
        this.name = name;
        this.area = area;
        this.capacity = capacity;
        this.state = new ComeState();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public CarState getState() {
        return state;
    }

    public void setState(CarState state) {
        this.state = state;
    }

    @Override
    public Car call() throws Exception {
        state.come(this);
        state.enter(this);
        state.leave(this);
        state.depart(this);

        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (area != car.area) return false;
        if (capacity != car.capacity) return false;
        if (name != null ? !name.equals(car.name) : car.name != null) return false;
        return state != null ? state.equals(car.state) : car.state == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + area;
        result = 31 * result + capacity;
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Car{");
        sb.append("name='").append(name).append('\'');
        sb.append(", area=").append(area);
        sb.append(", capacity=").append(capacity);
        sb.append(", state=").append(state.getClass().getSimpleName());
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int compareTo(Car o) {
        return  this.getName().compareTo(o.getName());
    }
}
