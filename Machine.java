import java.util.HashSet;
import java.util.HashMap;
import java.util.Collections;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Machine {
    /*
    * Turing machine has 7 parts
    * 1) Finite set of states
    * 2) Input alphabet
    * 3) Tape
    * 4) Transition function (read, write, move)
    * 5) Initial state
    * 6) Accept state
    * 7) Reject state
    * 8) Current state
    */

    public HashSet<String> states;
    public HashSet<Character> alphabet;
    public Tape tape;
    public HashMap<Tuple, Tuple> transitions;
    public String initial;
    public String accept;
    public String reject;
    public String state;

    /**
     * Initializes a machine to defaul values
     */
    Machine() {
        states = new HashSet<>();
        alphabet = new HashSet<>();
        tape = new Tape();
        transitions = new HashMap<>();
        // Other states must be initialized
    }

    /**
     * Initializes Machine from provided fields
     */
    Machine(HashSet<String> states, HashSet<Character> alphabet, Tape tape, HashMap<Tuple, Tuple> transitions, String initial, String accept, String reject) {
        this.states = states;
        this.alphabet = alphabet;
        this.tape = tape;
        this.transitions = transitions;
        this.initial = initial;
        this.accept = accept;
        this.reject = reject;
        state = initial;
    }

    /**
     * Creates a turing machine from inputted file (It's basically a constructor, but it was didn't want to handle execptions in a constructor)
     * @param fileName with structure 
     * Line 1: all states, separated by commas
     * Line 2: all characters in alphabet, separated by commas
     * Line 3: the tape, represented by a single string, with $ representing the end
     * Line 4: initial state, accept state, and reject state separated by commas
     * Lines 5-end: transitions, in form initial state, initial char, result state, result char ($ if no change), direction
     * @return instantiated machine
     */
    static Machine createMachine(String fileName){
        try {
            File file = new File(fileName);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            
            String line;
            // Track what line we are at
            int count = 0;
            Machine t = new Machine();
            while ((line = reader.readLine()) != null) {
                switch (count) {
                    case 0: String[] statesArr = line.split(",");
                            Collections.addAll(t.states, statesArr);
                            count++; break;
                    case 1: String[] charArr = line.split(",");
                            for (String s : charArr) {
                                // Assuming no spaces
                                t.alphabet.add(s.charAt(0));
                            } count++; break;
                    case 2: t.tape = new Tape(line.toCharArray(), t.alphabet); count++; break;
                    case 3: String[] collection = line.split(",");
                            if (collection.length != 3) {
                                System.out.println("Invalid amount of the determining states");
                                count = 5;
                                break;
                            } if (!t.states.contains(collection[0]) || !t.states.contains(collection[1]) || !t.states.contains(collection[2])) {
                                System.out.println(java.util.Arrays.toString(collection));
                                System.out.println("Invalid deterministic states. Make sure they are elements of the machine's states");
                                count = 5;
                                break;
                            }
                            t.initial = collection[0];
                            t.accept = collection[1];
                            t.reject = collection[2];
                            t.state = t.initial; count++; break;
                    case 4: collection = line.split(",");
                            if (collection.length != 5) {
                                break;
                            }
                            if (!t.states.contains(collection[0]) || !t.states.contains(collection[2])) {
                                System.out.println("Invalid state, ignored transition");
                                break;
                            }
                            Tuple config = new Tuple(collection[0], collection[1].charAt(0));
                            Tuple next_config = new Tuple(collection[2], collection[3].charAt(0), (Integer.parseInt(collection[4]) == 1));
                            t.transitions.put(config, next_config); break;
                    case 5: 
                            break;
                }
            }
            
            if (count == 5) {reader.close(); return null;};

            reader.close();
            return t;
        } catch (IOException e) {
            return null;
        }
    }

    void printMachine() {
        System.out.println("----------Machine----------");
        System.out.print("States: ");
        System.out.println(this.states);
        System.out.print("Alphabet: ");
        System.out.println(this.alphabet);
        System.out.print("Tape: ");
        System.out.println(this.tape);
        System.out.print("Transitions");
        System.out.println(Collections.singletonList(this.transitions));
        System.out.print("Initial State: ");
        System.out.println(this.initial);
        System.out.print("Accept State: ");
        System.out.println(this.accept);
        System.out.print("Reject State: ");
        System.out.println(this.reject);
        System.out.println("----------------------------");
    }

    /**
     * Changes tape to inputted tape
     * @param newTape a string representing tape of a turing machine
     */
    void changeTape(String newTape) {
        if (newTape.equals("")) {
            this.tape = new Tape(new char[] {'\0'}, this.alphabet);
            return;
        }
        this.tape = new Tape(newTape.toCharArray(), this.alphabet);
    }

    void reset() {
        this.tape.reset();
        this.state = this.initial;
    }
    // Steps through the turing machine
    void run() {
        // Loop until we reach the end of the tape
        try {
            // Reset tape and state
            System.out.print("Tape: " + tape + " | ");
            while(this.tape.notAtEnd) {
                // Create a tuple to represent configuration (direction is not needed here)
                try {
                    // Break if it's a halt state
                    if (this.state.equals(this.reject) || this.state.equals(this.accept))
			{
				
				break;
			}

                    Tuple configuration = new Tuple(this.state, tape.readChar());

                    //System.out.println("Current config: " + configuration.state + " " + configuration.ch);
                    
                    // Get next configuration
                    Tuple next_configuration = transitions.get(configuration);

                    //System.out.println("Next config: " + next_configuration.state + " " + next_configuration.ch);
    
                    // Check if it was a valid transition
                    if (next_configuration == null) {
                        System.out.println(configuration);
                        //throw new Exception("No transition avaliable");
                        System.out.print("No transition available, machine halting: ");
                        break;
                    }
    
                    this.state = next_configuration.state;
                    if (next_configuration.ch != '$') {
                        tape.changeChar(next_configuration.ch);
                    }
                    tape.move(next_configuration.direction);
                    
                    //System.out.println("Tape: " + this.tape);
                    
                    // Sleep for a second to see what's going on
                    /*
                    try {
                        java.util.concurrent.TimeUnit.SECONDS.sleep(1);    
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } 
                    */
                } catch (Tape.CharNotInAlphabet e) {
                    System.err.print(e + "; ");
                    break;
                }
            }

            // If it reaches the end, check what state its in
            if (this.state.equals(this.accept)) {
                System.out.println("Accepted");
            } else {
                this.state = this.reject;
                System.out.println("Rejected");
            }
        } catch (Tape.BeginningOfTape e) {
            System.err.println(e);
        }/* catch (Exception e) {
            System.err.println(e);
            if (this.state.equals(this.accept)) {
                System.out.println("Accepted");
            } else {
                System.out.println("Rejected");
            }
        }*/
    }
}
