import java.io.*;
import java.util.*;

public class FileHandler {
    private static final String FILE_NAME = "sample.txt";
    public static void writeToFile(String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(data);
            writer.newLine();
            System.out.println("Data written successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void readFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            System.out.println("File Contents:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
    public static void modifyFile(int lineNumber, String newData) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file for modification: " + e.getMessage());
            return;
        }

        if (lineNumber <= 0 || lineNumber > lines.size()) {
            System.out.println("Invalid line number.");
            return;
        }

        lines.set(lineNumber - 1, newData); // Modify the specific line

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
            System.out.println("File modified successfully.");
        } catch (IOException e) {
            System.out.println("Error writing modified content: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nFile Operations:");
            System.out.println("1. Write to File");
            System.out.println("2. Read from File");
            System.out.println("3. Modify a Line in File");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter text to write: ");
                    String data = scanner.nextLine();
                    writeToFile(data);
                    break;
                case 2:
                    readFromFile();
                    break;
                case 3:
                    System.out.print("Enter line number to modify: ");
                    int lineNumber = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter new text: ");
                    String newData = scanner.nextLine();
                    modifyFile(lineNumber, newData);
                    break;
                case 4:
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 4);

        scanner.close();
    }
}
