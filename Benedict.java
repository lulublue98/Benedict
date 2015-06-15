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
            return "Would you like to play nim, chopsticks or othello?";
        } else if (l.equals("let's talk")||l.equals("let's have a conversation")) {
            return "What do you want to talk about?";
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
    
    public String makeline(String type, String word) {
        boolean got = false;
        String l = "";
        while (got==false) {
            l = script.getRandomLine().toString();
            if (l.contains(type)) {
                got = true;
            }
        }
        l = l.replaceFirst(type,word);
        while (l.contains("verb")) {
            //l = l.replaceFirst("verb",verbs.getRandomWord());
        }
        while (l.contains("noun")) {
            //l = l.replaceFirst("noun",nouns.getRandomWord());
        }
        while (l.contains("adj")) {
            //l = l.replaceFirst("adj",adjs.getRandomWord());			
        }
        return l;
    }
    
    public void talk( String s ) {
        String type = "";
        String last = s;
        boolean done = false;
        Scanner scan = new Scanner(System.in);
        if (nouns.hasWord(s)==true) {
            type = "noun";
            System.out.println("hmmm");
            pause(300);
            System.out.println(makeline(type,s));
        } else if (verbs.hasWord(s)==true) {
            type = "verb";
            System.out.println("hmmm");
            pause(300);
            System.out.println(makeline(type,s));
        } else if (adjs.hasWord(s)==true) {
            type = "adj";
            System.out.println("hmmm");
            pause(300);
            System.out.println(makeline(type,s));
        } else {
            System.out.println("I don't know that word.");
            pause(100);
            System.out.println("Would you like to teach it to me?");
            String w = scan.nextLine();
            if (w.equals("no")) {
                System.out.println("Too bad");
                done = true;
            } else if (w.equals("yes")) {
                System.out.println("What kind of word is it?");
                System.out.println("Please enter 'noun', 'verb', or 'adjective'.");
                w = scan.nextLine();
                if (w.equals("noun")) nouns.addWord(last);
                if (w.equals("verb")) verbs.addWord(last);
                if (w.equals("adjective")) adjs.addWord(last);
                pause(300);
                System.out.println("I have learned the " + w + " " + last + ".");
                pause(500);
                System.out.println(makeline(w,last));
            }
        }
        while (done==false) {
            pause(300);
            String in = scan.nextLine();
            if (nouns.hasWord(in)) type = "noun";
            if (verbs.hasWord(in)) type = "verb";
            if (adjs.hasWord(in)) type = "adj";			
            if (in.equals("done")) {
                done = true;
            } else if (in.equals("no it isn't")||in.equals("no it can't")||in.equals("no")) {
                System.out.println("Oh sorry, my mistake");
                pause(300);
                System.out.println(makeline(type,last));
            } else if (in.equals("correct")||in.equals("yes")) {
                System.out.println("I see");
                pause(300);
                System.out.println(makeline(type,last));				
            } else {
                last = in;
                System.out.println("I don't know that word.");
                pause(100);
                System.out.println("Would you like to teach it to me?");
                in = scan.nextLine();
                if (in.equals("no")) {
                    System.out.println("Too bad");
                } else if (in.equals("yes")) {
                    System.out.println("What kind of word is it?");
                    System.out.println("Please enter 'noun', 'verb', or 'adjective'.");
                    in = scan.nextLine();
                    if (in.equals("noun")) nouns.addWord(last);
                    if (in.equals("verb")) verbs.addWord(last);
                    if (in.equals("adjective")) adjs.addWord(last);
                    pause(300);
                    System.out.println("I have learned the " + in + " " + last + ".");
                    pause(500);
                    System.out.println(makeline(in,last));
                }
            }
        }
        System.out.println("What do you want to do?");
    }
    
    public boolean nim(){
        Scanner n = new Scanner(System.in);
        System.out.println("View the README file for instructions.");
    
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
        return true;
    }
    
    public boolean chopsticks(){
        Scanner n = new Scanner(System.in);
        System.out.println("View the README file for instructions.");
        
        chopsticksboard cB = new chopsticksboard();
        while(!cB.pHands(0,0)){
            cB.printBoard();
            cB.pMove(n);
            cB.printBoard();
            pause(1000);
            cB.bMove();
        }
        cB.printBoard();
        return true;
    }

    public int othello(){
        Scanner n = new Scanner(System.in);
        System.out.println("View the README file for instructions.");
        System.out.println("Would you like to go first or second?");
        String fOrS = "";
        while(!((fOrS.equals("first"))||(fOrS.equals("second")))){
            fOrS = n.nextLine();
            if(fOrS.equals("first")){
                othelloboard oB = new othelloboard('X');
                while(!oB.gameOver()){
                    oB.printDots();
                    oB.pMove(n);
                    System.out.println("\n");
                    oB.printClean();
                    pause(2000);
                    oB.bMove();
                }
                return oB.endGameTally();
            }else if(fOrS.equals("second")){
                othelloboard oB = new othelloboard('O');
                while(!oB.gameOver()){
                    oB.printClean();
                    pause(2000);
                    oB.bMove();
                    oB.printDots();
                    oB.pMove(n);
                    System.out.println("\n");
                }
                return oB.endGameTally();
            }else{
                System.out.println("Choose either 'first' or 'second'");
            }
        }
        return 2;
    }
    
    public void printScript(){
        System.out.println(script);
    } 
}
