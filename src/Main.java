import java.io.*;
import java.util.Scanner;

public class Main {
    private static NodeManager nodes = new NodeManager();
    private static final String FILEPATH = "src/files/nodes.dat";
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
                handleNodeCommand(parts);
                exitSection();
            } else if (option.equals("unlearned")) {
                nodes.displayFalseNodes();
                exitSection();
            } else if (option.equals("add")) {
                addNode();
                exitSection();
            } else if (option.equals("edit")) {
                nodes.editNode();
                exitSection();
            } else if (option.equals("save")) {
                save();
                exitSection();
            } else if (option.equals("load")) {
                load();
                exitSection();
            } else if (option.equals("clear")) {
                nodes.clearList();
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

    private static void handleNodeCommand(String[] parts) {
        if (parts.length == 1) {
            System.out.println("Invalid node command.  Try 'node all', 'node [index]', etc.");
            return;
        }

        if (parts[1].equals("all")) {
            nodes.displayNodes();
        } else {
            try {
                int index = Integer.parseInt(parts[1]);
                if (index >= 0 && index < nodes.getNodes().size()) {
                    Node selectedNode = nodes.getNodes().get(index);
                    if (parts.length == 2) {
                        displayNodeDetails(selectedNode, index);
                    } else if (parts[2].equals("move")) {
                        try {
                            int target = Integer.parseInt(parts[3]);
                            nodes.moveNode(index, target);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid target index.");
                        }
                    } else if (parts[2].equals("remove")) {
                        nodes.removeNode(index);
                    } else if (parts[2].equals("link")) {
                        try {
                            int target = Integer.parseInt(parts[3]);
                            nodes.addReference(index, target);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid target index.");
                        }
                    } else {
                        System.out.println("Invalid node subcommand.");
                    }
                } else {
                    System.out.println("Invalid node index.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid node index.");
            }
        }
    }

    private static void displayNodeDetails(Node node, int index) {
        System.out.println("Node " + index + ": " + node.getLine() + " (Learned: " + node.getStatus() + ")");
        System.out.println("Links to:");
        for (Node linkedNode : node.getLinks()) {
            System.out.println("- " + linkedNode.getLine());
        }
    }

    public static void addNode() {
        clearConsole();
        System.out.println("NEW NODE");
        System.out.print("Node Name: ");
        String nodeName = input.nextLine();
        System.out.print("Learned (true/false): ");
        boolean learned = input.nextBoolean();
        input.nextLine(); // Consume newline
        nodes.addNode(nodeName, learned);
        System.out.println("Node added successfully!");
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
                NodeManager loadedNodes = (NodeManager) in.readObject();
                Main.nodes = loadedNodes;
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
