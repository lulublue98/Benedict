import java.util.*;
import java.io.*;

public class Benedict {

    private Dictionary nouns, verbs, adj;
    //private Script script;
    //private boolean isTalking;

    public Benedict() {
        nouns = new Dictionary("nouns.txt");
        //verbs = new Dictionary("verbs.txt");
        //adj = new Dictionary("adj.txt");
	//script = new Script();
    }

    /* public String addCondition( String command ) {
	if ( command == "That is incorrect." ) {
	    
	}
        }*/

    public String respond( String l ) {
		if(l.equals("game")) {
			return "Would you like to play nim, ___ or ___?";
		} else if (l.equals("Hello.")){
			return "Hello. How are you?";
		} else if (l.equals("Goodbye.")) {
			return "Goodbye.";
		} else {
			return nouns.getWord(1,2,3);
		}
    }

    public void pause (int ms) {
        try {
            Thread.sleep(ms);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    /*public String parseLine( String s ) {
	
    }

    public String buildLine( String s ) {

    }

    public void learnLine( String s ) {
	script.addLine(s);
        }*/
    
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

    public void endProgram() {
		
    }

}
