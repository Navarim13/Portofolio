//compile with Book.java
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class LibraryAppGUI extends JFrame {
    private ArrayList<Book> books = new ArrayList<>();
    private JTable table;
    private DefaultTableModel model;
    private JTextField idField, titleField, authorField;

    public LibraryAppGUI() {
        setTitle("Library App");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Table
        model = new DefaultTableModel(new String[]{"ID", "Title", "Author"}, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        // Form input
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        idField = new JTextField();
        titleField = new JTextField();
        authorField = new JTextField();
        JButton addButton = new JButton("Tambah");
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Hapus");

        formPanel.add(new JLabel("ID"));
        formPanel.add(idField);
        formPanel.add(new JLabel("Judul"));
        formPanel.add(titleField);
        formPanel.add(new JLabel("Penulis"));
        formPanel.add(authorField);
        formPanel.add(addButton);
        formPanel.add(editButton);

        // Layout
        add(scrollPane, BorderLayout.CENTER);
        add(formPanel, BorderLayout.SOUTH);
        add(deleteButton, BorderLayout.NORTH);

        // Action Listeners
        addButton.addActionListener(e -> addBook());
        editButton.addActionListener(e -> editBook());
        deleteButton.addActionListener(e -> deleteBook());

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    idField.setText(model.getValueAt(row, 0).toString());
                    titleField.setText(model.getValueAt(row, 1).toString());
                    authorField.setText(model.getValueAt(row, 2).toString());
                }
            }
        });
    }

    private void addBook() {
        String id = idField.getText();
        String title = titleField.getText();
        String author = authorField.getText();

        if (id.isEmpty() || title.isEmpty() || author.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua field harus diisi!");
            return;
        }

        Book book = new Book(id, title, author);
        books.add(book);
        model.addRow(new Object[]{id, title, author});
        clearForm();
    }

    private void editBook() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih buku yang ingin diedit!");
            return;
        }

        String id = idField.getText();
        String title = titleField.getText();
        String author = authorField.getText();

        model.setValueAt(id, row, 0);
        model.setValueAt(title, row, 1);
        model.setValueAt(author, row, 2);

        books.get(row).setTitle(title);
        books.get(row).setAuthor(author);
        clearForm();
    }

    private void deleteBook() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih buku yang ingin dihapus!");
            return;
        }

        model.removeRow(row);
        books.remove(row);
        clearForm();
    }

    private void clearForm() {
        idField.setText("");
        titleField.setText("");
        authorField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LibraryAppGUI().setVisible(true);
        });
    }
}
