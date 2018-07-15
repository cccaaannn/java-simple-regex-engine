package regex_engine;

public class Edge {

	
	private static final char EPSILON = '\u0395';
	
	private int sourceState;
	private int destinationState;
	private char symbol;
	
	public int getSourceState() {
		return sourceState;
	}
	
	public int getDestinationState() {
		return destinationState;
	}
	
	public char getSymbol() {
		return symbol;
	}
	
	public Edge(int sourceState, int destinationState, char symbol) {
		this.sourceState = sourceState;
		this.destinationState = destinationState;
		this.symbol = symbol;
	}
	
	public Edge(int sourceState, int destinationState) {        ///////////////////////////////////////////
		this.sourceState = sourceState;
		this.destinationState = destinationState;
		this.symbol = EPSILON;	
	}
	
	public static Edge epsilonTransition(int source, int destination) {
		return new Edge(source, destination, EPSILON);
	}
	
	public boolean isEpsilonTransition() {
		return symbol == EPSILON;
	}
	
	public String toString() {
		String strRepresentation =  "(" + sourceState + ", " + destinationState + ", ";
		if (isEpsilonTransition()) {
			strRepresentation += "" +  symbol + ")";
		} else {
			strRepresentation += "'" +  symbol + "')";
		}
		return strRepresentation;
	}



}
