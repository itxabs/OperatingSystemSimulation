import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalTime;
import java.util.LinkedList;



public class ProcessManagement extends JPanel {

    private static LinkedList<process> processes = new LinkedList<>();
    private static LinkedList<process> suspendedProcesses = new LinkedList<>();
    int idCounter = 0;
    int id;
    int priority;
    process p;
    int selectedRow;

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

        String[] columnNames = {"Process ID", "Priority","Status","Arrival Time"};
        tableModel = new DefaultTableModel(columnNames, 0){
            @Override
            public boolean isCellEditable(int row, int column){
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
                        case "Set Priority":
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

//    private void setPriority() {
//        selectedRow = processTable.getSelectedRow();
//        if (selectedRow != -1) {
//            String status = processTable.getValueAt(selectedRow, 2).toString();
//            if (!status.equals("Suspended")) {
//                JOptionPane.showMessageDialog(this, "Please suspend this process first!");
//            } else if (status.equals("Suspended")){
//                int getProcessID = (int) processTable.getValueAt(selectedRow, 0);
//
//                for (process suspendedProcess : suspendedProcesses) {
//                    if (suspendedProcess.getProcessId() == getProcessID) {
//                        try {
//
//                            String input = JOptionPane.showInputDialog(this,"Enter priority for process " + getProcessID);
//
//                            priority = Integer.parseInt(input);
//                            suspendedProcess.setPriority(priority);
//
//                            tableModel.setValueAt(priority, selectedRow, 1);
//                            JOptionPane.showMessageDialog(this,"Priority updated for process " + getProcessID);
//                            return;
//                        } catch (NumberFormatException e) {
//                            JOptionPane.showMessageDialog(this,"Invalid input! Please enter a numeric priority.");
//                        }
//                    }
//                }
//            }else{
//                JOptionPane.showMessageDialog(this,"Process is not suspended!");
//            }
//        } else {
//            JOptionPane.showMessageDialog(this, "Please select a process to set priority!");
//        }
//    }


    private void setPriority() {
        selectedRow = processTable.getSelectedRow();
        if (selectedRow != -1) {
            String status = processTable.getValueAt(selectedRow, 2).toString();
            if (!status.equals("Suspended")) {
                JOptionPane.showMessageDialog(this, "Please suspend this process first!");
            } else {
                int processID = (int) processTable.getValueAt(selectedRow, 0); // Get process ID

                // Find the suspended process and update priority
                for (process suspendedProcess : suspendedProcesses) {
                    if (suspendedProcess.getProcessId() == processID) {
                        try {
                            String priorityInput = JOptionPane.showInputDialog(
                                    this, "Enter new priority for process " + processID);
                            if (priorityInput == null || priorityInput.isEmpty()) {
                                JOptionPane.showMessageDialog(this, "Priority input is required!");
                                return;
                            }
                            priority = Integer.parseInt(priorityInput); // Validate integer input
                            suspendedProcess.setPriority(priority); // Update process priority

                            // Update table model
                            tableModel.setValueAt(priority, selectedRow, 1); // Priority is in column 1
                            JOptionPane.showMessageDialog(this, "Priority updated successfully!");
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(this, "Invalid input. Please enter an integer value!");
                        }
                        break;
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a process to set priority!");
        }
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
        selectedRow = processTable.getSelectedRow();
        if (selectedRow != -1) {

            String status = processTable.getValueAt(selectedRow, 2).toString();
            if (status.equals("Ready")) {
                JOptionPane.showMessageDialog(this, "The process is already ready!");
            } else if (status.equals("Suspended")) {
                int getProcessID = (int) processTable.getValueAt(selectedRow, 0);

                for (int i = 0; i < suspendedProcesses.size(); i++) {
                    process suspendedProcess = suspendedProcesses.get(i);
                    if (suspendedProcess.getProcessId() == getProcessID) {

                        suspendedProcess.setStatus("Ready");
                        processes.add(suspendedProcess);
                        suspendedProcesses.remove(i);

                        tableModel.setValueAt("Ready", selectedRow, 2);
                        break;
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a process to resume!");
        }
    }

    private void suspendProcess() {
        selectedRow = processTable.getSelectedRow();
        if(selectedRow !=-1){
            if (processTable.getValueAt(selectedRow,2)=="Suspended"){
                JOptionPane.showMessageDialog(this, "The process is already suspended!");

            }else{
                int getProcessID = (int) processTable.getValueAt(selectedRow,0);
                int priority = (int) processTable.getValueAt(selectedRow,1);
                LocalTime getArrivaltime = (LocalTime) processTable.getValueAt(selectedRow,3);
                p = new process(getProcessID,priority,"Suspended");
                p.setArrivalTime(getArrivaltime);
                suspendedProcesses.add(p);
                processes.remove(selectedRow);
                tableModel.removeRow(selectedRow);
                tableModel.addRow(new Object[]{getProcessID,priority,"Suspended",getArrivaltime});
            }

        }else {
            JOptionPane.showMessageDialog(this, "Select the process to suspend!");
        }
    }

    private void destroyProcess() {
        selectedRow = processTable.getSelectedRow();

        if (selectedRow != -1) {

            int processID = (int) processTable.getValueAt(selectedRow, 0);
            String status = processTable.getValueAt(selectedRow, 2).toString();

            if ("Suspended".equals(status)) {

                suspendedProcesses.removeIf(process -> process.getProcessId() == processID);
            } else {

                processes.removeIf(process -> process.getProcessId() == processID);
            }


            tableModel.removeRow(selectedRow);

        } else {
            JOptionPane.showMessageDialog(this, "Please select a process to destroy!");
        }
    }

    private void createProcess() {

        if(tableModel.getRowCount()<=10){
            id = idCounter++;
            priority = Integer.parseInt(JOptionPane.showInputDialog("Enter priority for process " + id));
            p = new process(id, priority, "New");
            processes.add(p);
            tableModel.addRow(new Object[]{p.getProcessId(), p.getPriority(), p.getStatus(),p.getArrivalTime()});
        }else {
            JOptionPane.showMessageDialog(this, "Please select a process to destroy!");
        }

    }

}