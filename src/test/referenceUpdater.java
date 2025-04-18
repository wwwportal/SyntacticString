package test;

import java.util.ArrayList;
import java.util.Scanner;

public class referenceUpdater {
    public static Scanner input = new Scanner(System.in);
    public static ArrayList<String> topics = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String topic = scanner.nextLine();
        referenceUpdater updater = new referenceUpdater();
        topics.add(topic);
        scanner.close();
        
    }
}
