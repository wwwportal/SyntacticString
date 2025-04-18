import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TopicManager {
	private static final String filepath = "C:/CST8132/StudyTopics/src/files/topics.csv";
	private static ArrayList<Topic> topics = new ArrayList<>();	
	private static Scanner in = new Scanner(System.in);
	public void loadTopics() {
		System.out.println("Loading topics...");

		String line;
		try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
			while ((line = br.readLine()) != null) {
				parseLine(line);
			}
			System.out.println("Topics successfully loaded!");
		}
		catch (IOException e ) {
			System.out.println("Error reading from file: " + e.getMessage());
		}
	}
	
	public static void addTopic(String name, String description, boolean learned) {
		Topic topic = new Topic(name, description, learned);
		topics.add(topic);
	}
	
	public void displayTopics() {
		if (topics.isEmpty()) {
			System.err.println("There are currently no topics in the system");
			System.err.println("Try adding a new topic!");
		}
		else {
			System.out.println("TOPICS");
			for (int i = 0; i < topics.size(); i++) {
				String name = topics.get(i).getTopic();
				System.out.println(i + ". " + name);
			}
		}
	}
	
	public void displayUnlearnedTopics() {
		if (topics.isEmpty()) {
			System.err.println("There are currently no topics in the system");
			System.err.println("Try adding a new topic!");
		}
		else {
			for (Topic topic : topics) {
				if (topic.getLearned() == false) {
					System.out.println(topic.getTopic() + ", " + topic.getLearned());
				}
			}
		}
	}
	
	public static ArrayList<Topic> getTopics() {
		return topics;
	}
	
	public void editTopic() {
	    System.out.println("EDIT TOPIC");
	    System.out.println("Enter the name of the topic:");
	    String topicName = in.nextLine().trim().toLowerCase(); // Trim and lowercase user input
	    ArrayList<Topic> found = new ArrayList<>();
	    for (Topic topic : topics) {
	        if (topic.getTopic().trim().toLowerCase().contains(topicName)) { // Trim and lowercase topic name from file
	            found.add(topic);
	        }
	    }
	    if (found.isEmpty()) {
	        System.err.println("Topic not found.");
	        return; // Exit the method if no topic is found
	    }
	    System.out.println("Results: ");
	    for (int i = 0; i < found.size(); i++) {
	        System.out.println(i + ". " + found.get(i).getTopic());
	    }
	    System.out.println("Enter the number of the topic you would like to edit.");
	    System.out.print("Number: ");
	    int editIndex = in.nextInt();
	    in.nextLine(); // Consume newline

	    if (editIndex >= 0 && editIndex < found.size()) {
	        Topic selected = found.get(editIndex);
	        System.out.println("Set learning status of " + selected.getTopic() + " to:");
	        System.out.println("0. FALSE");
	        System.out.println("1. TRUE");
	        int bool = in.nextInt();
	        in.nextLine(); // Consume newline
	        switch (bool) {
	            case 0:
	                selected.setLearned(false);
	                break; // Add break statement
	            case 1:
	                selected.setLearned(true);
	                break; // Add break statement
	            default:
	                System.out.println("Invalid input.");
	        }
	    } else {
	        System.out.println("Invalid topic number.");
	    }
	}

	public static void parseLine(String line) {
		String[] fields = line.split(",");
		System.out.println(fields[2]);
		addTopic(
			fields[0], 
			fields[1], 
			Boolean.parseBoolean(fields[2]));
	}

	public void clearList() {
		topics.clear();
	}

	public void moveTopic() {
		System.out.println("Move: [source index] [target index]");
		System.out.print("> ");
		try {
			int sourceIndex = in.nextInt();
			int targetIndex = in.nextInt();

			if (sourceIndex < 0 || sourceIndex >= topics.size() || targetIndex < 0 || targetIndex > topics.size()) {
				System.out.println("Invalid index. Please enter a valid index within the range of the list.");
				return;
			}

			Topic sourceTopic = topics.remove(sourceIndex);
			topics.add(targetIndex, sourceTopic);

			System.out.println(sourceTopic.getTopic() + " Has been successfully moved to position " + targetIndex);
		} catch (InputMismatchException e) {
			System.out.println("Invalid input. Please enter integer values for the indices.");
			in.next(); // Consume the invalid input to prevent an infinite loop
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Index out of bounds. Please enter a valid index within the range of the list.");
		}
	}

}
