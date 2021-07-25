public class Tuple {
// This class is a modification of Brunelle's tuple class from the
// automata material.

    public final String state;
    public final char ch;
    public final boolean direction;
    
    // If boolean is unnecessary, can default it to false
    public Tuple(String s, char c) {
        this.state = s;
        this.ch = c;
        this.direction = false;
    }

    public Tuple(String s, char c, boolean b) {
        this.state = s;
        this.ch = c;
        this.direction = b;
    }
    
    /**
     * Equality is based on input state and input char, as there should not be multiple
     * transitions for that combination
     */
    public boolean equals(Object o){
        if (this == o) return true;
        if(!(o instanceof Tuple)) return false;
        Tuple t = (Tuple) o;
        return (this.state).equals(t.state) && this.ch == t.ch;
    }

    @Override
    public String toString() {
        return this.state + " " + this.ch;
    }
    
    public int hashCode() {
        return this.state.hashCode() + this.ch;
    }

}
