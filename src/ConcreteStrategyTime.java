import java.util.List;

public class ConcreteStrategyTime implements Strategy{
    @Override
    public void addClient(List<QueueClient> queueClientList, Client client) {
        int minimumWaitingTime = queueClientList.get(0).getWaitingPeriod().get();
        QueueClient bestQueue = queueClientList.get(0);
        for(QueueClient queueClient : queueClientList){
            if(queueClient.getWaitingPeriod().get() <= minimumWaitingTime){
                minimumWaitingTime = queueClient.getWaitingPeriod().get();
                bestQueue = queueClient;
            }
        }
        bestQueue.addClient(client);
    }
}
