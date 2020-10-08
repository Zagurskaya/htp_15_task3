package com.zagurskaya.ferry.parser;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DataParser {
    private static final String VALUE_SEPARATOR = "\\,";
    private static final String CAR_SEPARATOR = "\n";

    public List<String> parseRowToStringPointsList(String rowTriangle) {

        List<String> pointsStringList = Arrays.stream(rowTriangle.split(CAR_SEPARATOR)).collect(Collectors.toList());
        return pointsStringList;
    }
    public List<String> parseRowPointsToStringList(String rowPoints) {

        List<String> coordinateList = Arrays.stream(rowPoints
                .split(VALUE_SEPARATOR))
                .collect(Collectors.toList());
        return coordinateList;
    }

}
