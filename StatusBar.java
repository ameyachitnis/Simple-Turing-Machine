// Taken from https://www.java-tips.org/java-se-tips-100019/15-javax-swing/39-creating-a-status-bar.html
import javax.swing.*;
import java.awt.Dimension;

public class StatusBar extends JLabel {
     
    /** Creates a new instance of StatusBar */
    public StatusBar() {
        super();
        super.setPreferredSize(new Dimension(100, 16));
        setMessage("Ready");
    }
     
    public void setMessage(String message) {
        setText(" "+message);        
    }        
}
