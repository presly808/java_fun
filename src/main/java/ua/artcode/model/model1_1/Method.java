package ua.artcode.model.model1_1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by serhii on 20.02.15.
 */
public class Method {

    private String name;
    private Type returnType;
    private List<Arg> args;
    private String body;

    public Method(String name, Type returnType) {
        this.name = name;
        this.returnType = returnType;
        this.args = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getReturnType() {
        return returnType;
    }

    public void setReturnType(Type returnType) {
        this.returnType = returnType;
    }

    public List<Arg> getArgs() {
        return args;
    }

    public void setArgs(List<Arg> args) {
        this.args = args;
    }
}
