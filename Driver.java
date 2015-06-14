import java.util.*;
import java.io.*;

public class Driver {
    
    public static void main( String[] args ) {
	
        Benedict B = new Benedict();
        Scanner s = new Scanner(System.in);
        boolean done = false;
        boolean ingame = false;
		boolean talk = false;
        System.out.println("Greetings, I'm Benedict the robot.");
		System.out.println("If you're confused as to how to talk to me, please address my instructions file.");
        System.out.println("Let's have a conversation! Or type 'game' to play a game.");
        while(!done) {
            if(ingame) {
               	String game = "";
               	boolean win = false;
               	while(!((game.equals("nim"))||(game.equals("othello")))) {
                    game = s.nextLine();
                    if(game.equals("nim")) {
                       	win = B.nim();
                       	ingame = false;
                    }else if(game.equals("othello")){
                        win = B.othello();
                        ingame = false;
                    }else{
                       	System.out.println("Choose either 'nim' or 'othello'");
                    }
               	}
			}
			if(talk) { 
				System.out.println("talking");
			}
            String out = B.respond(s.nextLine());
            if(out.equals("Would you like to play nim, ___ or othello?")) ingame = true;
			if(out.equals("Okay, what do you want to talk about?")) talk = true;
            if (out.equals("Goodbye")) done = true;
            System.out.println(out);
        }	
    }
}
