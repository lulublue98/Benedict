import java.util.*;
import java.io.*;

public class Script {

    private ArrayList<Line> lines;

    public Script() {
	lines = new ArrayList<Line>();
        f = new BufferedReader(new FileReader("Script.txt"));
        out = new PrintWriter(new BufferedWriter(new FileWriter("Script.txt")));
        String s = f.readLine();
	while(s!=null) {
	    lines.addLine(s);
	}
    }

    public void addLine( String s ) {
	lines.add(new Line(s));
    }

    public ArrayList<Line> getLines() {
	ArrayList<Line> temp = lines;
	return temp;
    }

}


