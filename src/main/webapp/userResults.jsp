<%@ page import="java.util.ArrayList" %>
<%@ page import="edu.matc.entity.User" %>

<html><body>
<style>
    table {
        border-collapse: collapse;
        padding: 1em;
        border: solid black 1px;
    }
    th, td {
        padding: 1em;
        border: solid black 1px;
    }
</style>
<div class="container-fluid">
    <form action="searchUser" method="GET"><br /><br />
        <label for="searchTerm">Search Term: </label>
        <input type="text" name="searchTerm" id="searchTerm" value=""/>
        <input type="submit" value="Enter" />
    </form>
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
</div>

</body>
</html>
