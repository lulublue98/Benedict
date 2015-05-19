public class Word {

    private String word;
    private ArrayList<Word> meaning;

    public Word( String w ) {
	word = w;
    }

    public Word( String w, String m ) {
	word = w;
	meaning.add(m);
    }

    public void addDef( String m ) {
	meaning.add(m);
    }

}
