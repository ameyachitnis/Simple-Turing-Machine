import java.util.HashSet;

public class Tape {
    /**
    * Tape class is designed to make the turing machine tape a little
    * friendlier
    */
    // "Initial" fields allow for the tape to be reset
    public char[] tape;
    private char[] init_tape;
    // Need to access this
    public int pos;
    private int initial_pos;
    private HashSet<Character> alphabet;
    public boolean notAtEnd = true;

    /**
     * Creates a new tape with default length of 100.
     * Position is initialized to zero.
     * Reccommended that this isn't used
     */
    Tape() {
        tape = new char[100];
        initial_pos = 0;
        this.pos = this.initial_pos;
        alphabet = new HashSet<>();
    }

    /**
     * Creates a new tape from provided char array
     * Position is initialized to 0
     * Alphabet initialized to provided HashSet
     */
    Tape(char[] charr, HashSet<Character> alphabet) {
        init_tape = charr;
        // "Deep" copy
        tape = init_tape.clone();
        // Do a "deep" copy
        initial_pos = 0;
        this.alphabet = alphabet;
        this.pos = this.initial_pos;
    }

    /**
     * Creates a new tape from provided char array
     * Position is initialized to provided integer
     * Alphabet initialized to provided HashSet
     */
     Tape(char[] charr, int pos, HashSet<Character> alphabet) {
        init_tape = charr;
        tape = init_tape.clone();
        initial_pos = pos;
        this.alphabet = alphabet;
        this.pos = this.initial_pos;
     }

     /**
      * Moves the tape in provided direction
      * @param direction true is right, false is left
      */
     void move(boolean direction) throws BeginningOfTape {
        if (direction) {
            // Check if out of bounds, if not position is iterated anyways
            if (++pos >= tape.length) {
                this.notAtEnd = false;
                --pos;
            }
        } else {
            // Same checking
            if (--pos < 0) {
                ++pos;
                throw new BeginningOfTape();
            }
        }
     }
     
     /**
      * Used to read character on tape
      * @return char at current position in the tape
      */
     char readChar() throws CharNotInAlphabet {
         if (this.alphabet.contains(this.tape[this.pos])) return this.tape[this.pos];
         // Throw exception if it's not in the alphabet
         else throw new CharNotInAlphabet("Character not found in alphabet, invalid tape");
     }

     /**
      * Used to overwrite character on tape 
      * @param c replaces char at current position
      */
     void changeChar(char c) {
         this.tape[this.pos] = c;
     }

     /**
      * Resets tape back to original instantiation
      */
     void reset() {
         this.pos = initial_pos;
         this.tape = init_tape.clone();
         this.notAtEnd = true;
     }

     @Override
     public String toString() {
         String ret = java.util.Arrays.toString(tape);
         return ret;
     }

    public class CharNotInAlphabet extends Exception {
 
        public CharNotInAlphabet(String message) {
            super(message);
        }
    }

    public class BeginningOfTape extends Exception {
 
        public BeginningOfTape() {
            super("Cannot move past start of the tape");
        }
    }

}