<%@include file="include.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Do Task Page</title>
</head>
<body>

    <h1>${task.title}</h1>

    <div id="desc">
        ${task.description}
    </div>

    <form action="/task/${task.accessKey}/go.do" method="POST">
        <textarea rows="24" cols="80" name="methodBody">${task.taskMethod.methodBody}</textarea><br>
        <input type="submit"/>
    </form>

</body>
</html>
