import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MemoryManagement extends JPanel {

    // Reference to the MemoryManagement class
    private Schedular schedular;
    private JTable memoryTable;
    private DefaultTableModel tableModel;

    public MemoryManagement(Schedular schedular) {
        this.schedular = schedular;

        JPanel sideBarPanel = new JPanel();
        sideBarPanel.setLayout(new GridLayout(5, 1, 0, 3));

        String[] buttonLabels = {
                "Allocate Memory",
                "Deallocate Memory",
                "Compact Memory",
                "View Memory",
                "Back"
        };

        createButtons(sideBarPanel, buttonLabels);

        String[] columnNames = {"Block ID", "Size", "Status", "Process ID"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        memoryTable = new JTable(tableModel);
        memoryTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        memoryTable.setFillsViewportHeight(true);
        memoryTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        memoryTable.setFont(new Font("Monospaced", Font.PLAIN, 13));

        JTableHeader tableHeader = memoryTable.getTableHeader();
        tableHeader.setFont(new Font("Poppins", Font.BOLD, 16));
        tableHeader.setForeground(Color.WHITE);
        tableHeader.setBackground(new Color(54, 81, 94));
        tableHeader.setReorderingAllowed(false);

        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(20, 20, 20, 20));
        setBackground(new Color(35, 61, 77));
        add(sideBarPanel, BorderLayout.EAST);
        add(new JScrollPane(memoryTable), BorderLayout.CENTER);

        //refreshTable(); // Populate the table initially
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
            case "Allocate Memory":
                //allocateMemory();
                break;
            case "Deallocate Memory":
                //deallocateMemory();
                break;
            case "Compact Memory":
                //compactMemory();
                break;
            case "View Memory":
                //refreshTable();
                break;
            case "Back":
                back();
                break;
        }
    }

//    private void allocateMemory() {
//        String blockSizeInput = JOptionPane.showInputDialog(this, "Enter Block Size to Allocate:");
//        String processIdInput = JOptionPane.showInputDialog(this, "Enter Process ID:");
//
//        try {
//            if (blockSizeInput != null && processIdInput != null && !blockSizeInput.isEmpty() && !processIdInput.isEmpty()) {
//                int blockSize = Integer.parseInt(blockSizeInput);
//                int processId = Integer.parseInt(processIdInput);
//                if (memoryManagement.allocateMemory(blockSize, processId)) {
//                    refreshTable();
//                    JOptionPane.showMessageDialog(this, "Memory allocated successfully!");
//                } else {
//                    JOptionPane.showMessageDialog(this, "Failed to allocate memory!");
//                }
//            } else {
//                JOptionPane.showMessageDialog(this, "All fields are required!");
//            }
//        } catch (NumberFormatException e) {
//            JOptionPane.showMessageDialog(this, "Invalid input! Block size and Process ID must be integers.");
//        }
//    }

//    private void deallocateMemory() {
//        int selectedRow = memoryTable.getSelectedRow();
//        if (selectedRow != -1) {
//            int blockId = (int) memoryTable.getValueAt(selectedRow, 0);
//            if (memoryManagement.deallocateMemory(blockId)) {
//                refreshTable();
//                JOptionPane.showMessageDialog(this, "Memory deallocated successfully!");
//            } else {
//                JOptionPane.showMessageDialog(this, "Failed to deallocate memory!");
//            }
//        } else {
//            JOptionPane.showMessageDialog(this, "Select a memory block to deallocate!");
//        }
//    }

//    private void compactMemory() {
//        memoryManagement.compactMemory();
//        refreshTable();
//        JOptionPane.showMessageDialog(this, "Memory compacted successfully!");
//    }

    private void back() {
        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(MemoryManagement.this);
        topFrame.getContentPane().removeAll();
        topFrame.add(new Main().getContentPane());
        topFrame.revalidate();
        topFrame.repaint();
    }

//    private void refreshTable() {
//        tableModel.setRowCount(0);
//        for (MemoryBlock block : memoryManagement.getMemoryBlocks()) {
//            tableModel.addRow(new Object[]{
//                    block.getBlockId(),
//                    block.getSize(),
//                    block.getStatus(),
//                    block.getProcessId()
//            });
//        }
//    }
}
