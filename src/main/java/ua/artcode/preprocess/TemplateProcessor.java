package ua.artcode.preprocess;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import ua.artcode.model.test.TestArg;

import java.io.*;
import java.util.List;


//TODO
public class TemplateProcessor {

    public static void process(String templatePath, String srcFileDest, String className, String methodName, List<TestArg> args, String allMethod){

        //TODO inti velocity via spring
        Velocity.init(); // инициализация Velocity
        VelocityContext vc = new VelocityContext(); // создание контекста Velocity

        vc.put("methodName", methodName);
        vc.put("allMethod", allMethod);
        vc.put("className", className);
        vc.put("argsList", args);
        vc.put("lastArgNum", args.size() - 1); // use it variable for set comma iteration

        Template template = Velocity.getTemplate(templatePath, "utf-8"); // download template from file

        //TODO hold template in app for optimization
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(srcFileDest)))){
            template.merge(vc, bw); // метод merge() принимает набор данных в виде объекта "vc" и объект потока "bw"
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
