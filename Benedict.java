import java.util.*;
import java.io.*;

public class Benedict {

    private Dictionary nouns, verbs, adjs;
    private Script script;
	private ArrayList<String> likes;

    public Benedict() {
        nouns = new Dictionary("Nouns.txt");
        verbs = new Dictionary("Verbs.txt");
        adjs = new Dictionary("Adjs.txt");
		script = new Script();
    }

    public String respond( String l ) {
		l = l.toLowerCase();
		if(l.equals("game")) {
	    	return "Would you like to play nim, ___ or othello?";
		} else if (l.equals("let's talk")){
	    	return "Okay, what do you want to talk about?";
		} else if (l.equals("hello")) {
	 	   return "Hello";  
		} else if (l.equals("goodbye")) {
	 	   return "Goodbye";
		} else {
	   		return "I don't understand.";
		}
    }

    public void pause (int ms) {
        try {
            Thread.sleep(ms);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
	
	/*
	public String talk() {
		
	}
  	  */
    
    public boolean nim(){
        Scanner n = new Scanner(System.in);
        System.out.println("Would you like instructions on how to play nim?");
        String instructions = "";
        while(!((instructions.equals("yes"))||(instructions.equals("no")))){
            instructions = n.nextLine();
            if(instructions.equals("yes")){
                System.out.println("instructions");
            }else if(!(instructions.equals("no"))){
                System.out.println("yes or no");
            }
        }
        nimboard nimB = new nimboard(n);
        pause(500);
        System.out.println("hmm");
        pause(1000);
        if(nimB.losing()){
            System.out.println("I'll let you go first.");
        }else{
            System.out.println("I'll go first.");
            nimB.bMove();
        }
        while(!(nimB.done())){
            nimB.pMove(n);
            nimB.bMove();
        }
        System.out.println("I win!");
        return true;
    }

    public boolean othello(){
        Scanner n = new Scanner(System.in);
        othelloboard oB = new othelloboard('X');
        while(!oB.gameOver()){
            oB.printDots();
            oB.pMove(n);
            oB.printClean();
            oB.bMove();
        }
        return true;
    }

}
