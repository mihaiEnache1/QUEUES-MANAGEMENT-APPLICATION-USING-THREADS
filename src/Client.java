public class Client {
    private final int clientId;
    private final int arrivalTime;
    private int serviceTime;
    private static int idCounter=0;

    public Client(int arrivalTime, int serviceTime){
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
        idCounter++;
        this.clientId = idCounter;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void decreaseServiceTime(){
        serviceTime--;
    }

    @Override
    public String toString() {
        return "(" + clientId + "," + arrivalTime + "," + serviceTime + ")";
    }
}
