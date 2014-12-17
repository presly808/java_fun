package ua.artcode.view.frame;

import ua.artcode.dao.FileDAO;
import ua.artcode.model.common.Task;
import ua.artcode.process.TaskRunFacade;
import ua.artcode.db.DataHolder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by admin on 29.11.2014.
 */
public class MenuFrame extends JFrame {

    private DataHolder dataHolder;
    private TaskRunFacade taskRunFacade;
    private FileDAO<Task> dao;

    public MenuFrame(TaskRunFacade taskRunFacade, DataHolder dataHolder, FileDAO<Task> fileDAO) throws HeadlessException {
        this.taskRunFacade = taskRunFacade;
        this.dataHolder = dataHolder;
        this.dao = fileDAO;

        setSize(450, 80);

        init();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setVisible(true);


    }

    private void init() {
        JButton taskAddButton = new JButton("Add new task");
        taskAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //new CreateTaskFrame(dataHolder);
                new ConstructorTaskFrame(dataHolder);
            }
        });
        JButton taskListButton = new JButton("Task List");
        taskListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TaskListFrame(dataHolder, taskRunFacade);
            }
        });

        JButton loadTasksButton = new JButton("Load tasks");
        loadTasksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dataHolder.setTasks(dao.loadAll());
            }
        });

        JButton saveTasksButton = new JButton("Save tasks");
        saveTasksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dao.saveAll(dataHolder.getTasks());
            }
        });

        setLayout(new FlowLayout());
        getContentPane().add(taskAddButton);
        getContentPane().add(taskListButton);
        getContentPane().add(loadTasksButton);
        getContentPane().add(saveTasksButton);


    }


}
