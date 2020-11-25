<%--
  Created by IntelliJ IDEA.
  User: Elizabeth Sajdak
  Date: 10/18/20
  Time: 6:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign in</title>
</head>
<body>
<FORM ACTION="j_security_check" METHOD="POST">
        <H1>Sign In</H1>
    <TABLE>
        <TR><TD>Email: <INPUT TYPE="TEXT" NAME="j_username">
        <TR><TD>Password: <INPUT TYPE="PASSWORD" NAME="j_password">
        <TR><TH><INPUT TYPE="SUBMIT" VALUE="Log In">
    </TABLE>
</FORM>
</body>
</html>
