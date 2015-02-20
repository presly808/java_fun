package ua.artcode.db;

import ua.artcode.model.common.Task;
import ua.artcode.model.common.TaskMethod;
import ua.artcode.model.test.DataPoint;
import ua.artcode.model.test.TestArg;
import ua.artcode.model.test.TestCase;

import java.util.ArrayList;
import java.util.List;

/**
 * Test temp data, will be replaced by db and DAO
 */
public class DataHolder {

    public List<Task> tasks;

    public DataHolder() {
        tasks = new ArrayList<>();
    }


    public void addNewTask(Task t){

        tasks.add(t);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}


/*
*
*
*
* */