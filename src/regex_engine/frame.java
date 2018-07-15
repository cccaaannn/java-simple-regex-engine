package regex_engine;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;

import javafx.scene.control.ScrollPane;



public class frame extends JFrame {

	

	String RE;
	String pattern[] = new String[1000];
	int length = 0;
	File file = new File("C:\\Users\\user\\eclipse-workspace\\regex_engine\\pattern.txt");
	
	
	
	
	
	
	
	
	frame(String a){
		super(a);
	
	setLayout(null);
	

	this.getContentPane().setBackground(new Color(0,120,255));
	
	
	
	this.add(lb1);
	this.add(lb2);
	this.add(lb3);
	
	lb1.setFont(new Font("arial",Font.BOLD,15));
	lb2.setFont(new Font("arial",Font.BOLD,15));
	lb3.setFont(new Font("arial",Font.BOLD,15));
	
	lb1.setBounds(20,20,350,30);
	lb2.setBounds(20,100,350,30);
	lb3.setBounds(400,100,350,30);
	

	
	this.add(tf1);

	tf1.setBounds(20,50,350,40);
	
	tf1.setFont(new Font("arial",Font.BOLD,20));

	
	//this.add(ta1);
	
	this.add(sp2);
	
	ta1.setFont(new Font("arial",Font.PLAIN,20));
	
	sp2.setBounds(20,130,350,470);
	
	
	
    //this.add(ta2);
	
    this.add(sp);
    
    ta2.setFont(new Font("arial",Font.PLAIN,20));
    
	sp.setBounds(400,130,350,470);
	
	ta2.setEditable(false);
	
	
	this.add(b1);
	
	b1.setBounds(20,610,70,30);
	
    this.add(b2);
	
	b2.setBounds(100,610,120,30);
	
	
	
	//this.add(ta1);
	
	//ta1.setBounds(arg0, 1000, arg2, arg3);
	
	
	b1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
		
			if(tf1.getText().equals("") || ta1.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Fill the empty fields");
			}	
			else {
				
				ta2.setText("");
				
				
				RE = tf1.getText().toString();
			

				
				
		
				
				
				
				 int lines = ta1.getLineCount();

			        try{
			            for(int i = 0; i < lines; i ++){
			                int start = ta1.getLineStartOffset(i);
			                int end = ta1.getLineEndOffset(i);
			              
			                pattern[i] = ta1.getText(start, end-start);

			                length++;
			              //  System.out.println(pattern[i]);
			            }
			        }catch(BadLocationException e){
			           System.out.println("alamadým");
			        
			        }
				
				
               System.out.println("RE " + RE+" PATTERN "+ pattern[0]+" length "+length);
				
               NFA.automatamatic(RE,pattern,length);
				
               System.out.println("RE " + RE+" PATTERN "+ pattern[0]+" length "+length);
				
               
				length = 0;
				
				String pattern[] = new String[1000];
			
			}
			
		}
	});
	
	
	
	
	
	
	
	
	
	
	
	
	
	b2.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
		
			
			
			if(tf1.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Fill the empty fields");
			}	
			else {
				
				ta2.setText("");
				
				
				
				RE = tf1.getText().toString();
			
///////////////read from file////////////
				try {
					  BufferedReader br = new BufferedReader(new FileReader(file));
					  int i = 0;
					  while ((pattern[i] = br.readLine()) != null) { 
					 i++;
					 length++;
					  }
					  
				}
					  catch (Exception e) {
						System.out.println("OKUYAMADIM");
					}
	/////////////send it to nfa////////////////	
	
               NFA.automatamatic(RE,pattern,length);
				
   /////////////reset string /////////////
				length = 0;
				
				String pattern[] = new String[1000];
			
			}
			
		}
	});
	}
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	GridLayout gl = new GridLayout(3,1);
	
	FlowLayout fl = new FlowLayout();
	
	
	
	
	
	JLabel lb1 = new JLabel("ENTER REGULAR EXPRESSÝON");
	JLabel lb2 = new JLabel("ENTER TEST PATTERN");
	JLabel lb3 = new JLabel("ACCEPTED STRINGS");
	
	
	
	
	
	JTextField tf1 = new JTextField(); 
	
	
	JTextArea ta1 = new JTextArea();
	static JTextArea ta2 = new JTextArea();
	
	
	
	JScrollPane sp = new JScrollPane(ta2);
	JScrollPane sp2 = new JScrollPane(ta1);
	
	
	
	
	
	JButton b1 = new JButton("regex"); 
	JButton b2 = new JButton("read from text"); 
	
	
	

	public static void setta2(String s) {
		ta2.setText(s);

	}
	
	public static String getta2() {
		return ta2.getText().toString();

	}
	
	
	
	
}
