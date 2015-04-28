import org.hamcrest.Matcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class NieksztaltekTest {

    private Psikus psikus = new PsikusImpl();

    private int value;
    private Matcher expectedValue;

    public NieksztaltekTest(int value, Matcher expectedValue) {
        this.value = value;
        this.expectedValue = expectedValue;
    }

    @Parameterized.Parameters
    public static Iterable<Object[]> data() {
        Psikus psikus = new PsikusImpl();
        return Arrays.asList(new Object[][]{
                {psikus.nieksztaltek(151), equalTo(151)},
                {psikus.nieksztaltek(213), anyOf(equalTo(213), equalTo(218))},
                {psikus.nieksztaltek(337766), anyOf(equalTo(887766), equalTo(331166), equalTo(337799))},
        });
    }

    @Test
    public void testNumbers() {
        assertThat(describeExpectations(), expectedValue.matches(value), is(true));
    }

    private String describeExpectations() {
        return Integer.toString(value)
                + " should be " + expectedValue.toString();
    }
}

