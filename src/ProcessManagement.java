import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ProcessManagement extends JPanel {

    private Schedular schedular; // Reference to the Schedular class
    private int idCounter = 0;// For generating unique process IDs
    private int ArrivalTime = 0;
    private JTable processTable;
    private DefaultTableModel tableModel;

    public ProcessManagement(Schedular schedular) {
        this.schedular = schedular;

        JPanel sideBarPanel = new JPanel();
        sideBarPanel.setLayout(new GridLayout(9, 1, 0, 3));

        String[] buttonLabels = {
                "Create",
                "Destroy",
                "Suspend",
                "Resume",
                "Block",
                "Wakeup",
                "Dispatch",
                "Set Priority",
                "Back"
        };

        createButtons(sideBarPanel, buttonLabels);

        String[] columnNames = {"Process ID", "Priority", "Status", "Arrival Time", "Owner", "Memory", "IO Info"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

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
        setBorder(new EmptyBorder(20, 20, 20, 20));
        setBackground(new Color(35, 61, 77));
        add(sideBarPanel, BorderLayout.EAST);
        add(new JScrollPane(processTable), BorderLayout.CENTER);

        refreshTable();
    }

    private void createButtons(JPanel mainPanel, String[] buttonLabels) {
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFocusable(false);
            button.setOpaque(true);
            button.setBackground(new Color(254, 127, 45));
            button.setForeground(Color.WHITE);
            button.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2, true));
            button.setFont(new Font("Poppins", Font.BOLD, 13));
            button.setBorder(new EmptyBorder(0, 40, 0, 40));

            button.addActionListener(e -> handleButtonAction(label));
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    button.setBackground(new Color(252, 202, 70));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    button.setBackground(new Color(254, 127, 45));
                }
            });

            mainPanel.add(button);
        }
    }

    private void handleButtonAction(String label) {
        switch (label) {
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
            case "Set Priority":
                setPriority();
                break;
            case "Back":
                Back();
                break;
        }
    }

    private void createProcess() {
        String priorityInput = JOptionPane.showInputDialog(this, "Enter Priority for the Process:");
        String owner = JOptionPane.showInputDialog(this, "Enter owner of the process:");
        String memory = JOptionPane.showInputDialog(this, "Enter memory requirement:");
        String ioInfo = JOptionPane.showInputDialog(this, "Enter IO information:");

        try {
            if (priorityInput != null && !priorityInput.isEmpty() && owner != null && memory != null && ioInfo != null) {
                int priority = Integer.parseInt(priorityInput);
                PCB newProcess = new PCB(++idCounter, priority, "New", owner, memory, ioInfo, ++ArrivalTime);
                schedular.addToNewQueue(newProcess); // Now add PCB, not process
                refreshTable();
                JOptionPane.showMessageDialog(this, "Process created successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "All fields are required!");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input! Priority must be an integer.");
        }
    }

    private void suspendProcess() {
        int selectedRow = processTable.getSelectedRow();
        if (selectedRow != -1) {
            int processID = (int) processTable.getValueAt(selectedRow, 0);
            if (schedular.suspendProcess(processID)) {  // Now, directly call the suspendProcess method
                refreshTable();
                JOptionPane.showMessageDialog(this, "Process suspended successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Unable to suspend process!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select a process to suspend!");
        }
    }


    private void destroyProcess() {
        int selectedRow = processTable.getSelectedRow();
        if (selectedRow != -1) {
            int processID = (int) processTable.getValueAt(selectedRow, 0);
            System.out.println(schedular.processinqueues());
            if(schedular.destroyProcess(processID)){
                refreshTable();
                schedular.processinqueues();
                JOptionPane.showMessageDialog(this, "Process destroyed successfully!");
            }else {
                JOptionPane.showMessageDialog(this, "Please suspend the process first!!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select a process to destroy!");
        }
    }

    private void resumeProcess() {
        int selectedRow = processTable.getSelectedRow();
        if (selectedRow != -1) {
            int processID = (int) processTable.getValueAt(selectedRow, 0);
            System.out.println("Readyfirst: "+ schedular.processinqueuer());
            if (schedular.resumeProcess(processID)) { // Move to ready queue

                System.out.println("Readyafter: "+ schedular.processinqueuer());
                refreshTable();
                JOptionPane.showMessageDialog(this, "Process resumed successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Unable to resume process!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select a process to resume!");
        }
    }

    private void blockProcess() {
        int selectedRow = processTable.getSelectedRow();
        if (selectedRow != -1) {
            int processID = (int) processTable.getValueAt(selectedRow, 0);
            if (schedular.BlockProcess(processID)) { // Move to blocked queue
                refreshTable();
                JOptionPane.showMessageDialog(this, "Process blocked successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Unable to block process!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select a process to block!");
        }
    }

    private void wakeupProcess() {
        int selectedRow = processTable.getSelectedRow();
        if (selectedRow != -1) {
            int processID = (int) processTable.getValueAt(selectedRow, 0);
            if (schedular.WakeupProcess(processID)) { // Move from blocked to ready
                refreshTable();
                JOptionPane.showMessageDialog(this, "Process woken up successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Unable to wake up process!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select a process to wake up!");
        }
    }

    private void dispatchProcess(){
        String[] algorithms = {"FCFS", "Priority Scheduling"};

        String selectedAlgorithm = (String) JOptionPane.showInputDialog(
                null,
                "Select a Scheduling Algorithm:",
                "Scheduling Algorithm Selector",
                JOptionPane.QUESTION_MESSAGE,
                null,
                algorithms,
                algorithms[0]
        );


        if (selectedAlgorithm != null) {
            if ("FCFS".equals(selectedAlgorithm)) {
                    schedular.FCFS(this);

            } else if ("Priority Scheduling".equals(selectedAlgorithm)) {
                schedular.PriorityScheduling(this);

            }
        } else {
            JOptionPane.showMessageDialog(null, "No selection made.", "Warning", JOptionPane.WARNING_MESSAGE);
        }

    }

    private void setPriority() {
        int selectedRow = processTable.getSelectedRow();
        if (selectedRow != -1) {
            int processID = (int) processTable.getValueAt(selectedRow, 0);
            String newPriority = JOptionPane.showInputDialog(this, "Enter new priority for Process " + processID + ":");
            try {
                if (newPriority != null && !newPriority.isEmpty()) {
                    int priority = Integer.parseInt(newPriority);
                    if (schedular.updateProcessPriority(processID, priority)) { // Update process priority
                        refreshTable();
                        JOptionPane.showMessageDialog(this, "Priority updated successfully!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Unable to update priority!");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Priority is required!");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid input! Priority must be an integer.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select a process to set priority!");
        }
    }

    private void Back(){
        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(ProcessManagement.this);
        topFrame.getContentPane().removeAll();
        topFrame.add(new Main().getContentPane());
       // topFrame.setSize(900, 700);
        //topFrame.setLocationRelativeTo(null);
        topFrame.revalidate();
        topFrame.repaint();

    }

    public void refreshTable() {
        tableModel.setRowCount(0);
        for (PCB pcb : schedular.getAllProcesses()) {
            tableModel.addRow(new Object[]{
                    pcb.getProcessId(),
                    pcb.getPriority(),
                    pcb.getStatus(),
                    pcb.getArrivalTime(),
                    pcb.getOwnerOfProcess(),
                    pcb.getMemoryRequirement(),
                    pcb.getIoInformation()
            });
        }
    }
}
