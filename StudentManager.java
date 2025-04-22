import java.util.ArrayList;
import java.util.Scanner;

public class StudentManager {
    private static ArrayList<Student> students = new ArrayList<>();
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n=== MENU STUDENT MANAGER ===");
            System.out.println("1. Tambah Mahasiswa");
            System.out.println("2. Tampilkan Semua Mahasiswa");
            System.out.println("3. Cari Mahasiswa");
            System.out.println("4. Edit Mahasiswa");
            System.out.println("5. Hapus Mahasiswa");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");
            choice = input.nextInt();
            input.nextLine(); // konsumsi enter

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> showAllStudents();
                case 3 -> searchStudent();
                case 4 -> editStudent();
                case 5 -> deleteStudent();
                case 0 -> System.out.println("Keluar dari program.");
                default -> System.out.println("Pilihan tidak valid.");
            }
        } while (choice != 0);
    }

    private static void addStudent() {
        System.out.print("Masukkan NIM: ");
        String nim = input.nextLine();
        System.out.print("Masukkan Nama: ");
        String name = input.nextLine();
        System.out.print("Masukkan Jurusan: ");
        String major = input.nextLine();

        Student newStudent = new Student(nim, name, major);
        students.add(newStudent);
        System.out.println("Mahasiswa berhasil ditambahkan.");
    }

    private static void showAllStudents() {
        if (students.isEmpty()) {
            System.out.println("Belum ada data mahasiswa.");
        } else {
            for (Student s : students) {
                s.printInfo();
            }
        }
    }

    private static void searchStudent() {
        System.out.print("Masukkan NIM yang dicari: ");
        String nim = input.nextLine();
        boolean found = false;

        for (Student s : students) {
            if (s.getNim().equals(nim)) {
                s.printInfo();
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Mahasiswa tidak ditemukan.");
        }
    }

    private static void editStudent() {
        System.out.print("Masukkan NIM mahasiswa yang ingin diedit: ");
        String nim = input.nextLine();

        for (Student s : students) {
            if (s.getNim().equals(nim)) {
                System.out.print("Nama baru: ");
                s.setName(input.nextLine());
                System.out.print("Jurusan baru: ");
                s.setMajor(input.nextLine());
                System.out.println("Data berhasil diupdate.");
                return;
            }
        }

        System.out.println("Mahasiswa tidak ditemukan.");
    }

    private static void deleteStudent() {
        System.out.print("Masukkan NIM mahasiswa yang ingin dihapus: ");
        String nim = input.nextLine();

        for (Student s : students) {
            if (s.getNim().equals(nim)) {
                students.remove(s);
                System.out.println("Data berhasil dihapus.");
                return;
            }
        }

        System.out.println("Mahasiswa tidak ditemukan.");
    }
}
