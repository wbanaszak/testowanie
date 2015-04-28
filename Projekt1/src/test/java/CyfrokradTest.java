import static org.junit.Assert.*;

import static org.hamcrest.CoreMatchers.*;

import org.hamcrest.Matcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

@RunWith(Parameterized.class)
public class CyfrokradTest {
    private Psikus psikus = new PsikusImpl();

    private int value;
    private Matcher expectedValue;

    public CyfrokradTest(int value, Matcher expectedValue) {
        this.value = value;
        this.expectedValue = expectedValue;
    }

    @Parameterized.Parameters
    public static Iterable<Object[]> data() {
        Psikus psikus = new PsikusImpl();
        return Arrays.asList(new Object[][]{
                {psikus.cyfrokrad(151), anyOf(equalTo(15), equalTo(11), equalTo(51))},
                {psikus.cyfrokrad(21), anyOf(equalTo(2), equalTo(1))},
                {psikus.cyfrokrad(2011), anyOf(equalTo(211), equalTo(201), equalTo(11))},
        });
    }

    @Test
    public void testNumbers() {
        assertThat(describeExpectations(), expectedValue.matches(value), is(true));
    }

    @Test
    public void testOneDigitNumber() {
        assertThat(psikus.cyfrokrad(1), nullValue());
    }

    @Test(expected = NullPointerException.class)
    public void testNull() {
        psikus.cyfrokrad(null);
    }

    private String describeExpectations() {
        return Integer.toString(value)
                + " should be " + expectedValue.toString();
    }

}
