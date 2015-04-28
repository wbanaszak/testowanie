import org.hamcrest.Matcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class HultajTest {

    private Psikus psikus = new PsikusImpl();

    private int value;
    private Matcher expectedValue;

    public HultajTest(int value, Matcher expectedValue) {
        this.value = value;
        this.expectedValue = expectedValue;
    }

    @Parameterized.Parameters
    public static Iterable<Object[]> data() {
        Psikus psikus = new PsikusImpl();
        return Arrays.asList(new Object[][]{
                {psikus.hultajchochla(151), anyOf(equalTo(511), equalTo(115), equalTo(151))},
                {psikus.hultajchochla(21), anyOf(equalTo(12), equalTo(21))},
                {psikus.hultajchochla(2011), anyOf(equalTo(1012), equalTo(2011), equalTo(211), equalTo(2110), equalTo(2101), equalTo(1021), equalTo(2001))},

        });
    }

    @Test
    public void testNumbersLongerThanOneChar() {
        assertThat(describeExpectations(), expectedValue.matches(value), is(true));
    }

    @Test(expected = NieduanyPsikusException.class)
    public void testShouldThrowNieudanyPsikusException() {
        psikus.hultajchochla(1);
    }

    private String describeExpectations() {
        return Integer.toString(value)
                + " should be " + expectedValue.toString();
    }

}
