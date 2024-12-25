import java.sql.Timestamp;
import java.time.LocalTime;

public class process {
    private int processId;
    private int priority;
    private LocalTime ArrivalTime;
    private String status;

    public process(int processId, int priority, String status) {
        this.processId = processId;
        this.priority = priority;
        ArrivalTime = LocalTime.now();
        this.status = status;
    }


    public int getProcessId() {
        return processId;
    }

    public void setProcessId(int processId) {
        this.processId = processId;
    }


    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public LocalTime getArrivalTime() {
        return ArrivalTime;
    }

    //only for when we transfer the process to another list
    public LocalTime setArrivalTime(LocalTime ArrivalTime){
        this.ArrivalTime = ArrivalTime;
        return null;
    }
}
