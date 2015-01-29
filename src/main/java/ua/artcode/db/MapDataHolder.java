package ua.artcode.db;

import ua.artcode.model.common.Task;

import java.util.LinkedHashMap;
import java.util.Map;


public class MapDataHolder {

    private Map<String, Task> taskMap = new LinkedHashMap<>();

    public MapDataHolder() {
    }

    public Map<String, Task> getTaskMap() {
        return taskMap;
    }

    public void setTaskMap(Map<String, Task> taskMap) {
        this.taskMap = taskMap;
    }

    public void addTask(Task task){
        taskMap.put(task.getAccessKey(), task);
    }

    public Task getTaskByKey(String key){
        return taskMap.get(key);
    }

}
