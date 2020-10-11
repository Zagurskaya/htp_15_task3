package reader;

import com.zagurskaya.ferry.reader.DataReader;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class CarDataReaderTest extends Assert {
    DataReader dataReader;
    String fileName;

    @BeforeTest
    public void setUp() {
        dataReader = new DataReader();
    }

    @Test
    public void readTest() {
        fileName = "data/dataTest.txt";

        List<String> expected = new ArrayList<>();
        expected.add("1,10,10");

        List<String> actual = dataReader.read(fileName);
        assertEquals(actual, expected);
    }
}
