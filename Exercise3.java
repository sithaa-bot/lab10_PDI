package JavaSwing;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Exercise3 {
    public static void main(String[] args) {
        ImageIcon icon = new ImageIcon("itc.png");
        JFrame frame = new JFrame("Student List");
        frame.setIconImage(icon.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(null); 
        frame.setLocationRelativeTo(null);

        String[] columnNames = {"ID", "Name", "Age", "Major"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 20, 550, 300);
        frame.add(scrollPane);

        try (BufferedReader br = new BufferedReader(new FileReader("info.csv"))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) { 
                    isFirstLine = false;
                    continue;
                }
                String[] data = line.split(",");
                model.addRow(data);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "Error reading file", "Error", JOptionPane.ERROR_MESSAGE);
        }

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String studentDetails = "ID: " + model.getValueAt(selectedRow, 0) + "\n" +
                                            "Name: " + model.getValueAt(selectedRow, 1) + "\n" +
                                            "Age: " + model.getValueAt(selectedRow, 2) + "\n" +
                                            "Major: " + model.getValueAt(selectedRow, 3);
                    JOptionPane.showMessageDialog(frame, studentDetails, "Student Details", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        frame.setVisible(true);
    }
}