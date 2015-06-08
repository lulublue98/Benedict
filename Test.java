import java.util.*;

public class Test {
	
	public static void main(String[] args) {
		
		Dictionary d = new Dictionary("Nouns.txt");
		ArrayList<String> h = d.getDict(0,0);
		for (int i=0;i<h.size();i++) {
			System.out.println(h.get(i));
		}

	}

}
