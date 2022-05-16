<%-- 
    Document   : OrderPage
    Created on : Jul 9, 2021, 3:03:28 AM
    Author     : IM10
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Poppins">
        <link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <style>
            <%@ include file="menucart1.css"%>
        </style>
        <script type="text/javascript">
            function decrement(clicked_id)
            {
                let str1 = "input";
                let str2 = clicked_id;
                document.getElementById(str1.concat(str2)).stepDown();
            }
            function increment(clicked_id)
            {
                let str1 = "input";
                let str2 = clicked_id;
                document.getElementById(str1.concat(str2)).stepUp();
            }
        </script>

    </head>
    <body>
        <div class="container">
            <h1>Menu</h1>
            <div class="cart">
                <div class="menus">
                    <c:forEach var="menu" items="${menulist}">
                        <div class="menu">

                            <img src="data:image/jpg;base64,${menu.menuImage}" width="200" height="200" />
                            <div class="menu-info">
                                <h3 class="menu-name"><c:out value="${menu.menuName}" /> </h3>
                                <h4 class="menu-description"><c:out value="${menu.menuDesc}" /></h4>
                                <h4 class="menu-price"><c:out value="${menu.menuCategory}" /></h4>
                                <h4 class="menu-price">RM <c:out value="${menu.menuPrice}" /></h4>
                                <form action="createOrder?tableID=${tableID}" method="post" class="menu-remove">
                                    <input type="text" name="menuID" value="<c:out value="${menu.menuID}" />" hidden>
                                    <button class="w3-button w3-red w3-round-xlarge" id="quantity<c:out value="${menu.menuID}"/>" type="button" onclick="decrement(this.id)">-</button>
                                    <input id="inputquantity<c:out value="${menu.menuID}"/>" type=number name="quantity" min="1" max="20" value="1">
                                    <button  class="w3-button w3-red w3-round-xlarge" id="quantity<c:out value="${menu.menuID}"/>" type="button" onclick="increment(this.id)">+</button>

                                    <button type="submit" class="w3-button w3-red w3-round-xlarge">submit</button>
                                </form>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <div class="cart-total">
                    <h2>
                        <span>Order Summary</span>
                    </h2>
                    <table>
                        <tr>
                            <th>name</th>
                            <th>quantity</th>
                            <th>price</th>
                            <th>total price</th>
                            <th>Action</th>
                        </tr>
                        <c:forEach  var="orderlist" items="${orderlist}">
                            <tr>
                                <td><c:out value="${orderlist.menuName}" /></td>
                                <td><c:out value="${orderlist.quantity}" /></td>
                                <td><c:out value="${orderlist.menuPrice}" /></td>
                                <td><c:out value="${orderlist.total_price}" /></td>
                                <td><a href="<%=request.getContextPath()%>/deleteOrder?orderID=<c:out value="${orderlist.orderID}" />&tableID=${tableID}">Delete</a></td>
                            </tr>
                        </c:forEach>
                    </table>
                    <a href="<%=request.getContextPath()%>/confirmOrder?tableID=${tableID}">Confirm Order</a>
                    <br>
                    <a href="<%=request.getContextPath()%>/myOrder?tableID=${tableID}">Back</a>
                </div>
            </div>
        </div> 
    </body>
</html>
