import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BigNumArithmeticTest {

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
        String testString = "123   22    33";
        LList tempList = new LList();
        BigNumArithmetic.readLine(testString);
        LStack stack = BigNumArithmetic.getStack();
        tempList = (LList) stack.pop();
        assertEquals(3, tempList.get(0));
        assertEquals(3, tempList.get(1));
        tempList = (LList) stack.pop();
        assertEquals(2, tempList.get(0));
        assertEquals(2, tempList.get(1));
        tempList = (LList) stack.pop();
        assertEquals(3, tempList.get(0));
        assertEquals(2, tempList.get(1));
        assertEquals(1, tempList.get(2));
    }
}