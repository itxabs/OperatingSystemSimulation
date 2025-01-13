import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MemoryManagement extends JPanel {

    MemoryManagement() {

        JPanel sideBarPanel = new JPanel();
        sideBarPanel.setLayout(new GridLayout(8, 1, 0, 3));

        String[] buttonLabels = {

                "Set Page Size",
        };


        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(20, 20, 20, 20));
        setBackground(new Color(35, 61, 77));

        add(sideBarPanel, BorderLayout.EAST);
        setVisible(true);
    }

    public void createButtons(JPanel mainPanel, String[] buttonLabels) {
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFocusable(false);
            button.setOpaque(true);
            button.setBackground(new Color(254, 127, 45));
            button.setForeground(Color.WHITE);
            button.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2, true));
            button.setFont(new Font("Poppins", Font.BOLD, 13));
            button.setBorder(new EmptyBorder(0, 40, 0, 40));

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    switch (label) {

                        case "Set Page Size":
                            setPageSize();
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

        }
    }
    private void setPageSize(){

    }
}
