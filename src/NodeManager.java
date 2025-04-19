import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class NodeManager {
	private static final String filepath = "C:/CST8132/Studynodes/src/files/nodes.csv";
	private static ArrayList<Node> nodes = new ArrayList<>();	
	private static Scanner in = new Scanner(System.in);
	public void loadnodes() {
		System.out.println("Loading nodes...");

		String line;
		try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
			while ((line = br.readLine()) != null) {
				parseLine(line);
			}
			System.out.println("nodes successfully loaded!");
		}
		catch (IOException e ) {
			System.out.println("Error reading from file: " + e.getMessage());
		}
	}
	
	public static void addNode(String name, String description, boolean learned) {
		Node node = new Node(name, description, learned);
		nodes.add(node);
	}
	
	public void displayNodes() {
		if (nodes.isEmpty()) {
			System.err.println("There are currently no nodes in the system");
			System.err.println("Try adding a new node!");
		}
		else {
			System.out.println("nodeS");
			for (int i = 0; i < nodes.size(); i++) {
				String name = nodes.get(i).getNode();
				System.out.println(i + ". " + name);
			}
		}
	}
	
	public void displayUnlearnedNodes() {
		if (nodes.isEmpty()) {
			System.err.println("There are currently no nodes in the system");
			System.err.println("Try adding a new node!");
		}
		else {
			for (Node node : nodes) {
				if (node.getLearned() == false) {
					System.out.println(node.getNode() + ", " + node.getLearned());
				}
			}
		}
	}
	
	public static ArrayList<Node> getnodes() {
		return nodes;
	}
	
	public void editnode() {
	    System.out.println("EDIT node");
	    System.out.println("Enter the name of the node:");
	    String nodeName = in.nextLine().trim().toLowerCase(); // Trim and lowercase user input
	    ArrayList<Node> found = new ArrayList<>();
	    for (Node node : nodes) {
	        if (node.getNode().trim().toLowerCase().contains(nodeName)) { // Trim and lowercase node name from file
	            found.add(node);
	        }
	    }
	    if (found.isEmpty()) {
	        System.err.println("node not found.");
	        return; // Exit the method if no node is found
	    }
	    System.out.println("Results: ");
	    for (int i = 0; i < found.size(); i++) {
	        System.out.println(i + ". " + found.get(i).getNode());
	    }
	    System.out.println("Enter the number of the node you would like to edit.");
	    System.out.print("Number: ");
	    int editIndex = in.nextInt();
	    in.nextLine(); // Consume newline

	    if (editIndex >= 0 && editIndex < found.size()) {
	        Node selected = found.get(editIndex);
	        System.out.println("Set learning status of " + selected.getNode() + " to:");
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
	        System.out.println("Invalid node number.");
	    }
	}

	public static void parseLine(String line) {
		String[] fields = line.split(",");
		System.out.println(fields[2]);
		addNode(
			fields[0], 
			fields[1], 
			Boolean.parseBoolean(fields[2]));
	}

	public void clearList() {
		nodes.clear();
	}

	public void moveNode() {
		System.out.println("Move: [source index] [target index]");
		System.out.print("> ");
		try {
			int sourceIndex = in.nextInt();
			int targetIndex = in.nextInt();

			if (sourceIndex < 0 || sourceIndex >= nodes.size() || targetIndex < 0 || targetIndex > nodes.size()) {
				System.out.println("Invalid index. Please enter a valid index within the range of the list.");
				return;
			}

			Node sourcenode = nodes.remove(sourceIndex);
			nodes.add(targetIndex, sourcenode);

			System.out.println(sourcenode.getNode() + " Has been successfully moved to position " + targetIndex);
		} catch (InputMismatchException e) {
			System.out.println("Invalid input. Please enter integer values for the indices.");
			in.next(); // Consume the invalid input to prevent an infinite loop
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Index out of bounds. Please enter a valid index within the range of the list.");
		}
	}

}
