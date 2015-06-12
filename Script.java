import java.util.*;
import java.io.*;

public class Script {

    private ArrayList<Line> lines;
    BufferedReader f;

    public Script() {
	lines = new ArrayList<Line>();
	String s = null;
	try {
	    f = new BufferedReader(new FileReader("Script.txt"));
	    s = f.readLine();
	} catch(IOException e) {
	    System.out.println("exception");
	}
	while(s!=null) {
	    Line l = new Line(s);
	    lines.add(l);
	    try{
		s=f.readLine();
	    }catch(IOException e){
		System.out.println("exception");
	    }
	}
    }

    public void addLine( String s ) {
	lines.add(new Line(s));
    }

    public Line getLine( int i ) {
	return lines.get(i);
    }

    public ArrayList<Line> getLines() {
	ArrayList<Line> temp = lines;
	return temp;
    }

}
