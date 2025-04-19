import java.io.Serializable;
import java.util.ArrayList;

public class Node implements Serializable {
	private String line;
	private boolean status;
	private ArrayList<Node> references = new ArrayList<>();

	public Node(String line, boolean status) {
		this.line = line;
		this.status = status;
	}

	public String getNode() {
		return line;
	}

	public boolean getStatus() {
		return status;

	}

	public void setNode(String line) {
		this.line = line;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public void setReferences(Node... nodes) {
		for (Node node : nodes) {
			references.add(node);
		}
	}

	@Override
	public String toString() {
		return String.format(line + ", " + status);
	}
}
