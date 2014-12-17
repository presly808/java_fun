package ua.artcode.view.frame;

import ua.artcode.process.TaskRunFacade;
import ua.artcode.db.DataHolder;
import ua.artcode.model.common.Task;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by admin on 30.11.2014.
 */
public class TaskListFrame  extends JFrame {

    private DataHolder holder;
    private JList<Task> taskJList;
    private JButton startTaskButton;
    private TaskRunFacade taskRunFacade;
    private JButton deleteTaskButton;
    private JButton editTaskButton;

    public TaskListFrame(DataHolder holder, TaskRunFacade taskRunFacade) throws HeadlessException {
        this.holder = holder;
        this.taskRunFacade = taskRunFacade;


        if(holder.tasks == null || holder.tasks.isEmpty()){
            JOptionPane.showMessageDialog(this, "Load data from main menu", "",JOptionPane.WARNING_MESSAGE);
            dispose();
            return;
        }


        setSize(500, 500);

        init();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);


    }

    private void init() {

        Task[] arr = new Task[holder.getTasks().size()];
        holder.getTasks().toArray(arr);

        taskJList = new JList<>(arr);
        getContentPane().add(taskJList, BorderLayout.CENTER);


        JPanel southPanel = new JPanel(new GridLayout(1,3));

        startTaskButton = new JButton("GO");
        startTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Task task = taskJList.getSelectedValue();
                new DoTaskFrame("TASK", task, taskRunFacade);
            }
        });

        deleteTaskButton = new JButton("Remove");
        deleteTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


            }
        });

        editTaskButton = new JButton("Edit");
        editTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        southPanel.add(startTaskButton);
        southPanel.add(deleteTaskButton);
        southPanel.add(editTaskButton);



        getContentPane().add(southPanel, BorderLayout.SOUTH);





    }
}
