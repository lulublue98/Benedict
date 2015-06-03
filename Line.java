import java.util.*;
import java.io.*;

public class Line {
    
    private String line;
    private ArrayList<String> conditions;

    public Line( String l ) {
	line = l;
    }

    public void AddCondition( String c ) {
	conditions.add(c);
    }

    public ArrayList<String> getConditions() {
	return conditions;
    }

}
