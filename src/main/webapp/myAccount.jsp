<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <c:import url="header.jsp"/>
    <script src="js/account.js"></script>
    <body>
        <c:import url="nav.jsp"/>
<%--                <c:if test="${pageContext.request.isUserInRole('admin)}">--%>
<%--                        only admin will see what's here/ this goes on some jsp that you want to forward to--%>
<%--                </c:if>--%>
        <!-- Breadcrumb Start -->
        <div class="breadcrumb-wrap">
            <div class="container-fluid">
                <ul class="breadcrumb">
                    <li class="breadcrumb-item"><a href="home">Home</a></li>
                    <li class="breadcrumb-item"><a href="#">Products</a></li>
                    <li class="breadcrumb-item active">My Account</li>
                </ul>
            </div>
        </div>
        <!-- Breadcrumb End -->

        <!-- My Account Start -->
        <div class="my-account">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-3">
                        <div class="btn-group-vertical">
                            <button id="dashboard-nav" tabToShow="dashboard-tab"><i class="fa fa-tachometer-alt"></i>Dashboard</button>
                            <button id="orders-nav" tabToShow="orders-tab"><i class="fa fa-shopping-bag"></i>Orders</button>
                            <button id="products-nav" tabToShow="products-tab"><i class="fa fa-paint-brush"></i>Products</button>
                        </div>
                    </div>
                    <div class="col-md-9">
                        <div>
                            <div id="dashboard-tab" aria-labelledby="dashboard-nav">
                                <h4>${currentUser.firstName}'s Account</h4>
                                <p>
                                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. In condimentum quam ac mi viverra dictum. In efficitur ipsum diam, at dignissim lorem tempor in. Vivamus tempor hendrerit finibus. Nulla tristique viverra nisl, sit amet bibendum ante suscipit non. Praesent in faucibus tellus, sed gravida lacus. Vivamus eu diam eros. Aliquam et sapien eget arcu rhoncus scelerisque.
                                </p>
                            </div>
                            <div id="orders-tab" aria-labelledby="orders-nav" style="display:none;">
                                <div class="table-responsive">
                                    <table class="table table-bordered">
                                        <thead class="thead-dark">
                                        <tr>
                                            <th>Order Number</th>
                                            <th>Item</th>
                                            <th>Quantity</th>
<%--                                            <th>Price</th>--%>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="order" items="${currentUser.orders}">
                                            <c:forEach var="orderItem" items="${order.orderItems}">
                                                <tr>
                                                    <td>${order.orderNumber}</td>
                                                    <td>${orderItem.product.title}</td>
                                                    <td>${orderItem.quantity}</td>
<%--                                                    <td>${orderItem.product.price * orderItem.quantity}</td>--%>
                                                </tr>
                                            </c:forEach>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <div id="products-tab" aria-labelledby="products-nav" style="display:none;">
                                <div class="table-responsive">
                                    <table class="table table-bordered">
                                        <thead class="thead-dark">
                                        <tr>
                                            <th>Title</th>
                                            <th>Description</th>
                                            <th>Image</th>
                                            <th>Tags</th>
                                            <th>Price</th>
                                            <th>Production Cost</th>
                                            <th>Customizable</th>
                                            <th>Edit/Delete</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="product" items="${currentUser.products}">
                                            <tr>
                                                <td>${product.title}</td>
                                                <td>${product.description}</td>
                                                <td><img height="50" width="auto" id="${product.itemId}" src="productImages/${product.image}" alt="product"/></td>
                                                <td>${product.tags}</td>
                                                <td>${product.price}</td>
                                                <td>${product.productionCost}</td>
                                                <td>${product.customizable}</td>
                                                <td>
                                                    <form action="editProduct" method="post">
                                                        <input type="hidden" id="productId" name="productId" value=${product.itemId}>
                                                        <input type="submit" name="button" value="Edit">
                                                        <input type="submit" name="button" value="Delete">
                                                    </form>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- My Account End -->

        <c:import url="footer.jsp"/>
    </body>
</html>
