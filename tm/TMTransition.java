package tm;

/**
 * TMTransition is the object class for a turing machine's transitions.
 * Each transition knows it's toState, what it writes, and whether it should move the tape left or right.
 * The main class will keep track of the fromState and onRead of each transition, since they are used as hashing keys.
 * @author Dean Cunningham, Haylee Staub
 * @date Spring 2026
 */
public class TMTransition {

    private boolean LR; //Determines if you step left or right on transition (0 = Left, 1 = Right)
    private int write;
    private int toState;
    
    public TMTransition(int toState, int write, boolean LR) {
        this.toState = toState;
        this.write = write;
        this.LR = LR;
    }

    /**
     * Gets the toState for a given transition.
     * @return int toState
     */
    public int getToState(){
        return this.toState;
    }

    /**
     * Gets the value a transition will write
     * @return int write
     */
    public int getWrite(){
        return this.write;
    }

    /**
     * Gets the LR boolean for a given transition
     * @return boolean LR
     */
    public boolean getLR(){
        return this.LR;
    }
}
