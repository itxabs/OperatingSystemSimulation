//import java.util.LinkedList;
//import java.util.Queue;
//
//public class Schedular extends AbstractSchedular {
//
//    private Queue<process> readyQueue;
//    private Queue<process> blockedQueue;
//    private Queue<process> suspendedQueue;
//    private Queue<process> newQueue;
//
//
//    public Schedular() {
//        this.readyQueue = new LinkedList<>();
//        this.blockedQueue = new LinkedList<>();
//        this.suspendedQueue = new LinkedList<>();
//        this.newQueue = new LinkedList<>();
//    }
//
//
//    @Override
//    public void addToReadyQueue(PCB p) {
//        readyQueue.add(p);
//    }
//
//    @Override
//    public void suspendProcess(int id) {
//        for (process p : readyQueue) {
//            if (p.getProcessId() == id) {
//                readyQueue.remove(p);
//                suspendedQueue.add(p);
//                System.out.println("Suspended Process: " + p.getArrivalTime());
//                break;
//            }
//        }
//    }
//
//
//    @Override
//    public void resumeProcess(PCB p) {
//        for (process process : suspendedQueue) {
//            if (process.getProcessId() == p.getProcessId()) {
//                suspendedQueue.remove(p);
//                readyQueue.add(p);
//                System.out.println("Suspended Process: " + p.getArrivalTime());
//                break;
//            }
//        }
//    }
//
//    @Override
//    public void dispatchNextProcess() {
//
//    }
//
//    @Override
//    public void addToNewQueue(PCB p) {
//        newQueue.add(p);
//    }
//
//
//    public void moveToBlocked(PCB p) {
//
//    }
//    public void terminateProcess(PCB p,int id) {
//        if ("Suspended".equals(p.getStatus())){
//            suspendedQueue.removeIf(q -> q.getProcessId() == id);
//
//        }else {
//            readyQueue.removeIf(q -> q.getProcessId() == id);
//        }
//    }
//
//
//}

import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;
import java.util.List;

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
    public void addToReadyQueue(PCB p) {
        readyQueue.add(p);  // This is fine because readyQueue is of type Queue<PCB>
    }

    @Override
    public void suspendProcess(int id) {
        PCB targetProcess = findAndRemoveProcess(readyQueue, id);  // Use PCB instead of process
        if (targetProcess != null) {
            targetProcess.setStatus("Suspended");
            suspendedQueue.add(targetProcess);
        }
    }

    @Override
    public void resumeProcess(PCB p) {
        PCB targetProcess = findAndRemoveProcess(suspendedQueue, p.getProcessId());  // Use PCB instead of process
        if (targetProcess != null) {
            targetProcess.setStatus("Ready");
            readyQueue.add(targetProcess);
        }
    }


    @Override
    public void dispatchNextProcess() {
        if (!readyQueue.isEmpty()) {
            process nextProcess = readyQueue.poll();
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
        }
    }

    public void terminateProcess(PCB p, int id) {
        if ("Suspended".equals(p.getStatus())) {
            suspendedQueue.removeIf(q -> q.getProcessId() == id);
        } else {
            readyQueue.removeIf(q -> q.getProcessId() == id);
        }
    }

    public void removeProcess(int processID) {
        if (removeFromQueue(readyQueue, processID) ||
                removeFromQueue(blockedQueue, processID) ||
                removeFromQueue(suspendedQueue, processID) ||
                removeFromQueue(newQueue, processID)) {
            System.out.println("Process " + processID + " removed successfully.");
        } else {
            System.out.println("Process " + processID + " not found.");
        }
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

    public process dispatch() {
        if (!readyQueue.isEmpty()) {
            process nextProcess = readyQueue.poll();
            if (nextProcess != null) {
                nextProcess.setStatus("Running");
            }
            return nextProcess;
        }
        return null;
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
