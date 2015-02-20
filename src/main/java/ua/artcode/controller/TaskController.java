package ua.artcode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.artcode.dao.FileDAO;
import ua.artcode.db.DataHolder;
import ua.artcode.db.MapDataHolder;
import ua.artcode.exception.CompilationException;
import ua.artcode.model.common.Task;
import ua.artcode.model.common.TaskMethod;
import ua.artcode.model.test.DataPoint;
import ua.artcode.model.test.TestArg;
import ua.artcode.model.test.TestCase;
import ua.artcode.preprocess.TaskParserUtils;
import ua.artcode.process.TaskRunFacade;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskRunFacade runner;

    @Autowired
    private MapDataHolder dataHolder;

    @RequestMapping(value = "/new.do", method = RequestMethod.GET)
    public String createTask(){
        return "new-task";
    }

    @RequestMapping(value = "/add-task.do",method = RequestMethod.POST)
    public String addTask(HttpServletRequest request, HttpServletResponse response){

        String taskName = request.getParameter("task_name");
        String taskDescription = request.getParameter("task_description");

        String methodName = request.getParameter("method_name");
        String methodSignature = request.getParameter("method_signature");
        String dataPoints = request.getParameter("data_points");

        //TODO validate parameters, must have null checker

        Task task = parseTask(taskName, taskDescription, methodName, methodSignature, dataPoints);

        dataHolder.addTask(task);

        return "redirect:/index.html";
    }

    private Task parseTask(String taskName, String taskDescription, String methodName, String methodSignature, String dataPoints) {
        Task task = new Task();
        task.setTitle(taskName);
        task.setDescription(taskDescription);

        TaskMethod taskMethod = new TaskMethod();
        taskMethod.setMethodName(methodName);

        // SIGNATURE parsing and convert to OO model
        String[] strSignature = methodSignature.split("-");

        TestArg outType = new TestArg();
        outType.setType(strSignature[0]);

        String[] strArgsTypes = strSignature[1].split(",");
        String[] strDataPoint = dataPoints.split("-");
        outType.setValue(strDataPoint[0]);

        String[] dataPointInValues = strDataPoint[1].split(",");

        List<TestArg> inArg = new LinkedList<>();

        for(int i = 0; i < strArgsTypes.length; i++){
            inArg.add(new TestArg(i, strArgsTypes[i], dataPointInValues[i], "arg" + i));
        }


        TestCase testCase = new TestCase();
        DataPoint dataPoint = new DataPoint();
        dataPoint.setExpected(outType);
        dataPoint.setIn(inArg);
        dataPoint.setReal(strDataPoint[0]);
        testCase.addDataPoint(dataPoint);

        taskMethod.setMethodBody(TaskParserUtils.genMethodBody(methodName, dataPoint));

        task.setTestCase(testCase);
        task.setTaskMethod(taskMethod);
        String accessKey = UUID.randomUUID().toString().substring(0,6);
        task.setAccessKey(accessKey);
        return task;
    }

    @RequestMapping(value = "/list.do", method = RequestMethod.GET)
    public String tasksList(ModelMap modelMap){
        Collection<Task> taskList = dataHolder.getTaskMap().values();
        modelMap.addAttribute("tasks", taskList);
        return "task-list";
    }

    @RequestMapping(value = "/{taskKey}.do", method = RequestMethod.GET)
    public String doTask(@PathVariable String taskKey,ModelMap modelMap, HttpServletRequest request, HttpServletResponse response){
        Task task = dataHolder.getTaskByKey(taskKey);
        modelMap.addAttribute("task",task);
        return "do-task";
    }

    @RequestMapping(value = "/{taskKey}/go.do", method = RequestMethod.POST)
    public String checkPerformedTask(@PathVariable String taskKey, @RequestParam("methodBody") String methodBody, ModelMap modelMap){
        Task task = dataHolder.getTaskByKey(taskKey);
        try {
            runner.runTask(task,methodBody);
            modelMap.addAttribute("testCase", task.getTestCase());
            modelMap.addAttribute("inArg", TaskParserUtils.buildArgs(task.getTaskMethod().getMethodName(),
                    task.getTestCase().getDataPointList().get(0).getIn()));
        } catch (CompilationException e) {
            e.printStackTrace();
        }
        return "task-result";
    }

    public TaskRunFacade getRunner() {
        return runner;
    }

    public void setRunner(TaskRunFacade runner) {
        this.runner = runner;
    }






}
