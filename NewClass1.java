import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.io.*;
import javax.swing.*;
public class NewClass1 extends JFrame
{
	public NewClass1(){
	JPanel p = new JPanel();
    	JTextPane tp = new JTextPane();
	TextFromFile(tp);
	Font font = new Font("",Font.BOLD,20);
        tp.setFont(font);
	tp.setForeground(Color.WHITE);
	tp.setBackground(Color.BLACK);
	JScrollPane jp = new JScrollPane(tp);
	p.setLayout(new BorderLayout());
    	p.add(jp,BorderLayout.CENTER);
	setContentPane(p);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setLocationRelativeTo(null);
    	setSize(800,500);
    	setVisible(true);
    	}
    
    /*
    create a function to get the text from a text file 
    and set it into a JTextPane
    */ 
    	public static void TextFromFile(JTextPane tp)
    	 {
        try{
            //the file path
            String path = "readMe.md";
            File file = new File(path);
            FileReader fr = new FileReader(file);
            while(fr.read() != -1){
              tp.read(fr,null);
            }
            fr.close();
        } catch(Exception ex){
          ex.printStackTrace();
        }
     }
    
}


  
