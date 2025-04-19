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
            option = input.next().toLowerCase();

            if (option.equals("display")) {
                nodes.displayNodes(); // Use instance method
                exitSection();
            } else if (option.equals("unlearned")) {
                nodes.displayFalseNodes(); // Use instance method
                exitSection();
            } else if (option.equals("add")) {
                addNode();
                exitSection();
            } else if (option.equals("edit")) {
                nodes.editNode(); // Use instance method
                exitSection();
            } else if (option.equals("save")) {
                save();
                exitSection();
            } else if (option.equals("load")) {
                load();
                exitSection();
            } else if (option.equals("clear")) {
                nodes.clearList(); // Use instance method
                exitSection();
            } else if (option.equals("move")) {
                nodes.moveNode(); // Use instance method
                exitSection();
            } else if (option.equals("help")) {
                displayHelp();
                exitSection();
            } else if (option.equals("exit")) {
                repeat = false;
            } else if (option.equals("link")) {
                link();
            } else {
                System.out.println("Invalid command. Please try again.");
                exitSection();
            }
        }
        input.close();
        System.out.println("Exiting Node Manager.");
    }

    public static void addNode() {
        clearConsole();
        System.out.println("NEW NODE");
        System.out.print("Node Name: ");
        String nodeName = input.nextLine();
        System.out.print("Learned (true/false): ");
        boolean learned = input.nextBoolean();
        input.nextLine(); // Consume newline
        nodes.addNode(nodeName, learned); // Use instance method
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
        System.out.println("display   - Displays all nodes in the system.");
        System.out.println("unlearned - Displays only the nodes that are marked as 'unlearned'.");
        System.out.println("add       - Adds a new node to the system.");
        System.out.println("edit      - Allows you to edit an existing node.");
        System.out.println("save      - Saves the current list of nodes to the file.");
        System.out.println("load      - Loads nodes from the file.");
        System.out.println("clear     - Clears the current list of nodes (removes all nodes).");
        System.out.println("move      - Moves a node to a different position in the list.");
        System.out.println("help      - Displays this help message.");
        System.out.println("exit      - Exits the program.");
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

    public static void link() {
        System.out.println("LINK");
        System.out.print("> ");
    }

    public static void exitSection() {
        System.out.println("[Press Enter to continue]");
        input.nextLine(); // Wait for Enter key
    }
}
