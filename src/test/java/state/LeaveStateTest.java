package state;

import com.zagurskaya.ferry.entity.Car;
import com.zagurskaya.ferry.state.CarState;
import com.zagurskaya.ferry.state.impl.DepartState;
import com.zagurskaya.ferry.state.impl.LeaveState;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LeaveStateTest extends Assert {
    CarState carState;
    Car carExpected;
    Car caraActual;

    @BeforeTest
    public void setUp() {
        carState = new LeaveState();
    }

    @Test
    public void leaveTest() {
        carExpected = new Car("1", 10, 10);
        carExpected.setState(new DepartState());
        caraActual = new Car("1", 10, 10);
        carState.leave(caraActual);
        assertEquals(caraActual, carExpected);
    }
}
