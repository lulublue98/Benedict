public class Benedict {

    private Dictionary nouns, verbs, adj;
    private Script script;

    public Benedict() {
        nouns = new Dictionary("nouns.txt");
        verbs = new Dictionary("verbs.txt");
        adj = new Dictionary("adj.txt");
	script = new Script();
    }

    public void buildDictionaries() {

    }

    public void buildScript() {

    }

    // not yet complete
    // return is a stand-in so the file will compile
    public String respond( String l ) {
	return;
    }   
    
    public String parseLine( String s ) {
	
    }

    public String buildLine( String s ) {

    }

    public void learnLine( String s ) {
	script.addLine(s);
    }

    public void endProgram() {
	
    }

}
