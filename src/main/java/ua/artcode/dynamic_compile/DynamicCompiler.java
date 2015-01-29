package ua.artcode.dynamic_compile;

import ua.artcode.exception.CompilationException;
import ua.artcode.utils.IOUtils;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.*;
import java.util.logging.Logger;

/**
 * use command for compile javac -cp /home/serhii/dev/apache-tomcat-7.0.57/webapps/ROOT/WEB-INF/classes/ /home/serhii/dev/javafun/temp/_sum59382.java
 */
//TODO avoid static , add field root, resolve multithreading using stream
public class DynamicCompiler {

    public DynamicCompiler() {

    }

    public String compile(String path) throws CompilationException {
        File sourceFile = new File(path); // TODO check if sourceFile redundant
        /*JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();// TODO tomcat can resolve classpath

        PipedOutputStream pipedOutputStream = new PipedOutputStream();
        PipedInputStream pipedInputStream = null;
        BufferedReader bufferedReader = null;
        try {
            pipedInputStream = new PipedInputStream(pipedOutputStream);
            bufferedReader = new BufferedReader(new InputStreamReader(pipedInputStream));
        } catch (IOException e) {
            e.printStackTrace();
        }


        int res = compiler.run(null, null, pipedOutputStream, sourceFile.getPath());

        if (res != 0){
            String message = IOUtils.getMessage(bufferedReader);
            System.err.println(message); // TODO wrap result of compilation and show user
            throw new CompilationException(message);
        }*/

        /*replace compilation logic*/
        try {
            String absolutePath = sourceFile.getCanonicalPath();
            Process pr = Runtime.getRuntime().exec(String.format("javac -cp /home/serhii/dev/apache-tomcat-7.0.57/webapps/ROOT/WEB-INF/classes/ %s",absolutePath));

            if (pr.waitFor() != 0){
                String message = IOUtils.getMessage(new BufferedReader(new InputStreamReader(pr.getErrorStream())));
                System.err.println(message); // TODO wrap result of compilation and show user
                throw new CompilationException(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        return path; // return path to class
    }

    //TODO extract into IOUtils or find better solution



}
