## Description
This code both provides an emulation of a turing machine (based around a char array tape) and a GUI to show how the machine moves around. The primary purpose of this code is to give a visual example of how turing machine works, so it is not ideally suited for large turing machine examples. However, it theoretically should be able to do so, given that the implementation follows all properties of a turing machine, but I have not tested these for bugs.

## Instructions
Compile the java files with javac *.java, and the main method is in main.java for execution (java main). You can select one of the three files, step through them / run them, and the machine 6-tuple descriptiopn is outputted to console upon sepection. Furthermore, the user can input given that it follows the correct format.

## Limitations
* Tape size: Due to time constraints and bugs, I removed the feature of a moving window, so it is reccomended that tape inputs be limited to all that is necessary to show the functionality.
* Inifity: This machine will run forever if the machine never terminates (only if run is hit), so this machine should only be used for turing-decidable languages

## Files included:
*Machine.java     - the actual implementation of a turing machine
*Tape.java        - a "helper" class that simplifies the traversal of the char array underneath
*machine_gui.java - the gui code
*Tuple.java       - modified code from Brunelle that allows for the transtions to be "one type"
*StatusBar.java   - simple code that made an easy status StatusBar
*even_a.txt       - a simple machine to decides strings with even a's (empty string should be represented by $)
*palindrome.txt   - checks palindrome
*anbncn.txt       - a machine that shows decides a^nb^nc^n

## Input File Format:
Line 1: Set of states, separated by a comma (no spaces unless part of of state name) \
Line 2: Set of characters in the alphabet, separated by comma (char must be first item in row, ex. b,a will read b and a, but b, a will read b and space) \
Line 3: The tape, represented by a string. Note that I use $ as a symbol to represent the end of a string for each file, and though this isn't necessary, any custom tape of these must end with $ \
Line 4: Initial state, accept state, reject state; in that order, no spaces (unless part of state name) \
Lines 5-end: Transitions, listed as cur_state, cur_char, next_state, changed_char, direction. Put $ if no change is desired; 1 is right, 0 is left for direction

Here is even_a for an example: \
Even,Odd,Accept,Reject \
a,$ \
aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa$ \
Even,Accept,Reject \
Even,a,Odd,$,1 \
Odd,a,Even,$,1 \
Even,$,Accept,$,1 \
Odd,$,Reject,$,1

## Sources:
* StatusBar - https://www.java-tips.org/java-se-tips-100019/15-javax-swing/39-creating-a-status-bar.html
* https://www.geeksforgeeks.org/java-swing-jcombobox-examples/
* https://stackoverflow.com/questions/17456401/how-to-update-a-jlabel-text/17456465
* https://stackoverflow.com/questions/5752307/how-to-retrieve-value-from-jtextfield-in-java-swing
* https://stackoverflow.com/questions/7300135/how-to-use-an-action-listener-to-check-if-a-certain-button-was-clicked
