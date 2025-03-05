package JavaSwing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Exercise1 {

    private static JLabel numberLabel;
    private static int counter = 0;

    public static void main(String[] args) {

        JFrame frame = new JFrame("InCrease Or DeCrease");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(330, 250);

        frame.setLayout(new BorderLayout());

        numberLabel = new JLabel("0", SwingConstants.CENTER);
        numberLabel.setFont(new Font("Serif", Font.PLAIN, 30));
        numberLabel.setOpaque(true);
        numberLabel.setBackground(Color.YELLOW);
        numberLabel.setPreferredSize(new Dimension(100, 50));
        frame.add(numberLabel, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JButton increaseButton = new JButton("Increase (+)");
        JButton decreaseButton = new JButton("Decrease (-)");

        increaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                counter++;
                numberLabel.setText(Integer.toString(counter));
            }
        });

        decreaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                counter--;
                numberLabel.setText(Integer.toString(counter));
            }
        });

        panel.add(increaseButton);
        panel.add(decreaseButton);
        frame.add(panel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}
