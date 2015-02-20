package ua.artcode.model.model1_1;

import java.util.List;

/**
 * Created by serhii on 20.02.15.
 */
public class Task {


    private String accessKey;
    private String name;
    private String description;
    private String examples;
    private List<String> hints;
    private Method method;

    public Task() {
    }

    public Task(String description, String name, String accessKey) {
        this.description = description;
        this.name = name;
        this.accessKey = accessKey;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExamples() {
        return examples;
    }

    public void setExamples(String examples) {
        this.examples = examples;
    }

    public List<String> getHints() {
        return hints;
    }

    public void setHints(List<String> hints) {
        this.hints = hints;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }


}
