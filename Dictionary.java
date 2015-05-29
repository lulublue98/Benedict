public class Dictionary {

    private ArrayList<Word>[] dictionary;

    public Dictionary() {
	dictionary = new ArrayList<Word>[26];
    }

    public void addWord( String w ) {
	Word word = new Word(w);
	char ch = w.charAt(0);
	char[] alpha = { 'a', 'b', 'c', 'd', 'e', 'f',
			 'g', 'h', 'i', 'j', 'k', 'l',
			 'm', 'n', 'o', 'p', 'q', 'r',
			 's', 't', 'u', 'v', 'w', 'x',
			 'y', 'z' };
	boolean in = false;
	int index = 0;
	while ( in == false ) {
	    if ( ch == alpha[index] ) {
		dictionary[index].add(word);
		in = true;
	    } else {
		index = index + 1;
	    }
	}
    }

    public String getWord( String line ) {
        for (int i=0;i<dictionary.length;i++) {
	    for (int j=0;j<dictionary[i].size();i++) {
		String w = dictionary[i].get(J).GetWord();
		if ( line.getConditions().contains(w) ) {
		    return w;
		}
	    }
	}
	return "error 404";
    }

}
