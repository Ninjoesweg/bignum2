import java.io.FileReader;
import java.io.IOException;

public class BigNumArithmetic{
    private static LStack stack = new LStack();
    private static String lines = "";
    private static String output = "";
    private static String equals = "";
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

            }else{
                equals = " = ";
                LList temp = (LList) stack.pop();
                int i = 1;
                while(i <= temp.length()){
                    equals = equals + temp.get(temp.length() - i);
                    i++;
                }
                output = output + equals;
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
                if(!isValid()){
                    stack.push(new LList());
                    return "";
                }
                stack.push(add());
                return string.substring(1);
            }
            else if(string.charAt(0) == '*'){
                // Multiply
                if(!isValid()){
                    return string;
                }
            } else if (string.charAt(0) == '^') {
                // Exponent
                if(!isValid()){
                    return string;
                }
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
        while(i < lines.length() && lines.charAt(i) != '\r'){
            i++;
        }
        String temp = lines.substring(0, i);
        if(i + 1 < lines.length()) {
            lines = lines.substring(i + 2);
        }else{
            lines = "";
        }
        return temp;
    }

    public static LList add(){
        LList a = (LList) stack.pop();
        LList b = (LList) stack.pop();
        LList temp = new LList();
        int length1 = a.length();
        int length2 = b.length();
        int i = 0;
        int sum = 0;
        int r = 0;
        while(i < length1 && i < length2){
            if(a.getValue() == null){
                sum = (int) b.getValue() + r;
                b.next();
            }
            else if(b.getValue() == null){
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
            temp.append(sum);
            i++;
        }
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

    public static boolean isValid(){
        if(stack.length() < 2){
            return false;
        }
        else{
            return true;
        }
    }

    public static LStack getStack() {return stack;}
}
