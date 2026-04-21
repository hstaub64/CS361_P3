package tm;

import java.util.ArrayList;

public class TMTape {
    private ArrayList<Integer> tape;    //See if there's a better list implementation,
                                        //Right now adding to front of list is O(n) which is very slow
    private int head;   //The current position of the head

    /**
     * Creates a new tape with the given string (list of numbers) as the starting tape
     * Sets head of tape to the start of string, or points to a blank if startingTape is empty.
     * @param startingTape
     */
    public TMTape(String startingTape) {
        //Populate the tape with starting string
        this.tape = new ArrayList<>();
        for (int i = 0; i<startingTape.length(); i++) { //Add each character of the string to the starting tape
            tape.add(startingTape.charAt(i) - '0');
        }
        if (tape.isEmpty()) {
            tape.add(0);    //0 represents a blank
        }
        //Set the head
        head = 0;
    }

    /**
     * Reads the current character, then writes over it and moves
     * in the specified direction.
     * @param write
     * @param LR direction of head movement (false = left, true = right)
     * @return int value of the tape symbol before modification
     */
    public void Write(int write, boolean LR){
        //Moving Left
        if (LR == false){
            //Extend the tape if at the start
            if (head == 0) {
                tape.set(head, write);
                tape.add(0, 0); //Add a blank   
            } else {
                tape.set(head, write);
                head--;
            }
        }
        //Moving Right
        if (LR == true){
            //Extend the tape if at the end
            tape.set(head, write);
            head++;
            if (head == tape.size()){
                tape.add(0); // Add a blank
            } 
        }
    }

    /**
     * Gets the character currently at the head of the string
     * @return
     */
    public int Read(){
        return tape.get(head);
    }

    /**
     * ToString for TMTape
     */
    public String toString(){
        String output = "";
        int length = 0;
        //Print the entire tape
        for (int i = 0; i < tape.size(); i++) {
            output += tape.get(i) + "";
            length++;
        }

        //The rest of this is for debugging, TODO: remove when its time to pass tests.
        //Add a pointer to the current head position
        output += "\n";
        for (int i = 0; i < head; i++) {
            output += " ";
        }
        output += "^";
        output += "\n Output Length: " + length;
        return output;
    }
}
