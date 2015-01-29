package ua.artcode.view.frame;

import ua.artcode.db.DataHolder;
import ua.artcode.model.common.Task;
import ua.artcode.model.common.TaskMethod;
import ua.artcode.model.test.DataPoint;
import ua.artcode.model.test.TestArg;
import ua.artcode.model.test.TestCase;
import ua.artcode.view.common.ComponentNameConstants;
import ua.artcode.view.panel.MethodSignaturePanel;
import ua.artcode.view.panel.TaskDataPanel;
import ua.artcode.view.panel.TestDataPointsPanel;

import static ua.artcode.view.common.ComponentNameConstants.*;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;


public class ConstructorTaskFrame extends JFrame {

    private DataHolder dataHolder;
    private MethodSignaturePanel methodSignaturePanel;
    private TestDataPointsPanel testDataPointsPanel;
    private TaskDataPanel taskDataPanel;
    private JButton saveTaskButton;

    public ConstructorTaskFrame(DataHolder dataHolder) throws HeadlessException {
        this.dataHolder = dataHolder;

        setSize(500, 500);

        init();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
    }


    private void init(){
        JPanel center = new JPanel(new GridLayout(2,1));
        taskDataPanel = new TaskDataPanel();
        testDataPointsPanel = new TestDataPointsPanel();
        methodSignaturePanel = new MethodSignaturePanel("MethodPanel",testDataPointsPanel);


        saveTaskButton = new JButton("Save");
        saveTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Map<String, Component> map = new LinkedHashMap<String, Component>();
                initMap(getContentPane(), ComponentNameConstants.PARSE_KEY, map);
                dataHolder.addNewTask(constructTask(map));
                JOptionPane.showMessageDialog(ConstructorTaskFrame.this, "Task was successfully added!", "All is OK", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        center.add(testDataPointsPanel);
        center.add(taskDataPanel);

        getContentPane().add(methodSignaturePanel, BorderLayout.NORTH);
        getContentPane().add(center, BorderLayout.CENTER);
        getContentPane().add(saveTaskButton, BorderLayout.SOUTH);

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
        task.setDescription(getTextFromComponent(map.get(PARSE_METHOD_DESC)));


        TaskMethod taskMethod = new TaskMethod();
        taskMethod.setMethodName(getTextFromComponent(map.get(PARSE_METHOD_NAME)));

        taskMethod.setSolution(getTextFromComponent(map.get(PARSE_SOLUTION)));

        TestArg expectedArg = new TestArg();
        expectedArg.setType(getTextFromComponent(map.get(PARSE_EXPECTED_TYPE)));

        //parse InArg
        List<TestArg> inArgs = parseInArgs(methodSignaturePanel.getMethodSignaturePanel());

        TestCase testCase = parseTestCasePoint(testDataPointsPanel.getDataPointPanel(), inArgs, expectedArg);

        taskMethod.setMethodBody(genMethodBody(taskMethod.getMethodName() ,new DataPoint(inArgs, expectedArg)));

        task.setTaskMethod(taskMethod);
        task.setTestCase(testCase);

        return task;

    }

    //TODO test this place
    private void initMap(Container container, String keyName, Map<String,Component> map){
        for(Component component : container.getComponents()){
            if(component.getName() != null && component.getName().contains(keyName)) {
                map.put(component.getName(), component);
            } else if(component instanceof Container){
                initMap((Container) component, keyName, map);
            }
        }
    }

    private void initMapRefactored(Container container, String keyName, Map<String,Component> map){
        if(container == null || container.getComponentCount() < 1){
            return;
        }

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

        java.util.List<TestArg> argList = dataPoint.getIn();

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

    private TestCase parseTestCasePoint(JPanel dataPointPanel, List<TestArg> in, TestArg expected){
        TestCase testCase = new TestCase();
        for(Component component : dataPointPanel.getComponents()){
            if("dataPoint".equals(component.getName())){
                testCase.addDataPoint(parsePoint((JPanel) component, in, expected));
            }
        }

        return testCase;
    }

    private List<TestArg> parseInArgs(JPanel methodSignPanel){
        List<TestArg> list = new LinkedList<>();
        TestArg arg = new TestArg();
        int order = 0;
        for(Component component : methodSignPanel.getComponents()){
            if("ArgType".equals(component.getName())){
                arg.setType(getTextFromComponent(component));
                arg.setOrder(order++);
            } else if("ArgName".equals(component.getName())){
                arg.setName(getTextFromComponent(component));
                list.add(arg);
                arg = new TestArg();
            }
        }
        return list;
    }

    private List<TestArg> deepCopy(List<TestArg> list){
        List<TestArg> copy = new LinkedList<>();
        for(TestArg arg : list){
            copy.add(new TestArg(arg.getOrder(), arg.getType(),null));
        }
        return copy;
    }


    //TODO refactor structure of project, dont copy list with args and test this place
    private DataPoint parsePoint(JPanel argPanel, List<TestArg> in, TestArg expected){
        List<TestArg> copyList = deepCopy(in);// todo solve problem with test points(always same in args)

        TestArg copyExpected = new TestArg(expected.getType(),null);

        DataPoint dataPoint = new DataPoint(copyList,copyExpected);
        copyExpected.setValue(getTextFromComponent(argPanel.getComponent(1)));

        for(int j = 0, i = 2; j < copyList.size(); i++, j++){
            copyList.get(j).setValue(getTextFromComponent(argPanel.getComponent(i)));
        }

        dataPoint.setExpected(copyExpected);
        dataPoint.setIn(copyList);

        return dataPoint;
    }

}
