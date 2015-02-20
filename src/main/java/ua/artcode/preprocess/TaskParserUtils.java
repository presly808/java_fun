package ua.artcode.preprocess;

import ua.artcode.model.test.DataPoint;
import ua.artcode.model.test.TestArg;

/**
 * Created by serhii on 20.02.15.
 */
public class TaskParserUtils {

    public static String genMethodBody(String methodName, DataPoint dataPoint){
        String template = String.format("public %s %s(", dataPoint.getExpected().getType(), methodName);

        java.util.List<TestArg> argList = dataPoint.getIn();

        if(argList.isEmpty()){
            return template + "){\n}";
        }

        int last = argList.size() - 1;
        for(int i = 0; i < last; i++){
            template += argList.get(i).getType() + " " + argList.get(i).getName() + ",";
        }
        template += argList.get(last).getType() + " " + argList.get(last).getName() + ") {\n}";
        return template;
    }

    public static String buildArgs(String methodName, java.util.List<TestArg> args){
        methodName = methodName + "(";
        int last = args.size() - 1;
        for(int i = 0; i < last; i++){

            methodName += args.get(i).getValue()+ ",";
        }

        methodName += args.get(last).getValue() + ")";
        return methodName;
    }

}
