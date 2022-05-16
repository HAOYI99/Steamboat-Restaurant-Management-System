<%-- 
    Document   : MenuForm
    Created on : Jul 7, 2021, 10:53:37 PM
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
                background-color: #ff968f;
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
                        <h2>Menu Form</h2>
                    </div>
                    <c:if test="${menu != null}">
                        <div class="dropdown">
                            <img src="data:image/jpg;base64,${menu.menuImage}" 
                                 width="200" height="180" onclick="imageDropdown()" class="dropbtn" />
                            <form id="formImage" action="updateMenuImage" method="POST" enctype="multipart/form-data" class="w3-container">
                                <div id="myDropdown" class="dropdown-content">
                                    <input type="text" name="menuID" value="<c:out value="${menu.menuID}"/>" hidden>
                                    <input id="upload" type="file" name="menuImage" onchange="form.submit()" />
                                    <a href="" id="upload_link">Upload your photo</a>
                                </div>
                            </form>
                            <script>
                                $(function () {
                                    $("#upload_link").on('click', function (e) {
                                        e.preventDefault();
                                        $("#upload:hidden").trigger('click');
                                    });
                                });
                            </script>
                        </div>
                    </c:if>
                    <c:if test="${menu == null}">
                        <form action="newMenu" method="POST" enctype="multipart/form-data" class="w3-container">
                        </c:if>
                        <c:if test="${menu != null}">
                            <form action="updateMenu" method="POST" class="w3-container">
                            </c:if>
                            <table border='1'>
                                <tr>
                                    <td>menuID</td>
                                    <td><input type="text" name="menuID" value="<c:out value="${menu.menuID}"/>" required></td>
                                </tr>
                                <tr>
                                    <td>menuName</td>
                                    <td><input type="text" name="menuName" value="<c:out value="${menu.menuName}"/>" required></td>
                                </tr>
                                <tr>
                                    <td>menuDesc</td>
                                    <td><textarea name="menuDesc" cols="30" rows="5"><c:out value="${menu.menuDesc}"/></textarea></td>
                                </tr>
                                <tr>
                                    <td>menuCategory</td>
                                    <td>
                                        <select name="menuCategory" id="menuCategory" required>
                                            <option value="seafood">seafood</option>
                                            <option value="meat">meat</option>
                                            <option value="vegetable">vegetable</option>
                                            <option value="beverage">beverage</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>menuPrice</td>
                                    <td><input type="number" name="menuPrice" value="<c:out value="${menu.menuPrice}"/>" min="0" required></td>
                                </tr>
                                <c:if test="${menu == null}">
                                    <tr>
                                        <td>menuImage</td>
                                        <td>
                                            <input type="file" name="menuImage" id="menuImage" required>
                                            <img id="preview" src="#" alt="" width="200" height="200">
                                            <script>
                                                menuImage.onchange = evt => {
                                                    const [file] = menuImage.files
                                                    if (file) {
                                                        preview.src = URL.createObjectURL(file);
                                                        document.getElementById("preview").style.visibility = "visible";
                                                    }
                                                }
                                            </script>
                                        </td>
                                    </tr>
                                </c:if>
                                <tr>
                                    <td>menuAvailable</td>
                                    <td>
                                        <select name="menuAvailable" id="menuAvailable" required>
                                            <option value="available" selected="">available</option>
                                            <option value="not available">not available</option>
                                        </select>
                                    </td>
                                </tr>
                                <script>
                                    selectElement('menuCategory', '<c:out value="${menu.menuCategory}" />')
                                    selectElement('menuAvailable', '<c:out value="${menu.menuAvailable}" />')

                                    function selectElement(id, valueToSelect) {
                                        let element = document.getElementById(id);
                                        element.value = valueToSelect;
                                    }

                                    function imageDropdown() {
                                        document.getElementById("myDropdown").classList.toggle("show");
                                    }
                                    window.onclick = function (event) {
                                        if (!event.target.matches('.dropbtn')) {
                                            var dropdowns = document.getElementsByClassName("dropdown-content");
                                            var i;
                                            for (i = 0; i < dropdowns.length; i++) {
                                                var openDropdown = dropdowns[i];
                                                if (openDropdown.classList.contains('show')) {
                                                    openDropdown.classList.remove('show');
                                                }
                                            }
                                        }
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
                                        location.href = "<%=request.getContextPath()%>/menulist";
                                    };
                                </script>
                            </table>
                        </form>
                </div>
            </div>
        </div>
    </body>
</html>
