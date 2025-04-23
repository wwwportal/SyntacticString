import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Node implements Serializable {
    private static final long serialVersionUID = 1L;
	private int id;
    private String line;
    private ArrayList<Node> links = new ArrayList<>();

	public Node(String line) {
        this.line = line;
    }

    public Node( int id, String line) {
		this.id = id;
        this.line = line;
    }

    public String getLine() {
        return line;
    }

	public void setLine(String line) {
        this.line = line;
    }

    public void setLinks(List<Node> links) {
		for (Node node : links) {
			this.links.add(node);
		}
    }

    public List<Node> getLinks() {
        return links;
    }

	public List<String> displayLinks() {
		List<String> whole = new ArrayList<>();
		for (Node link : links) {
			String part =" [" + link.id + "] ";
			whole.add(part);
		}
		return whole;
 	}

	public void setLink(Node link) {
		this.links.add(link);
    }

    @Override
    public String toString() {
		String info = id + ".  " + line + ". /n LINKS: " + displayLinks();
        return info;
    }
}
