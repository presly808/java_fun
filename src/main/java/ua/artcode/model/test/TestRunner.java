package ua.artcode.model.test;

import ua.artcode.dynamic_compile.MethodInvoker;

import java.util.List;

/**
 * TODO create interface, SOLID code
 */
public class TestRunner {


    public static TestCase run(MethodInvoker method, TestCase testCase){
        List<DataPoint> steps = testCase.getDataPointList();

        //TODO refactor this approach

        for(DataPoint dataPoint : steps){
            Object[] convertedArg = dataPoint.toArray();
            Object real = method.call(convertedArg);
            dataPoint.setReal(real);
            dataPoint.setPassed(real.equals(dataPoint.getExpected().getValue())); //TODO check this place
        }

        return testCase;
    }

}
