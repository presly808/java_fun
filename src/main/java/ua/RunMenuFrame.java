package ua;

import ua.artcode.dao.FileDAOImpl;
import ua.artcode.model.common.Task;
import ua.artcode.process.TaskRunFacade;
import ua.artcode.db.DataHolder;
import ua.artcode.view.frame.MenuFrame;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;

/**
 * initialization of app data
 */
public class RunMenuFrame {

    //TODO
    public static final String GEN_TEMPLATE_PATH = "general_template.vm";
    public static final String TEMP_METHOD_PATH = "temp/method.txt";

    public static void main(String[] args) throws FileNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {



        new MenuFrame(new TaskRunFacade(), new DataHolder(), new FileDAOImpl<Task>(RunMenuFrame.class.getClassLoader().getResource("tasks.txt").getFile()));

    }

}



