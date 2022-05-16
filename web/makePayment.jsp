<%-- 
    Document   : makePayment
    Created on : Jul 10, 2021, 1:27:39 AM
    Author     : IM10
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <title>SRMS</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Poppins">
        <script src="http://code.jquery.com/jquery-1.7.2.min.js"></script>
        <style>
            body,
            h1,
            h2,
            h3,
            h4,
            h5 {
                font-family: "Poppins", sans-serif
            }

            body {
                font-size: 16px;
            }

            .w3-half img {
                margin-bottom: -6px;
                margin-top: 16px;
                opacity: 0.8;
                cursor: pointer
            }

            .w3-half img:hover {
                opacity: 1
            }
            .dropbtn {
                background-color: red;
                color: white;
                padding: 16px;
                font-size: 16px;
                border: none;
                cursor: pointer;
            }

            .dropbtn:hover,
            .dropbtn:focus {
                background-color: #3e8e41;
            }

            .dropdown {
                float: left;
                position: relative;
                display: inline-block;
            }

            .dropdown-content {
                display: none;
                position: absolute;
                background-color: #f1f1f1;
                min-width: 160px;
                overflow: auto;
                box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
                right: 0;
                z-index: 1;
            }

            .dropdown-content a {
                color: black;
                padding: 12px 16px;
                text-decoration: none;
                display: block;
            }

            .dropdown a:hover {
                background-color: #ddd;
            }

            .show {
                display: block;
            }

            #upload {
                display: none;
            }

            #preview {
                visibility: hidden;
            }
        </style>
    </head>
    <body>
        <div class="w3-main" style="">
            
            <div class="w3-red">
                <button class="w3-button w3-red w3-xlarge w3-hide-large w3-hover-white" onclick="w3_open()">&#9776;</button>
            </div>
            <div class="w3-container">
                <p></p>
                <div class="w3-card-4">
                    <div class="w3-container w3-red">
                        <h2>Make Payment</h2>
                    </div>
                    <h3 class="w3-padding">Table ${tableID}</h3>
                    <table class="w3-table-all w3-hoverable">
                        <thead>
                            <tr class="w3-light-grey">
                                <th>name</th>
                                <th>quantity</th>
                                <th>price per item</th>
                                <th>total price</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach  var="list" items="${receiptlist}">
                            <tr>
                                <td><c:out value="${list.menuName}" /></td>
                                <td><c:out value="${list.quantity}" /></td>
                                <td><c:out value="${list.menuPrice}" /></td>
                                <td><c:out value="${list.total_price}" /></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <h4 class="w3-padding">Total Amount : RM <c:out value="${receiptTotalPrice}"/></h4>
                    <form action="completePayment" method="post" id="theForm">
                        <input type="text" name="tableID" value="${tableID}" hidden>
                        <input type="number" name="balance" value="<c:out value="${receiptTotalPrice}"/>" hidden>
                        <input type="number" name="paymentAmount" min="0" id="paymentAmount" class="w3-padding w3-margin">
                        <button type="button" onclick="getInputValue()" class="w3-button w3-red w3-border w3-hover-white w3-margin">Pay</button>
                    </form>
                    <script>
                        function getInputValue() {
                            var paymentAmount = document.getElementById("paymentAmount").value;
                            if (paymentAmount > <c:out value="${receiptTotalPrice}"/> || paymentAmount == <c:out value="${receiptTotalPrice}"/>) {
                                document.getElementById("theForm").submit();
                                alert("Payment Successful! Printing Receipt...");
                            } else {
                                alert("Payment Amount Not Enough");
                            }
                        }
                    </script>
                </div>
            </div>
        </div>
    </body>
</html>
