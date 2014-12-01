package ua.artcode.dynamic_compile;

import ua.artcode.exception.CompilationException;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.*;
import java.util.logging.Logger;

/**
 * Created by admin on 28.11.2014.
 */
//TODO avoid static , add field root, resolve multithreading using stream
public class DynamicCompiler {

    private File srcRoot;

    public DynamicCompiler(File srcRoot) {
        this.srcRoot = srcRoot;
    }

    public String compile(String path) throws CompilationException {
        File sourceFile = new File(path); // TODO check if sourceFile redundant
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

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
            String message = getMessage(bufferedReader);
            System.err.println(message); // TODO wrap result of compilation and show user
            throw new CompilationException(message);
        }



        return path; // return path to class
    }

    private String getMessage(BufferedReader bf){
        StringBuilder sb = new StringBuilder();

        try {
            String line = null;
            while(bf.ready()){
                sb.append(bf.readLine()).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();

    }


}
