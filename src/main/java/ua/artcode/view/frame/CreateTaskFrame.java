package ua.artcode.view.frame;

import ua.artcode.db.DataHolder;
import ua.artcode.model.common.Task;
import ua.artcode.model.common.TaskMethod;
import ua.artcode.model.test.DataPoint;
import ua.artcode.model.test.TestArg;
import ua.artcode.model.test.TestCase;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * TODO delete this old version
 */
public class CreateTaskFrame extends JFrame{

    public static final String PARSE_METHOD_NAME = "$parseMethodName";
    public static final String PARSE_METHOD_DESC = "$parseMethodDesc";
    public static final String PARSE_METHOD_EXAMPLE = "$parseMethodExample";
    public static final String PARSE_SOLUTION = "$parseSolution";
    public static final String PARSE_ARG_MAIN_PANEL = "$parseArgMainPanel";
    public static final String PARSE_EXPECTED_TYPE = "$parseExpectedType";
    public static final String PARSE_EXPECTED_VALUE = "$parseExpectedValue";
    public static final String PARSE_KEY = "$parse";
    public static final String PARSE_TASK_TITLE = "$parseTaskTitle";

    private int count;
    private DataHolder dataHolder;

    private JTextField methodNameField;
    private JButton addNewArgButton;
    private JButton deleteLastArgButton;
    private JButton saveTaskButton;
    private JPanel southPanel;
    private JButton previewButton;
    private JTextField methodBodyField;
    private JTextArea methodSolutionTextArea;

    public CreateTaskFrame(DataHolder dataHolder) throws HeadlessException {

        setSize(600, 400);
        this.dataHolder = dataHolder;

        init();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void init() {

        JPanel methodNamePanel = new JPanel(new GridLayout(4,2));
        methodNamePanel.setName("methodPanel");

        methodNamePanel.add(new JLabel("Task name"));
        JTextField taskTitleField = new JTextField();
        taskTitleField.setName(PARSE_TASK_TITLE);
        methodNamePanel.add(taskTitleField);

        methodNamePanel.add(new JLabel("Method name"));
        methodNameField = new JTextField();
        methodNameField.setName(PARSE_METHOD_NAME);

        methodNamePanel.add(methodNameField);

        methodNamePanel.add(new JLabel("Method description"));
        JTextField methodDescTextField = new JTextField();
        methodDescTextField.setName(PARSE_METHOD_DESC);
        methodNamePanel.add(methodDescTextField);


        methodNamePanel.add(new JLabel("Method example"));
        JTextField exampleTextField = new JTextField();
        exampleTextField.setName(PARSE_METHOD_EXAMPLE);
        methodNamePanel.add(exampleTextField);



        getContentPane().add(methodNamePanel, BorderLayout.NORTH);

        JPanel center = new JPanel(new GridLayout(2,1));
        methodSolutionTextArea = new JTextArea("Your solution");
        methodSolutionTextArea.setName(PARSE_SOLUTION);
        center.add(methodSolutionTextArea);

        final JPanel columnNamesPanel = new JPanel(new GridLayout(0,1));
        columnNamesPanel.setName(PARSE_ARG_MAIN_PANEL);
        JPanel addColumns = new JPanel(new GridLayout(1,4));
        addColumns.add(new JLabel("ORDER"));
        addColumns.add(new JLabel("TYPE"));
        addColumns.add(new JLabel("ARG NAME"));
        addColumns.add(new JLabel("ARG VALUE"));
        columnNamesPanel.add(addColumns);

        center.add(columnNamesPanel);
        getContentPane().add(center, BorderLayout.CENTER);


        southPanel = new JPanel(new FlowLayout());
        addNewArgButton = new JButton("ADD NEW ARG");
        addNewArgButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                columnNamesPanel.add(createArgInPanel());
                columnNamesPanel.validate();
                columnNamesPanel.repaint();
            }
        });

        JPanel southContainer = new JPanel(new GridLayout(3,1));
        southContainer.setName("southContainer");

        JPanel expectedValuePanel = new JPanel(new GridLayout(2,2));
        expectedValuePanel.setName("expectedValuePanel");
        expectedValuePanel.add(new JLabel("Expected type"));
        expectedValuePanel.add(new JLabel("Expected value"));

        JTextField expectedTypeTextField = new JTextField();
        expectedTypeTextField.setName(PARSE_EXPECTED_TYPE);
        expectedValuePanel.add(expectedTypeTextField);

        JTextField expectedValueTextField = new JTextField();
        expectedValueTextField.setName(PARSE_EXPECTED_VALUE);
        expectedValuePanel.add(expectedValueTextField);
        southContainer.add(expectedValuePanel);

        methodBodyField = new JTextField();
        methodBodyField.setEditable(false);
        southContainer.add(methodBodyField);

        southPanel.add(addNewArgButton);
        deleteLastArgButton = new JButton("DELETE LAST ARG");
        southPanel.add(deleteLastArgButton);
        previewButton = new JButton("PREVIEW METHOD SIGN");

        //TODO split by methods, extract using class and flex architecture
        previewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Map<String, Component> map = new LinkedHashMap<String, Component>();
                initMap(getContentPane(), PARSE_KEY, map);

                Task task = constructTask(map);
                dataHolder.addNewTask(task);
                methodBodyField.setText(task.getTaskMethod().getMethodBody());
            }
        });
        southPanel.add(previewButton);
        saveTaskButton = new JButton("SAVE TASK");
        southPanel.add(saveTaskButton);


        southContainer.add(southPanel);

        getContentPane().add(southContainer, BorderLayout.SOUTH);

    }

    private String getTextFromComponent(Component component){
        if(!(component instanceof JTextComponent))
            return null;

        JTextComponent jTextComponent = (JTextComponent) component;
        return jTextComponent.getText().trim();
    }

    private Task constructTask(Map<String, Component> map){
        Task task = new Task();
        task.setTitle(getTextFromComponent(map.get(PARSE_TASK_TITLE)));
        task.setDescription(getTextFromComponent(map.get(PARSE_METHOD_NAME)));
        task.setExample(getTextFromComponent(map.get(PARSE_METHOD_EXAMPLE)));


        TaskMethod taskMethod = new TaskMethod();
        taskMethod.setMethodName(getTextFromComponent(map.get(PARSE_METHOD_NAME)));
        taskMethod.setSolution(getTextFromComponent(map.get(PARSE_SOLUTION)));

        DataPoint dataPoint = new DataPoint();

        TestArg expectedArg = new TestArg();
        expectedArg.setType(getTextFromComponent(map.get(PARSE_EXPECTED_TYPE)));
        expectedArg.setValue(getTextFromComponent(map.get(PARSE_EXPECTED_VALUE)));

        dataPoint.setExpected(expectedArg);

        java.util.List<TestArg> inArgs = parseArgMainPanel((JPanel) map.get(PARSE_ARG_MAIN_PANEL));
        if(inArgs != null && !inArgs.isEmpty()){

            dataPoint.setIn(inArgs);
        }
        taskMethod.setMethodBody(genMethodBody(taskMethod.getMethodName() ,dataPoint));

        TestCase testCase = new TestCase();
        testCase.addDataPoint(dataPoint);

        task.setTaskMethod(taskMethod);
        task.setTestCase(testCase);

        return task;

    }

    private void initMap(Container container, String keyName, Map<String,Component> map){
        for(Component component : container.getComponents()){
            if(component.getName() != null && component.getName().contains(keyName)) {
                map.put(component.getName(), component);
            } else if(component instanceof Container){
                initMap((Container) component, keyName, map);
            }
        }
    }


    //TODO replace by StringBuilder
    private String genMethodBody(String methodName, DataPoint dataPoint){
        String template = String.format("public %s %s(", dataPoint.getExpected().getType(), methodName);

        List<TestArg> argList = dataPoint.getIn();

        if(argList.isEmpty()){
            return template + "){\n}";
        }

        int last = argList.size() - 1;
        for(int i = 0; i < last; i++){
            template += argList.get(i).getType() + " " + argList.get(i).getName() + ",";
        }
        template += argList.get(last).getType() + " " + argList.get(last).getName() + ") {\n}";
        return template;
    }


    //TODO refactor make more flexible
    private java.util.List<TestArg> parseArgMainPanel(JPanel argPanels){
        java.util.List<TestArg> argList = new LinkedList<>();
        for(Component component : argPanels.getComponents()){
            if("argPanel".equals(component.getName())){
                argList.add(parseArgPanel((JPanel) component));
            }
        }
        return argList;
    }

    //TODO use component naming instead indexing
    private TestArg parseArgPanel(JPanel argPanel){
        JTextField fieldOrder = (JTextField) (argPanel.getComponent(0));
        JTextField fieldType = (JTextField) (argPanel.getComponent(1));
        JTextField fieldName = (JTextField) (argPanel.getComponent(2));
        JTextField fieldValue = (JTextField) (argPanel.getComponent(3));

        return new TestArg(Integer.valueOf(fieldOrder.getText().trim()), fieldType.getText().trim(),
                fieldValue.getText().trim(), fieldName.getText().trim());
    }

    private JPanel createArgInPanel(){
        JPanel argPanel = new JPanel(new GridLayout(1,4));
        argPanel.setName("argPanel");
        argPanel.add(new JTextField(String.valueOf(count++)));
        argPanel.add(new JTextField());
        argPanel.add(new JTextField());
        argPanel.add(new JTextField());
        return argPanel;
    }



}
