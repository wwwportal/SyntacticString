import java.util.ArrayList;

public class Node {
	private String name;
	private String description;
	private boolean learned;
	private ArrayList<Node> references = new ArrayList<>();

	public Node(String name, String description, boolean learned) {
		this.name = name;
		this.description = description;
		this.learned = learned;
	}

	public String getNode() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public boolean getLearned() {
		return learned;

	}

	public void setNode(String node) {
		this.name = node;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setLearned(boolean learned) {
		this.learned = learned;
	}
	
	public void setReferences(Node... nodes) {
		for (Node node : nodes) {
			references.add(node);
		}
	}

	@Override
	public String toString() {
		return String.format(name + ", " + description + ", " + learned);
	}
}
