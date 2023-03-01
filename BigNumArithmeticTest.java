import static org.junit.jupiter.api.Assertions.assertEquals;

class BigNumArithmeticTest {

    @org.junit.jupiter.api.Test
    void str_to_numTest() {
        LList temp = new LList();
        BigNumArithmetic.str_to_num("156", temp);
        char test = (char) temp.get(0);
        assertEquals('1', test);
        assertEquals('5', temp.get(1));
        assertEquals('6', temp.get(2));
    }

    @org.junit.jupiter.api.Test
    void remove0Test() {
        assertEquals("120", BigNumArithmetic.remove0("00120"));
        assertEquals("120", BigNumArithmetic.remove0("120"));
        assertEquals("0", BigNumArithmetic.remove0("000000000"));
    }

}