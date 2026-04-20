package tm;

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
