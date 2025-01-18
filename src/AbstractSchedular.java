import java.util.List;
import java.util.Queue;

public abstract class AbstractSchedular {
    public abstract boolean suspendProcess(int id);
    public abstract boolean resumeProcess(int id);
    public abstract void dispatchNextProcess();
    public abstract void addToNewQueue(PCB p);
    public abstract boolean BlockProcess(int id);
    public abstract boolean WakeupProcess(int id);
}
