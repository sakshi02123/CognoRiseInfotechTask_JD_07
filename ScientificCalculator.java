import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//Task-07
public class ScientificCalculator extends JFrame implements ActionListener {
    private JTextField displayField;

    public ScientificCalculator() {
        setTitle("Scientific Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());

        displayField = new JTextField();
        displayField.setEditable(false);
        panel.add(displayField, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(5, 4, 5, 5));

        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+",
                "C", "sqrt", "exp", "del"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        panel.add(buttonPanel, BorderLayout.CENTER);
        add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "=":
                evaluateExpression();
                break;
            case "C":
                clearDisplay();
                break;
            case "del":
                deleteLastDigit();
                break;
            case "sqrt":
                calculateSquareRoot();
                break;
            case "exp":
                calculateExponential();
                break;
            default:
                addToDisplay(command);
                break;
        }
    }

    private void addToDisplay(String input) {
        displayField.setText(displayField.getText() + input);
    }

    private void clearDisplay() {
        displayField.setText("");
    }

    private void deleteLastDigit() {
        String currentText = displayField.getText();
        if (!currentText.isEmpty()) {
            displayField.setText(currentText.substring(0, currentText.length() - 1));
        }
    }

    private void evaluateExpression() {
        String expression = displayField.getText();
        try {
            double result = evaluate(expression);
            displayField.setText(Double.toString(result));
        } catch (Exception e) {
            displayField.setText("Error");
        }
    }

    private double evaluate(String expression) {
        // Implement the logic to evaluate the expression here
        // This is a simplified version, you may need to use a more complex expression evaluator
        return Double.parseDouble(expression);
    }

    private void calculateSquareRoot() {
        try {
            double value = Double.parseDouble(displayField.getText());
            double result = Math.sqrt(value);
            displayField.setText(Double.toString(result));
        } catch (NumberFormatException e) {
            displayField.setText("Error");
        }
    }

    private void calculateExponential() {
        try {
            double value = Double.parseDouble(displayField.getText());
            double result = Math.exp(value);
            displayField.setText(Double.toString(result));
        } catch (NumberFormatException e) {
            displayField.setText("Error");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ScientificCalculator calculator = new ScientificCalculator();
            calculator.setVisible(true);
        });
    }
}