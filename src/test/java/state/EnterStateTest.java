package state;

import com.zagurskaya.ferry.entity.Car;
import com.zagurskaya.ferry.state.CarState;
import com.zagurskaya.ferry.state.impl.EnterState;
import com.zagurskaya.ferry.state.impl.LeaveState;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class EnterStateTest extends Assert {
    CarState carState;
    Car carExpected;
    Car caraActual;

    @BeforeTest
    public void setUp() {
        carState = new EnterState();
    }

    @Test
    public void enterTest() {
        carExpected = new Car("1", 10, 10);
        carExpected.setState(new LeaveState());
        caraActual = new Car("1", 10, 10);
        carState.enter(caraActual);
        assertEquals(caraActual, carExpected);
    }
}
