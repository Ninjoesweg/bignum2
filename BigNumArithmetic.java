import java.io.FileReader;
import java.io.IOException;

public class BigNumArithmetic{
    //initialize variables
    private static LStack stack = new LStack();//we use this to pop and push digits
    private static String lines = "";//has everything that has not been checked
    private static String output = "";//this is used for formatting output
    private static String equals = "";// to perform operation and the result if line in is valid
    public static void main(String[] args){
        if(args.length < 2){
            System.exit(0);
        }
        String filename = args[1];
        try {
           lines = readFile(filename);
        }catch (IOException e){
            System.out.println("error");
        }
        while(!lines.isEmpty()){
            output = getFirstLine();
            readLine(output);
            if(stack.length() != 1){
                String t = output;
                output = "";
                while(!t.isEmpty()){
                    t = removeSpace(t);
                    t = remove0(t);
                    int i = 0;//initialize counter to loop through the string until 1st space using string length
                    while(i < t.length() && t.charAt(i) != ' '){
                        i++;//update counter
                    }
                    output = output + t.substring(0, i) + " ";
                    t = t.substring(i);
                }
                output = output + "=";
            }else{
                LList temp = (LList) stack.pop();
                int i = 1;
                output = output + " = ";
                while(i <= temp.length()){
                    output = output + temp.get(temp.length() - i);
                    i++;
                }
            }
            System.out.println(output);
        }
    }
    // Use str_to_num without leading 0's
    public static LList str_to_num(String string, LList list){
        // Base case
        // String is "" return
        if(string.isEmpty()){
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

    // Use string of just leading 0's and one number no space
    public static String remove0(String string){
        // If string was 0 to start with return "0"
        if(string.isEmpty()){
            return ("0");
        }
        // Check if first digit is 0
        else if(string.charAt(0) == '0'){
            // Recursive using substring
            return remove0(string.substring(1));
        } else{
            return string;
        }
    }

    // Use string with spaces and numbers
    public static String removeSpace(String string){
        //checks the string and if the first character is a space it deletes it changes the string and checks it
        //again
        while(!string.isEmpty() && string.charAt(0) == ' '){
            //gets everything except the first index
            string = string.substring(1);
        }
        return string;//return without spaces
    }
    // Remove space before running
    public static String getFirst(String string){
        string = removeSpace(string);//removes the leading space
        string = remove0(string);//removes leading 0s
        //checks if the string is empty
        if(string.isEmpty()){
            return string;
        }
        else{
            int i = 0;//initialize counter to loop through the string until 1st space using string length
            while(i < string.length() && string.charAt(i) != ' '){
                i++;//update counter
            }
            //checks to see what operation is there it is addition
            if(string.charAt(0) == '+'){
                // Add
                if(!isValid()){// checks if stacks has two numbers on the stack to perform operations
                    stack.push(new LList());
                    return "";
                }
                stack.push(add());//pushing the sum back onto the stack
                return string.substring(1);//return everything not first the first char(basically get rid of +)
            }
            //checks to see what operation is there it is multiplication
            else if(string.charAt(0) == '*'){
                // Multiply
                // checks if stacks has two numbers on the stack to perform operations
                if(!isValid()){
                    return string;
                }
                //checks to see what operation is there it is exponent

            } else if (string.charAt(0) == '^') {
                // Exponent
                if(!isValid()){// checks if stacks has two numbers on the stack to perform operations
                    return string;
                }
            }
            else {
                //create temp list to use str_to_num method
                LList tempList = new LList();
                //pushing the tempList onto the stack and the it contains the first num in the string
                stack.push(str_to_num(string.substring(0, i), tempList));
                //returns a substring of everything we have not read yet
                return string.substring(i);
            }
            return string;
        }
    }

    //
    public static void readLine(String string){
        while(!string.isEmpty()){
            string = getFirst(string);
        }
    }

    public static String readFile(String filename) throws IOException {
        //initialize filereader
        FileReader fileReader = new FileReader(filename);
        String value = "";
        //tells whether file is ready to be read one char at time
        while(fileReader.ready()){
            //string of chars
            value += (char) fileReader.read();
        }
        return value;
    }

    public static String getFirstLine(){
        /*
        if(lines.isEmpty()){
            return "";
        }
         */
        int i = 0;//initialize counter
        //checks for whether we are on new line
        while(i < lines.length() && lines.charAt(i) != '\r'){
            i++;//update counter
        }
        //storing the 1st line
        String temp = lines.substring(0, i);
        //storing lines as everything except the 1st line
        if(i + 2 < lines.length()) {
            lines = lines.substring(i + 2);
        }else{
            lines = "";
        }
        return temp;
    }
/*
have to check if valid before doing add otherwise error due to our implementation
 */
    public static LList add(){
        //intialize LList containing nums on the stack
        LList a = (LList) stack.pop();
        LList b = (LList) stack.pop();
        LList temp = new LList();//created to return the nums
        int i = 0;//counter
        int sum = 0;//that is the sum
        int r = 0;//the remainder for the carry over if necessary
        while(i < a.length() || i < b.length()){
            // Check to see if past a's index
            if(i >= a.length()){
                sum = (int) b.getValue() + r;
                b.next();
            }
            // Check to see if past b's index
            else if(i >= b.length()){
                sum = (int) a.getValue() + r;
                a.next();
            } else{
                sum = (int) a.getValue() + (int) b.getValue() + r;
                a.next();
                b.next();
            }
            if(sum > 10){
                r = sum / 10;
                sum = sum % 10;
            }else{
                r = 0;
            }
            // add digit to llist
            temp.append(sum);
            i++; // update counter
        }
        return temp;
    }

    // Takes in char c and converts to int
    public static int char_to_int(char c){
        if(c == '0'){return 0;}
        if(c == '1'){return 1;}
        if(c == '2'){return 2;}
        if(c == '3'){return 3;}
        if(c == '4'){return 4;}
        if(c == '5'){return 5;}
        if(c == '6'){return 6;}
        if(c == '7'){return 7;}
        if(c == '8'){return 8;}
        if(c == '9'){return 9;}
        return 0;
    }

    public static boolean isValid(){
        // checks if at least two nums on stack
        if(stack.length() < 2){
            return false;
        }
        else{
            return true;
        }
    }

    // For testing
    public static LStack getStack() {return stack;}
}
