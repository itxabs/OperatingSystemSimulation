import javax.swing.*;
import java.awt.*;

public class ProcessManagement extends JFrame {
    ProcessManagement(){

        setTitle("Operating System Simulation - Process Management");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        JPanel sideBarPanel = new JPanel();
        sideBarPanel.setLayout(new GridLayout(9,1,2,3));

        String[] buttonLabels = {
                "Create a process",
                "Destroy a process",
                "Suspend a process",
                "Resume a process",
                "Block a process",
                "Wakeup a process",
                "Dispatch a process",
                "Change process priority",
                "Process communication with other"
        };
        createButtons(sideBarPanel, buttonLabels);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        add(sideBarPanel,BorderLayout.WEST);
        setVisible(true);
    }

    public static void createButtons(JPanel mainPanel, String[] buttonLabels) {
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFocusable(false);
            button.setOpaque(true);
            button.setBackground(Color.WHITE);
            button.setBorder(BorderFactory.createLineBorder(Color.GREEN,2,true));
            button.setFont(new Font("Poppins", Font.BOLD, 12));
            mainPanel.add(button);
        }
    }
}
