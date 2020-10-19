<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 10/19/20
  Time: 12:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
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

</div>
</body>
</html>
