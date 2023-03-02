
public class BigNumArithmetic{
    public static void main(String args){

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
        list.append(char_to_int(string.charAt(0)));
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
        if(string.isEmpty()){
            return string;
        }else {
            removeSpace(string.substring(1));
        }
        return string;
    }
    // Remove space before running
    public static String getFirst(String string, Stack stack){
        if(string.isEmpty()){
            return string;
        }
        else{
            int i = 0;
            if(i < string.length() && string.charAt(i) != ' '){
                i++;
            }
            String temp = string.substring(i);
            return temp;
        }
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
}
