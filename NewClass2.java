//import javax.swing.Container;
//import java.awt.Container;
import java.awt.*;
import java.lang.*;
import javax.swing.*;
public class NewClass2 extends JFrame
{
	NewClass2()
	{
		setSize(600,600);
		setLocation(500,300);
		Font f = new Font("SansSerif", Font.PLAIN, 18);
		Container cp = this.getContentPane();  
       	 	JTextPane pane = new JTextPane();
		pane.setBackground(new Color(223,215,255));
		//pane.setBackground(new Color(255,250,240));
		JScrollPane scrollPane = new JScrollPane(pane);
                cp.add(scrollPane); 
		pane.setContentType("text/html"); 
		pane.setEditable(false);
		pane.setFont(f);
		pane.setLayout(new FlowLayout());
		pane.setText("<br><br><center><h1 color=orange style=\"font-size:35px; \"><u>CREDIT GOES TO !!</u></h1><h1 style=\"font-size:25px; \"><br><br><img src=file:\"ameya.jpeg\" height=250 width=220 align=center border=4><br><br>Name : Ameya Chitnis<br>DIV : A<br>Roll Number :8713<br>Email : <font color=blue></font><br>Phone : <br><br><br><br><img src=file:\"shankar.jpeg\" height=250 width=220 align=center border=3><br><br>Name : Shankar Birajdar<br>DIV : A<br>Roll Number :8760<br>Email : <font color=blue></font><br>Phone : <br><br><br><br><img src=file:\"ravi.jpeg\" height=250 width=220 align=center border=3><br><br>Name : Ravikant Dukre<br>DIV : A<br>Roll Number :8747<br>Email : <font color=blue></font><br>Phone : <br><br><h1><center>");
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}

