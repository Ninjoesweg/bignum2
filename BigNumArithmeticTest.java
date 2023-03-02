import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BigNumArithmeticTest {

    @Test
    void str_to_numTest() {
        LList temp = new LList();
        BigNumArithmetic.str_to_num("156", temp);
        assertEquals(1, temp.get(0));
        assertEquals(5, temp.get(1));
        assertEquals(6, temp.get(2));
    }

    @Test
    void remove0Test() {
        assertEquals("120", BigNumArithmetic.remove0("00120"));
        assertEquals("120", BigNumArithmetic.remove0("120"));
        assertEquals("0", BigNumArithmetic.remove0("000000000"));
    }

    @Test
    void removeSpaceTest(){
        String testString = "     1  123";
        testString = BigNumArithmetic.removeSpace(testString);
        assertEquals("1  123", testString);
    }

    @Test
    void getFirstTest(){
        String testString = "1   22    33";
        String temp = BigNumArithmetic.getFirst(testString);
        assertEquals("1", temp);
        assertEquals("   22    33", testString);
    }
}