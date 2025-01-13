//import javax.swing.*;
//import javax.swing.border.EmptyBorder;
//import javax.swing.table.DefaultTableModel;
//import javax.swing.table.JTableHeader;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//import java.time.LocalTime;
//import java.util.LinkedList;
//
//public class ProcessManagement extends JPanel {
//
//    private static LinkedList<process> processes = new LinkedList<>();
//    private static LinkedList<process> suspendedProcesses = new LinkedList<>();
//    public Schedular schedular = new Schedular();
//
//    int idCounter = 0;
//    int id;
//    String memoryRequirement;
//    int priority;
//    process p;
//    PCB pcb;
//    int selectedRow;
//
//    private JTable processTable;
//    private DefaultTableModel tableModel;
//
//    ProcessManagement() {
//
//        JPanel sideBarPanel = new JPanel();
//        sideBarPanel.setLayout(new GridLayout(9, 1, 0, 3));
//
//        String[] buttonLabels = {
//                "Create",
//                "Destroy",
//                "Suspend",
//                "Resume",
//                "Block",
//                "Wakeup",
//                "Dispatch",
//                "Set Priority",
//                "Back"
//        };
//
//        createButtons(sideBarPanel, buttonLabels);
//
//        String[] columnNames = {"Process ID", "Priority","Status","Arrival Time"};
//        tableModel = new DefaultTableModel(columnNames, 0){
//            @Override
//            public boolean isCellEditable(int row, int column){
//                return false;
//            }
//        };
//
//        processTable = new JTable(tableModel);
//        processTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
//        processTable.setFillsViewportHeight(true);
//        processTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//        processTable.setFont(new Font("Monospaced", Font.PLAIN, 13));
//
//        JTableHeader tableHeader = processTable.getTableHeader();
//        tableHeader.setFont(new Font("Poppins", Font.BOLD, 16));
//        tableHeader.setForeground(Color.WHITE);
//        tableHeader.setBackground(new Color(54, 81, 94));
//        tableHeader.setReorderingAllowed(false);
//
//        setLayout(new BorderLayout());
//        setBorder(new EmptyBorder(20,20,20,20));
//        setBackground(new Color(35, 61, 77));
//        add(sideBarPanel, BorderLayout.EAST);
//        add(new JScrollPane(processTable), BorderLayout.CENTER);
//
//
//    }
//
//    public void createButtons(JPanel mainPanel, String[] buttonLabels) {
//        for (String label : buttonLabels) {
//            JButton button = new JButton(label);
//            button.setFocusable(false);
//            button.setOpaque(true);
//            button.setBackground(new Color(254, 127, 45));
//            button.setForeground(Color.WHITE);
//            button.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2, true));
//            button.setFont(new Font("Poppins", Font.BOLD, 13));
//            button.setBorder(new EmptyBorder(0,40,0,40));
//
//            button.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    switch (label){
//                        case "Create":
//                            createProcess();
//                            break;
//                        case "Destroy":
//                            destroyProcess();
//                            break;
//                        case "Suspend":
//                            suspendProcess();
//                            break;
//                        case "Resume":
//                            resumeProcess();
//                            break;
//                        case "Block":
//                            blockProcess();
//                             break;
//                        case "Wakeup":
//                            wakeupProcess();
//                            break;
//                        case "Dispatch":
//                            dispatchProcess();
//                            break;
//                        case "Set Priority":
//                            setPriority();
//                            break;
//                        case "Back":
//                            Back();
//                            break;
//
//                    }
//                }
//            });
//
//            button.addMouseListener(new MouseAdapter() {
//                @Override
//                public void mouseEntered(MouseEvent e) {
//                    button.setBackground(new Color(252, 202, 70));
//                    button.setForeground(Color.WHITE);
//                }
//
//                @Override
//                public void mouseExited(MouseEvent e) {
//                    button.setBackground(new Color(254, 127, 45));
//                    button.setForeground(Color.WHITE);
//                }
//
//            });
//
//            mainPanel.add(button);
//        }
//    }
//
//
//    private void setPriority() {
//        selectedRow = processTable.getSelectedRow();
//        if (selectedRow != -1) {
//            String status = processTable.getValueAt(selectedRow, 2).toString();
//            if (!status.equals("Suspended")) {
//                JOptionPane.showMessageDialog(this, "Please suspend this process first!");
//            } else {
//                int processID = (int) processTable.getValueAt(selectedRow, 0); // Get process ID
//
//                for (process suspendedProcess : suspendedProcesses) {
//                    if (suspendedProcess.getProcessId() == processID) {
//                        try {
//                            String priorityInput = JOptionPane.showInputDialog(
//                                    this, "Enter new priority for process " + processID);
//                            if (priorityInput == null || priorityInput.isEmpty()) {
//                                JOptionPane.showMessageDialog(this, "Priority input is required!");
//                                return;
//                            }
//                            priority = Integer.parseInt(priorityInput); // Validate integer input
//                            suspendedProcess.setPriority(priority); // Update process priority
//
//                            tableModel.setValueAt(priority, selectedRow, 1);
//                            JOptionPane.showMessageDialog(this, "Priority updated successfully!");
//                        } catch (NumberFormatException ex) {
//                            JOptionPane.showMessageDialog(this, "Invalid input. Please enter an integer value!");
//                        }
//                        break;
//                    }
//                }
//            }
//        } else {
//            JOptionPane.showMessageDialog(this, "Please select a process to set priority!");
//        }
//    }
//
//
//
//    private void dispatchProcess() {
//        JOptionPane.showMessageDialog(this, "Dispatch functionality is under development!");
//    }
//
//    private void wakeupProcess() {
//        JOptionPane.showMessageDialog(this, "Wakeup functionality is under development!");
//    }
//
//    private void blockProcess() {
//        selectedRow = processTable.getSelectedRow();
//
//        if(selectedRow !=-1){
//
//        }
//
//        JOptionPane.showMessageDialog(this, "Block functionality is under development!");
//    }
//
//    private void resumeProcess() {
//        selectedRow = processTable.getSelectedRow();
//        if (selectedRow != -1) {
//
//            String status = processTable.getValueAt(selectedRow, 2).toString();
//            if (status.equals("Ready")) {
//                JOptionPane.showMessageDialog(this, "The process is already ready!");
//            } else if (status.equals("Suspended")) {
//                int getProcessID = (int) processTable.getValueAt(selectedRow, 0);
//
//                for (int i = 0; i < suspendedProcesses.size(); i++) {
//                    process suspendedProcess = suspendedProcesses.get(i);
//                    if (suspendedProcess.getProcessId() == getProcessID) {
//
//                        suspendedProcess.setStatus("Ready");
//                        processes.add(suspendedProcess);
//                        suspendedProcesses.remove(i);
//
//                        tableModel.setValueAt("Ready", selectedRow, 2);
//                        break;
//                    }
//                }
//            }
//        } else {
//            JOptionPane.showMessageDialog(this, "Please select a process to resume!");
//        }
//    }
//
//    private void suspendProcess() {
//        selectedRow = processTable.getSelectedRow();
//        if(selectedRow !=-1){
//            if (processTable.getValueAt(selectedRow,2)=="Suspended"){
//                JOptionPane.showMessageDialog(this, "The process is already suspended!");
//
//            }else{
//                id = (int) processTable.getValueAt(selectedRow,0);
//                priority = (int) processTable.getValueAt(selectedRow,1);
//                LocalTime getArrivaltime = (LocalTime) processTable.getValueAt(selectedRow,3);
//                schedular.suspendProcess(id);
//                tableModel.removeRow(selectedRow);
//                tableModel.addRow(new Object[]{id,priority,"Suspended",getArrivaltime});
//            }
//
//        }else {
//            JOptionPane.showMessageDialog(this, "Select the process to suspend!");
//        }
//    }
//
//    private void destroyProcess() {
//        selectedRow = processTable.getSelectedRow();
//
//        if (selectedRow != -1) {
//
//            id = (int) processTable.getValueAt(selectedRow, 0);
//            schedular.terminateProcess(pcb,id);
//            tableModel.removeRow(selectedRow);
//
//        } else {
//            JOptionPane.showMessageDialog(this, "Please select a process to destroy!");
//        }
//    }
//
//    private void createProcess() {
//
//        if(tableModel.getRowCount()<=10){
//            id = idCounter++;
//            priority = Integer.parseInt(JOptionPane.showInputDialog("Enter priority for process " + id));
//            memoryRequirement = JOptionPane.showInputDialog("Enter the Memory Requirement" + id);
//            pcb = new PCB(id,priority,"New","Abdul",memoryRequirement,"NO");
//            schedular.addToNewQueue(pcb);
//            tableModel.addRow(new Object[]{pcb.getProcessId(), pcb.getPriority(), pcb.getStatus(),pcb.getArrivalTime()});
//        }else {
//            JOptionPane.showMessageDialog(this, "Please select a process to destroy!");
//        }
//
//    }
//
//    private void Back(){
//        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(ProcessManagement.this);
//        topFrame.getContentPane().removeAll();
//        topFrame.add(new Main().getContentPane());
//       // topFrame.setSize(900, 700);
//        //topFrame.setLocationRelativeTo(null);
//        topFrame.revalidate();
//        topFrame.repaint();
//
//    }
//
//}



import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ProcessManagement extends JPanel {

    private Schedular schedular; // Reference to the Schedular class
    private int idCounter = 0;   // For generating unique process IDs
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

        refreshTable(); // Populate the table initially
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
                //dispatchProcess();
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
                PCB newProcess = new PCB(++idCounter, priority, "Ready", owner, memory, ioInfo);
                schedular.addToReadyQueue(newProcess); // Now add PCB, not process
                refreshTable();
                JOptionPane.showMessageDialog(this, "Process created successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "All fields are required!");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input! Priority must be an integer.");
        }
    }


    private void destroyProcess() {
        int selectedRow = processTable.getSelectedRow();
        if (selectedRow != -1) {
            int processID = (int) processTable.getValueAt(selectedRow, 0);
            schedular.removeProcess(processID); // Remove from any queue
            refreshTable();
            JOptionPane.showMessageDialog(this, "Process destroyed successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Select a process to destroy!");
        }
    }

    private void suspendProcess() {
        int selectedRow = processTable.getSelectedRow();
        if (selectedRow != -1) {
            int processID = (int) processTable.getValueAt(selectedRow, 0);
            if (schedular.updateProcessStatus(processID, "Suspended")) { // Move to suspended queue
                refreshTable();
                JOptionPane.showMessageDialog(this, "Process suspended successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Unable to suspend process!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select a process to suspend!");
        }
    }

    private void resumeProcess() {
        int selectedRow = processTable.getSelectedRow();
        if (selectedRow != -1) {
            int processID = (int) processTable.getValueAt(selectedRow, 0);
            if (schedular.updateProcessStatus(processID, "Ready")) { // Move to ready queue
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
            if (schedular.updateProcessStatus(processID, "Blocked")) { // Move to blocked queue
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
            if (schedular.updateProcessStatus(processID, "Ready")) { // Move from blocked to ready
                refreshTable();
                JOptionPane.showMessageDialog(this, "Process woken up successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Unable to wake up process!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select a process to wake up!");
        }
    }

//    private void dispatchProcess() {
//        PCB nextProcess = schedular.dispatch(); // Fetch the next PCB to execute
//        if (nextProcess != null) {
//            JOptionPane.showMessageDialog(this, "Dispatched Process: " + nextProcess);
//            refreshTable();
//        } else {
//            JOptionPane.showMessageDialog(this, "No process available to dispatch!");
//        }
//    }


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

    private void refreshTable() {
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
