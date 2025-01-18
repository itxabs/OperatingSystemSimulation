
import java.util.*;

public class Schedular extends AbstractSchedular {

    private Queue<PCB> readyQueue;
    private Queue<PCB> blockedQueue;
    private Queue<PCB> suspendedQueue;
    private Queue<PCB> newQueue;

    public Schedular() {
        this.readyQueue = new LinkedList<>();
        this.blockedQueue = new LinkedList<>();
        this.suspendedQueue = new LinkedList<>();
        this.newQueue = new LinkedList<>();
    }


    @Override
    public boolean suspendProcess(int id) {

        PCB targetProcess = null;

        if (readyQueue.stream().anyMatch(p -> p.getProcessId() == id)) {
            targetProcess = findAndRemoveProcess(newQueue, id);
        }
        if (blockedQueue.stream().anyMatch(p -> p.getProcessId() == id)) {
            targetProcess = findAndRemoveProcess(blockedQueue, id);
        }
        if (newQueue.stream().anyMatch(p -> p.getProcessId() == id)) {
            targetProcess = findAndRemoveProcess(newQueue, id);
        }

        if (targetProcess != null) {
            targetProcess.setStatus("Suspended");  // Update status
            suspendedQueue.add(targetProcess);  // Move to suspended queue
            return true;
        }
        return false;  // If the process was not found, return false
    }

    @Override
    public boolean resumeProcess(int  id) {

        PCB targetProcess = null;

        if (suspendedQueue.stream().anyMatch(p -> p.getProcessId() == id)) {
            targetProcess = findAndRemoveProcess(suspendedQueue, id);
        }

        if (targetProcess != null) {
            targetProcess.setStatus("Ready");
            readyQueue.add(targetProcess);
            System.out.println("Added to Ready Queue: " + targetProcess);
            return true;
        }
        return false;
    }


    @Override
    public void dispatchNextProcess() {
        if (!readyQueue.isEmpty()) {
            PCB nextProcess = readyQueue.poll();
            nextProcess.setStatus("Running");
            System.out.println("Dispatched Process: " + nextProcess.getProcessId());
        } else {
            System.out.println("No process to dispatch!");
        }
    }

    @Override
    public void addToNewQueue(PCB p) {
        newQueue.add(p);
    }

    public void moveToBlocked(PCB p) {
        PCB targetProcess = findAndRemoveProcess(readyQueue, p.getProcessId());
        if (targetProcess != null) {
            targetProcess.setStatus("Blocked");
            blockedQueue.add(targetProcess);
            System.out.println(getAllProcesses());
        }
    }

    @Override
    public boolean BlockProcess(int id) {

        PCB targetProcess = null;

        if (readyQueue.stream().anyMatch(p -> p.getProcessId() == id)) {
            targetProcess = findAndRemoveProcess(readyQueue, id);
        }

        if (targetProcess != null) {
            targetProcess.setStatus("Blocked");
            blockedQueue.add(targetProcess);

            System.out.println("Process " + id + " is now Blocked.");
            return true;
        }else {
            System.out.println("Process " + id + " is not Blocked.");
        }
        return false;

    }

    @Override
    public boolean WakeupProcess(int id){

        PCB targetProcess = null;

        if (blockedQueue.stream().anyMatch(p -> p.getProcessId() == id)) {
            targetProcess = findAndRemoveProcess(blockedQueue, id);
        }

        if (targetProcess != null) {
            targetProcess.setStatus("Ready");
            readyQueue.add(targetProcess);

            System.out.println("Process " + id + " is now ready to be executed.");
            return true;
        } else {
            System.out.println("Process with ID " + id + " not found in Blocked Queue.");
        }
        return false;
    }

    public void terminateProcess(PCB p, int id) {
        if ("Suspended".equals(p.getStatus())) {
            suspendedQueue.removeIf(q -> q.getProcessId() == id);
        } else {
            readyQueue.removeIf(q -> q.getProcessId() == id);
        }
    }

    public boolean destroyProcess(int processID) {

        if (removeFromQueue(suspendedQueue, processID)) {
            System.out.println("Process " + processID + " removed successfully.");
            return true;
        }
        else {

            System.out.println("Process " + processID + " not found.");
        }
        return false;
    }

    public boolean updateProcessStatus(int processID, String newStatus) {
        process targetProcess = findProcess(processID);
        if (targetProcess != null) {
            targetProcess.setStatus(newStatus);
            return true;
        }
        return false;
    }

    public boolean updateProcessPriority(int processID, int newPriority) {
        process targetProcess = findProcess(processID);
        if (targetProcess != null) {
            targetProcess.setPriority(newPriority);
            return true;
        }
        return false;
    }

    public void FCFS(ProcessManagement processManagement) {
        // Step 1: Sort the processes based on arrival time
        List<PCB> sortedProcesses = new ArrayList<>(readyQueue);
        sortedProcesses.sort(Comparator.comparingInt(PCB::getArrivalTime)); // Sort by arrival time

        // Step 2: Clear the ready queue
        readyQueue.clear();

        // Step 3: Add sorted processes back to the ready queue
        for (PCB process : sortedProcesses) {
            process.setStatus("Ready"); // Set the status to Ready
            readyQueue.add(process); // Add to readyQueue
            processManagement.refreshTable(); // Refresh the table after updating status
        }

        // Step 4: Process each PCB in the ready queue
        while (!readyQueue.isEmpty()) {
            PCB process = readyQueue.poll(); // Get and remove the first process in the queue

            // Set the process status to "Running"
            process.setStatus("Running");
            System.out.println("Process " + process.getProcessId() + " is running.");
            processManagement.refreshTable(); // Refresh the table to reflect the "Running" state

            // Simulate process execution
            try {
                Thread.sleep(1000); // Simulate execution time
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Set the process status to "Finished"
            process.setStatus("Finished");
            System.out.println("Process " + process.getProcessId() + " is finished.");
            processManagement.refreshTable(); // Refresh the table to reflect the "Finished" state

            // Remove the process from the queue (already removed using poll)
        }
    }


    public void PriorityScheduling(ProcessManagement processManagement) {

        List<PCB> sortedList = new ArrayList<>(readyQueue);

        sortedList.sort(Comparator.comparingInt(PCB::getPriority).reversed());


        readyQueue.clear();

        System.out.println("readyQueue is empty");
        //System.out.println(readyQueue.poll().getProcessId());

        for (PCB process : sortedList) {
            process.setStatus("Ready"); // Set status to Ready
            readyQueue.add(process);
            processManagement.refreshTable(); // Refresh the table after adding
        }

        // Step 5: Process each PCB in the ready queue
        while (!readyQueue.isEmpty()) {
            PCB process = readyQueue.poll(); // Remove the first process from the queue

            // Set process to Running state and refresh
            process.setStatus("Running");
            System.out.println("Process " + process.getProcessId() + " is now " + process.getStatus() + " having "+process.getPriority() + "Priority");
            processManagement.refreshTable();

            // Simulate process completion by setting it to Finished
            process.setStatus("Finished");
            System.out.println("Process " + process.getProcessId() + " is now " + process.getStatus());
            processManagement.refreshTable();
        }
    }







    public List<PCB> getAllProcesses() {
        List<PCB> allProcesses = new ArrayList<>();
        // Add all processes from each queue directly
        allProcesses.addAll(readyQueue);
        allProcesses.addAll(blockedQueue);
        allProcesses.addAll(suspendedQueue);
        allProcesses.addAll(newQueue);

        // Return the combined list of PCB objects
        return allProcesses;
    }
//for debugging
    public List<PCB> processinqueues() {
        List<PCB> allProcesses = new ArrayList<>();
        // Add all processes from each queue directly
        //allProcesses.addAll(readyQueue);
        //allProcesses.addAll(blockedQueue);
        allProcesses.addAll(suspendedQueue);
        //allProcesses.addAll(newQueue);

        // Return the combined list of PCB objects
        return allProcesses;
    }

    public List<PCB> processinqueuer() {
        List<PCB> allProcesses = new ArrayList<>();
        // Add all processes from each queue directly
        allProcesses.addAll(readyQueue);
        //allProcesses.addAll(blockedQueue);
        //allProcesses.addAll(suspendedQueue);
        //allProcesses.addAll(newQueue);

        // Return the combined list of PCB objects
        return allProcesses;
    }


    private PCB findProcess(int processID) {
        // Iterate through all queues: readyQueue, blockedQueue, suspendedQueue, and newQueue
        for (Queue<PCB> queue : List.of(readyQueue, blockedQueue, suspendedQueue, newQueue)) {
            for (PCB proc : queue) {
                if (proc.getProcessId() == processID) {
                    return proc;
                }
            }
        }
        return null;
    }


    private PCB findAndRemoveProcess(Queue<PCB> queue, int processID) {
        for (PCB proc : queue) {  // Use PCB instead of process
            if (proc.getProcessId() == processID) {
                queue.remove(proc);
                return proc;
            }
        }
        return null;
    }


    private boolean removeFromQueue(Queue<PCB> queue, int processID) {
        return queue.removeIf(proc -> proc.getProcessId() == processID);
    }

}
