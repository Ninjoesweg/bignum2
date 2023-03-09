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

    /**
     * @param args
     */
    // to perform operation and the result if line in is valid
    public static void main(String[] args) {
        //reads in file name from the command line
        String filename = args[0];
        //try catch to see whether the file exists
        try {
            //reads everything that has not be checked from the file
            lines = readFile(filename);
            //print error message if canot read from file
        } catch (IOException e) {
            System.out.println("error");
        }
        /*while lines that have not been check
        are not equal to empty call getfirst line and store into output
        then read in the lines while calling remove space function on output
        to remove the spaces in output file
         */
        while (!lines.isEmpty()) {
            String output = getFirstLine();
            String t = output;
            readLine(removeSpace(output));
            //if stack has something on it
            if (stack.length() > 1) {
                output = "";//output is equal to empty string
                //does under the condition that string t is not empty
                while (!t.isEmpty()) {
                    /*calling remove space on temp variable t and passing it in
                    remove space as it takes a string var as a parmeter
                    then updates t to new value of t with output
                    that has removed spaces
                     */
                    t = removeSpace(t);
                    /*calling remove 0 on temp variable t and passing it in remove 0
                    as it takes a string var as a parmeter
                    then updates t to the new value of t with output
                    that has removed all leading 0's

                     */
                    t = remove0(t);//
                    int i = 0;
                    //initialize counter to
                    // loop through the string until 1st space using string length
                    while (i < t.length() && t.charAt(i) != ' ') {
                        i++; //update counter
                    }
                    //added to prevent additional singular space at beginning
                    t = removeSpace(t);
                    /*output is updated to be equal to itself plus the value
                    of whatever the first number is without the leading 0s

                     */
                    output = output + remove0(t.substring(0, i)) + " ";
                    //sets temp string var t to everything except the first number or operator
                    t = t.substring(i);
                }
                output = output + "=";// adds an equal sign to output
                stack.clear();//clear the stack
                //other case the stack is not empty then do everything in this conditional
            } else if (!stack.isEmpty()){
                /*make a LList temp variable that stores the value
                of whatever was poped on the stack
                 */
                LList temp = (LList) stack.pop();
                //this is the variable used to loop through the temp list
                // it is 1 we subtract from it later which would cause a crash
                //if it were 0
                int i = 1;
                output = output + " = ";//adds an equal sign to output
                /*loop for the conditional that the variable
                i is less than or equal to the lenght of the LList temp value
                 */
                while (i <= temp.length()) {
                    /*updates the value of output
                    to reverse the number stored in temp
                    to make math is easier
                     */
                    output = output + temp.get(temp.length() - i);
                    i++;//update the counter
                }
            }
            boolean loop = true;// make a boolean value and intilize to true
            //update output and remove the space on output again
            output = removeSpace(output);
            //if output is empty return false cancel loop
            if (output.isEmpty()){
                loop = false;
            }
            int c = 0;// other counter for new loop
            // while loop is equal to true do everything under the conditional
            while (loop){
                //if c is greater or equal the length of output

                if (c >= output.length()){
                    loop = false;//return false

                }
                //removes leading 0s and spaces for each value
                else if (output.charAt(c) == ' '){
                    output = remove0(output.substring(0, c)) + " " + remove0(removeSpace(output.substring(c)));
                }
                c++;//updates counter
            }
            //update output to remove space
            output = removeSpace(output);
            /* if the output is not equal to empty
            it doesn't output anything
            specifically looking for the
            empty lines
             */
            if (!output.isEmpty()) {
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
        //checks whether is empty and if it is return the LList
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
        string = removeSpace(string);
        //if string is empty return empty string
        if (string.isEmpty()){
            return "";
        }
        // if string is = to just 0 reuturn the string
        if (string.length() < 2 || string.charAt(1) == ' '){
            return string;
        }
        // if the string starts with 0 remove it
        if (string.charAt(0) == '0') {
            return remove0(string.substring(1));
        }
        // otherwise return just the string
        else {
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
        int c = 0;// intialize c
        while (c < string.length() && string.charAt(c) != ' '){
            c++;//update variable
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
                    //pushs a new stack to cause to be caught
                    stack.push(new LList());
                    return "";
                }
                /*multiples output and pushes onto stack and
                pops the numbers used to multiply
                 */
                stack.push(multiply((LList) stack.pop(), (LList) stack.pop()));
                //removes the operator
                string = string.substring(1);
                string = removeSpace(string); //removes the leading space
                return string;
                //checks to see what operation is there it is exponent

            } else if (string.charAt(0) == '^') {
                // Exponent
                // checks if stacks has two numbers on the stack to perform operations
                if (!isValid()) {
                    //pushs a new stack to cause to be caught
                    stack.push(new LList());
                    return "";
                }
                /*create two LList for parmeters for later function
                pop the digits that are having the operator performed on them
                and then push the result of each product of exponent onto
                the stack
                 */

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
        //checks for if the string is not empty
        while (!string.isEmpty()) {
            // if its not empty performs operations to make empty 1 value a time
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
        // intialize 2 LLists
        LList output = new LList();
        LList tempList = new LList();
        int temp = 0; //create a temp variable
        int c; //create 2nd counter
        int i = 0;// create 1st counter
        /*
        loops through LList a and at every stop
        loops through LList b and performs multiply
        and then appends to the output
         */
        while (i < a.length()){
            c =0;
            int counter = 0;
            tempList.clear();
            while (counter < i) {
                tempList.append(0);
                counter++;//update counter
            }
            /*

             */
                while (c < b.length()){
                    temp = (int) b.get(c) * (int) a.get(i);
                    tempList.append(temp);
                    c++;//update counter
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
                LList temp = new LList();
                output = exponent(multiply(a, a), str_to_num(remove0(num_to_str(halve(b))), temp));
            } else if (b.length() == 1 && (int) b.get(0) == 1) {
                return a;
            } else if (b.length() > 0 && (int) b.get(0) % 2 == 1) {
                int temp = (int) b.get(0) - 1;
                LList templist = new LList();
                b.moveToStart();
                b.remove();
                b.insert(temp);
                output = multiply(a, exponent(multiply(a, a), str_to_num(remove0(num_to_str(halve(b))), templist)));
            }
            return output;
        }

        public static LList halve(LList list){
        LList output = new LList();
        for (int i = 0; i < list.length(); i++){
            if ((int) list.get(i) % 2 == 0) {
                output.append((int) list.get(i) / 2);
            } else if (output.length() > i - 1){
                int temp = (int) list.get(i - 1) / 2;
                output.append((temp + 5));
                output.moveToPos(i - 1);
                output.remove();
                if((int) list.get(i) == 1) {
                    output.append(0);
                }
                if (i == list.length() - 1){
                    output.append((int) list.get(i) / 2);
                }
            } else {
                output.append(0);
            }
        }
        return output;
        }

        public static String num_to_str(LList list){
            String output = "";
            for (int i = 1; i <= list.length(); i++) {
                output = output + list.get(list.length() - i);
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
