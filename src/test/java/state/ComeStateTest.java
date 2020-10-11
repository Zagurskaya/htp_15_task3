package state;

import com.zagurskaya.ferry.entity.Car;
import com.zagurskaya.ferry.reader.DataReader;
import com.zagurskaya.ferry.state.CarState;
import com.zagurskaya.ferry.state.impl.ComeState;
import com.zagurskaya.ferry.state.impl.EnterState;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class ComeStateTest extends Assert {
    CarState carState;
    Car carExpected;
    Car caraActual;

    @BeforeTest
    public void setUp() {
        carState = new ComeState();
    }

    @Test
    public void comeTest() {
        carExpected = new Car("1", 10, 10);
        carExpected.setState(new EnterState());
        caraActual = new Car("1", 10, 10);
        carState.come(caraActual);
        assertEquals(caraActual, carExpected);
    }
}
