//compile both Book.java and LibraryManager.java
public class Book {
    private String id;
    private String title;
    private String author;

    public Book(String id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String toFileString() {
        return id + ";" + title + ";" + author;
    }

    public void printInfo() {
        System.out.println("ID    : " + id);
        System.out.println("Judul : " + title);
        System.out.println("Penulis: " + author);
        System.out.println("-------------------------");
    }
}
