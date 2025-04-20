import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Node implements Serializable {
    private static final long serialVersionUID = 1L;
    private String line;
    private boolean status;
    private ArrayList<Node> links = new ArrayList<>();

    public Node(String line, boolean status) {
        this.line = line;
        this.status = status;
    }

    public String getLine() {
        return line;
    }

    public boolean getStatus() {
        return status;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void addLink(Node node) {
        this.links.add(node);
    }

    public List<Node> getLinks() {
        return links;
    }

    @Override
    public String toString() {
        return String.format(line + ", " + status);
    }
}
