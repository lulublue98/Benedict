import java.util.*;
import java.io.*;

public class Driver {
    
    public static void main( String[] args ) {
	
		Benedict B = new Benedict();
		Scanner s = new Scanner(System.in);
		boolean done = false;
        	boolean ingame = false;
        	System.out.println("Greetings, I'm Benedict the robot.");
        	System.out.println("Let's have a conversation! Or type 'game' to play a game.");
		while(!done) {
            if(ingame) {
               	String game = "";
               	boolean win = false;
               	while(!(game.equals("nim"))) {
                   	game = s.nextLine();
                   	if(game.equals("nim")) {
                       	win = B.nim();
                       	ingame = false;
                       	done = true;
                   	}else{
                       	System.out.println("Choose either 'nim'");
                   	}
               	}
			}
	    	String out = B.respond(s.nextLine());
           	if(out.equals("Would you like to play nim, ___ or ___?")) ingame = true;
	   		if (out.equals("Goodbye")) done = true;
	   		System.out.println(out);
		}	
    }
}
