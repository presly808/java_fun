package ua.artcode.model.test;

import java.io.Serializable;

public class TestArg implements Serializable {

    private Integer order;
    private String type;
    private Object value;
    private String name;

    public TestArg() {
    }

    //constructor for return type and value
    public TestArg(String type, Object value) {
        this.type = type;
        this.value = value;
    }

    public TestArg(int order, String type, Object value, String name) {
        this.order = order;
        this.type = type;
        this.value = value;
        this.name = name;
    }

    public TestArg(int order, String type, Object value) {
        this.order = order;
        this.type = type;
        this.value = value;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TestArg{" +
                "order=" + order +
                ", type='" + type + '\'' +
                ", value=" + value +
                '}';
    }
}
