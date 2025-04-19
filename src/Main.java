import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileReader;

public class Main {
    private static NodeManager nodes = new NodeManager();
    private static final String FILEPATH = "C:/CST8132/StudyTopics/src/files/nodes.csv";
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        boolean repeat = true;
        String option;
        nodes.loadnodes();
        while (repeat) {
            System.out.println("[help]");
            System.out.print("> ");
            option = input.nextLine().trim().toLowerCase();

            if (option.equals("display")) {
                nodes.displayNodes();
            } else if (option.equals("unlearned")) {
                nodes.displayUnlearnedNodes();
            } else if (option.equals("add")) {
                addNode();
            } else if (option.equals("edit")) {
                nodes.editnode();
            } else if (option.equals("save")) {
                savenodes();
            } else if (option.equals("load")) {
                loadnodes();
            } else if (option.equals("clear")) {
                nodes.clearList();
            } else if (option.equals("move")) {
                nodes.moveNode();
            } else if (option.equals("help")) {
                displayHelp(); // Call the help method
            } else if (option.equals("exit")) {
                repeat = false;
            } else {
                System.out.println("Invalid command. Please try again.");
            }
        }
        input.close();
        System.out.println("Exiting node Manager.");
    }

    public static void addNode() {
        clearConsole();
        System.out.print("node: ");
        String node = input.nextLine();
        System.out.print("Description: ");
        String description = input.nextLine();
        System.out.print("Learned (true/false): ");
        boolean learned = input.nextBoolean();
        input.nextLine(); // Consume newline
        NodeManager.addNode(node, description, learned);
        System.out.println("node added successfully!");
        clearConsole();
    }

    public static void savenodes() {
        clearConsole();
        System.out.println("Saving nodes...");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILEPATH))) {
            System.out.println("Saving nodes to " + FILEPATH);
            for (Node item : NodeManager.getnodes()) {
                writer.write(item.toString() + "\n");
            }
            System.out.println("nodes saved successfully!");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
        finally {
            exitSection();
        }
    }

    public static void loadnodes() {
        clearConsole();
        System.out.println("Loading nodes...");
        try (BufferedReader br = new BufferedReader(new FileReader(FILEPATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                NodeManager.parseLine(line);
            }
            System.out.println("nodes successfully loaded!");
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
        finally {
            exitSection();
        }
    }

    // New method to display help information
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
        exitSection();
    }

    // Method to clear the console screen
    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException ex) {
            // Handle any exceptions, for example
            System.out.println("Error clearing console: " + ex.getMessage());
        }
    }
    
    public static void exitSection() {
        System.out.println("[exit]");
        System.out.print("> ");
        String help = input.nextLine();       
        if (help.equals("exit")) {
            clearConsole();// Call the help method
        }
        else {
            clearConsole();
            main(null);
        }
    }
}
