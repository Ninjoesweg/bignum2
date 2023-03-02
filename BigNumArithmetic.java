import java.io.FileReader;
import java.io.IOException;

public class BigNumArithmetic{
    private static LStack stack = new LStack();
    private static String lines = "";
    public static void main(String[] args){
        String filename = args[1];
        try {
           lines = readFile(filename);
        }catch (IOException e){
            System.out.println("error");
        }
        while(!lines.isEmpty()){
            readLine(getFirstLine());
        }
        LList test = new LList();
        test = (LList) stack.pop();
        System.out.println(test.get(0));
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
        while(!string.isEmpty() && string.charAt(0) == ' '){
            string = string.substring(1);
        }
        return string;
    }
    // Remove space before running
    public static String getFirst(String string){
        string = removeSpace(string);
        string = remove0(string);
        if(string.isEmpty()){
            return string;
        }
        else{
            int i = 0;
            while(i < string.length() && string.charAt(i) != ' '){
                i++;
            }
            if(string.charAt(0) == '+'){
                // Add
            }
            else if(string.charAt(0) == '*'){
                // Multiply
            } else if (string.charAt(0) == '^') {
                // Exponent
            }
            else {
                LList tempList = new LList();
                stack.push(str_to_num(string.substring(0, i), tempList));
                String temp = string.substring(i);
                return temp;
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
        FileReader fileReader = new FileReader(filename);
        String value = "";
        while(fileReader.ready()){
            value += (char) fileReader.read();
        }
        return value;
    }

    public static String getFirstLine(){
        if(lines.isEmpty()){
            return "";
        }
        int i = 0;
        while(i < lines.length() && lines.charAt(i) != '\n'){
            i++;
        }
        String temp = lines.substring(0, i);
        lines = lines.substring(i + 1);
        return temp;
    }


    public static int char_to_int(char c){
        if(c == '0'){
            return 0;
        }
        if(c == '1'){
            return 1;
        }
        if(c == '2'){
            return 2;
        }
        if(c == '3'){
            return 3;
        }
        if(c == '4'){
            return 4;
        }
        if(c == '5'){
            return 5;
        }
        if(c == '6'){
            return 6;
        }
        if(c == '7'){
            return 7;
        }
        if(c == '8'){
            return 8;
        }
        if(c == '9'){
            return 9;
        }
        return 0;
    }

    public boolean isValid(LStack stack){
        if(stack.length() < 2){
            return false;
        }
        else{
            return true;
        }
    }

    public static LStack getStack() {return stack;}
}
