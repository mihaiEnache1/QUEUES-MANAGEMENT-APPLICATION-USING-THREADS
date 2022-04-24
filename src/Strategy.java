import java.util.List;

public interface Strategy {
    void addClient(List<QueueClient> queueClientList, Client client);
}
