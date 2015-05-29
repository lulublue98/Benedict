public class Script {

    private ArrayList<Line> lines;

    public Script() {
	lines = new Arraylist<Line>();
    }

    public void addLine( String s ) {
	lines.add(new Line(s));
    }

}


