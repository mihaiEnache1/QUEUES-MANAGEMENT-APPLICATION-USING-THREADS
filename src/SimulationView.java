import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.DecimalFormat;

public class SimulationView extends JFrame {
    private final JTextArea textArea;
    private final JTextField averageWaitingTime;
    private final JTextField averageServiceTime;
    private final JTextField peakHour;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public SimulationView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 911, 547);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        textArea = new JTextArea();
        textArea.setBounds(20, 21, 400, 480);
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(20, 21, 400, 480);
        scrollPane.setVisible(true);
        contentPane.add(scrollPane);

        JTextField txtAvgWaitingTime = new JTextField();
        txtAvgWaitingTime.setFont(new Font("Tahoma", Font.PLAIN, 18));
        txtAvgWaitingTime.setText("Average Waiting Time:");
        txtAvgWaitingTime.setBounds(582, 167, 194, 47);
        contentPane.add(txtAvgWaitingTime);
        txtAvgWaitingTime.setColumns(10);

        JTextField txtAvgServiceTime = new JTextField();
        txtAvgServiceTime.setFont(new Font("Tahoma", Font.PLAIN, 18));
        txtAvgServiceTime.setText("Average Service Time:");
        txtAvgServiceTime.setBounds(582, 224, 194, 47);
        contentPane.add(txtAvgServiceTime);
        txtAvgServiceTime.setColumns(10);

        JTextField txtPeakHour = new JTextField();
        txtPeakHour.setFont(new Font("Tahoma", Font.PLAIN, 18));
        txtPeakHour.setText("Peak Hour:");
        txtPeakHour.setBounds(582, 281, 194, 47);
        contentPane.add(txtPeakHour);
        txtPeakHour.setColumns(10);

        averageWaitingTime = new JTextField();
        averageWaitingTime.setBounds(786, 167, 96, 47);
        contentPane.add(averageWaitingTime);
        averageWaitingTime.setColumns(10);

        averageServiceTime = new JTextField();
        averageServiceTime.setBounds(786, 224, 96, 47);
        contentPane.add(averageServiceTime);
        averageServiceTime.setColumns(10);

        peakHour = new JTextField();
        peakHour.setBounds(786, 281, 96, 47);
        contentPane.add(peakHour);
        peakHour.setColumns(10);
    }

    public void setAverageWaitingTime(double avgWaitingTime){
        averageWaitingTime.setText(df.format(avgWaitingTime));
    }

    public void setAverageServiceTime(double avgServiceTime){
        averageServiceTime.setText(df.format(avgServiceTime));
    }

    public void setPeakHour(int peakHour){
        this.peakHour.setText(Integer.toString(peakHour));
    }

    public void setTextArea(StringBuilder text){
        String newText;
        newText = textArea.getText() + text.toString();
        textArea.setText(newText);
    }

    public String getTextArea() {
        return textArea.getText();
    }
}
