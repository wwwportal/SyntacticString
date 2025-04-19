import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class NodeManager implements Serializable {
    private ArrayList<Node> nodes = new ArrayList<>(); // Remove static
    private static Scanner in = new Scanner(System.in);

    public void addNode(String name, boolean learned) { // Remove static
        Node node = new Node(name, learned);
        nodes.add(node);
    }

    public void displayNodes() {
        if (nodes.isEmpty()) {
            System.err.println("There are currently no nodes in the system");
            System.err.println("Try adding a new node!");
        } else {
            System.out.println("ALL NODES");
            for (int i = 0; i < nodes.size(); i++) {
                String name = nodes.get(i).getNode();
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
                if (node.getStatus() == false) {
                    System.out.println(node.getNode() + ", " + node.getStatus());
                }
            }
        }
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public void editNode() {
        System.out.println("EDIT node");
        System.out.println("Enter the name of the node:");
        String nodeName = in.nextLine().trim().toLowerCase();
        ArrayList<Node> found = new ArrayList<>();
        for (Node node : nodes) {
            if (node.getNode().trim().toLowerCase().contains(nodeName)) {
                found.add(node);
            }
        }
        if (found.isEmpty()) {
            System.err.println("node not found.");
            return;
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
                    selected.setStatus(false);
                    break; // Add break statement
                case 1:
                    selected.setStatus(true);
                    break;
                default:
                    System.out.println("Invalid input.");
            }
        } else {
            System.out.println("Invalid node number.");
        }
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
