import static org.junit.jupiter.api.Assertions.assertEquals;

class BigNumArithmeticTest {

    @org.junit.jupiter.api.Test
    void str_to_num() {
    }

    @org.junit.jupiter.api.Test
    void remove0() {
        assertEquals("120", BigNumArithmetic.remove0("00120"));
        assertEquals("120", BigNumArithmetic.remove0("120"));
        assertEquals("0", BigNumArithmetic.remove0("000000000"));
    }

}