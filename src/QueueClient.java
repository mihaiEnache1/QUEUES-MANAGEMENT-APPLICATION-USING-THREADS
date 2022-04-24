import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class QueueClient implements Runnable{
    private final int queueId;
    private final BlockingQueue<Client> clients;
    private final AtomicInteger waitingPeriod;
    static int idCounter = 0;
    private boolean ok=true;

    public QueueClient(int maxTasksPerServer){
        clients = new LinkedBlockingQueue<>(maxTasksPerServer);
        waitingPeriod = new AtomicInteger();
        waitingPeriod.set(0);
        idCounter++;
        this.queueId = idCounter;
    }

    public void addClient(Client newClient){
        clients.add(newClient);
        waitingPeriod.addAndGet(newClient.getServiceTime());
    }

    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }

    public BlockingQueue<Client> getClients() {
        return clients;
    }

    public int getQueueId() {
        return queueId;
    }

    @Override
    public void run() {
        while(ok){
            if(!clients.isEmpty()){
                Client client = clients.peek();
                try{
                    Thread.sleep(500);
                    if(client.getServiceTime() > 1){
                        client.decreaseServiceTime();
                        waitingPeriod.getAndDecrement();
                    } else {
                        clients.poll();
                    }
                } catch(InterruptedException exception){
                    exception.printStackTrace();
                }
            } else{
                try{
                    Thread.sleep(500);
                } catch(InterruptedException exception){
                    exception.printStackTrace();
                }
            }
        }
    }

    public void stopQueue(){
        ok = false;
    }
}
