<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <c:import url="header.jsp"/>
    <body>
        <c:import url="nav.jsp"/>
        <!-- Breadcrumb Start -->
        <div class="breadcrumb-wrap">
            <div class="container-fluid">
                <ul class="breadcrumb">
                    <li class="breadcrumb-item"><a href="#">Home</a></li>
                    <li class="breadcrumb-item"><a href="#">Products</a></li>
                    <li class="breadcrumb-item active">Login & Register</li>
                </ul>
            </div>
        </div>
        <!-- Breadcrumb End -->
        
        <!-- Login Start -->
        <div class="login">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="register-form">
                            <FORM ACTION="j_security_check" METHOD="POST">
                                <H1>Sign In</H1>
                                <TABLE>
                                    <TR><TD>Email: <INPUT TYPE="TEXT" NAME="j_username">
                                    <TR><TD>Password: <INPUT TYPE="PASSWORD" NAME="j_password">
                                    <TR><TH><INPUT TYPE="SUBMIT" VALUE="Log In">
                                </TABLE>
                            </FORM>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Login End -->

        <c:import url="footer.jsp"/>
    </body>
</html>
