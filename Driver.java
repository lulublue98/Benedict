import java.util.*;
import java.io.*;

public class Driver {
    
    public static void main( String args ) {
	
	Benedict B = new Benedict();
	B.buildDictionaries();
	B.buildScript();
	Scanner s = new Scanner();
	boolean done = false;
	while(!done){
	    String out = B.respond(s.nextLine());
	    if(out.equals("Good Bye")) done = true;
	    System.out.println(out);
	}
    }

}
