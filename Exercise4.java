package JavaSwing;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Exercise4 {
    public static void main(String[] args) {
        ImageIcon icon = new ImageIcon("itc.png");
        JFrame frame = new JFrame("Student List");
        frame.setIconImage(icon.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null); 

        String[] columnNames = {"ID", "Name", "Age", "Major"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 20, 650, 250);
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

        JLabel idLabel = new JLabel("ID:");
        idLabel.setBounds(20, 280, 100, 25);
        frame.add(idLabel);

        JTextField idField = new JTextField();
        idField.setBounds(120, 280, 200, 25);
        frame.add(idField);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20, 310, 100, 25);
        frame.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(120, 310, 200, 25);
        frame.add(nameField);

        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setBounds(20, 340, 100, 25);
        frame.add(ageLabel);

        JTextField ageField = new JTextField();
        ageField.setBounds(120, 340, 200, 25);
        frame.add(ageField);

        JLabel majorLabel = new JLabel("Major:");
        majorLabel.setBounds(20, 370, 100, 25);
        frame.add(majorLabel);

        JTextField majorField = new JTextField();
        majorField.setBounds(120, 370, 200, 25);
        frame.add(majorField);

        JButton addButton = new JButton("Add Student");
        addButton.setBounds(120, 410, 150, 30);
        frame.add(addButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String name = nameField.getText();
                String age = ageField.getText();
                String major = majorField.getText();

                if (id.isEmpty() || name.isEmpty() || age.isEmpty() || major.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    model.addRow(new Object[]{id, name, age, major});

                    try (FileWriter fw = new FileWriter("students.csv", true)) {
                        fw.write("\n" + id + "," + name + "," + age + "," + major);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(frame, "Error saving student to file", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                    idField.setText("");
                    nameField.setText("");
                    ageField.setText("");
                    majorField.setText("");
                }
            }
        });

        frame.setVisible(true);
    }
}