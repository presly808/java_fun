<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new task</title>
</head>
<body>
<h1>Create New Task</h1>

<form action="/task/add-task.do" method="post">
    Input task name<br>
    <label>
        <input type="text" name="task_name">
    </label><br>
    Input task description<br>
    <label>
    <textarea name="task_description" rows="10" cols="50"></textarea>
    </label><br>

    Method name:<br>
    <label>
        <input type="text" name="method_name">
    </label><br>
    Set return type and argument types<br>
    Example:<br>
    <code>int-double,int,int</code><br>
    <label>
        <input type="text" name="method_signature">
    </label><br>
    Set test data point<br>
    Example:<br>
    <code>25-2.45,12,12</code><br>
    <label>
        <input type="text" name="data_points">
    </label><br>

    <input type="submit">
</form>


</body>
</html>
