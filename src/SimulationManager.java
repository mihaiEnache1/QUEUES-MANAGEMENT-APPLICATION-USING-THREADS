import java.util.*;
import java.util.concurrent.BlockingQueue;

public class SimulationManager implements Runnable{
    private final int timeLimit;
    private final int maxServiceTime;
    private final int minServiceTime;
    private final int maxArrivalTime;
    private final int minArrivalTime;
    private final int noClients;
    private final SimulationView simulationView;
    private final Scheduler scheduler;
    private final List<Client> clientList = new LinkedList<>();
    private List<QueueClient> queueClientList;

    float averageServiceTime = 0;
    int peakHour;
    int numberClients = 0;
    int maxClientsQueue = 0;
    static int averageWaitingTime=0;

    public SimulationManager(SimulationView simulationView, int timeLimit, int maxServiceTime, int minServiceTime, int maxArrivalTime, int minArrivalTime, int noQueues, int noClients, String policy){
        this.simulationView = simulationView;
        this.timeLimit = timeLimit;
        this.maxServiceTime = maxServiceTime;
        this.minServiceTime = minServiceTime;
        this.maxArrivalTime = maxArrivalTime;
        this.minArrivalTime = minArrivalTime;
        this.noClients = noClients;
        this.queueClientList = new ArrayList<>();

        SelectionPolicy selectionPolicy;
        if(policy.equals("SHORTEST_QUEUE")){
            selectionPolicy = SelectionPolicy.SHORTEST_QUEUE;
        } else{
            selectionPolicy = SelectionPolicy.SHORTEST_TIME;
        }

        scheduler = new Scheduler(noQueues, noClients, selectionPolicy);
        this.generateNRandomClients();
    }

    public void generateNRandomClients(){
        for(int i=0; i<noClients; i++){
            Random random = new Random();
            int serviceTime = random.nextInt(maxServiceTime - minServiceTime) + minServiceTime;
            int arrivalTime = random.nextInt(maxArrivalTime - minArrivalTime) + minArrivalTime;
            averageServiceTime = averageServiceTime + serviceTime;
            Client client = new Client(arrivalTime, serviceTime);
            clientList.add(client);
        }
        averageServiceTime = averageServiceTime / noClients;
        SortByArrivalTime sortTasks = new SortByArrivalTime();
        clientList.sort(sortTasks);
    }

    public boolean notEmptyQueues(){
        for(QueueClient queueClient : queueClientList){
            if(queueClient.getClients().size() > 0){
                return true;
            }
        }
        return false;
    }

    @Override
    public void run() {
        int currentTime = 0;

        while(currentTime < timeLimit){
            StringBuilder text = new StringBuilder();
            final int ctTime = currentTime;
            List<Client> list = clientList.stream().filter(e -> e.getArrivalTime() == ctTime).toList();

            for(Client client : list){
                scheduler.dispatchClient(client);
                clientList.remove(client);
            }

            try{
                Thread.sleep(500);
            } catch(InterruptedException exception){
                exception.printStackTrace();
            }

            text.append("Time: ").append(currentTime).append("\n").append("Waiting clients: ");
            for(Client client : clientList){
                text.append(client.toString()).append(" ");
            }
            text.append("\n");

            queueClientList = scheduler.getQueueClientList();
            for(QueueClient queueClient : queueClientList){
                if(queueClient.getClients().size() > 1){
                    averageWaitingTime += queueClient.getClients().size() - 1;
                }
            }

            numberClients = 0;
            for(QueueClient queueClient : queueClientList){
                text.append("Queue ").append(queueClient.getQueueId()).append(": ");
                numberClients += queueClient.getClients().size();
                if(queueClient.getClients().isEmpty()){
                    text.append("closed\n");
                } else{
                    BlockingQueue<Client> clients = queueClient.getClients();
                    for(Client client : clients){
                        text.append(client.toString()).append(" ");
                    }
                    text.append("\n");
                }
            }

            if(numberClients > maxClientsQueue){
                maxClientsQueue = numberClients;
                peakHour = currentTime;
            }

            this.simulationView.setTextArea(text);
            currentTime++;

            if(clientList.size() < 1){
                if(!notEmptyQueues()){
                    break;
                }
            }

        }

        for(QueueClient queueClient : queueClientList){
            queueClient.stopQueue();
        }

        simulationView.setAverageServiceTime(averageServiceTime);
        simulationView.setPeakHour(peakHour);
        simulationView.setAverageWaitingTime((double)averageWaitingTime / noClients);

        WriteToFile logFile = new WriteToFile();
        String logFileInfo = this.simulationView.getTextArea();
        logFile.write("Report3.txt", logFileInfo);
    }

    public static void main(String[] args) {
        View view = new View();
        SimulationView simulationView = new SimulationView();
        new Controller(view, simulationView);
    }
}

class SortByArrivalTime implements Comparator<Client> {
    @Override
    public int compare(Client o1, Client o2) {
        return o1.getArrivalTime() - o2.getArrivalTime();
    }
}
