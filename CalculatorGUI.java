import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JavaCalculator extends JFrame implements ActionListener {
    private JTextField inputField;
    private double num1, num2, result;
    private char operator;

    public JavaCalculator() {
        setTitle("Kalkulator Sederhana");
        setSize(300, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        inputField = new JTextField();
        inputField.setFont(new Font("Arial", Font.PLAIN, 24));
        inputField.setEditable(false);
        add(inputField, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 5, 5));

        String[] buttonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "=", "+"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.BOLD, 18));
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.charAt(0) >= '0' && command.charAt(0) <= '9') {
            inputField.setText(inputField.getText() + command);
        } else if (command.equals("C")) {
            inputField.setText("");
            num1 = num2 = result = 0;
        } else if (command.equals("=")) {
            try {
                num2 = Double.parseDouble(inputField.getText());
                switch (operator) {
                    case '+': result = num1 + num2; break;
                    case '-': result = num1 - num2; break;
                    case '*': result = num1 * num2; break;
                    case '/':
                        if (num2 == 0) {
                            inputField.setText("Error");
                            return;
                        }
                        result = num1 / num2;
                        break;
                }
                inputField.setText(String.valueOf(result));
            } catch (NumberFormatException ex) {
                inputField.setText("Error");
            }
        } else { // Operator (+, -, *, /)
            try {
                num1 = Double.parseDouble(inputField.getText());
                operator = command.charAt(0);
                inputField.setText("");
            } catch (NumberFormatException ex) {
                inputField.setText("Error");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JavaCalculator calc = new JavaCalculator();
            calc.setVisible(true);
        });
    }
}
