import java.util.*;
import java.io.*;

public class Driver {
    
    public static void main( String[] args ) {
        
        Benedict B = new Benedict();
        Scanner s = new Scanner(System.in);
        boolean done = false;
        boolean ingame = false;
        boolean talking = false;
        System.out.println("Greetings, I'm Benedict the robot.");
        System.out.println("If you're confused as to how to talk to me, please address my instructions file.");
        System.out.println("Let's have a conversation! Or type 'game' to play a game.");
        while(!done) {
            if(ingame) {
               	String game = "";
               	while(!((game.equals("nim"))||(game.equals("chopsticks"))||(game.equals("othello")))) {
                    game = s.nextLine();
                    if(game.equals("nim")) {
                       	boolean win = B.nim();
                        if(win) System.out.println("I win!");
                       	ingame = false;
                    }else if(game.equals("chopsticks")){
                        boolean win = B.chopsticks();
                        if(win) System.out.println("I win!");
                       	ingame = false;
                    }else if(game.equals("othello")){
                        int win = B.othello();
                        if(win==1){
                            System.out.println("I win!");
                        }else if(win==2){
                            System.out.println("It's a tie!");
                        }else{
                            System.out.println("You win! Congratulations!");
                        }
                        ingame = false;
                    }else{
                       	System.out.println("Choose either 'nim', 'chopsticks' or 'othello'");
                    }
               	}
                System.out.println("What do you want to do now?");
            }
            if(talking) { 
                String in = s.nextLine().toLowerCase();
                B.talk(in);
                talking = false;
            }
            String out = B.respond(s.nextLine());
            if(out.equals("Would you like to play nim, chopsticks or othello?")) ingame = true;
            if(out.equals("What do you want to talk about?")) talking = true;
            if (out.equals("Goodbye")) done = true;
            System.out.println(out);
        }	
    }
}
