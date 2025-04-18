import java.util.ArrayList;

public class Topic {
	private String name;
	private String description;
	private boolean learned;
	private ArrayList<Topic> references = new ArrayList<>();

	public Topic(String name, String description, boolean learned) {
		this.name = name;
		this.description = description;
		this.learned = learned;
	}

	public String getTopic() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public boolean getLearned() {
		return learned;

	}

	public void setTopic(String topic) {
		this.name = topic;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setLearned(boolean learned) {
		this.learned = learned;
	}
	
	public void setReferences(Topic... topics) {
		for (Topic topic : topics) {
			references.add(topic);
		}
	}

	@Override
	public String toString() {
		return String.format(name + ", " + description + ", " + learned);
	}
}
