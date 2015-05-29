public class Word {

    private String word;
    private String pos;

    public Word( String w ) {
	word = w;
    }

    public Word( String w; String p ) {
	word = w;
	pos = p;
    }

    public void setPOS( String p ) {
	pos = p;
    }

    public String getWord() {
	return word;
    }

}
