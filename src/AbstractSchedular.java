public abstract class AbstractSchedular {
    public abstract void addToReadyQueue(PCB p);
    public abstract void suspendProcess(int id);
    public abstract void resumeProcess(PCB p);
    public abstract void dispatchNextProcess();
    public abstract void addToNewQueue(PCB p);
}
