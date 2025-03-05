package JavaSwing;
import javax.swing.*;
import java.awt.*;
public class Exercise2 extends JFrame {
    
    private JTextField fieldA, fieldB, fieldC;
    private JButton solveButton;
    
    public Exercise2() {
        super("Quadratic Equation Solver");
        setSize(350, 180);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        

        JPanel mainPanel = new JPanel(new BorderLayout(5, 5));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        inputPanel.add(new JLabel("Enter a:"));
        fieldA = new JTextField("1");
        inputPanel.add(fieldA);
        
        inputPanel.add(new JLabel("Enter b:"));
        fieldB = new JTextField("0");
        inputPanel.add(fieldB);
        
        inputPanel.add(new JLabel("Enter c:"));
        fieldC = new JTextField("0");
        inputPanel.add(fieldC);
        
        JLabel titleLabel = new JLabel("Solve: ax² + bx + c = 0", JLabel.CENTER);
        solveButton = new JButton("Solve");

        solveButton.addActionListener(e -> solveEquation());
        
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(inputPanel, BorderLayout.CENTER);
        mainPanel.add(solveButton, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }
    

    private void solveEquation() {
        try {
            double a = Double.parseDouble(fieldA.getText());
            double b = Double.parseDouble(fieldB.getText());
            double c = Double.parseDouble(fieldC.getText());
            if (a == 0) {
                JOptionPane.showMessageDialog(this, "Linear equation: x = " + (-c/b));
                return;
            }
            double discriminant = b * b - 4 * a * c;
            String result;
            
            if (discriminant > 0) {

                double x1 = (-b + Math.sqrt(discriminant)) / (2 * a);
                double x2 = (-b - Math.sqrt(discriminant)) / (2 * a);
                result = "Two roots: x₁ = " + x1 + ", x₂ = " + x2;
            } else if (discriminant == 0) {

                double x = -b / (2 * a);
                result = "One root: x = " + x;
            } else {

                double real = -b / (2 * a);
                double imag = Math.sqrt(-discriminant) / (2 * a);
                result = "Complex roots: x₁ = " + real + " + " + imag + "i, x₂ = " + real + " - " + imag + "i";
            }
            JOptionPane.showMessageDialog(this, result, "Solution", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Exercise2());
    }
}