<%-- 
    Document   : WorkSectionForm
    Created on : Jul 8, 2021, 6:38:35 PM
    Author     : IM10
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <div class="w3-red w3-sidebar w3-bar-block w3-collapse w3-card w3-animate-left" style="width:200px;" id="mySidebar">
            <button class="w3-bar-item w3-button w3-large w3-hide-large w3-hover-white" onclick="w3_close()">Close &times;</button>
            <a href="<%=request.getContextPath()%>/emplist" class="w3-bar-item w3-button w3-hover-white w3-padding-32 w3-large" onclick="w3_close()">employeelist</a>
            <a href="<%=request.getContextPath()%>/menulist" class="w3-bar-item w3-button w3-hover-white w3-padding-32 w3-large" onclick="w3_close()">menulist</a>
            <a href="<%=request.getContextPath()%>/worksectionlist" class="w3-bar-item w3-button w3-hover-white w3-padding-32 w3-large" onclick="w3_close()">worksectionlist</a>
            <a href="<%=request.getContextPath()%>/chooseTable" class="w3-bar-item w3-button w3-hover-white w3-padding-32 w3-large" onclick="w3_close()">payment</a>
        </div>

        <div class="w3-main" style="margin-left:200px">
            <div class="w3-red">
                <button class="w3-button w3-red w3-xlarge w3-hide-large w3-hover-white" onclick="w3_open()">&#9776;</button>
                <div class="w3-container">
                    <h1>SRMS</h1>
                </div>
            </div>
            <div class="w3-container">
                <p></p>
                <div class="w3-card-4">
                    <div class="w3-container w3-red">
                        <h2>Assign Work</h2>
                    </div>
                    <c:if test="${empwork == null}">
                        <form action="AssignWork" method="POST" class="w3-container">
                        </c:if>
                        <c:if test="${empwork != null}">
                            <form action="updateWS" method="POST" class="w3-container">
                                <input type="hidden" name="ewID" value="<c:out value='${empwork.ewID}'/>"/>
                            </c:if>
                            <table border='1'>
                                <tr>
                                    <td>sectionID</td>
                                    <td>
                                        <select name="sectionID" id="sectionID">
                                            <c:forEach items="${worksection}" var="ws">
                                                <option value="${ws.sectionID}">
                                                    ${ws.sectionName} - ${ws.sectionJob}
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>empID</td>
                                    <td>
                                        <select name="empID" id="empID">
                                            <c:forEach items="${employee}" var="emp">
                                                <option value="${emp.empID}">
                                                    ${emp.empName}
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>duty</td>
                                    <td>
                                        <input list="duty" name="duty" id="duty">
                                        <datalist id="duty">
                                            <option value="Full Day">Full Day(4pm - 1am)</option>
                                            <option value="Half Day">Half Day(4pm - 10pm)</option>
                                        </datalist>
                                    </td>
                                </tr>
                                <script>
                                    selectElement('sectionID', '<c:out value='${empwork.sectionID}'/>')
                                    selectElement('empID', '<c:out value='${empwork.empID}'/>')
                                    selectElement('duty', '<c:out value='${empwork.duty}'/>')

                                    function selectElement(id, valueToSelect) {
                                        let element = document.getElementById(id);
                                        element.value = valueToSelect;
                                    }
                                </script>
                                <tr>
                                    <td>
                                        <button type="submit">Submit</button>
                                        <button type="button" id="cancel">Cancel</button>
                                    </td>
                                    <td></td>
                                </tr>
                                <script>
                                    document.getElementById("cancel").onclick = function () {
                                        location.href = "<%=request.getContextPath()%>/worksectionlist";
                                    };
                                </script>
                            </table>
                        </form>
                </div>
            </div>
        </div>
    </body>
</html>
