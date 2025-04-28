import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class LibraryAppGUICustom extends JFrame {
    private ArrayList<Book> books = new ArrayList<>();
    private JTable table;
    private DefaultTableModel model;
    private JTextField idField, titleField, authorField;
    private JButton addButton, editButton, deleteButton, clearButton;

    public LibraryAppGUICustom() {
        setTitle("📚 Library Manager");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(245, 245, 245));

        // Table
        model = new DefaultTableModel(new String[]{"ID", "Title", "Author"}, 0);
        table = new JTable(model);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(24);
        JScrollPane scrollPane = new JScrollPane(table);

        // Form Panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        formPanel.setBackground(Color.WHITE);

        idField = new JTextField();
        titleField = new JTextField();
        authorField = new JTextField();

        addButton = new JButton("Tambah");
        editButton = new JButton("Edit");
        deleteButton = new JButton("Hapus");
        clearButton = new JButton("Clear");

        styleButton(addButton);
        styleButton(editButton);
        styleButton(deleteButton);
        styleButton(clearButton);

        formPanel.add(new JLabel("ID Buku:"));
        formPanel.add(idField);
        formPanel.add(new JLabel("Judul Buku:"));
        formPanel.add(titleField);
        formPanel.add(new JLabel("Penulis:"));
        formPanel.add(authorField);
        formPanel.add(addButton);
        formPanel.add(editButton);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(new Color(230, 230, 250));
        topPanel.add(deleteButton);
        topPanel.add(clearButton);

        // Layout
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(formPanel, BorderLayout.SOUTH);

        // Listeners
        addButton.addActionListener(e -> addBook());
        editButton.addActionListener(e -> editBook());
        deleteButton.addActionListener(e -> deleteBook());
        clearButton.addActionListener(e -> clearForm());

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

    private void styleButton(JButton button) {
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(100, 149, 237));
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
    }

    private void addBook() {
        String id = idField.getText().trim();
        String title = titleField.getText().trim();
        String author = authorField.getText().trim();

        if (id.isEmpty() || title.isEmpty() || author.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua field harus diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (isDuplicateId(id)) {
            JOptionPane.showMessageDialog(this, "ID Buku sudah ada!", "Error", JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(this, "Pilih buku yang ingin diedit!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String title = titleField.getText().trim();
        String author = authorField.getText().trim();

        if (title.isEmpty() || author.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Judul dan Penulis tidak boleh kosong!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        model.setValueAt(title, row, 1);
        model.setValueAt(author, row, 2);

        books.get(row).setTitle(title);
        books.get(row).setAuthor(author);
        clearForm();
    }

    private void deleteBook() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih buku yang ingin dihapus!", "Peringatan", JOptionPane.WARNING_MESSAGE);
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

    private boolean isDuplicateId(String id) {
        for (Book b : books) {
            if (b.getId().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LibraryAppGUICustom().setVisible(true);
        });
    }
}
