package ua.artcode.process;

import ua.artcode.dynamic_compile.*;
import ua.artcode.exception.CompilationException;
import ua.artcode.model.common.Task;
import ua.artcode.model.test.TestArg;
import ua.artcode.model.test.TestRunner;
import ua.artcode.preprocess.DataUnmarshaler;
import ua.artcode.preprocess.TemplateProcessor;

import java.io.File;
import java.util.Arrays;
import java.util.List;

//TODO make high level of abstraction (create interface)
public class TaskRunFacade {

    private static final String GEN_TEMPLATE_PATH = "temp/general_template.vm";
    private File srcRoot = new File("temp");


    private DataUnmarshaler dateConverter = new DataUnmarshaler();
    private DynamicCompiler dynamicCompiler = new DynamicCompiler(srcRoot);

    public void runTask(Task task, String method) throws CompilationException {
        //Make method from template
        //TODO refactor this section

        String className = "_" + task.getTaskMethod().getMethodName() + String.valueOf(System.currentTimeMillis()).substring(8);
        String to = srcRoot.getPath()+ "/" + className + ".java";


        String methodName = task.getTaskMethod().getMethodName();

        //TODO refactor getting argsForTemplate
        List<TestArg> argsForTemplate = task.getTestCase().getDataPointList().get(0).getIn();
        TemplateProcessor.process(GEN_TEMPLATE_PATH, to, className, methodName, argsForTemplate, method);

        dynamicCompiler.compile(to);

        Class cl = BaseClassLoader.uriLoadClass(srcRoot, className);

        //Convert types, which retrieved fromDB as String
        dateConverter.convert(task.getTestCase());

        try {
            MethodInvoker action = (MethodInvoker) cl.newInstance();
            TestRunner.run(action, task.getTestCase());
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }


}
