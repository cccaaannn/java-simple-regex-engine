package regex_engine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

import javax.swing.JFrame;



public class Main {

	public static void main(String[] args) {
		
		String RE;
		String pattern[] = new String[10000];
		int length = 0;
		File file = new File("C:\\Users\\user\\eclipse-workspace\\regex_engine\\pattern.txt");//C:\\Users\\can\\eclipse-workspace\\regex_engine\\pattern.txt
		
		
		
		
		
		frame menu = new frame("REGEX ENGÝNE");
		menu.setBounds(300, 80, 800, 700);
		menu.setResizable(false);
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu.setVisible(true);
		
	
		
		//RE = menu.getRE();
		
		
		
		
		
		
		
		/*
		Scanner reader = new Scanner(System.in);
		System.out.println("Enter regular expression: ");
		RE = reader.nextLine();
	
		
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
		*/
		/*
		pattern[0] = "azazazbazbbbbbbbbbb";
		length = 1;
		*/
		
		//NFA.automatamatic(RE,pattern,length);
		
		
		
		
		//tam|(b*az)
		//(a|b*b)
		//a|bb*
		
	}
	
}

