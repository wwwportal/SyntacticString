import java.io.*;
import java.io.File;
import java.io.InvalidClassException;
import java.io.StreamCorruptedException;
import java.util.Scanner;

public class Main {
    private static final String FILEPATH = "C:\\CST8132\\StudyTopics\\src\\files\\nodes.dat";
    private static Nodes nodes = new Nodes();
    private static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        boolean repeat = true;
        String option;
        load();
        while (repeat) {
            System.out.print("> ");
            option = input.nextLine().trim().toLowerCase();
            String[] parts = option.split(" ");
            if (parts[0].equals("node")) {
                nodeMode(parts);
            } else if (parts[0].equals("nodes")) {
                nodesMode(parts);
            } else if (option.equals("help")) {
                displayHelp();
            } else if (option.equals("exit")) {
                repeat = false;
            } else {
                System.out.println("Invalid command. Please try again.");
            }
        }
        input.close();
        System.out.println("Exiting Node Manager.");
    }

    private static void nodeMode(String[] parts) {
        if (parts.length == 1) {
            System.out.println("Invalid node command.");
            return;
        }
        try {
            if (parts.length == 2) {
                int index = Integer.valueOf(parts[1]);
                Node selectedNode = nodes.getNodes().get(index);
                nodes.nodeDetails(selectedNode, index);
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
        } else if (parts[1].equals("load")) {
            load();
        } else if (parts[1].equals("save")) {
            save();
        } else if (parts[1].equals("clear")) {
            nodes.clearList();
        }
    }

    public static void displayHelp() {
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

    public static void load() {
        if (!new File(FILEPATH).exists()) {
            System.out.println("Starting with empty nodes list - no saved data found.");
            return;
        }

        try (FileInputStream fis = new FileInputStream(FILEPATH);
             ObjectInputStream in = new ObjectInputStream(fis)) {
            
            Object obj = in.readObject();
            if (obj instanceof Nodes) {
                Main.nodes = (Nodes) obj;
                System.out.println("Nodes loaded successfully from " + FILEPATH);
            } else {
                System.err.println("Error: File contains invalid data type");
                Main.nodes = new Nodes(); // Create fresh nodes object
            }
            
        } catch (ClassNotFoundException | InvalidClassException e) {
            System.err.println("Error loading nodes: Class definition mismatch - creating new nodes list");
            Main.nodes = new Nodes(); // Create fresh nodes object
        } catch (StreamCorruptedException e) {
            System.err.println("Error loading nodes: File is corrupted - creating new nodes list");
            Main.nodes = new Nodes(); // Create fresh nodes object 
        } catch (IOException e) {
            System.err.println("Error loading nodes: " + e.getMessage());
            Main.nodes = new Nodes(); // Create fresh nodes object
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
}
