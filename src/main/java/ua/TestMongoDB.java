package ua;


import com.mongodb.*;
import ua.artcode.dao.FileDAO;
import ua.artcode.dao.FileDAOImpl;
import ua.artcode.model.model1_1.*;

import java.net.UnknownHostException;
import java.util.List;
import java.util.UUID;

public class TestMongoDB {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static void main(String[] args) throws UnknownHostException {

//        FileDAO<Task> fileDAO = new FileDAOImpl<>(TestMongoDB.class.getResource("tasks.txt").getPath());


        //Input data from client
        String taskName = "Factorial";
        String desc = "Implement factorial, use recursion";

        String methodName = "fact";

        String solution = "";

        String types = "int-int";
        String[] testData = {"1-1","1-0","2-2","6-3","24-4","120-5", "720-6"};

        String accessKey = UUID.randomUUID().toString();
        Task task = new Task(desc,taskName, accessKey);


        //Method method = new Method(methodName, m)




        MongoClient mongoClient = new MongoClient("localhost");
        DB db = mongoClient.getDB("javafun");

        db.createCollection("tasks", null);

        System.out.println(db.getStats());
        DBCollection collection = db.getCollection("tasks");
        collection.insert(new BasicDBObject()
                .append("task", "factorial")
                .append("desc", "do factorial method"));




        DBObject object = collection.findOne();
        System.out.println(ANSI_GREEN + object + ANSI_RESET);


    }

    private static String genExamples(){
        return null;
    }
}
