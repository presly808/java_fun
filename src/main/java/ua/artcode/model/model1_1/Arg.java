package ua.artcode.model.model1_1;

/**
 * Created by serhii on 20.02.15.
 */
public class Arg {

    private int order;
    private Type type;
    private String name;

    public Arg() {
    }

    public Arg(int order, Type type, String name) {
        this.order = order;
        this.type = type;
        this.name = name;
    }

    public Arg(Type type, String name) {
        this.type = type;
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MethodArg{" +
                "order=" + order +
                ", type=" + type +
                ", name='" + name + '\'' +
                '}';
    }
}
