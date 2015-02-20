<%@include file="include.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Task Result</title>
</head>
<body>
<table border="1" style="width:100%">
    <tr>
        <th>in args</th>
        <th>real</th>
        <th>expected</th>
        <th>done</th>
    </tr>
    <c:forEach var="dataPoint" items="${testCase.dataPointList}" >
        <tr>
            <td>
                ${inArg}
            </td>
            <td>
                ${dataPoint.real}
            </td>
            <td>
                ${dataPoint.expected.value}
            </td>
            <td>
                ${dataPoint.passed}
            </td>
        </tr>
    </c:forEach>


</table>


</body>
</html>

