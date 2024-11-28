public class PCB {

    private int processId;
    private String state;
    private String ownerofProcess;
    private int priority;
    private String memoryRequirment;
    private String ioInformation;


    public PCB(int processId, String state, String ownerofProcess, int priority, String memoryRequirement, String ioInformation) {
        this.processId = processId;
        this.state = state;
        this.ownerofProcess = ownerofProcess;
        this.priority = priority;
        this.memoryRequirment = memoryRequirement;
        this.ioInformation = ioInformation;
    }


    public int getProcessId() {
        return processId;
    }

    public void setProcessId(int processId) {
        this.processId = processId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getOwnerofProcess() {
        return ownerofProcess;
    }

    public void setOwnerofProcess(String ownerofProcess) {
        this.ownerofProcess = ownerofProcess;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getMemoryRequirment() {
        return memoryRequirment;
    }

    public void setMemoryRequirment(String memoryRequirment) {
        this.memoryRequirment = memoryRequirment;
    }

    public String getIoInformation() {
        return ioInformation;
    }

    public void setIoInformation(String ioInformation) {
        this.ioInformation = ioInformation;
    }
}
