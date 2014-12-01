package ua;

import ua.artcode.dao.FileDAOImpl;
import ua.artcode.model.common.Task;
import ua.artcode.process.TaskRunFacade;
import ua.artcode.db.DataHolder;
import ua.artcode.view.swing.MenuFrame;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by admin on 28.11.2014.
 */
public class RunMenuFrame {

    public static final String GEN_TEMPLATE_PATH = "temp/general_template.vm";
    public static final String TEMP_METHOD_PATH = "temp/method.txt";

    public static void main(String[] args) throws FileNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        new MenuFrame(new TaskRunFacade(), new DataHolder(), new FileDAOImpl<Task>("storage/tasks.txt"));

    }

    //TODO chose between Reflection method invocation or via interface




}



