package ua.artcode.dynamic_compile;

import ua.artcode.exception.CompilationException;
import ua.artcode.utils.IOUtils;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.*;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.logging.Logger;

/**
 * use command for compile javac -cp /home/serhii/dev/apache-tomcat-7.0.57/webapps/ROOT/WEB-INF/classes/ /home/serhii/dev/javafun/temp/_sum59382.java
 */
//TODO avoid static , add field root, resolve multithreading using stream
public class DynamicCompiler {

//    public static final String LIBS_CLASSPATH = "/home/serhii/dev/apache-tomcat-7.0.57/webapps/ROOT/WEB-INF/classes/";
    public static final String LIBS_CLASSPATH = getLibsClasspath();

    private static String getLibsClasspath(){
        StringBuilder classPaths = new StringBuilder();
        URL[] urls= ((URLClassLoader) (Thread.currentThread().getContextClassLoader())).getURLs();
        for (URL url : urls) {
            classPaths.append(url.getFile()).append(":");
        }

        return classPaths.toString();

    }

    public DynamicCompiler() {}

    public String compile(String path) throws CompilationException {
        File sourceFile = new File(path); // TODO check if sourceFile redundant
        try {
            String absolutePath = sourceFile.getCanonicalPath();
            Process pr = Runtime.getRuntime().exec(String.format("javac -cp " + LIBS_CLASSPATH + " %s",absolutePath));

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

}
