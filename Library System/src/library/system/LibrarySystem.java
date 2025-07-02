
package library.system;
import java.io.*;
import java.util.Scanner;
/**
 *
 * @author masha
 */
public class LibrarySystem {

    
    public static void main(String[] args) {
     createFile();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add Book");
            System.out.println("2. Display Books");
            System.out.println("3. Search Book by Title");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addBook(scanner);
                    break;
                case 2:
                    displayBooks();
                    break;
                case 3:
                    searchBook(scanner);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);

        scanner.close();
    }
    
     private static void createFile() {
        try {
            File file = new File("library_book.txt");
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating the file.");
            e.printStackTrace();
        }
    }

    private static void addBook(Scanner scanner) {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author name: ");
        String author = scanner.nextLine();
        System.out.print("Enter year of publication: ");
        int year = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("library_book.txt", true))) {
            writer.write(title + "," + author + "," + year);
            writer.newLine();
            System.out.println("Book added successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    private static void displayBooks() {
        try (BufferedReader reader = new BufferedReader(new FileReader("library_book.txt"))) {
            String line;
            System.out.println("\nBooks in the Library:");
            while ((line = reader.readLine()) != null) {
                String[] bookDetails = line.split(",");
                System.out.println(
                        "Title: " + bookDetails[0] + ", Author: " + bookDetails[1] + ", Year: " + bookDetails[2]);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
    }

    private static void searchBook(Scanner scanner) {
        System.out.print("Enter book title to search: ");
        String titleToSearch = scanner.nextLine();
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader("library_book.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] bookDetails = line.split(",");
                if (bookDetails[0].equalsIgnoreCase(titleToSearch)) {
                    System.out.println("Book found: Title: " + bookDetails[0] + ", Author: " + bookDetails[1]
                            + ", Year: " + bookDetails[2]);
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("Book not found.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
    }
    
}
