import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Node implements Serializable {
    private static final long serialVersionUID = 1L;
	private int id;
    private String line;
    private boolean status;
    private ArrayList<Node> links = new ArrayList<>();

	public Node(String line, boolean status) {
        this.line = line;
        this.status = status;
    }

    public Node( int id, String line, boolean status) {
		this.id = id;
        this.line = line;
        this.status = status;
    }

    public String getLine() {
        return line;
    }

	public void setLine(String line) {
        this.line = line;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
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
		String info = id + ".  " + line + ". STATUS: " + status + ". /n LINKS: " + displayLinks();
        return info;
    }
}
