<%-- 
    Document   : WorkSectionList
    Created on : Jul 8, 2021, 5:27:09 PM
    Author     : IM10
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <title>SRMS</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Poppins">
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
    </style>
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
                <h2>Work Section</h2>

                <div class="w3-responsive">
                    <a href="<%=request.getContextPath()%>/newWSForm" class="w3-margin-right w3-margin-bottom w3-red w3-left w3-padding-large"
                       style="text-decoration: none">Assign Work</a>
                    <table class="w3-table-all w3-hoverable">
                        <thead>
                            <tr class="w3-light-grey">
                                <th>empName</th>
                                <th>sectionName</th>
                                <th>sectionJob</th>
                                <th>duty</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="ws" items="${empworklist}">
                                <tr>
                                    <td>
                                        <c:out value="${ws.empName}"/>
                                    </td>
                                    <td>
                                        <c:out value="${ws.sectionName}"/>
                                    </td>
                                    <td>
                                        <c:out value="${ws.sectionJob}"/>
                                    </td>
                                    <td>
                                        <c:out value="${ws.duty}"/>
                                    </td>
                                    <td>
                                        <a href="<%=request.getContextPath()%>/editWSForm?ewID=<c:out value='${ws.ewID}'/>" class="w3-red w3-padding w3-left" style="text-decoration: none">Update</a>
                                        <a href="<%=request.getContextPath()%>/deleteWS?ewID=<c:out value='${ws.ewID}'/>" class="w3-red w3-padding w3-left" style="text-decoration: none">Delete</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <script>
            // Script to open and close sidebar
            function w3_open() {
                document.getElementById("mySidebar").style.display = "block";
                document.getElementById("myOverlay").style.display = "block";
            }

            function w3_close() {
                document.getElementById("mySidebar").style.display = "none";
                document.getElementById("myOverlay").style.display = "none";
            }

            // Modal Image Gallery
            function onClick(element) {
                document.getElementById("img01").src = element.src;
                document.getElementById("modal01").style.display = "block";
                var captionText = document.getElementById("caption");
                captionText.innerHTML = element.alt;
            }

        </script>
    </body>
</html>
