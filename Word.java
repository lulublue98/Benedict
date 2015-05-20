public class Word {

    private String word;
    private ArrayList<Word> meaning;

    public Word( String w ) {
	meaning = new ArrayList<Word>();
	word = w;
    }

    public void addDef( Word m ) {
	meaning.add(m);
    }

}
