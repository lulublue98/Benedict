import java.io.*;
import java.util.*;

class XXX{
    public static void main (String [] args) throws IOException {
	BufferedReader f = new BufferedReader(new FileReader("XXX.in"));
	PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("XXX.out")));

	int c=0;
	String s = f.readLine();
	while(s!=null){
	    StringTokenizer st = new StringTokenizer(s);
	    int i1 = Integer.parseInt(st.nextToken());    // first integer
	    int i2 = Integer.parseInt(st.nextToken());    // second integer
	    c+=i1+i2;
	    s=f.readLine();
	}

	out.println(c);                           // output result

	out.close();                                  // close the output file
    }
}
