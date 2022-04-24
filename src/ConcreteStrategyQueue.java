import java.util.List;

public class ConcreteStrategyQueue implements Strategy{
    @Override
    public void addClient(List<QueueClient> queueList, Client client) {
        int minimumQueueSize = queueList.get(0).getClients().size();
        QueueClient bestQueue = queueList.get(0);
        for(QueueClient queueClient : queueList){
            if(queueClient.getClients().size() < minimumQueueSize){
                minimumQueueSize = queueClient.getClients().size();
                bestQueue = queueClient;
            }
        }
        bestQueue.addClient(client);
    }
}
