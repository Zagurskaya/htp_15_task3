package parser;

import com.zagurskaya.ferry.parser.DataParser;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class CarDataParserTest extends Assert {
    DataParser dataParser;
    String row;

    @BeforeTest
    public void setUp() {
        dataParser = new DataParser();
    }

    @Test
    public void arseRowCarToStringListTest() {
        row = "1,10,10";

        List<String> expected = new ArrayList<>();
        expected.add("1");
        expected.add("10");
        expected.add("10");

        List<String> actual = dataParser.parseRowCarToStringList(row);
        assertEquals(actual, expected);
    }

    @Test
    public void parseRowToStringPointsListTest() {
        row = "1,10,10";

        List<String> expected = new ArrayList<>();
        expected.add("1,10,10");

        List<String> actual = dataParser.parseRowToStringCarsList(row);
        assertEquals(actual, expected);
    }
}
