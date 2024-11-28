import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main extends JFrame {

    Main(){

        setTitle("Operating System Simulation");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(35, 61, 77));
        setLocationRelativeTo(null);

        JLabel ProjectName = new JLabel("Operating System Simulation - Digital Den");
        ProjectName.setHorizontalAlignment(SwingConstants.CENTER);
        ProjectName.setFont(new Font("Monospaced", Font.BOLD, 30));
        ProjectName.setBorder(new EmptyBorder(20, 0, 20, 0));
        ProjectName.setForeground(Color.WHITE);

        JPanel mainPanel = new JPanel(new GridLayout(2,2,20,20));
        mainPanel.setBorder(new EmptyBorder(50,50,50,50));
        mainPanel.setBackground(new Color(35, 61, 77));

        String[] buttonLabels = {
                "Process Management",
                "Memory Management",
                "I/O Management",
                "Other Operations"
        };

        createButtons(mainPanel, buttonLabels);

        add(ProjectName, BorderLayout.NORTH);
        add(mainPanel);

    }

    public void createButtons(JPanel mainPanel, String[] buttonLabels) {
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFocusable(false);
            button.setOpaque(true);
            button.setBackground(new Color(254, 127, 45));
            button.setForeground(Color.WHITE);
            button.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
            button.setFont(new Font("Popins", Font.BOLD, 16));

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    switch (label){
                        case "Process Management":
                            processManagement();
                            break;
                        case "Memory Management":
                            memoryManagement();
                            break;
                        case "I/O Management":
                            ioManagement();
                            break;
                        case "Other Operations":
                            otherOperation();
                            break;

                    }

                }

            });

            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    button.setBackground(new Color(252, 202, 70));
                    button.setForeground(Color.WHITE);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    button.setBackground(new Color(254, 127, 45));
                    button.setForeground(Color.WHITE);
                }

            });

            mainPanel.add(button);
        }
    }

    private void otherOperation() {
    }

    private void ioManagement() {
        
    }

    private void memoryManagement() {
        
    }

    private void processManagement() {
        ProcessManagement processManagement = new ProcessManagement();
        getContentPane().removeAll();
        getContentPane().add(processManagement);
        getContentPane().revalidate();
    }


    public static void main(String[] args) {
        new Main().setVisible(true);
    }
}


//https://github.com/romainguy/filthy-rich-clients

//https://coolors.co/abe188-2a6041-6c698d-aa968a-e15554