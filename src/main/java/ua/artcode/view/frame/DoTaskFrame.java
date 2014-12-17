package ua.artcode.view.frame;

import ua.artcode.exception.CompilationException;
import ua.artcode.process.TaskRunFacade;
import ua.artcode.model.common.Task;
import ua.artcode.model.test.DataPoint;
import ua.artcode.model.test.TestArg;
import ua.artcode.model.test.TestCase;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class DoTaskFrame extends JFrame {


    private TaskRunFacade taskRunFacade;
    private Task task;
    private JTextArea methodBodyArea;
    private JTextArea descArea;
    private JLabel label;
    private JButton goButton;

    public DoTaskFrame(String title, Task task, TaskRunFacade taskRunFacade) throws HeadlessException {
        super(title);

        this.taskRunFacade = taskRunFacade;
        this.task = task;

        setSize(600, 600);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        init();
        setVisible(true);
    }

    private void init(){
        label = new JLabel();
        label.setText(task.getTitle());

        JPanel central = new JPanel(new GridLayout(2,1));

        descArea = new JTextArea(task.getDescription());
        descArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        descArea.setEditable(false);

        methodBodyArea = new JTextArea(task.getTaskMethod().getMethodBody());
        methodBodyArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        central.add(descArea);
        central.add(methodBodyArea);

        goButton = new JButton("GO");
        goButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    taskRunFacade.runTask(task, methodBodyArea.getText());
                } catch (CompilationException e1) {
                    JOptionPane.showMessageDialog(null, e1.getMessage(), e1.getClass().getName(), JOptionPane.ERROR_MESSAGE);
                    return;
                }
                new ResultTestFrame("Result of test");
            }
        });

        getContentPane().add(label, BorderLayout.NORTH);
        getContentPane().add(central, BorderLayout.CENTER);
        getContentPane().add(goButton, BorderLayout.SOUTH);


    }

    private class ResultTestFrame extends JFrame {

        private ResultTestFrame(String title) throws HeadlessException {
            super(title);
            setSize(300, 300);

            Object[] columnNames = {"in arguments", "expected", "real", "Test is passed"};
            Object[][] rowData = initTableData(task.getTestCase()); // because inner class

            JTable table = new JTable(rowData, columnNames);

            JScrollPane jscrlp = new JScrollPane(table);

            this.getContentPane().add(jscrlp);

            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            setVisible(true);

        }

        private Object[][] initTableData(TestCase testCase){
            java.util.List<DataPoint> stepList = testCase.getDataPointList();
            Object[][] rowData = new Object[testCase.getDataPointList().size()][4];
            for(int i = 0; i < stepList.size(); i++){
                DataPoint step = stepList.get(i);
                String inParams = buildArgs(task.getTaskMethod().getMethodName(), step.getIn());
                rowData[i] = new Object[]{inParams, step.getExpected().getValue(),
                        step.getReal(), step.getPassed() ? "YES" : "NO"};
            }
            return rowData;
        }


        //TODO extract method buildIn
        private String buildArgs(String methodName, java.util.List<TestArg> args){
            methodName = methodName + "(";
            int last = args.size() - 1;
            for(int i = 0; i < last; i++){

                methodName += args.get(i).getValue()+ ",";
            }

            methodName += args.get(last).getValue() + ")";
            return methodName;
        }

    }



}
