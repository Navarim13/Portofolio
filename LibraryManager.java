//compile both Book.java and LibraryManager.java
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class LibraryManager {
    private static final String FILE_NAME = "books.txt";
    private static ArrayList<Book> books = new ArrayList<>();
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        loadBooks();

        int choice;
        do {
            System.out.println("\n=== MENU LIBRARY APP ===");
            System.out.println("1. Tambah Buku");
            System.out.println("2. Tampilkan Semua Buku");
            System.out.println("3. Cari Buku");
            System.out.println("4. Edit Buku");
            System.out.println("5. Hapus Buku");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");
            choice = input.nextInt();
            input.nextLine(); // konsumsi enter

            switch (choice) {
                case 1 -> addBook();
                case 2 -> showAllBooks();
                case 3 -> searchBook();
                case 4 -> editBook();
                case 5 -> deleteBook();
                case 0 -> saveBooks();
                default -> System.out.println("Pilihan tidak valid.");
            }
        } while (choice != 0);

        System.out.println("Program selesai.");
    }

    private static void loadBooks() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 3) {
                    books.add(new Book(parts[0], parts[1], parts[2]));
                }
            }
        } catch (IOException e) {
            // File tidak ditemukan, abaikan saja
        }
    }

    private static void saveBooks() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Book b : books) {
                writer.write(b.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Gagal menyimpan data.");
        }
    }

    private static void addBook() {
        System.out.print("Masukkan ID Buku: ");
        String id = input.nextLine();
        System.out.print("Masukkan Judul Buku: ");
        String title = input.nextLine();
        System.out.print("Masukkan Nama Penulis: ");
        String author = input.nextLine();

        books.add(new Book(id, title, author));
        System.out.println("Buku berhasil ditambahkan.");
    }

    private static void showAllBooks() {
        if (books.isEmpty()) {
            System.out.println("Belum ada data buku.");
        } else {
            for (Book b : books) {
                b.printInfo();
            }
        }
    }

    private static void searchBook() {
        System.out.print("Masukkan ID Buku yang dicari: ");
        String id = input.nextLine();
        boolean found = false;

        for (Book b : books) {
            if (b.getId().equalsIgnoreCase(id)) {
                b.printInfo();
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Buku tidak ditemukan.");
        }
    }

    private static void editBook() {
        System.out.print("Masukkan ID Buku yang ingin diedit: ");
        String id = input.nextLine();

        for (Book b : books) {
            if (b.getId().equalsIgnoreCase(id)) {
                System.out.print("Judul baru: ");
                b.setTitle(input.nextLine());
                System.out.print("Penulis baru: ");
                b.setAuthor(input.nextLine());
                System.out.println("Data buku berhasil diperbarui.");
                return;
            }
        }

        System.out.println("Buku tidak ditemukan.");
    }

    private static void deleteBook() {
        System.out.print("Masukkan ID Buku yang ingin dihapus: ");
        String id = input.nextLine();

        for (Book b : books) {
            if (b.getId().equalsIgnoreCase(id)) {
                books.remove(b);
                System.out.println("Buku berhasil dihapus.");
                return;
            }
        }

        System.out.println("Buku tidak ditemukan.");
    }
}
