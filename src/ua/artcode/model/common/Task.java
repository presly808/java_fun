package ua.artcode.model.common;

import ua.artcode.model.test.TestCase;

import java.io.Serializable;

/**
 * Created by admin on 29.11.2014.
 */
public class Task implements Serializable {

    String title;
    String description;
    String example;
    TaskMethod taskMethod;
    TestCase testCase;

    public Task() {
    }

    public Task(String title, String description, String example) {
        this.title = title;
        this.description = description;
        this.example = example;
    }

    public Task(String title, String description, String example, TaskMethod taskMethod, TestCase testCase) {
        this.title = title;
        this.description = description;
        this.example = example;
        this.taskMethod = taskMethod;
        this.testCase = testCase;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public TaskMethod getTaskMethod() {
        return taskMethod;
    }

    public void setTaskMethod(TaskMethod taskMethod) {
        this.taskMethod = taskMethod;
    }

    public TestCase getTestCase() {
        return testCase;
    }

    public void setTestCase(TestCase testCase) {
        this.testCase = testCase;
    }

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", example='" + example + '\'' +
                '}';
    }
}
