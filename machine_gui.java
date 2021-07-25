import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class machine_gui {
    // The private machine, static so I can edit it easily.
    private static Machine turing_machine;
    // Control display of turing_machine tape

    JFrame frame;
    // For error messages
    StatusBar status,status1;
    // Panel A is the main tape
    JPanel panelA;
    JPanel panelF;
    JLabel tape;
    JLabel im;

    // Panel B has the state it's current in
    JPanel panelB;
    JLabel state;
    // Panel C has Buttons and tape change;
    JPanel panelC;
    JPanel panelE;
    JButton run;
    JButton step;
    JButton reset;
    JButton nf1;
    JButton nf2;
    JButton nf3;
    JLabel tapeInstruction;
    JTextField changeTape;
    JButton inputTape;

    // Panel D is selecting file or inputting
    JPanel panelD;
    JLabel jcomboI;
    JComboBox<String> jcombo;
    JLabel or;
    JLabel instruction;
    JTextField tapeInput;
    JButton input;

	
    public machine_gui() {
        frame = new JFrame("Turing Machine");
        frame.setSize(950, 650);
        frame.setLocationRelativeTo(null);
	//frame.setUndecorated(true);
	//frame.getRootPane().setBorder(BorderFactory.createMatteBorder(3,3,3,3,Color.BLACK));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().setBackground(new Color(255,140,105));
        frame.setLayout(new GridLayout(7,1));
	Font f = new Font("serif", Font.BOLD, 10);
	Font biggerFont = f.deriveFont(25f);

        panelA = createPanelA();
        panelB = createPanelB();
        panelC = createPanelC();
	panelE = createPanelE();
        panelD = createPanelD();
	panelF = createPanelF(); 
	//panelE = createPanelE();


        frame.add(panelA);
        frame.add(panelB);
        frame.add(panelC);
	frame.add(panelE);
        frame.add(panelD);
	frame.add(panelF);
	//frame.add(panelE);

        status = new StatusBar();
	status.setSize(100,100);
	status.setFont(biggerFont);
        status.setMessage("Error Message will appear here");
	//status.setBounds(80,30,120,40);
        frame.add(status);
	status.setHorizontalAlignment(StatusBar.CENTER);
	//frame.add(status, BorderLayout.CENTER);*/

    }

    JPanel createPanelA() {
        JPanel panel = new JPanel(null);
	//panel.setSize(new Dimension(1000,500));
        panel.setLayout(new FlowLayout());
	panel.setBackground(new Color(255,250,240));
	//panel.setLayout(null);
        tape = new JLabel("<html><br><font size=10>[ T u r i n g   <u></font><font color='red'> M</font></u><font size=10> a c h i n e ]</font></html>");
        tape.setFont(new Font("Serif", Font.PLAIN, 25));
	tape.setBounds(700,50,600,100);
        panel.add(tape);
	//im=new JLabel(new ImageIcon("turingMachine.gif"));
	//im.setPreferredSize(new Dimension(400,300));
	//panel.add(im);

        return panel;
    }

    JPanel createPanelB() {        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1,2));
	panel.setBackground(new Color(255,250,240));

        state = new JLabel("State: Initial");
        state.setFont(new Font("Serif", Font.PLAIN, 30));
        state.setHorizontalAlignment(JLabel.CENTER);
        panel.add(state);
	String strstr=state.getText();
	String str="State: Initial";
	if(strstr.compareTo(str)==0)
	{
		System.out.println("AMEYA");
	}

        return panel;
    }

    JPanel createPanelC() {
        JPanel panel = new JPanel();
	Font f = new Font("serif", Font.BOLD, 25);
	//panel.setSize(new Dimension(1000,500));
        panel.setLayout(new FlowLayout(FlowLayout.CENTER,30,0));
	panel.setBackground(new Color(255,250,240));

        run = new JButton("Run");
	run.setFont(f);
	//run.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        run.addActionListener(new runMachine());
        panel.add(run);

        step = new JButton("Step");
	step.setFont(f);
        step.addActionListener(new stepMachine());
        panel.add(step);

        reset = new JButton("Reset");
	reset.setFont(f);
        reset.addActionListener(new resetMachine());
        panel.add(reset);
	
	return panel;
	}
    JPanel createPanelE() {
	JPanel panel = new JPanel();
	Font f = new Font("serif", Font.BOLD, 20);
	Font f1 = new Font("monospaced", Font.PLAIN, 18);
	//panel.setSize(new Dimension(1000,500));
        panel.setLayout(new FlowLayout());
	panel.setBackground(new Color(255,250,240));
        tapeInstruction = new JLabel("Change tape (string): ");
	tapeInstruction.setFont(f);
        panel.add(tapeInstruction);

        changeTape = new JTextField("", 20);
	changeTape.setFont(f1);
        panel.add(changeTape);

        inputTape = new JButton("Input");
	inputTape.setFont(f);
        inputTape.addActionListener(new changeMachineTape());
        panel.add(inputTape);

        return panel;
    }

    JPanel createPanelD() {
        JPanel panel = new JPanel();
	Font f = new Font("serif", Font.BOLD, 18);
        panel.setLayout(new FlowLayout());
	panel.setBackground(new Color(255,250,240));

        jcomboI = new JLabel("Select Example Machine: ");
	jcomboI.setFont(f);
        panel.add(jcomboI);

        jcombo = new JComboBox<>(new String[] {"even_a.txt", "palindrome.txt", "anbncn.txt", "fibonacci.txt"});
	jcombo.setFont(f);
        // I don't like the anonymous class, but it works
        jcombo.addActionListener(new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                machine_gui.turing_machine = Machine.createMachine((String)jcombo.getSelectedItem());
                updateTape();
            }
        });
        panel.add(jcombo);

        or = new JLabel("<html><font size=20 color='blue'>&nbsp;&nbsp;<u>OR</u>&nbsp;&nbsp;</font><html>");
	or.setFont(f);
        panel.add(or);

        instruction = new JLabel("Input Machine File: ");
	instruction.setFont(f);
        panel.add(instruction);

        tapeInput = new JTextField("", 20);
	tapeInput.setFont(f);
        panel.add(tapeInput);

        input = new JButton("Input");
	input.setFont(f);
        panel.add(input);
        input.addActionListener(new getFileInput());

        return panel;
    }
 
        JPanel createPanelF() {
        JPanel panel = new JPanel();
	Font f = new Font("SansSerif", Font.PLAIN, 18);
	//panel.setSize(new Dimension(1000,500));
        panel.setLayout(new FlowLayout(FlowLayout.CENTER,30,0));
	panel.setBackground(new Color(255,250,240));

        nf1 = new JButton("What is Turing Machine ?");
	nf1.setFont(f);
        nf1.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent e){
	NewClass nc=new NewClass();
	}
	});
        panel.add(nf1);
	

        nf2 = new JButton("About The Project");
	nf2.setFont(f);
        nf2.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent e){
	NewClass1 nc=new NewClass1();
	}
	});
        panel.add(nf2);

        nf3 = new JButton("We The Developers");
	nf3.setFont(f);
        nf3.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent e){
	NewClass2 nc=new NewClass2();
	}
	});
        panel.add(nf3);
	
	
	return panel;
	}
    /**
     * Updates tape, state, and status of machine
     */
    void updateTape() {
        if (machine_gui.turing_machine.tape == null) {
            tape.setText("[ ]");
            status.setMessage("Tape is empty");
            return;
        }

        state.setText("State: " + machine_gui.turing_machine.state);
	//if(machine_gui.turing_machine.state.compareTo("Halt")==0)

        // For highlighting, need to use html which is annoying, so i have to build it piece by piece
        StringBuilder str = new StringBuilder();
        // Store locally so don't have to constantlly access
        int tpos = machine_gui.turing_machine.tape.pos;
        char[] tp = machine_gui.turing_machine.tape.tape;

        str.append("<html><br><font size=10>[ ");

        
        // Append all characters up to current char
        for (int i = 0; i < tpos; i++) {
            str.append(tp[i]+" ");
        }

        // Append underlined character
        str.append("<u><font color='red'>");
        str.append(tp[tpos]);
        str.append("</font></u>");

        for (int i = tpos+1; i < tp.length; i++) {
            str.append(" "+tp[i]);
        }

        str.append(" ]</font></html>");
        tape.setText(str.toString());
    }


    /**
     * Attempts to create a machine from a fileName input. If invalid, will display error message.
     */
    private class getFileInput implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent arg0) {
            String fileName = tapeInput.getText();
            Machine m = Machine.createMachine(fileName);

            // Check if no machine created yet
            if (m == null) {
                status.setMessage("Invalid Machine Entry. Check formatting of input file.");
                return;
            }

            status.setMessage("Machine imported. See console for description.");
            machine_gui.turing_machine = m;
            machine_gui.turing_machine.printMachine();
            updateTape();
        }
    }

    private class changeMachineTape implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent arg0) {
            if (machine_gui.turing_machine == null) {
                status.setMessage("Please import a machine before changing the tape.");
                return;
            }

            String newTape = changeTape.getText();
            machine_gui.turing_machine.reset();
            machine_gui.turing_machine.changeTape(newTape);
            updateTape();


            status.setMessage("Tape changed.");
        }
    }

    private class runMachine implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent arg0) {
            if (machine_gui.turing_machine == null) {
                status.setMessage("Please import a machine before running.");
                return;
            }

            if (machine_gui.turing_machine.state.equals(machine_gui.turing_machine.accept) || machine_gui.turing_machine.state.equals(machine_gui.turing_machine.reject)) {
                status.setMessage("Please reset machine before running.");
		//JOptionPane.showMessageDailog("AMEYA CHITNIS");
                return;
            }

            machine_gui.turing_machine.reset();
            machine_gui.turing_machine.run();
            updateTape();

            status.setMessage("Machine Ran");
        }
    }

    private class stepMachine implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent arg0) {
            if (machine_gui.turing_machine == null) {
                status.setMessage("Please import a machine before running.");
                return;
            }

            if (machine_gui.turing_machine.state.equals(machine_gui.turing_machine.accept) || machine_gui.turing_machine.state.equals(machine_gui.turing_machine.reject)) {
                status.setMessage("Please reset machine before running.");
                return;
            }

            try {
                Tuple configuration = new Tuple(machine_gui.turing_machine.state, machine_gui.turing_machine.tape.readChar());
                Tuple next_configuration = machine_gui.turing_machine.transitions.get(configuration);

                if (next_configuration == null) {
                    //throw new Exception("No transition avaliable");
                    System.out.println(configuration);
                    status.setMessage("No transition available, machine halting");
                    state.setText("State: Halted");
                    return;
                }

                machine_gui.turing_machine.state = next_configuration.state;
                if (next_configuration.ch != '$') {
                    machine_gui.turing_machine.tape.changeChar(next_configuration.ch);
                }
                
                try {
                    machine_gui.turing_machine.tape.move(next_configuration.direction);
                    updateTape();
                } catch (Tape.BeginningOfTape e) {
                    status.setMessage("Error: Attempted to move past the beginning of the tape. Machine halted.");
                    state.setText("State: Halted");
                }
            } catch (Tape.CharNotInAlphabet e) {
                status.setMessage("Error: Char not in alphabet, halting machine");
                state.setText("State: Halted");
                return;                
            }
            
        }
    }

    private class resetMachine implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent arg0) {
            if (machine_gui.turing_machine == null) {
                status.setMessage("Please import a machine before running.");
                return;
            }

            machine_gui.turing_machine.state = machine_gui.turing_machine.initial;
            machine_gui.turing_machine.tape.reset();
            updateTape();
        }
    }
    
}
