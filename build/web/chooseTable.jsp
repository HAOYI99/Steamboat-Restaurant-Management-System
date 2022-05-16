<%-- 
    Document   : chooseTable
    Created on : Jul 10, 2021, 1:24:11 AM
    Author     : IM10
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <a href="<%=request.getContextPath()%>/makePayment?tableID=T001" class="w3-margin w3-red w3-padding-large"
                       style="text-decoration: none; display: block; width: 15%">Table T001</a><br>
        <a href="<%=request.getContextPath()%>/makePayment?tableID=T002" class="w3-margin w3-red w3-padding-large"
                       style="text-decoration: none; display: block; width: 15%">Table T002</a><br>
        <a href="<%=request.getContextPath()%>/makePayment?tableID=T003" class="w3-margin w3-red w3-padding-large"
                       style="text-decoration: none; display: block; width: 15%">Table T003</a><br>
        <a href="<%=request.getContextPath()%>/makePayment?tableID=T004" class="w3-margin w3-red w3-padding-large"
                       style="text-decoration: none; display: block; width: 15%">Table T004</a><br>
        <a href="<%=request.getContextPath()%>/makePayment?tableID=T005" class="w3-margin w3-red w3-padding-large"
                       style="text-decoration: none; display: block; width: 15%">Table T005</a><br>
        <a href="<%=request.getContextPath()%>/makePayment?tableID=T006" class="w3-margin w3-red w3-padding-large"
                       style="text-decoration: none; display: block; width: 15%">Table T006</a><br>
        <a href="<%=request.getContextPath()%>/makePayment?tableID=T007" class="w3-margin w3-red w3-padding-large"
                       style="text-decoration: none; display: block; width: 15%">Table T007</a><br>
        <a href="<%=request.getContextPath()%>/makePayment?tableID=T008" class="w3-margin w3-red w3-padding-large"
                       style="text-decoration: none; display: block; width: 15%">Table T008</a><br>
        <a href="<%=request.getContextPath()%>/makePayment?tableID=T009" class="w3-margin w3-red w3-padding-large"
                       style="text-decoration: none; display: block; width: 15%">Table T009</a><br>
    </body>
</html>
