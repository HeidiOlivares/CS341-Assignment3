package salesapp;

import javax.swing.*;
import java.awt.*;

/**
 * Controller: builds the window, wires up events, and owns the SalesSlip.
 */
public class Main {
    private final SalesSlip slip = new SalesSlip();

    private final JTextField nameField = new JTextField(12);
    private final JTextField costField = new JTextField(6);
    private final JTextField qtyField = new JTextField(4);
    private final JTextArea listArea = new JTextArea(10, 40);
    private final JLabel totalLabel = new JLabel("Total Sales: $0.00");
    private final JLabel messageLabel = new JLabel(" ");

    private void buildWindow() {
        JFrame frame = new JFrame("Sales List");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(8, 8));

        // Input row
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        inputPanel.add(new JLabel("Item:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Cost ($):"));
        inputPanel.add(costField);
        inputPanel.add(new JLabel("Qty:"));
        inputPanel.add(qtyField);
        JButton addButton = new JButton("Add Item");
        JButton clearButton = new JButton("Clear List");
        inputPanel.add(addButton);
        inputPanel.add(clearButton);

        // Item list
        listArea.setEditable(false);
        listArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
        listArea.setText(slip.toString());

        // Footer
        JPanel footer = new JPanel(new GridLayout(2, 1));
        totalLabel.setFont(totalLabel.getFont().deriveFont(Font.BOLD, 16f));
        messageLabel.setForeground(Color.RED);
        footer.add(totalLabel);
        footer.add(messageLabel);

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(new JScrollPane(listArea), BorderLayout.CENTER);
        frame.add(footer, BorderLayout.SOUTH);

        // Events
        addButton.addActionListener(e -> addItem());
        qtyField.addActionListener(e -> addItem());
        clearButton.addActionListener(e -> {
            slip.clear();
            refresh();
            messageLabel.setText(" ");
        });

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void addItem() {
        try {
            String name = nameField.getText();
            double cost = Double.parseDouble(costField.getText().trim());
            int qty = Integer.parseInt(qtyField.getText().trim());
            slip.addItem(name, cost, qty);   // validates inside SalesItem
            refresh();
            messageLabel.setText(" ");
            nameField.setText("");
            costField.setText("");
            qtyField.setText("");
            nameField.requestFocus();
        } catch (NumberFormatException ex) {
            messageLabel.setText("Cost must be a number and quantity a whole number.");
        } catch (IllegalArgumentException ex) {
            messageLabel.setText(ex.getMessage());
        }
    }

    private void refresh() {
        listArea.setText(slip.toString());
        totalLabel.setText(String.format("Total Sales: $%.2f", slip.getTotalSales()));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().buildWindow());
    }
}