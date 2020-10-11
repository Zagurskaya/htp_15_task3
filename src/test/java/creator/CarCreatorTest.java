package creator;

import com.zagurskaya.ferry.creator.CarCreator;
import com.zagurskaya.ferry.entity.Car;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CarCreatorTest extends Assert {
    Car car;
    CarCreator carCreator;
    String row;

    @BeforeTest
    public void setUp() {
        carCreator = new CarCreator();
    }

    @Test
    public void createTest() {
        car = new Car("1",10,10);
        row = "1,10,10";

        Car expected = car;
        Car actual = carCreator.create (row);
        assertEquals(actual, expected);
    }
}
