
public class StringVisitor<E> implements Visitor<E>{
	private String str;
	
	public StringVisitor() {
		str = "";
	}
	
	public void visit(E item) {
		str += item + " ";
	}
	
	public String getValue() {
		return "[" + str.trim() + "]";
	}
}
