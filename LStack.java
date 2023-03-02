// Linked stack implementation
class LStack implements Stack {
    private Link top;               // Pointer to first element
    private int size;               // Number of elements

    // Constructors
    LStack() { top = null; size = 0; }
    LStack(int size) { top = null; size = 0; }

    // Reinitialize stack
    public void clear() { top = null; size = 0; }

    // Put "it" on stack
    public boolean push(Object it) {
        top = new Link(it, top);
        size++;
        return true;
    }

    // Remove "it" from stack
    public Object pop() {
        if (top == null) return null;
        Object it = top.element();
        top = top.next();
        size--;
        return it;
    }

    public Object topValue() {      // Return top value
        if (top == null) return null;
        return top.element();
    }

    // Return stack length
    public int length() { return size; }

    // Check if the stack is empty
    public boolean isEmpty() { return size == 0; }

    //  Check to see if string of ()[] is valid or not
    // For this method I create a new stack to add the elements of the string
    // I then loop through each character of the string and apply methods based on what the char is
    // If the char is [ or ( push them to the stack
    // If the char is ] or ) pop the stack and see if what was popped matches
    public boolean isValid(String s){
        LStack stack = new LStack();    // Create new LStack
        int c = 0;      // Create counter
        while (c < s.length()){     // Loop through string
            if(s.charAt(c) == '[' || s.charAt(c) == '('){       // See if [ or ) and push
                stack.push(s.charAt(c));
            }
            if(s.charAt(c) == ']'){     // See if ] and pop to see if [ and if not return false
                if(stack.size <= 0 || (char)stack.pop() != '['){
                 System.out.println("Not valid");       // Print not valid
                 return false;
                }
            }
            if(s.charAt(c) == ')'){         // See if ) and pop to see if ( and if not return false
                if(stack.size <= 0 || (char)stack.pop() != '('){
                    System.out.println("Not valid");    // Print not valid
                    return false;
                }
            }
            c++;    // Increment counter
        }
        if(stack.size > 0){     // If stack has [ or ( left in it then return not valid
            System.out.println("Not valid");
            return false;
        }
        return true;
    }
}