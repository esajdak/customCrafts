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
    <%ArrayList<User> user = (ArrayList<User>)request.getAttribute("users");
        for(User u:user){%>
    <tr>
        <td><%=u.getFirstName()%></td>
        <td><%=u.getLastName()%></td>
        <td><%=u.getEmail()%></td>
    </tr>
    <%}%>
</table>
</body>
</html>
