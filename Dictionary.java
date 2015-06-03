import java.io.*;
import java.util.*;

public class Dictionary {

    private ArrayList<String>[][] dictionary;
    BufferedReader f;
    PrintWriter out;

    public Dictionary(String file) {
	dictionary = new ArrayList[26][27];
        f = new BufferedReader(new FileReader(file));
        out = new PrintWriter(new BufferedWriter(new FileWriter(file)));
        String s = f.readLine();
	while(s!=null){
            int x = (int)s.charAt(0)-97;
            int y = (s.length()==1) ? 26 : (int)s.charAt(1)-97;
            dictionary[x][y].add(s);
	    s=f.readLine();
	}
    }

    public void addWord(String w) {
	int x = (int)w.charAt(0)-97;
        int y = (w.length()==1) ? 26 : (int)w.charAt(1)-97;
        dictionary[x][y].add(w);
        out.println(w);
    }

    public boolean hasWord(String w) {
        int x = (int)w.charAt(0)-97;
        int y = (w.length()==1) ? 26 : (int)w.charAt(1)-97;
        return dictionary[x][y].contains(w);
    }
}
