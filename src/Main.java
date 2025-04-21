import java.io.*;
import java.util.Scanner;

public class Main {
    private static Nodes nodes = new Nodes();
    private static final String FILEPATH = "C:\\CST8132\\StudyTopics\\src\\files\\nodes.dat";
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        boolean repeat = true;
        String option;
        load();
        while (repeat) {
            clearConsole();
            System.out.println("[help]");
            System.out.print("> ");
            option = input.nextLine().trim().toLowerCase();
            String[] parts = option.split(" ");

            if (parts[0].equals("node")) {
                nodeMode(parts);
                exitSection();
            } else if (parts[0].equals("nodes")) {
                nodesMode(parts);
                exitSection();
            } else if (option.equals("help")) {
                displayHelp();
                exitSection();
            } else if (option.equals("exit")) {
                repeat = false;
            } else {
                System.out.println("Invalid command. Please try again.");
                exitSection();
            }
        }
        input.close();
        System.out.println("Exiting Node Manager.");
    }

    private static void nodeMode(String[] parts) {
        if (parts.length == 1) {
            System.out.println("Invalid node command.  Try 'node all', 'node [index]', etc.");
            return;
        }
        try {
            if (parts.length == 2) {
                int index = Integer.valueOf(parts[1]);
                Node selectedNode = nodes.getNodes().get(index);
                displayNodeDetails(selectedNode, index);
            } else if (parts[1].equals("move")) {
                int source = Integer.parseInt(parts[2]);
                int target = Integer.parseInt(parts[3]);
                nodes.moveNode(source, target);
            } else if (parts[1].equals("remove")) {
                int target = Integer.parseInt(parts[2]);
                nodes.removeNode(target);
            } else if (parts[1].equals("link")) {
                nodes.link(parts);
            } else if (parts[1].equals("add")) {
                nodes.addNode();
            } else if (parts[1].equals("edit")) {
                nodes.editNode(parts);
            } else {
                System.out.println("Invalid node subcommand.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid node index.");
        }
    }

    private static void nodesMode(String[] parts) {
        if (parts.length == 1) {
            nodes.displayNodes();
            exitSection();
        } else if (parts[1].equals("false")) {
            nodes.displayFalseNodes();
        } else if (parts[1].equals("load")) {
            load();
        } else if (parts[1].equals("save")) {
            save();
        } else if (parts[1].equals("clear")) {
            nodes.clearList();
        }
    }

    private static void displayNodeDetails(Node node, int index) {
        System.out.println("Node " + index + ": " + node.getLine() + " (Learned: " + node.getStatus() + ")");
        System.out.println("Links to:");
        for (Node linkedNode : node.getLinks()) {
            System.out.println("- " + linkedNode.getLine());
        }
    }

    public static void save() {
        try (FileOutputStream fos = new FileOutputStream(FILEPATH)) {
            try (ObjectOutputStream out = new ObjectOutputStream(fos)) {
                out.writeObject(nodes);
                System.out.println("Nodes saved successfully to " + FILEPATH);
            } catch (IOException e) {
                System.err.println("Error saving nodes: " + e.getMessage());
            }
        } catch (IOException e) {
            System.err.println("Error opening file for saving: " + e.getMessage());
        }
    }

    public static void load() {
        try (FileInputStream fis = new FileInputStream(FILEPATH)) {
            try (ObjectInputStream in = new ObjectInputStream(fis)) {
                Nodes loadedNodes = (Nodes) in.readObject();
                Main.nodes = loadedNodes; // Assuming 'nodes' is a static field in Main
                System.out.println("Nodes loaded successfully from " + FILEPATH);
            } catch (ClassNotFoundException e) {
                System.err.println("ClassNotFoundException: " + e.getMessage());
            } catch (IOException e) {
                System.err.println("Error loading nodes: " + e.getMessage());
            }
        } catch (IOException e) {
            System.err.println("Error opening file for loading: " + e.getMessage());
        }
    }

    public static void displayHelp() {
        clearConsole();
        System.out.println("\nCOMMAND HELP");
        System.out.println("node all - Displays all nodes in the system.");
        System.out.println("node [index] - Views node and links.");
        System.out.println("node [index] move [target] - Moves node to target index.");
        System.out.println("node [index] remove - Removes node.");
        System.out.println("node [index] link [index] - Links node to another node.");
        System.out.println("unlearned - Displays only the nodes that are marked as 'unlearned'.");
        System.out.println("add - Adds a new node to the system.");
        System.out.println("edit - Allows you to edit an existing node.");
        System.out.println("save - Saves the current list of nodes to the file.");
        System.out.println("load - Loads nodes from the file.");
        System.out.println("clear - Clears the current list of nodes (removes all nodes).");
        System.out.println("help - Displays this help message.");
        System.out.println("exit - Exits the program.");
    }

    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException ex) {
            System.err.println("Error clearing console: " + ex.getMessage());
        }
    }

    public static void exitSection() {
        System.out.println("[Press Enter to continue]");
        input.nextLine(); // Wait for Enter key
    }
}
