import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Nodes implements Serializable {
    private static final long serialVersionUID = 9067008293711085447L;
    private static ArrayList<Node> nodes = new ArrayList<>();
    private static Scanner in = new Scanner(System.in);

    public static void nodeMode(String[] parts) {
        if (parts.length == 1) {
            System.out.println("Invalid node command.");
            return;
        }
        try {
            if (parts.length == 2) {
                int index = Integer.valueOf(parts[1]);
                Node selectedNode = getNodes().get(index);
                nodeDetails(selectedNode, index);
            } else if (parts[1].equals("move")) {
                int source = Integer.parseInt(parts[2]);
                int target = Integer.parseInt(parts[3]);
                moveNode(source, target);
            } else if (parts[1].equals("remove")) {
                int target = Integer.parseInt(parts[2]);
                removeNode(target);
            } else if (parts[1].equals("link")) {
                link(parts);
            } else if (parts[1].equals("add")) {
                addNode(parts);
            } else {
                System.out.println("Invalid node subcommand.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid node input.");
        }
    }

    public static void addNode(String[] parts) {
        String name = parts[2];
        for (int i = 3; i < parts.length; i++) {
            name += " " + parts[i];
        }
        Node node = new Node(name);
        nodes.add(node);
        System.out.println("Node added successfully!");
    }

    public static void displayNodes() {
        if (nodes.isEmpty()) {
            System.err.println("There are currently no nodes in the system");
            System.err.println("Try adding a new node!");
        } else {
            System.out.println("ALL NODES");
            for (int i = 0; i < nodes.size(); i++) {
                String name = nodes.get(i).getContent();
                System.out.println(i + ". " + name);
            }
        }
    }

    public static ArrayList<Node> getNodes() {
        return nodes;
    }

    public static void clearList() {
        nodes.clear();
		System.out.println("Nodes cleared.");
    }

    public static void moveNode(int sourceIndex, int targetIndex) {
        try {
            if (sourceIndex < 0 || sourceIndex >= nodes.size() || targetIndex < 0 || targetIndex >= nodes.size()) {
                System.out.println("Invalid index. Please enter a valid index within the range of the list.");
                return;
            }
            Node sourcenode = nodes.remove(sourceIndex);
            nodes.add(targetIndex, sourcenode);
            System.out.println(sourcenode.getContent() + " Has been successfully moved to position " + targetIndex);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter integer values for the indices.");
            in.next(); // Consume the invalid input to prevent an infinite loop
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bounds. Please enter a valid index within the range of the list.");
        }
    }

    public static void removeNode(int index) {
        if (index >= 0 && index < nodes.size()) {
            nodes.remove(index);
            System.out.println("Node removed successfully.");
        } else {
            System.out.println("Invalid node index.");
        }
    }

    public static void nodeDetails(Node node, int index) {
        System.out.println("Node " + index + ": " + node.getContent());
        System.out.println("Links to:");
        for (Node linkedNode : node.getLinks()) {
            System.out.println("- " + linkedNode.getContent());
        }
    }

    public static void link(String[] parts) {
		Node sourceNode = nodes.get(Integer.parseInt(parts[2]));
		for (int i = 3; i<(parts.length-1); i++) {
			Node link = nodes.get(i);
			sourceNode.setLink(link);
		}
    }

	public static void findNode(String name) { // find nodes based on a search string
		ArrayList<Node> found = new ArrayList<>();
        for (Node node : nodes) { // 
            if (node.getContent().trim().toLowerCase().contains(name)) {
                found.add(node);
				System.out.print(node);
            }
        }
        if (found.isEmpty()) { // if the list "found" is empty, then no match was found
            System.err.println("node not found.");
            return;
        } else {

		}
	}
    
    public static void nodesMode(String[] parts) {
        if (parts.length == 1) {
            displayNodes();
        } else if (parts[1].equals("load")) {
            Main.load();
        } else if (parts[1].equals("save")) {
            Main.save();
        } else if (parts[1].equals("clear")) {
            clearList();
        }
    }

    public static ArrayList<String> parseCommand(String command) {
        boolean inQuotes = false;
        char character;
        String word = null;
        ArrayList<String> fields = new ArrayList<>();
        for (int i = 0; i < command.length(); i++) {
            character = command.charAt(i);
            if (character == '"' && inQuotes == false) {
                fields.add(word);
                inQuotes = true;
                word = null;
            }
            else if (character == '"' && inQuotes == true) {
                inQuotes = false;
                fields.add(word);
            }
            else {
                word += character;
            }
        }
        return fields;
    }
}
