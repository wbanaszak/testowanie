import org.junit.Test;

import static junit.framework.Assert.*;

public class LiczbyRzymskieTest {

    @Test
    public void testOneDigitNumbers() {
        assertEquals("I", new LiczbaRzymska(1).toString());
        assertEquals("II", new LiczbaRzymska(2).toString());
        assertEquals("III", new LiczbaRzymska(3).toString());
        assertEquals("IV", new LiczbaRzymska(4).toString());
        assertEquals("V", new LiczbaRzymska(5).toString());
        assertEquals("VI", new LiczbaRzymska(6).toString());
        assertEquals("VII", new LiczbaRzymska(7).toString());
        assertEquals("VIII", new LiczbaRzymska(8).toString());
        assertEquals("IX", new LiczbaRzymska(9).toString());
    }

    @Test
    public void testTwoDigitsNumbers() {
        assertEquals("X", new LiczbaRzymska(10).toString());
        assertEquals("XV", new LiczbaRzymska(15).toString());
        assertEquals("XXI", new LiczbaRzymska(21).toString());
        assertEquals("XCIX", new LiczbaRzymska(99).toString());
    }

    @Test
    public void testThreeDigitsNumbers() {
        assertEquals("D", new LiczbaRzymska(500).toString());
        assertEquals("CXI", new LiczbaRzymska(111).toString());
        assertEquals("CMXCIX",new LiczbaRzymska(999).toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentException() {
        new LiczbaRzymska(-1);
    }

}
