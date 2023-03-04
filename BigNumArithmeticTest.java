import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 */
public class BigNumArithmeticTest {

    /**
     *
     */
    @Test
    void str_to_numTest() {
        LList temp = new LList();
        BigNumArithmetic.str_to_num("156", temp);
        assertEquals(6, temp.get(0));
        assertEquals(5, temp.get(1));
        assertEquals(1, temp.get(2));
    }

    /**
     *
     */
    @Test
    void remove0Test() {
        assertEquals("120", BigNumArithmetic.remove0("00120"));
        assertEquals("120", BigNumArithmetic.remove0("120"));
        assertEquals("0", BigNumArithmetic.remove0("000000000"));
    }

    /**
     *
     */
    @Test
    void removeSpaceTest() {
        String testString = "     1  123";
        testString = BigNumArithmetic.removeSpace(testString);
        assertEquals("1  123", testString);
    }

    /**
     *
     */
    @Test
    void getFirstTest() {
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

    /**
     *
     */
    @Test
    void addTest() {
        // Simple case adding two numbers of same length without carryover
        String test = "123 456 +";
        BigNumArithmetic.readLine(test);
        LStack stack = BigNumArithmetic.getStack();
        LList list = (LList) stack.pop();
        String output = "";
        for (int i = 1; i <= list.length(); i++) {
            output = output + list.get(list.length() - i);
        }
        assertEquals("579", output);

        // Simple case adding two numbers of same length with carryover
        test = "123 567 +";
        BigNumArithmetic.readLine(test);
        stack = BigNumArithmetic.getStack();
        list = (LList) stack.pop();
        output = "";
        for (int i = 1; i <= list.length(); i++) {
            output = output + list.get(list.length() - i);
        }
        assertEquals("690", output);

        // Simple case adding two numbers of different length without carryover
        test = "12 567 +";
        BigNumArithmetic.readLine(test);
        stack = BigNumArithmetic.getStack();
        list = (LList) stack.pop();
        output = "";
        for (int i = 1; i <= list.length(); i++) {
            output = output + list.get(list.length() - i);
        }
        assertEquals("579", output);

        // Simple case adding two numbers of different length with carryover
        test = "14 567 +";
        BigNumArithmetic.readLine(test);
        stack = BigNumArithmetic.getStack();
        list = (LList) stack.pop();
        output = "";
        for (int i = 1; i <= list.length(); i++) {
            output = output + list.get(list.length() - i);
        }
        assertEquals("581", output);

        // Complex case adding three numbers of different lengths with carryover
        // and leading spaces and 0's
        test = "    00001234    0000056     000789 + +";
        BigNumArithmetic.readLine(test);
        stack = BigNumArithmetic.getStack();
        list = (LList) stack.pop();
        output = "";
        for (int i = 1; i <= list.length(); i++) {
            output = output + list.get(list.length() - i);
        }
        assertEquals("2079", output);

        // Simple case not enough operands
        test = "1 +";
        BigNumArithmetic.readLine(test);
        stack = BigNumArithmetic.getStack();
        list = (LList) stack.pop();
        output = "";
        for (int i = 1; i <= list.length(); i++) {
            output = output + list.get(list.length() - i);
        }
        assertEquals("", output);

        // Simple case not enough operators
        test = "1 2 3 +";
        BigNumArithmetic.readLine(test);
        stack = BigNumArithmetic.getStack();
        list = (LList) stack.pop();
        output = "";
        for (int i = 1; i <= list.length(); i++) {
            output = output + list.get(list.length() - i);
        }
        assertEquals("5", output);
    }
}