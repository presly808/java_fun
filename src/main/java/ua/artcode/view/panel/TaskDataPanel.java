package ua.artcode.view.panel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

import static ua.artcode.view.common.ComponentNameConstants.*;

/**
 * Created by admin on 02.12.2014.
 */
public class TaskDataPanel extends JPanel {


    private JTextArea solutionArea;
    private final JTextArea descriptionArea;
    private final JTextField taskNameField;

    public TaskDataPanel() {


        setLayout(new GridLayout(3,1));
        Border areaBorder = BorderFactory.createLineBorder(Color.BLACK);

        //TODO set names to components
        taskNameField = new JTextField("Input task name");
        taskNameField.setName(PARSE_TASK_TITLE);

        JTextField exampleField = new JTextField("Example of method using");
        exampleField.setName(PARSE_METHOD_EXAMPLE);

        JPanel areasPanel = new JPanel(new GridLayout(1,1));
        descriptionArea = new JTextArea("Input description of task");
        descriptionArea.setName(PARSE_METHOD_DESC);
        descriptionArea.setBorder(areaBorder);

        /*solutionArea = new JTextArea("Input your solution");
        solutionArea.setName(PARSE_SOLUTION);
        solutionArea.setBorder(areaBorder);
        areasPanel.add(solutionArea);*/
        areasPanel.add(descriptionArea);

        add(taskNameField);
        add(exampleField);
        add(areasPanel);

    }





}
