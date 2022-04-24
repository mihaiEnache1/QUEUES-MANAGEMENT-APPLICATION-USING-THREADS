public class Controller{
    private final View view;
    private int timeLimit;
    private int maxServiceTime;
    private int minServiceTime;
    private int maxArrivalTime;
    private int minArrivalTime;
    private int noClients;
    private int noQueues;
    private String selectionPolicy;
    private SimulationManager simulationManager;
    private final SimulationView simulationView;

    public Controller(View view, SimulationView simulationView){
        this.view = view;
        this.simulationView = simulationView;

        this.view.addComputeButtonListener(e -> {
            if(e.getSource() == this.view.getComputeButton()){
                try{
                    this.timeLimit = this.view.getSimulationTime();
                    this.maxServiceTime = this.view.getMaximumServiceTime();
                    this.minServiceTime = this.view.getMinimumServiceTime();
                    this.maxArrivalTime = this.view.getMaximumArrivalTime();
                    this.minArrivalTime = this.view.getMinimumArrivalTime();
                    this.noClients = this.view.getNumberOfClients();
                    this.noQueues = this.view.getNumberOfQueues();
                    this.selectionPolicy = this.view.getComboBox();
                    if(this.minServiceTime < this.maxServiceTime && minArrivalTime < maxArrivalTime){
                        simulationManager = new SimulationManager(simulationView, timeLimit, maxServiceTime, minServiceTime, maxArrivalTime, minArrivalTime, noQueues, noClients, selectionPolicy);
                        this.simulationView.setVisible(true);
                        Thread thread = new Thread(simulationManager);
                        thread.start();
                    }
                    else{
                        this.view.showErrorMessage("Minimum times must be lower than maximum times");
                    }
                } catch(NumberFormatException exception){
                    this.view.showErrorMessage("Invalid data input");
                }
            }
        });
    }


}
