package main.java.com.syntacticstring;
import java.util.ArrayList;
import java.util.Scanner;

public class NodeNavigator {
    private ArrayList<Node> nodes;
    private int currentPosition = 0;
    private Scanner scanner;

    public NodeNavigator(ArrayList<Node> nodes) {
        this.nodes = nodes;
        this.scanner = new Scanner(System.in);
    }

    public void navigate() {
        if (nodes.isEmpty()) {
            System.out.println("No nodes to navigate.");
            return;
        }

        while (true) {
            displayCurrentView();
            String input = scanner.nextLine().toLowerCase();

            if (input.equals("q")) {
                break;
            } else if (input.equals("up") || input.equals("\u001B[A")) {
                moveUp();
            } else if (input.equals("down") || input.equals("\u001B[B")) {
                moveDown();
            } else {
                // Search functionality
                searchNodes(input);
            }
        }
    }

    private void displayCurrentView() {
        System.out.println("\nNODE LIST (Press 'q' to exit, use up/down arrows or type to search)");
        System.out.println("----------------------------------------");
        
        for (int i = 0; i < nodes.size(); i++) {
            String prefix = (i == currentPosition) ? "→ " : "  ";
            System.out.println(prefix + i + ". " + nodes.get(i).getContent());
        }
    }

    private void moveUp() {
        if (currentPosition > 0) {
            currentPosition--;
        }
    }

    private void moveDown() {
        if (currentPosition < nodes.size() - 1) {
            currentPosition++;
        }
    }

    private void searchNodes(String searchTerm) {
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).getContent().toLowerCase().contains(searchTerm)) {
                currentPosition = i;
                break;
            }
        }
    }

    public Node getCurrentNode() {
        return nodes.get(currentPosition);
    }
}