package main.java.com.syntacticstring;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Node implements Serializable {
    private static final long serialVersionUID = 1L;
	private int id;
    private String content;
    private List<Node> linksTo = new ArrayList<>();
   // private List<Node> linkedFrom = new ArrayList<>();

	public Node(String content) {
        this.content = content;
    }

    public Node( int id, String content) {
		this.id = id;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

	public void setContent(String content) {
        this.content = content;
    }

    public void setLinks(List<Node> linksTo) {
		for (Node node : linksTo) {
			this.linksTo.add(node);
		}
    }

    public List<Node> getLinks() {
        return linksTo;
    }

	public List<String> displayLinks() {
		List<String> whole = new ArrayList<>();
		for (Node link : linksTo) {
			String part =" [" + link.id + "] ";
			whole.add(part);
		}
		return whole;
 	}

	public void setLink(Node link) {
		this.linksTo.add(link);
    }

    @Override
    public String toString() {
		String info = id + ".  " + content + ". /n LINKS: " + displayLinks();
        return info;
    }
}
