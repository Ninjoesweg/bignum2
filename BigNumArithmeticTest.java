import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 */
public class BigNumArithmeticTest {

    /**
     *
     */
    @Test
    public void str_to_numTest() {
        //create LList object
        LList temp = new LList();
        //call function on big num object
        //does it convert the 156 from string to numerical digit
        BigNumArithmetic.str_to_num("156", temp);
        assertEquals(6, temp.get(0));
        assertEquals(5, temp.get(1));
        assertEquals(1, temp.get(2));
    }

    /**
     *
     */
    @Test
    public void remove0Test() {
        //tests if 0s were removed from input string
        assertEquals("120", BigNumArithmetic.remove0("00120"));
        assertEquals("120", BigNumArithmetic.remove0("120"));
        assertEquals("0", BigNumArithmetic.remove0("000000000"));
    }

    /**
     *
     */
    @Test
    public void removeSpaceTest() {
        //checks to see if leading spaces were removed from the input string
        String testString = "     1  123";
        testString = BigNumArithmetic.removeSpace(testString);
        assertEquals("1  123", testString);
    }

    /**
     *
     */
    @Test
    public void getFirstTest() {
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
    public void addTest() {
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

    @Test
    public void roundTest() {
        // checks for whether carry over is done correctly
        String output = "";
        LList list = new LList();
        list.append(0);
        list.append(0);
        list.append(1);
        list = BigNumArithmetic.round(list);
        for (int i = 1; i <= list.length(); i++) {
            output = output + list.get(list.length() - i);
        }
        assertEquals("100", output);
    }

    @Test
    public void multiplyTest() {
        // Simple case two integers length one with carryover
        String test = "5 3 *";
        BigNumArithmetic.readLine(test);
        LStack stack = BigNumArithmetic.getStack();
        LList list = (LList) stack.pop();
        String output = "";
        for (int i = 1; i <= list.length(); i++) {
            output = output + list.get(list.length() - i);
        }
        assertEquals("15", output);

        // Simple case two integers length one without carryover
        test = "2 3 *";
        BigNumArithmetic.readLine(test);
        stack = BigNumArithmetic.getStack();
        list = (LList) stack.pop();
        output = "";
        for (int i = 1; i <= list.length(); i++) {
            output = output + list.get(list.length() - i);
        }
        assertEquals("6", output);

        // Simple case two integers length two with carryover
        test = "11 20 *";
        BigNumArithmetic.readLine(test);
        stack = BigNumArithmetic.getStack();
        list = (LList) stack.pop();
        output = "";
        for (int i = 1; i <= list.length(); i++) {
            output = output + list.get(list.length() - i);
        }
        assertEquals("220", output);

        // Simple case two integers length one
        // and length two without carryover
        test = "2 13 *";
        BigNumArithmetic.readLine(test);
        stack = BigNumArithmetic.getStack();
        list = (LList) stack.pop();
        output = "";
        for (int i = 1; i <= list.length(); i++) {
            output = output + list.get(list.length() - i);
        }
        assertEquals("26", output);

        // Simple case two integers length two
        // and length one without carryover
        test = "13 9 *";
        BigNumArithmetic.readLine(test);
        stack = BigNumArithmetic.getStack();
        list = (LList) stack.pop();
        output = "";
        for (int i = 1; i <= list.length(); i++) {
            output = output + list.get(list.length() - i);
        }
        assertEquals("117", output);

        // Simple case two integers length three with carryover
        test = "123 567 *";
        BigNumArithmetic.readLine(test);
        stack = BigNumArithmetic.getStack();
        list = (LList) stack.pop();
        output = "";
        for (int i = 1; i <= list.length(); i++) {
            output = output + list.get(list.length() - i);
        }
        assertEquals("69741", output);

        // Simple case two integers length two and three with carryover
        test = "12 567 *";
        BigNumArithmetic.readLine(test);
        stack = BigNumArithmetic.getStack();
        list = (LList) stack.pop();
        output = "";
        for (int i = 1; i <= list.length(); i++) {
            output = output + list.get(list.length() - i);
        }
        assertEquals("6804", output);

        // Simple case two integers length one and three with carryover
        test = "7 961 *";
        BigNumArithmetic.readLine(test);
        stack = BigNumArithmetic.getStack();
        list = (LList) stack.pop();
        output = "";
        for (int i = 1; i <= list.length(); i++) {
            output = output + list.get(list.length() - i);
        }
        assertEquals("6727", output);

        // Complex test three integers length one with carryover
        test = "7 7 7 * *";
        BigNumArithmetic.readLine(test);
        stack = BigNumArithmetic.getStack();
        list = (LList) stack.pop();
        output = "";
        for (int i = 1; i <= list.length(); i++) {
            output = output + list.get(list.length() - i);
        }
        assertEquals("343", output);

        // complex case leading spaces and zeros
        test = "    00002310    000064    007 * *";
        BigNumArithmetic.readLine(test);
        stack = BigNumArithmetic.getStack();
        list = (LList) stack.pop();
        output = "";
        for (int i = 1; i <= list.length(); i++) {
            output = output + list.get(list.length() - i);
        }
        assertEquals("1034880", output);
    }

    @Test
    public void exponentTest() {
        // Simple case
        String test = "2 1 ^";
        BigNumArithmetic.readLine(test);
        LStack stack = BigNumArithmetic.getStack();
        LList list = (LList) stack.pop();
        String output = "";
        for (int i = 1; i <= list.length(); i++) {
            output = output + list.get(list.length() - i);
        }
        assertEquals("2", output);

        // Simple case with 0
        test = "2 0 ^";
        BigNumArithmetic.readLine(test);
        stack = BigNumArithmetic.getStack();
        list = (LList) stack.pop();
        output = "";
        for (int i = 1; i <= list.length(); i++) {
            output = output + list.get(list.length() - i);
        }
        assertEquals("1", output);


        // Simple case with 0
        test = "0 2 ^";
        BigNumArithmetic.readLine(test);
        stack = BigNumArithmetic.getStack();
        list = (LList) stack.pop();
        output = "";
        for (int i = 1; i <= list.length(); i++) {
            output = output + list.get(list.length() - i);
        }
        assertEquals("0", output);

        // Simple case with 0
        test = "0 0 ^";
        BigNumArithmetic.readLine(test);
        stack = BigNumArithmetic.getStack();
        list = (LList) stack.pop();
        output = "";
        for (int i = 1; i <= list.length(); i++) {
            output = output + list.get(list.length() - i);
        }
        assertEquals("1", output);


        // Simple case with leading zeros and spaces
        test = "00021    00005 ^";
        BigNumArithmetic.readLine(test);
        stack = BigNumArithmetic.getStack();
        list = (LList) stack.pop();
        output = "";
        for (int i = 1; i <= list.length(); i++) {
            output = output + list.get(list.length() - i);
        }
        assertEquals("4084101", output);

        // Complex case
        test = "5 2  5 ^ ^";
        BigNumArithmetic.readLine(test);
        stack = BigNumArithmetic.getStack();
        list = (LList) stack.pop();
        output = "";
        for (int i = 1; i <= list.length(); i++) {
            output = output + list.get(list.length() - i);
        }
        assertEquals("23283064365386962890625", output);

    }

    @Test
    public void halveTest() {
        String test = "3124";
        BigNumArithmetic object = new BigNumArithmetic();
        LList list = new LList();
        list = object.str_to_num(test, list);
        list = object.halve(list);
        String output = "";
        for (int i = 1; i <= list.length(); i++) {
            output = output + list.get(list.length() - i);
        }
        assertEquals("1562", output);
    }

    @Test
    public void num_to_str() {
        LList list = new LList();
        BigNumArithmetic object = new BigNumArithmetic();
        list.append(4);
        list.append(3);
        list.append(2);
        list.append(1);
        assertEquals("1234", object.num_to_str(list));
    }

    @Test
    public void char_to_int() {
        BigNumArithmetic object = new BigNumArithmetic();
        assertEquals(0, object.char_to_int('0'));
        assertEquals(1, object.char_to_int('1'));
        assertEquals(2, object.char_to_int('2'));
        assertEquals(3, object.char_to_int('3'));
        assertEquals(4, object.char_to_int('4'));
        assertEquals(5, object.char_to_int('5'));
        assertEquals(6, object.char_to_int('6'));
        assertEquals(7, object.char_to_int('7'));
        assertEquals(8, object.char_to_int('8'));
        assertEquals(9, object.char_to_int('9'));
    }

}