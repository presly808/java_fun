<%@include file="include.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Task List</title>
</head>
<body>
  <table border="1" style="width:100%">
    <tr>
      <c:forEach var="task" items="${tasks}">
          <td><a href="/task/${task.accessKey}.do">task name : ${task.title}</a></td>
      </c:forEach>
    </tr>
  </table>
</body>
</html>
