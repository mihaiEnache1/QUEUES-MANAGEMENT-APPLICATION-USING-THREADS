import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    private final List<QueueClient> queueClientList;
    private int maxNoQueues;
    private int maxClientsPerQueue;
    private Strategy strategy;

    public Scheduler(int maxNoQueues, int maxClientsPerQueue, SelectionPolicy selectionPolicy){
        this.maxNoQueues = maxNoQueues;
        this.maxClientsPerQueue = maxClientsPerQueue;
        this.queueClientList = new ArrayList<>(maxNoQueues);
        for(int i=0; i<maxNoQueues; i++){
            QueueClient queueClient = new QueueClient(maxClientsPerQueue);
            queueClientList.add(queueClient);
            Thread thread = new Thread(queueClient);
            thread.start();
        }
        changeStrategy(selectionPolicy);
    }

    public void changeStrategy(SelectionPolicy selectionPolicy){
        if(selectionPolicy == SelectionPolicy.SHORTEST_QUEUE){
            strategy = new ConcreteStrategyQueue();
        } else{
            strategy = new ConcreteStrategyTime();
        }
    }

    public void dispatchClient(Client client){
        strategy.addClient(queueClientList, client);
    }

    public List<QueueClient> getQueueClientList() {
        return queueClientList;
    }
}
