import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CalculatorTest {

    public static final double EPSILON = 0.01;
    public static Calculator calculator = new CalculatorImpl();

    @Rule
    public ExpectedException exception = ExpectedException.none();


    @Test
    public void testAdding() {
        assertEquals(5, calculator.add(2, 3), EPSILON);
        assertEquals(5, calculator.add(2., 3.), EPSILON);
        assertEquals(4.000001, calculator.add(2.000001, 2), EPSILON);
        assertEquals(4.000012, calculator.add(2.000001, 2.000011), EPSILON);
        assertEquals(-0.000400, calculator.add(-0.000521, 0.000121), EPSILON);
        assertEquals(0.9, calculator.add(2, -1.1), EPSILON); //w javie: 2 + (-1.1) = 0.8999999999999999
    }

    @Test
    public void testSub() {
        assertEquals(0.9, calculator.sub(2, 1.1), EPSILON); //w javie: 2 + (-1.1) = 0.8999999999999999
        assertEquals(1, calculator.sub(1.000000001, 0.00000001), EPSILON);
        assertEquals(0.01, calculator.sub(1, 0.99), EPSILON);
    }

    @Test
    public void testMulti() {
        assertEquals(6, calculator.multi(2, 3), EPSILON);
        assertEquals(6, calculator.multi(2., 3.), EPSILON);
        assertEquals(4.000001, calculator.multi(2.000001, 2), EPSILON);
        assertEquals(8, calculator.multi(-2, -4), EPSILON);
    }

    @Test
    public void testDividing() {
        assertEquals(2.5, calculator.div(10, 4), EPSILON);
        assertEquals(2, calculator.div(10, 5.), EPSILON);
        assertEquals(0.33, calculator.div(1, 3), EPSILON);
    }

    @Test
    public void testComparison() {
        assertTrue(calculator.greater(1.5, 1));
        assertFalse(calculator.greater(1, 1.5));
    }

    @Test(expected = ArithmeticException.class)
    public void shouldThrowArithmeticException() {
        calculator.div(4, 0.0);
    }
}
