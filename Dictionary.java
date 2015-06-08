import java.io.*;
import java.util.*;

public class Dictionary {

    private ArrayList<String>[][] dictionary;
    BufferedReader f;
    PrintWriter out;

    public Dictionary(String file) {
	dictionary = new ArrayList[26][27];
	for(int i=0; i<26; i++){
	    for(int j=0; j<27; j++){
		dictionary[i][j] = new ArrayList<String>();
	    }
	}
	String s = null;
        try{
            f = new BufferedReader(new FileReader(file));
	    s = f.readLine();
            //out = new PrintWriter(new BufferedWriter(new FileWriter(file)));
	}catch(IOException e){
	    System.out.println("Can't find the file");
	}
	while(s!=null){
	    int x = (int)s.charAt(0)-97;
	    int y = (s.length()==1) ? 26 : (int)s.charAt(1)-97;
	    dictionary[x][y].add(s);
	    try{
		s=f.readLine();
	    }catch(IOException e){
		System.out.println("exception");
	    }
	}
    }

    public void addWord(String w) {
	int x = (int)w.charAt(0)-97;
        int y = (w.length()==1) ? 26 : (int)w.charAt(1)-97;
        dictionary[x][y].add(w);
        //out.println(w);
    }

    public boolean hasWord(String w) {
        int x = (int)w.charAt(0)-97;
        int y = (w.length()==1) ? 26 : (int)w.charAt(1)-97;
        return dictionary[x][y].contains(w);
    }
	
    public String getWord( int a, int b, int c ) {
	return dictionary[a][b].get(c);
    }
    
    public ArrayList<String> getDict(int a, int b) {
	return dictionary[a][b];
    }
    
}
