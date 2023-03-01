
public class BigNumArithmetic{
    public static void main(String args){

    }
    // Use str_to_num without leading 0's
    public LList str_to_num(String string, LList list){
        // Base case
        // String is "" return
        if(string.isEmpty()){
            return list;
        }

        // Recursive case
        // Set node equal to first digit
        char c = string.charAt(0);
        list.append((int) c);
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
    public String[] removeSpace(String string, String[] array){
        while(!string.isEmpty() && string.charAt(0) == ' '){
            string = string.substring(1);
        }
        int c = 0;
        if(string.isEmpty()){
            return array;
        }else {
            while(string.charAt(c) != ' '){
                c++;
            }
            int length = array.length;
            array[length] = string.substring(0, c);
            removeSpace(string.substring(c), array);
        }
        return array;
    }
}
