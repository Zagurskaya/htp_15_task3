package com.zagurskaya.ferry.creator;

import com.zagurskaya.ferry.entity.Car;
import com.zagurskaya.ferry.parser.DataParser;

import java.util.List;

public class CarCreator {

    public Car create(String row) {

        DataParser dataParser = new DataParser();
        List<String> dataList = dataParser.parseRowCarToStringList(row);
        if (dataList.size() != 0) {
            return new Car(dataList.get(0), Integer.parseInt(dataList.get(1)), Integer.parseInt(dataList.get(2)));
        }
        return null;
    }
}
