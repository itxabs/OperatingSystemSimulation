public class PCB extends process {

    private String ownerOfProcess;
    private String memoryRequirement;
    private String ioInformation;


    public PCB(int processId, int priority, String status, String ownerOfProcess, String memoryRequirement, String ioInformation,int ArrivalTime) {
        super(processId, priority, status, ArrivalTime);
        this.ownerOfProcess = ownerOfProcess;
        this.memoryRequirement = memoryRequirement;
        this.ioInformation = ioInformation;
    }


    public String getOwnerOfProcess() {
        return ownerOfProcess;
    }

    public void setOwnerOfProcess(String ownerOfProcess) {
        this.ownerOfProcess = ownerOfProcess;
    }

    public String getMemoryRequirement() {
        return memoryRequirement;
    }

    public void setMemoryRequirement(String memoryRequirement) {
        this.memoryRequirement = memoryRequirement;
    }

    public String getIoInformation() {
        return ioInformation;
    }

    public void setIoInformation(String ioInformation) {
        this.ioInformation = ioInformation;
    }


}
