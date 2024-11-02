import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {

    Main(){

        setTitle("Operating System Simulation");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        JPanel mainPanel = new JPanel(new GridLayout(2,2,20,20));
        mainPanel.setBorder(new EmptyBorder(20,20,20,20));

        String[] buttonLabels = {
                "Process management",
                "Memory management",
                "I/O management",
                "Other operations management"
        };

        createButtons(mainPanel, buttonLabels);

        add(mainPanel);
        setVisible(true);

    }

    public void createButtons(JPanel mainPanel, String[] buttonLabels) {
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFocusable(false);
            button.setOpaque(true);
            button.setBackground(Color.WHITE);
            button.setBorder(BorderFactory.createLineBorder(Color.GREEN,3,true));
            button.setFont(new Font("Popins", Font.BOLD, 16));


            button.addActionListener(new ActionListener() {
                @Override
                    public void actionPerformed(ActionEvent e) {
                    switch (label){
                        case "Process management":
                            processManagement();
                            break;
                        case "Memory management":
                            memoryManagement();
                            break;
                        case "I/O management":
                            ioManagement();
                            break;
                        case "Other operations management":
                            otherOperationManegment();
                            break;

                    }

                }

            });
            mainPanel.add(button);
        }
    }

    private void otherOperationManegment() {
    }

    private void ioManagement() {
        
    }

    private void memoryManagement() {
        
    }

    private void processManagement() {
        ProcessManagement processManagement = new ProcessManagement();
    }


    public static void main(String[] args) {
        new Main();
    }
}