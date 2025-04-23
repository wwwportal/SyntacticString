import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Nodes implements Serializable {
    private static final long serialVersionUID = 9067008293711085447L;
    private static ArrayList<Node> nodes = new ArrayList<>();
    private static Scanner in = new Scanner(System.in);

    public void addNode() {
        System.out.println("NEW NODE");
        System.out.print("> ");
        String name = in.nextLine();
        System.out.print("Status: ");
        boolean status = in.nextBoolean();
        Node node = new Node(name, status);
        nodes.add(node);
        System.out.println("Node added successfully!");
    }

    public void displayNodes() {
        if (nodes.isEmpty()) {
            System.err.println("There are currently no nodes in the system");
            System.err.println("Try adding a new node!");
        } else {
            System.out.println("ALL NODES");
            for (int i = 0; i < nodes.size(); i++) {
                String name = nodes.get(i).getLine();
                System.out.println(i + ". " + name);
            }
        }
    }

    public void displayFalseNodes() {
        if (nodes.isEmpty()) {
            System.err.println("There are currently no nodes in the system");
            System.err.println("Try adding a new node!");
        } else {
            for (Node node : nodes) {
                if (!node.getStatus()) {
                    System.out.println(node.getLine() + ", " + node.getStatus());
                }
            }
        }
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public void editNode(String[] parts) {
        System.out.println("EDIT NODE");
		Node node = nodes.get(Integer.parseInt(parts[2]));
		if (parts[3].contains("status")) {
			if (parts[4].equals("false")) {
				node.setStatus(false);
			}
			else if (parts[4].equals("true")) {
				node.setStatus(true);
			}
		}
    }

    public void clearList() {
        nodes.clear();
		System.out.println("Nodes cleared.");
    }

    public void moveNode(int sourceIndex, int targetIndex) {
        try {
            if (sourceIndex < 0 || sourceIndex >= nodes.size() || targetIndex < 0 || targetIndex >= nodes.size()) {
                System.out.println("Invalid index. Please enter a valid index within the range of the list.");
                return;
            }
            Node sourcenode = nodes.remove(sourceIndex);
            nodes.add(targetIndex, sourcenode);
            System.out.println(sourcenode.getLine() + " Has been successfully moved to position " + targetIndex);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter integer values for the indices.");
            in.next(); // Consume the invalid input to prevent an infinite loop
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bounds. Please enter a valid index within the range of the list.");
        }
    }

    public void removeNode(int index) {
        if (index >= 0 && index < nodes.size()) {
            nodes.remove(index);
            System.out.println("Node removed successfully.");
        } else {
            System.out.println("Invalid node index.");
        }
    }



    public void nodeDetails(Node node, int index) {
        System.out.println("Node " + index + ": " + node.getLine() + " (Learned: " + node.getStatus() + ")");
        System.out.println("Links to:");
        for (Node linkedNode : node.getLinks()) {
            System.out.println("- " + linkedNode.getLine());
        }
    }

    public void link(String[] parts) {
		Node sourceNode = nodes.get(Integer.parseInt(parts[2]));
		for (int i = 3; i<(parts.length-1); i++) {
			Node link = nodes.get(i);
			sourceNode.setLink(link);
		}
    }

	public static void findNode(String name) { // find nodes based on a search string
		ArrayList<Node> found = new ArrayList<>();
        for (Node node : nodes) { // 
            if (node.getLine().trim().toLowerCase().contains(name)) {
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
}
