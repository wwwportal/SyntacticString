import java.io.*;
import java.io.File;
import java.io.InvalidClassException;
import java.io.StreamCorruptedException;
import java.util.Scanner;

public class Main {
    private static final String FILEPATH = "C:\\SyntacticString\\src\\nodes.dat";
    private static Nodes nodes = new Nodes();
    private static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        boolean repeat = true;
        String command;
        load();
        while (repeat) {
            System.out.print("> ");
            // parse the input before using it
            command = input.nextLine().trim().toLowerCase();
            Nodes.parseCommand(command);
            String[] parts = command.split(" ");
            if (parts[0].equals("node")) {
                Nodes.nodeMode(parts);
            } else if (parts[0].equals("nodes")) {
                Nodes.nodesMode(parts);
            } else if (command.equals("help")) {
                displayHelp();
            } else if (command.equals("exit")) {
                repeat = false;
            } else {
                System.out.println("Invalid command. Please try again.");
            }
        }
        input.close();
        System.out.println("Exiting Node Manager.");
    }

    public static void displayHelp() {
        System.out.println("\nCOMMAND HELP");
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
