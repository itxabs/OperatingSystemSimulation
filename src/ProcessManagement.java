import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.text.TableView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

public class ProcessManagement extends JPanel {

    private static LinkedList<process> processes = new LinkedList<>();
    int idCounter = 0;

    private JTable processTable;
    private DefaultTableModel tableModel;

    ProcessManagement() {


        JButton Back = new JButton("Back");
        add(Back, BorderLayout.NORTH);
        Back.setForeground(Color.WHITE);

        JPanel sideBarPanel = new JPanel();
        sideBarPanel.setLayout(new GridLayout(8, 1, 0, 3));

        String[] buttonLabels = {
                "Create",
                "Destroy",
                "Suspend",
                "Resume",
                "Block",
                "Wakeup",
                "Dispatch",
                "Set Priority"
        };

        createButtons(sideBarPanel, buttonLabels);

        String[] columnNames = {"Process ID", "Priority", "Status"};
        tableModel = new DefaultTableModel(columnNames, 0);

        processTable = new JTable(tableModel);
        processTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        processTable.setFillsViewportHeight(true);
        processTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        processTable.setFont(new Font("Monospaced", Font.PLAIN, 13));

        JTableHeader tableHeader = processTable.getTableHeader();
        tableHeader.setFont(new Font("Poppins", Font.BOLD, 16));
        tableHeader.setForeground(Color.WHITE);
        tableHeader.setBackground(new Color(54, 81, 94));
        tableHeader.setReorderingAllowed(false);



        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(20,20,20,20));
        setBackground(new Color(35, 61, 77));
        add(sideBarPanel, BorderLayout.EAST);
        add(new JScrollPane(processTable), BorderLayout.CENTER);

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
            button.setBorder(new EmptyBorder(0,40,0,40));

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    switch (label){
                        case "Create":
                            createProcess();
                            break;
                        case "Destroy":
                            destroyProcess();
                            break;
                        case "Suspend":
                            suspendProcess();
                            break;
                        case "Resume":
                            resumeProcess();
                            break;
                        case "Block":
                            blockProcess();
                             break;
                        case "Wakeup":
                            wakeupProcess();
                            break;
                        case "Dispatch":
                            dispatchProcess();
                            break;
                        case "SetPriority":
                            setPriority();
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

    private void setPriority() {
        JOptionPane.showMessageDialog(this, "Set Priority functionality is under development!");
    }

    private void dispatchProcess() {
        JOptionPane.showMessageDialog(this, "Dispatch functionality is under development!");
    }

    private void wakeupProcess() {
        JOptionPane.showMessageDialog(this, "Wakeup functionality is under development!");
    }

    private void blockProcess() {
        JOptionPane.showMessageDialog(this, "Block functionality is under development!");
    }

    private void resumeProcess() {
        JOptionPane.showMessageDialog(this, "Resume functionality is under development!");
    }

    private void suspendProcess() {
        JOptionPane.showMessageDialog(this, "Suspend functionality is under development!");
    }

    private void destroyProcess() {
        int selectedRow = processTable.getSelectedRow();
        if (selectedRow != -1) {
            processes.remove(selectedRow);
            tableModel.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a process to destroy!");
        }
    }

    private void createProcess() {

        int id = idCounter++;
        process p = new process(id, 1, "Ready");
        processes.add(p);

        tableModel.addRow(new Object[]{p.getProcessId(), p.getPriority(), p.getStatus()});


    }

}