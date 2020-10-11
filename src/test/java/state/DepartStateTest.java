package state;

import com.zagurskaya.ferry.entity.Car;
import com.zagurskaya.ferry.state.CarState;
import com.zagurskaya.ferry.state.impl.ComeState;
import com.zagurskaya.ferry.state.impl.DepartState;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DepartStateTest extends Assert {
    CarState carState;
    Car carExpected;
    Car caraActual;

    @BeforeTest
    public void setUp() {
        carState = new DepartState();
    }

    @Test
    public void departTest() {
        carExpected = new Car("1", 10, 10);
        carExpected.setState(new ComeState());
        caraActual = new Car("1", 10, 10);
        carState.depart(caraActual);
        assertEquals(caraActual, carExpected);
    }
}
