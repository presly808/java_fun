package ua.artcode.view.panel;

import ua.artcode.view.common.ComponentNameConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by admin on 01.12.2014.
 */
public class MethodSignaturePanel extends JPanel {

    private int args = 0;
    private JPanel methodSignaturePanel;
    private JPanel changeMethodPanel;
    private TestDataPointsPanel testDataPointsPanel;
    private JButton addDataPointButton;
    private JTextField returnType;
    private JTextField methodName;

    public MethodSignaturePanel(String title, TestDataPointsPanel testDataPointsPanel) {
        this.testDataPointsPanel = testDataPointsPanel;

        setLayout(new GridLayout(3, 1));
        init();
    }

    private void init() {
        methodSignaturePanel = new JPanel(new FlowLayout());
        methodSignaturePanel.add(new JLabel("public"));

        returnType = new JTextField("returnType");
        returnType.setName(ComponentNameConstants.PARSE_EXPECTED_TYPE);

        methodSignaturePanel.add(returnType);

        methodName = new JTextField("methodName");
        methodName.setName(ComponentNameConstants.PARSE_METHOD_NAME);

        methodSignaturePanel.add(methodName);
        methodSignaturePanel.add(new JLabel("("));
        methodSignaturePanel.add(new JLabel(")"));

        changeMethodPanel = new JPanel(new FlowLayout());
        final JButton addArgCompButton = new JButton("add arg");

        addArgCompButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                testDataPointsPanel.initColumnsNames();
                addNewArgComponents();
            }
        });


        JButton delArgCompButton = new JButton("delete arg");
        delArgCompButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int length = methodSignaturePanel.getComponentCount() - 1;
                if (args > 1) {
                    methodSignaturePanel.remove(length - 1);
                    methodSignaturePanel.remove(length - 2);
                    methodSignaturePanel.remove(length - 3);
                    args--;
                } else if (args == 1) {
                    methodSignaturePanel.remove(length - 1);
                    methodSignaturePanel.remove(length - 2);
                    args--;
                }

                //change datapoint panel
                testDataPointsPanel.reconstructDataPointColumns();


                methodSignaturePanel.revalidate();
                MethodSignaturePanel.this.repaint();
            }
        });

        addDataPointButton = new JButton("add data point");
        addDataPointButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                testDataPointsPanel.initDataPointRow();
            }
        });

        changeMethodPanel.add(addArgCompButton);
        changeMethodPanel.add(delArgCompButton);
        changeMethodPanel.add(addDataPointButton);


        add(methodSignaturePanel, BorderLayout.NORTH);
        add(changeMethodPanel, BorderLayout.CENTER);
    }

    public void addNewArgComponents() {
        int last = methodSignaturePanel.getComponentCount() - 1;
        JTextField argType = new JTextField("ArgType");
        argType.setName("ArgType");
        JTextField argName = new JTextField("ArgName" + args);
        argName.setName("ArgName");


        methodSignaturePanel.add(argName, last);
        methodSignaturePanel.add(argType, last);
        if (args != 0) {
            methodSignaturePanel.add(new JLabel(","), last);
        }
        methodSignaturePanel.revalidate();
        this.repaint();

        args++;
    }

    public JPanel getMethodSignaturePanel() {
        return methodSignaturePanel;
    }

    public void setMethodSignaturePanel(JPanel methodSignaturePanel) {
        this.methodSignaturePanel = methodSignaturePanel;
    }
}
