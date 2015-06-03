import java.util.*;
import java.io.*;

public class Benedict {

    private Dictionary nouns, verbs, adj;
    private Script script;
    private boolean isTalking;

    public Benedict() {
        nouns = new Dictionary("nouns.txt");
        verbs = new Dictionary("verbs.txt");
        adj = new Dictionary("adj.txt");
	script = new Script();
    }

    public String addCondition( String command ) {
	if ( command == "That is incorrect." ) {
	    
	}
    }

    public String respond( String l ) {
	return;
    }   

    public String parseLine( String s ) {
	
    }

    public String buildLine( String s ) {

    }

    public void learnLine( String s ) {
	script.addLine(s);
    }

    public void endProgram() {
	
    }

}
