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
                    <FORM name="edit-product" action="editThisProduct" METHOD="POST">
                        <H1>Edit ${selectedProduct.title}</H1>
                        <TABLE>
                            <TR><TD>Title: <INPUT TYPE="TEXT" NAME="title" value="${selectedProduct.title}">
                            <TR><TD>Description: <INPUT TYPE="TEXT" NAME="description" value="${selectedProduct.description}">
                            <TR><TD>Image: <INPUT TYPE="TEXT" NAME="image" value="${selectedProduct.image}">
                            <TR><TD>Tags: <INPUT TYPE="text" NAME="tags" value="${selectedProduct.tags}">
                            <TR><TD>Price: <INPUT TYPE="text" NAME="price" value="${selectedProduct.price}">
                            <TR><TD>Production Cost: <INPUT TYPE="text" NAME="productionCost" value="${selectedProduct.productionCost}">
                            <TR><TD>Customizable: <INPUT TYPE="text" NAME="customizable" value="${selectedProduct.customizable}">
                            <input type="hidden" id="productId" name="productId" value=${selectedProduct.itemId}>
                            <TR><TH><INPUT TYPE="SUBMIT" VALUE="Edit">
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
