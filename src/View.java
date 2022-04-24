import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class View extends JFrame {
    private final JTextField numberOfClients;
    private final JTextField maximumArrivalTime;
    private final JTextField numberOfQueues;
    private final JTextField minimumArrivalTime;
    private final JTextField minimumServiceTime;
    private final JTextField maximumServiceTime;
    private final JTextField simulationTime;
    private final JButton computeButton;
    private final JComboBox comboBox;

    public View() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 554, 509);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JTextField txtNumberOfClients = new JTextField();
        txtNumberOfClients.setFont(new Font("Tahoma", Font.PLAIN, 20));
        txtNumberOfClients.setText("Number of clients:");
        txtNumberOfClients.setBounds(79, 10, 220, 41);
        contentPane.add(txtNumberOfClients);
        txtNumberOfClients.setColumns(10);

        JTextField txtNumberOfQueues = new JTextField();
        txtNumberOfQueues.setText("Number of queues:");
        txtNumberOfQueues.setFont(new Font("Tahoma", Font.PLAIN, 20));
        txtNumberOfQueues.setColumns(10);
        txtNumberOfQueues.setBounds(79, 61, 220, 41);
        contentPane.add(txtNumberOfQueues);

        JTextField txtMinimumArrivalTime = new JTextField();
        txtMinimumArrivalTime.setText("Minimum arrival time:");
        txtMinimumArrivalTime.setFont(new Font("Tahoma", Font.PLAIN, 20));
        txtMinimumArrivalTime.setColumns(10);
        txtMinimumArrivalTime.setBounds(79, 112, 220, 41);
        contentPane.add(txtMinimumArrivalTime);

        JTextField txtMaximumArrivalTime = new JTextField();
        txtMaximumArrivalTime.setText("Maximum arrival time:");
        txtMaximumArrivalTime.setFont(new Font("Tahoma", Font.PLAIN, 20));
        txtMaximumArrivalTime.setColumns(10);
        txtMaximumArrivalTime.setBounds(79, 163, 220, 41);
        contentPane.add(txtMaximumArrivalTime);

        JTextField txtMinimumServiceTime = new JTextField();
        txtMinimumServiceTime.setText("Minimum service time:");
        txtMinimumServiceTime.setFont(new Font("Tahoma", Font.PLAIN, 20));
        txtMinimumServiceTime.setColumns(10);
        txtMinimumServiceTime.setBounds(79, 214, 220, 41);
        contentPane.add(txtMinimumServiceTime);

        JTextField txtMaximumServiceTime = new JTextField();
        txtMaximumServiceTime.setText("Maximum service time:");
        txtMaximumServiceTime.setFont(new Font("Tahoma", Font.PLAIN, 20));
        txtMaximumServiceTime.setColumns(10);
        txtMaximumServiceTime.setBounds(79, 265, 220, 41);
        contentPane.add(txtMaximumServiceTime);

        numberOfClients = new JTextField();
        numberOfClients.setBounds(324, 10, 143, 37);
        contentPane.add(numberOfClients);
        numberOfClients.setColumns(10);

        maximumArrivalTime = new JTextField();
        maximumArrivalTime.setColumns(10);
        maximumArrivalTime.setBounds(324, 163, 143, 37);
        contentPane.add(maximumArrivalTime);

        numberOfQueues = new JTextField();
        numberOfQueues.setColumns(10);
        numberOfQueues.setBounds(324, 61, 143, 37);
        contentPane.add(numberOfQueues);

        minimumArrivalTime = new JTextField();
        minimumArrivalTime.setColumns(10);
        minimumArrivalTime.setBounds(324, 112, 143, 41);
        contentPane.add(minimumArrivalTime);

        minimumServiceTime = new JTextField();
        minimumServiceTime.setColumns(10);
        minimumServiceTime.setBounds(324, 214, 143, 41);
        contentPane.add(minimumServiceTime);

        maximumServiceTime = new JTextField();
        maximumServiceTime.setColumns(10);
        maximumServiceTime.setBounds(324, 265, 143, 37);
        contentPane.add(maximumServiceTime);

        computeButton = new JButton("Compute");
        computeButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        computeButton.setBounds(324, 387, 143, 41);
        contentPane.add(computeButton);

        JTextField txtSimulationTime = new JTextField();
        txtSimulationTime.setText("Simulation Time:");
        txtSimulationTime.setFont(new Font("Tahoma", Font.PLAIN, 20));
        txtSimulationTime.setColumns(10);
        txtSimulationTime.setBounds(79, 316, 220, 41);
        contentPane.add(txtSimulationTime);

        simulationTime = new JTextField();
        simulationTime.setColumns(10);
        simulationTime.setBounds(324, 320, 143, 37);
        contentPane.add(simulationTime);

        String[] selectPolicy = {"SHORTEST_QUEUE", "SHORTEST_TIME"};
        comboBox = new JComboBox(selectPolicy);
        comboBox.setSelectedIndex(0);
        comboBox.setBounds(84, 387, 215, 47);
        contentPane.add(comboBox);

        this.setVisible(true);
    }

    public JButton getComputeButton() {
        return computeButton;
    }

    public void addComputeButtonListener(ActionListener action){
        computeButton.addActionListener(action);
    }

    public int getSimulationTime() {
        return Integer.parseInt(simulationTime.getText());
    }

    public int getMaximumArrivalTime() {
        return Integer.parseInt(maximumArrivalTime.getText());
    }

    public int getMinimumArrivalTime() {
        return Integer.parseInt(minimumArrivalTime.getText());
    }

    public int getMaximumServiceTime() {
        return Integer.parseInt(maximumServiceTime.getText());
    }

    public int getMinimumServiceTime() {
        return Integer.parseInt(minimumServiceTime.getText());
    }

    public int getNumberOfClients() {
        return Integer.parseInt(numberOfClients.getText());
    }

    public int getNumberOfQueues() {
        return Integer.parseInt(numberOfQueues.getText());
    }

    public String getComboBox(){
        return comboBox.getSelectedItem().toString();
    }

    public void showErrorMessage(String errorMessage){
        JOptionPane.showMessageDialog(this, errorMessage);
        refresh();
    }

    public void refresh(){
        numberOfClients.setText(null);
        numberOfQueues.setText(null);
        simulationTime.setText(null);
        maximumArrivalTime.setText(null);
        minimumArrivalTime.setText(null);
        maximumServiceTime.setText(null);
        minimumServiceTime.setText(null);
    }
}
