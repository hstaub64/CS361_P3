package tm;

import java.util.HashMap;
import java.util.Scanner;
import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;

public class TMSimulator {

    private int numStates;
    private int sigma; 
    private HashMap<Point, TMTransition> transitions;
    private int curState;
    private TMTape tape;

    public static void main(String[] args) {
        //Checks for valid args before continuing into Simulation
        TMSimulator simulator = new TMSimulator();
        if (args.length != 1){
            System.out.println("Incorrect number of arguments.\nPlease run using java tm.TMSimulator [filename]");
            System.exit(1);
        }
        File file = new File(args[0]);
        Scanner scan;
        try {
            scan = new Scanner(file);
            String tapeOutput = simulator.SimulateFile(scan);
            System.out.println(tapeOutput);
            scan.close();
            System.exit(0);
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + args[0]);
            e.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * SimulateFile is the main driver for TMSimulator. It assumes the provided file
     * is valid for the TM format in project specifications.
     * The return value is the error code, if an error is hit. (Returns 0 on success)
     * 
     * It reads the provided file to collect the 6-tuple for the Turing Machine
     * and runs the provided string through the machine to see if it is valid.
     * @param scan
     * @return ending tape
     */
    public String SimulateFile(Scanner scan){
        //The first two lines will provide the States, Sigma, startState, and finalState
        numStates = Integer.parseInt(scan.nextLine());
        sigma = Integer.parseInt(scan.nextLine());
        transitions = new HashMap<>();
        //startState = 0;
        //haltState = numStates - 1;
        /*
        The next states * (sigma + 1) lines provide a list of transitions
        Each line is 3 comma separated values following the format:
        next_state, write_symbol, move (Left/Right)
        */
        for (int i = 0; i < numStates-1; i++) {
            for (int j = 0; j < sigma+1; j++) {
                String curLine = scan.nextLine();
                String[] csv = curLine.split(",");
                int toState = Integer.parseInt(csv[0]);
                int writeSymbol = Integer.parseInt(csv[1]);
                boolean LR;
                if (csv[2].equals("L")) { LR = false; }
                else { LR = true; }
                Point key = new Point(i, j);    //Key format: (fromState, onSymbol)
                TMTransition transition = new TMTransition(toState, writeSymbol, LR);
                transitions.put(key, transition);
                //System.out.println(i + ", " + j);
            }
        }

        //The starting tape is the last value we read from the file
        String startString = "0";   //Default to a blank if there's no starting tape
        if (scan.hasNext()){
            startString = scan.nextLine();
        }
        scan.close();
        
        tape = new TMTape(startString);
        curState = 0;

        boolean halted = false;
        while (!halted) {
            halted = TMStep();
        }
        return tape.toString();
    }

    /**
     * TMStep simulates taking one step with the Turing Machine.
     * Returns true if the TM enters the halt state.
     * @return
     */
    public boolean TMStep(){
        int head = tape.Read();
        Point key = new Point(curState, head);
        TMTransition transition = transitions.get(key);

        tape.Write(transition.getWrite(), transition.getLR());

        curState = transition.getToState();

        if (curState == numStates - 1) {
            return true;
        } else {
            return false;
        }
    }
}