import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileReader;

public class Main {
    private static TopicManager topics = new TopicManager();
    private static final String FILEPATH = "src/files/topics.csv";
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        boolean repeat = true;
        String option;
        topics.loadTopics();
        while (repeat) {
            clearConsole(); // Clear the console before displaying the menu
            displayMenu();
            System.out.print("> ");
            option = input.nextLine().trim().toLowerCase();

            if (option.equals("display")) {
                topics.displayTopics();
            } else if (option.equals("unlearned")) {
                topics.displayUnlearnedTopics();
            } else if (option.equals("add")) {
                addTopic();
            } else if (option.equals("edit")) {
                topics.editTopic();
            } else if (option.equals("save")) {
                saveTopics();
            } else if (option.equals("load")) {
                loadTopics();
            } else if (option.equals("clear")) {
                topics.clearList();
            } else if (option.equals("move")) {
                topics.moveTopic();
            } else if (option.equals("help")) {
                displayHelp(); // Call the help method
            } else if (option.equals("exit")) {
                repeat = false;
            } else {
                System.out.println("Invalid command. Please try again.");
            }
        }
        input.close();
        System.out.println("Exiting Topic Manager.");
    }

    public static void addTopic() {
        System.out.print("Topic: ");
        String topic = input.nextLine();
        System.out.print("Description: ");
        String description = input.nextLine();
        System.out.print("Learned (true/false): ");
        boolean learned = input.nextBoolean();
        input.nextLine(); // Consume newline
        TopicManager.addTopic(topic, description, learned);
        System.out.println("Topic added successfully!");
    }

    public static void displayMenu() {
        System.out.println("\n TOPIC MANAGER");
        System.out.println("display - Display all topics");
        System.out.println("unlearned - Display unlearned topics");
        System.out.println("add - Add new topic");
        System.out.println("edit - Edit topic");
        System.out.println("save - Save topics to file");
        System.out.println("load - Load topics from file");
        System.out.println("clear - Clear list");
        System.out.println("move - Move topic");
        System.out.println("help - Display available commands"); // Added help to the menu
        System.out.println("exit - Exit the program");
    }

    public static void saveTopics() {
        System.out.println("Saving topics...");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILEPATH))) {
            System.out.println("Saving topics to " + FILEPATH);
            for (Topic item : TopicManager.getTopics()) {
                writer.write(item.toString() + "\n");
            }
            System.out.println("Topics saved successfully!");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void loadTopics() {
        System.out.println("Loading topics...");
        try (BufferedReader br = new BufferedReader(new FileReader(FILEPATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                TopicManager.parseLine(line);
            }
            System.out.println("Topics successfully loaded!");
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }

    // New method to display help information
    public static void displayHelp() {
        System.out.println("\nCOMMAND HELP");
        System.out.println("display   - Displays all topics in the system.");
        System.out.println("unlearned - Displays only the topics that are marked as 'unlearned'.");
        System.out.println("add       - Adds a new topic to the system.");
        System.out.println("edit      - Allows you to edit an existing topic.");
        System.out.println("save      - Saves the current list of topics to the file.");
        System.out.println("load      - Loads topics from the file.");
        System.out.println("clear     - Clears the current list of topics (removes all topics).");
        System.out.println("move      - Moves a topic to a different position in the list.");
        System.out.println("help      - Displays this help message.");
        System.out.println("exit      - Exits the program.");
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
}
