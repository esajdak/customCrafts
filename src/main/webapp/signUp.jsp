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
            <li class="breadcrumb-item"><a href="home">Home</a></li>
            <li class="breadcrumb-item"><a href="#">Products</a></li>
            <li class="breadcrumb-item active">Register</li>
        </ul>
    </div>
</div>
<!-- Breadcrumb End -->

<!-- Register Start -->
<div class="login">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12">
                <div class="register-form">
                    <script>
                        function validate()
                        {
                            var firstname = document.form.fullname.value;
                            var lastname = document.form.fullname.value;
                            var email = document.form.email.value;
                            var password = document.form.password.value;
                            var conpassword= document.form.conpassword.value;

                            if (firstname==null || firstname=="")
                            {
                                alert("Full Name can't be blank");
                                return false;
                            }
                            else if (lastname==null || lastname=="")
                            {
                                alert("Full Name can't be blank");
                                return false;
                            }
                            else if (email==null || email=="")
                            {
                                alert("Email can't be blank");
                                return false;
                            }
                            else if(password.length<6)
                            {
                                alert("Password must be at least 6 characters long.");
                                return false;
                            }
                            else if (password!=conpassword)
                            {
                                alert("Confirm Password should match with the Password");
                                return false;
                            }
                        }
                    </script>
                    <FORM name="register-form" action="signUp" onsubmit="return validate()" METHOD="POST">
                        <H1>Sign In</H1>
                        <TABLE>
                            <TR><TD>First Name: <INPUT TYPE="TEXT" NAME="firstName">
                            <TR><TD>Last Name: <INPUT TYPE="TEXT" NAME="lastName">
                            <TR><TD>Email: <INPUT TYPE="TEXT" NAME="email">
                            <TR><TD>Password: <INPUT TYPE="PASSWORD" NAME="password">
                            <TR><TD>Confirm Password: <INPUT TYPE="PASSWORD" NAME="confirmPassword">
                            <TR><TH><INPUT TYPE="SUBMIT" VALUE="Register"><input type="reset" value="Clear">
                        </TABLE>
                    </FORM>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Register End -->

<c:import url="footer.jsp"/>
</body>
</html>
