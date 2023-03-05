import java.io.FileReader;
import java.io.IOException;

/**
 *
 */
public class BigNumArithmetic {
    //initialize variables
    //we use this to pop and push digits
    private static LStack stack = new LStack();
    private static String lines = ""; //has everything that has not been checked
    private static String output = ""; //this is used for formatting output

    /**
     * @param args
     */
    // to perform operation and the result if line in is valid
    public static void main(String[] args) {
        if (args.length < 2) {
            System.exit(0);
        }
        String filename = args[1];
        try {
            lines = readFile(filename);
        } catch (IOException e) {
            System.out.println("error");
        }
        while (!lines.isEmpty()) {
            output = getFirstLine();
            String t = output;
            readLine(removeSpace(output));
            if (stack.length() > 1) {
                output = "";
                while (!t.isEmpty()) {
                    t = removeSpace(t);
                    t = remove0(t);
                    int i = 0;
                    //initialize counter to
                    // loop through the string until 1st space using string length
                    while (i < t.length() && t.charAt(i) != ' ') {
                        i++; //update counter
                    }
                    t = removeSpace(t);
                    output = output + remove0(t.substring(0, i)) + " ";
                    t = t.substring(i);
                }
                output = output + "=";
                stack.clear();
            } else if (!stack.isEmpty()){
                LList temp = (LList) stack.pop();
                int i = 1;
                output = output + " = ";
                while (i <= temp.length()) {
                    output = output + temp.get(temp.length() - i);
                    i++;
                }
            }
            boolean loop = true;
            if (output.isBlank()){
                loop = false;
            }
            int c = 0;
            while (loop){
                if (c >= output.length()){
                    loop = false;
                } else if (output.charAt(c) == ' '){
                    output = remove0(output.substring(0, c)) + " " + remove0(removeSpace(output.substring(c)));
                }
                c++;
            }
            if (!output.isBlank()) {
                System.out.println(output);
            }
        }
    }

    /**
     * @param string
     * @param list
     * @return
     */
    // Use str_to_num without leading 0's
    public static LList str_to_num(String string, LList list) {
        // Base case
        // String is "" return
        if (string.isEmpty()) {
            return list;
        }

        // Recursive case
        // Set node equal to first digit
        list.moveToStart();
        // Insert to reverse num order
        list.insert(char_to_int(string.charAt(0)));
        // set node.next to str_to_num(substring)
        str_to_num(string.substring(1), list);
        return list;
    }

    /**
     * @param string
     * @return
     */
    // Use string of just leading 0's and one number no space
    public static String remove0(String string) {
        // If string was 0 to start with return "0"
        if (string.isEmpty() || string.charAt(0) == ' ') {
            return ("0" + string);
        }
        // Check if first digit is 0
        else if (string.charAt(0) == '0') {
            // Recursive using substring
            return remove0(string.substring(1));
        } else {
            return string;
        }
    }

    /**
     * @param string
     * @return
     */
    // Use string with spaces and numbers
    public static String removeSpace(String string) {
        //checks the string and if the first character is a space
        // it deletes it changes the string and checks it again
        while (!string.isEmpty() && string.charAt(0) == ' ') {
            //gets everything except the first index
            string = string.substring(1);
        }
        return string; //return without spaces
    }

    /**
     * @param string
     * @return
     */
    // Remove space before running
    public static String getFirst(String string) {
        string = removeSpace(string); //removes the leading space
        int c = 0;
        while (c < string.length() && string.charAt(c) != ' '){
            c++;
        }
        string = remove0(string.substring(0, c)) + string.substring(c); //removes leading 0s
        //checks if the string is empty
        if (string.isEmpty()) {
            return string;
        } else {
            int i = 0;//initialize counter to loop through the string
            // until 1st space using string length
            while (i < string.length() && string.charAt(i) != ' ') {
                i++; //update counter
            }
            //checks to see what operation is there it is addition
            if (string.charAt(0) == '+') {
                // Add
                // checks if stacks has two numbers on the stack to perform operations
                if (!isValid()) {
                    stack.push(new LList());
                    return "";
                }
                stack.push(add((LList) stack.pop(), (LList) stack.pop())); //pushing the sum back onto the stack
                //return everything not first the first char(basically get rid of +)
                string = string.substring(1);
                string = removeSpace(string); //removes the leading space
                return string;
            }
            //checks to see what operation is there it is multiplication
            else if (string.charAt(0) == '*') {
                // Multiply
                // checks if stacks has two numbers on the stack to perform operations
                if (!isValid()) {
                    stack.push(new LList());
                    return "";
                }
                stack.push(multiply((LList) stack.pop(), (LList) stack.pop()));
                string = string.substring(1);
                string = removeSpace(string); //removes the leading space
                return string;
                //checks to see what operation is there it is exponent

            } else if (string.charAt(0) == '^') {
                // Exponent
                // checks if stacks has two numbers on the stack to perform operations
                if (!isValid()) {
                    stack.push(new LList());
                    return "";
                }
                LList b = (LList) stack.pop();
                LList a = (LList) stack.pop();
                stack.push(exponent(a, b));
                string = string.substring(1);
                string = removeSpace(string); //removes the leading space
                return string;
            } else {
                //create temp list to use str_to_num method
                LList tempList = new LList();
                //pushing the tempList onto the stack,
                // and it contains the first num in the string
                stack.push(str_to_num(string.substring(0, i), tempList));
                //returns a substring of everything we have not read yet
                return string.substring(i);
            }
        }
    }

    /**
     * @param string
     */
    //
    public static void readLine(String string) {
        while (!string.isEmpty()) {
            string = getFirst(string);
        }
    }

    /**
     * @param filename
     * @return
     * @throws IOException
     */
    public static String readFile(String filename) throws IOException {
        //initialize filereader
        FileReader fileReader = new FileReader(filename);
        String value = "";
        //tells whether file is ready to be read one char at time
        while (fileReader.ready()) {
            //string of chars
            value += (char) fileReader.read();
        }
        return value;
    }

    /**
     * @return
     */
    public static String getFirstLine() {
        /*
        if(lines.isEmpty()){
            return "";
        }
         */
        int i = 0; //initialize counter
        //checks for whether we are on new line
        while (i < lines.length() && lines.charAt(i) != '\r' && lines.charAt(i) != '\n') {
            i++; //update counter
        }
        //storing the 1st line
        String temp = lines.substring(0, i);
        boolean loop = true;
        while (loop){
            if(i >= lines.length()){
                loop = false;
            } else if (lines.charAt(i) == '\r' || lines.charAt(i) == '\n'){
                i++;
            } else{
                loop = false;
            }
        }
        //storing lines as everything except the 1st line
        if (i < lines.length()) {
            lines = lines.substring(i);
        } else {
            lines = "";
        }
        return temp;
    }

    /**
     * @return
     */
/*
have to check if valid before doing add
otherwise error due to our implementation
 */
    public static LList add(LList a, LList b) {
        //intialize LList containing nums on the stack
        LList temp = new LList();//created to return the nums
        int i = 0; //counter
        int sum = 0; //that is the sum
        int r = 0; //the remainder for the carry over if necessary
        while (i < a.length() || i < b.length()) {
            // Check to see if past a's index
            if (i >= a.length() && i < b.length()) {
                sum = (int) b.get(i) + r;
            }
            // Check to see if past b's index
            else if (i >= b.length()) {
                sum = (int) a.get(i) + r;
            } else if (i < b.length() && i < a.length()) {
                sum = (int) a.get(i) + (int) b.get(i) + r;
            }
            if (sum >= 10) {
                r = sum / 10;
                sum = sum % 10;
            } else {
                r = 0;
            }
            // add digit to llist
            temp.append(sum);
            i++; // update counter
        }
        if(r != 0){
            temp.append(r);
        }
        return temp;
    }

    public static LList multiply(LList a, LList b){
        LList output = new LList();
        LList tempList = new LList();
        int temp = 0;
        int c;
        int i = 0;
        while (i < a.length()){
            c =0;
            int counter = 0;
            tempList.clear();
            while (counter < i) {
                tempList.append(0);
                counter++;
            }
                while (c < b.length()){
                    temp = (int) b.get(c) * (int) a.get(i);
                    tempList.append(temp);
                    c++;
                }
                tempList = round(tempList);
            output = add(output, tempList);
            i++;
        }

        return output;
    }


    public static LList round(LList list){
        int r = 0;
        int temp = 0;
        LList output = new LList();
        for (int i = 0; i < list.length(); i++){
            temp = (int) list.get(i) + r;
            if((int) list.get(i) >= 10){
                r = temp / 10;
                temp = temp % 10;
                output.append(temp);
            }
            else {
                r = 0;
                output.append(temp);
            }
            }
        if(r != 0){
            output.append(r);
        }
        for (int i = 0; i < output.length(); i++){
            if((int) output.get(i) >= 10){
                return round(output);
            }
        }
            return output;
        }

        public static LList exponent(LList a, LList b){
        LList output = new LList();
            if(b.length() == 1 && (int) b.get(0) == 0){
                output.append(1);
                return output;
            } else if (b.length() > 0 && (int) b.get(0) % 2 == 0) {
                return exponent(multiply(a, a), halve(b));
            } else if (b.length() > 0 && (int) b.get(0) % 2 == 1) {
                int temp = (int) b.get(0) - 1;
                b.moveToStart();
                b.remove();
                b.insert(temp);
                return multiply(a, exponent(multiply(a, a), halve(b)));
            }
            return output;
        }

        public static LList halve(LList list){
        LList output = new LList();
        for (int i = 0; i < list.length(); i++){
            if ((int) list.get(i) % 2 == 0) {
                output.append((int) list.get(i) / 2);
            } else {
                int temp = (int) list.get(i - 1);
                output.moveToPos(i - 1);
                //output.remove();
                output.append((temp + 5));
                if((int) list.get(i) / 2 != 0) {
                    output.append((int) list.get(i) / 2);
                }
            }
        }
        return output;
        }

    /**
     * @param c
     * @return
     */
    // Takes in char c and converts to int
    public static int char_to_int(char c) {
        if (c == '0') {
            return 0;
        }
        if (c == '1') {
            return 1;
        }
        if (c == '2') {
            return 2;
        }
        if (c == '3') {
            return 3;
        }
        if (c == '4') {
            return 4;
        }
        if (c == '5') {
            return 5;
        }
        if (c == '6') {
            return 6;
        }
        if (c == '7') {
            return 7;
        }
        if (c == '8') {
            return 8;
        }
        if (c == '9') {
            return 9;
        }
        return 0;
    }

    /**
     * @return
     */
    public static boolean isValid() {
        // checks if at least two nums on stack
        if (stack.length() < 2) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * @return
     */
    // For testing
    public static LStack getStack() {
        return stack;
    }
}
