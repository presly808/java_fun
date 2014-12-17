package ua.artcode.view.panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 */
public class TestDataPointsPanel extends JPanel {

    private int args = 0;
    private int dataPointCount = 0;
    private JPanel dataPointPanel = new JPanel(new GridLayout(0,1));
    private JPanel dataPointArgNamePanel = new JPanel(new GridLayout(1, 2 + args));
    private JButton addDataPointButton;

    public TestDataPointsPanel(){
        init();
    }

    void init() {

        dataPointArgNamePanel.add(new JLabel("DataPoint № "));
        dataPointArgNamePanel.add(new JLabel("ExpectedValue "));

        dataPointPanel.add(dataPointArgNamePanel);

        add(dataPointPanel);

    }

    public void initColumnsNames(){
        dataPointArgNamePanel.setLayout(new GridLayout(1, 2 + args));
        dataPointArgNamePanel.add(new JLabel("ArgValue" + args + " "));
        revalidate();
        repaint();
        args++;
    }

    public void reconstructDataPointColumns(){

        int count = dataPointArgNamePanel.getComponentCount();
        if(count > 2){
            dataPointArgNamePanel.remove(dataPointArgNamePanel.getComponentCount()-1);
            clearDataPoint();
            args--;
        }

    }

    public void clearDataPoint(){
        for(int i = dataPointPanel.getComponentCount() - 1; i > 0; i--){
            dataPointPanel.remove(i);
        }
    }

    public void initDataPointRow(){
        JPanel dataPointRow = new JPanel(new GridLayout(1, 2 + args));
        dataPointRow.setName("dataPoint");

        JTextField dataPointOrderTextField = new JTextField(String.valueOf(dataPointCount));
        dataPointOrderTextField.setName("orderPoint");
        dataPointRow.add(dataPointOrderTextField);
        JTextField expectedValueField = new JTextField("");
        expectedValueField.setName("expectedValue");
        dataPointRow.add(expectedValueField);
        for(int i = 0; i < args; i++){
            JTextField argField = new JTextField();
            argField.setName("ArgValue"+args);
            dataPointRow.add(argField);
        }
        dataPointPanel.add(dataPointRow);
        revalidate();
        repaint();
    }

    public JPanel getDataPointPanel() {
        return dataPointPanel;
    }

    public void setDataPointPanel(JPanel dataPointPanel) {
        this.dataPointPanel = dataPointPanel;
    }

    public JPanel getDataPointArgNamePanel() {
        return dataPointArgNamePanel;
    }

    public void setDataPointArgNamePanel(JPanel dataPointArgNamePanel) {
        this.dataPointArgNamePanel = dataPointArgNamePanel;
    }
}
