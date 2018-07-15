package regex_engine;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

import javax.swing.text.html.HTMLDocument.Iterator;

public class NFA {
	
	private static int stateIDsource = 0;

	private int initialState;
	private HashSet<Integer> allStates;
	private HashSet<Integer> acceptStates;
	private HashSet<Edge> transitions;
	
	private HashSet<Integer> currentStates;
	
	private NFA(int initialState, HashSet<Integer> allStates, HashSet<Integer> acceptStates, HashSet<Edge> transitions) {
		this.initialState = initialState;
		this.allStates = new HashSet<Integer>(allStates);
		this.acceptStates = new HashSet<Integer>(acceptStates);
		this.transitions = new HashSet<Edge>(transitions);
	}
	
	public String toString() {
		String stringRepresentation = "States : \n";
		stringRepresentation += allStates + "\nTransitions : \n";
		for (Edge edge : transitions) {
			stringRepresentation += edge + "\n";
		}
		return ("initial state:\n" + this.initialState + "\n" + "accepting states: \n" + acceptStates.toString() + "\n" + stringRepresentation);
	}
	
	public boolean accepts(String s) {
			

			 HashSet<Integer> E = new HashSet();
			 HashSet<Integer> C = new HashSet();
			
			int charcount = 0;
		
			
			E.add(this.initialState);
	            
			
			while(charcount != s.length()){
				
			
				int size = E.size();
	          int size2 = size;
				
	          
	          
	          
	          
	          //if there is epsilon after epsilon we should repeat
	          while(true) {
	             
      
	             for(int i = 0;i < size;i++) {
				//for (int i:E) {
					for(Edge j:this.transitions) {             //this and part is prevents to try again if it exist
						if(E.contains(j.getSourceState()) && !E.contains(j.getDestinationState())) {
							if(j.isEpsilonTransition()) {
								E.add(j.getDestinationState());
							    size2++;
						
							    //	System.out.println("epsilon kontrolu ");
							}
						}
					}
				}
	            
	             
			//if there is diffrence loop again
		if(size == size2) {
					break;
				}
				else {
					size = size2;
				}
				
	             }
				
	
	          
	          
	          
	          
	          
	          
	          C.clear();
	
	          //this part is decides that if the char can go furter on the machine
				
				for (int i:E) {
					for(Edge j:this.transitions) {
							if(j.getSourceState() == i) {
								if(j.getSymbol() == s.charAt(charcount)) {
								C.add(j.getDestinationState());
								//System.out.println("char kontrolü ");
							}
						}
					}	
				}
			
				
				
				

			E.clear();
				
			
			E.addAll(C);
			
			 
				charcount++;
				
			}
			

			//this part decides if there is any accept state remain on the set E and if there is we accept string 
				for (int i:E) {
					for(int j:acceptStates) {
					 if(i == j) {
						return true;			
					}
				}
			}
			
			return false;
			
		

	}
	
	//we assume that we handled epsilon part on the first loop of accept function so we didnt used this
	/*
	public HashSet<Integer> epsilonClosure(int state) {
    //finds the set of states that are in epsilon closure of given 'state' within
    //this automaton. (set of states that can be reached from 'state' using
    //epsilon transitions)
    
	
		
		//TODO implement kleeneClosure!

		//throw new UnsupportedOperationException("Not implemented: kleeneClosure");
	}
	*/
	private static int newState() {
		//every new state must have distinct id, so increment the id generator
		//each time you need a new one.
		return stateIDsource++;
	}
	
	public static NFA singleSymbol(char symbol) {
		//implemented for you: singleSymbol
		int initialState = newState();
		int finalState = newState();
		
		//we have just two states here
		HashSet<Integer> statesSet = new HashSet<Integer>();
		statesSet.add(initialState);
		statesSet.add(finalState);

		//there is a single accept state,
		HashSet<Integer> acceptStates = new HashSet<Integer>();
		acceptStates.add(finalState);
		
		//there is a single transition in this automaton, which takes initial state
		//to final state if input is given as the same as 'symbol' variable.
		Edge edge = new Edge(initialState, finalState, symbol);
		HashSet<Edge> transitions = new HashSet<Edge>();
		transitions.add(edge);
		
		//create an NFA with such properties.
		return new NFA(initialState, statesSet, acceptStates, transitions);
	}
	
	//we used the way that shown on the lecture 
	//we connected both nfa s to a new initial state with epsilon
	
	public static NFA union(NFA nfa1, NFA nfa2) {
		//TODO implement union!
		
		int newinitialstate = newState();
		
		Edge e1 = new Edge(newinitialstate,nfa1.initialState); 
		Edge e2 = new Edge(newinitialstate,nfa2.initialState); 
		
		
		HashSet<Integer> statesSet = new HashSet<Integer>();
		HashSet<Integer> acceptStates = new HashSet<Integer>();
		HashSet<Edge> transitions = new HashSet<Edge>();
		
		statesSet.addAll(nfa1.allStates);
		statesSet.addAll(nfa2.allStates);
		statesSet.add(newinitialstate);
		
		acceptStates.addAll(nfa1.acceptStates);
		acceptStates.addAll(nfa2.acceptStates);
		
		transitions.addAll(nfa1.transitions);
		transitions.addAll(nfa2.transitions);
		transitions.add(e1);
		transitions.add(e2);
		
		return(new NFA(newinitialstate,statesSet,acceptStates,transitions));
		
	//	throw new UnsupportedOperationException("Not implemented: union");
	}
	
	
	//we connected fist nfas accepting all states to the second nfa in the procces we deleated first nfa s accepting states
	
	public static NFA concatenate(NFA nfa1, NFA nfa2) {
		//TODO implement concatenate!
		
		HashSet<Integer> statesSet = new HashSet<Integer>();
		HashSet<Integer> acceptStates = new HashSet<Integer>();
		HashSet<Edge> transitions = new HashSet<Edge>();
		
		statesSet.addAll(nfa1.allStates);
		statesSet.addAll(nfa2.allStates);
				
		//acceptStates.addAll(nfa1.acceptStates);
		acceptStates.addAll(nfa2.acceptStates);
		
		transitions.addAll(nfa1.transitions);
		transitions.addAll(nfa2.transitions);
		
		Edge e1;
		
		for (int i : nfa1.acceptStates) {
		 e1 = new Edge(i,nfa2.initialState);	
        transitions.add(e1);
		}
		
		return(new NFA(nfa1.initialState,statesSet,acceptStates,transitions));
		
	//	throw new UnsupportedOperationException("Not implemented: concatenate");
	}
	
	
	//we connected all accepting states of nfa to the initial state of it than add an other accepting state to the start and make it accepting state
	
	public static NFA star(NFA nfa) {
		//TODO implement star!
		

		HashSet<Integer> statesSet = new HashSet<Integer>();
		HashSet<Integer> acceptStates = new HashSet<Integer>();
		HashSet<Edge> transitions = new HashSet<Edge>();
		
		statesSet.addAll(nfa.allStates);
						
		acceptStates.addAll(nfa.acceptStates);
				
		transitions.addAll(nfa.transitions);
				
		
		//Edge e1;
		
		for (int i : nfa.acceptStates) {
			Edge e1 = new Edge(i,nfa.initialState);	
        transitions.add(e1);
		}
		

		int newinitialstate = newState();
		acceptStates.add(newinitialstate);
		statesSet.add(newinitialstate);
		
		Edge e2 = new Edge(newinitialstate,nfa.initialState); 
		
		transitions.add(e2);
		
		return(new NFA(newinitialstate,statesSet,acceptStates,transitions));
		
	//throw new UnsupportedOperationException("Not implemented: star");
	}
	
	public static void test() {
	/*	System.out.println("Testing...");
		System.out.println();
		System.out.println("Trying to construct automaton that recognizes: ");
		System.out.println("(a|b)*abb");
		*/
		/*
		
		NFA a = NFA.singleSymbol('a');
		NFA b = NFA.singleSymbol('b');
		NFA union = NFA.union(a, b);
		NFA star = NFA.star(union);
		
		NFA anotherA = NFA.singleSymbol('a');
		NFA anotherB = NFA.singleSymbol('b');
		NFA lastB = NFA.singleSymbol('b');
		
		NFA ab = NFA.concatenate(anotherA, anotherB);
		NFA abb = NFA.concatenate(ab, lastB);
		
		NFA result = NFA.concatenate(star, abb);
		*/
		
		NFA a = NFA.singleSymbol('a');
		NFA b = NFA.singleSymbol('b');
		NFA bb = NFA.singleSymbol('b');
		
		NFA star = NFA.star(bb);
		NFA concat = NFA.concatenate(star,b);
		
		NFA union = NFA.union(concat, a);
		
		NFA result = union;
		
		
		
		
		String testString = "aaaa";
		
		if (result.accepts(testString)) {
			System.out.println("NFA accepts " + testString);
		} else {
		//throw new RuntimeException("NFA did not accept " + testString);
		}
		
		//System.out.println("All tests passed!");
		System.out.println("Here is the resulting NFA: ");
		System.out.println(result);
		
	}

	static String substring;
	
	public static void automatamatic(String s,String []pattern,int length) {
		
		
		System.out.println("Constructing automaton for regular expression\n");
		System.out.println("infix: " + s);
		
		
		InfixToPostfixConverter converter = new InfixToPostfixConverter(s);
		
		s = converter.getPosfixExpression();
		
		
		System.out.println("postfix: " + s);
		System.out.println();
		
		Stack <NFA> nfa = new Stack<NFA>();
		
		int i = 0;

		
		//this part constructs nfa automatically according to the given input we used the algorithm given in pdf
		
			
			while (i != s.length()){
				
				
				
				if (s.charAt(i) == '*'){ 
		
					NFA nfa1 = NFA.star(nfa.peek());
					nfa.pop();
					nfa.push(nfa1);
				
				}
				
				
				else if (s.charAt(i) == '|'){
				
					NFA nfa1 = nfa.peek();
					nfa.pop();
					NFA nfa2 = nfa.peek();
					nfa.pop();
					NFA nfa3 = NFA.union(nfa1,nfa2);
					nfa.push(nfa3);
				
				}
				
				
				else if (s.charAt(i) == '&'){
				
					NFA nfa1 = nfa.peek();
					nfa.pop();
					NFA nfa2 = nfa.peek();
					nfa.pop();
					NFA nfa3 = NFA.concatenate(nfa2,nfa1); 
					nfa.push(nfa3);
				
				}
				
				
				
				else{
				
					NFA nfa1 = NFA.singleSymbol(s.charAt(i));
					nfa.push(nfa1);
				
				}
				
			
				i ++ ;
			}

			/*
			if (nfa.peek().accepts(pattern)) {
				System.out.println("NFA accepts: " + pattern);
			} 
			
			else {
			//throw new RuntimeException("NFA did not accept: " + pattern);
			}
			*/
			//System.out.println("All tests passed!");
			System.out.println("Here is the resulting NFA: ");
			System.out.println(nfa.peek());
			
			System.out.println("\nACCEPTED LINES FOR PATTERN\n\n");
			
			
			
			//inside this for we try all possible strings from given string by brute force
			
			
	for (i = 0; i < length; i++) {
		
		boolean spacelines = false;
 	   

		int substringstart = 0;
		int substringend = pattern[i].length();
		
		for (int j = 0; j < pattern[i].length(); j++) {

			for (int j2 = 0; j2 < substringend; j2++) {
	
				//System.out.println("start "+substringstart+" end " + substringend);
				
				if(nfa.peek().accepts(pattern[i].substring(substringstart,substringend))) {
		    	   System.out.println("ACCEPTED LINE " + i + " " + pattern[i] + "  substring start " + substringstart + " substringend " + substringend + " substring is " + pattern[i].substring(substringstart,substringend));
		
		    	   spacelines = true;
		    	   
		    	   if(!frame.getta2().equals("")) {
		    	   frame.setta2(frame.getta2() + "\n LINE: " + (i+1) + " '" + pattern[i].substring(substringstart,substringend) + "' starts: " + substringstart + " ends: " + substringend);
				}
		    	   else {
		    		   frame.setta2(" LINE: " + (i+1) + " '" + pattern[i].substring(substringstart,substringend) + "' starts: " + substringstart + " ends: " + substringend);
					}
				
				}
		    	
				
				substringstart++;
		}
			substringstart = 0;
			substringend--;
			
		}		
		
		if(spacelines == true) {
		 frame.setta2(frame.getta2() + "\n" );
		}
		/*
		if(nfa.peek().accepts(pattern[i])) {
	    	   System.out.println("ACCEPTED LINE " + i + " " + pattern[i]);
	       }
	       else {
	    	   System.out.println("NOT ACCEPTED LINE " + i + " " + pattern[i]);
	       }
		*/
	}
	
	
	
	
	
	
	
	
	
	
	}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*
		NFA nfa1 = NFA.singleSymbol(s.charAt(0));
		NFA nfa2 = NFA.singleSymbol(s.charAt(0));
		NFA nfa3;
		
		s = s.substring(1,s.length());
		
		while(s.charAt(0) == '*' && !s.equals("")) {
			System.out.println(s);
			nfa1 = NFA.star(nfa1);
			s = s.substring(1,s.length());
		}
	
		
		while(!(s.equals(""))) {
			if(s.charAt(0) == '*') {
			nfa1 = NFA.star(nfa1);
			s = s.substring(1,s.length());
			continue;
			}

			else if(s.charAt(0) == '|') {
				nfa1 = NFA.union(nfa1,nfa2);
				s = s.substring(1,s.length());
				continue;	
			}

			else if(s.charAt(0) == '&') {
				nfa1 = NFA.concatenate(nfa1, nfa2);
				s = s.substring(1,s.length());
				continue;
			}

			else{
                nfa2 = NFA.singleSymbol(s.charAt(0));
               
                s = s.substring(1,s.length());
				
				while(s.charAt(0) != '&' && s.charAt(0) != '|' && s.charAt(0) != '*' && !s.equals("")){    
					nfa3 = NFA.singleSymbol(s.charAt(0));
					//System.out.println("KAVUN");
					s = s.substring(1,s.length());
					if(s.charAt(0) == '*') {                     
						nfa3 = NFA.star(nfa3);
						s = s.substring(1,s.length());	
						continue;
					}

						 if(s.charAt(0) == '|') {           
							nfa2 = NFA.union(nfa2,nfa3);
							s = s.substring(1,s.length());
							continue;
						 }

						 if(s.charAt(0) == '&') {
							nfa2 = NFA.concatenate(nfa2, nfa3);
							s = s.substring(1,s.length());
							continue;
						 }
				}
				
				
				continue;
			}
			
		}
	   
		
	    
	    
	    
	    
//String testString = "a";
		
		if (nfa1.accepts(pattern)) {
			System.out.println("NFA accepts: " + pattern);
		} else {
		//throw new RuntimeException("NFA did not accept: " + pattern);
		}
		
		System.out.println("All tests passed!");
		System.out.println("Here is the resulting NFA: ");
		System.out.println(nfa1);
		
	    
	    
		
	}
	
	*/
	
	
	
	
	
	
	
	
	
}
