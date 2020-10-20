<%@ page import="java.util.ArrayList" %>
<%@ page import="edu.matc.entity.User" %>

<html><body>

<h2>Search Results: </h2>
<table>
    <tr>
        <th> First Name </th>
        <th> Last Name </th>
        <th> Email </th>
    </tr>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.firstName} ${user.lastName}</td>
            <td>${user.email}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
