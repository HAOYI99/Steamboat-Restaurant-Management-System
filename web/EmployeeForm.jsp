<%-- 
    Document   : EmployeeForm
    Created on : Jul 7, 2021, 12:02:36 AM
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
                        <h2>Employee Form</h2>
                    </div>
                    <c:if test="${employee != null}">
                        <div class="dropdown">
                            <img src="data:image/jpg;base64,${employee.empImage}" 
                                 width="200" height="180" onclick="imageDropdown()" class="dropbtn" />
                            <form id="formImage" action="updateEmpImage" method="POST" enctype="multipart/form-data" class="w3-container">
                                <div id="myDropdown" class="dropdown-content">
                                    <input type="text" name="empID" value="<c:out value="${employee.empID}"/>" hidden>
                                    <input id="upload" type="file" name="empImage" onchange="form.submit()" />
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
                    <c:if test="${employee == null}">
                        <form action="newEmp" method="POST" enctype="multipart/form-data" class="w3-container">
                        </c:if>
                        <c:if test="${employee != null}">
                            <form action="updateEmp" method="POST" class="w3-container">
                            </c:if>
                            <table border='1'>
                                <tr>
                                    <td>empID</td>
                                    <td><input type="text" name="empID" value="<c:out value="${employee.empID}"/>" required></td>
                                </tr>
                                <tr>
                                    <td>empName</td>
                                    <td><input type="text" name="empName" value="<c:out value="${employee.empName}"/>" required></td>
                                </tr>
                                <tr>
                                    <td>empIC</td>
                                    <td><input type="text" name="empIC" value="<c:out value="${employee.empIC}"/>" required></td>
                                </tr>
                                <tr>
                                    <td>empHPno</td>
                                    <td><input type="text" name="empHPno" value="<c:out value="${employee.empHPno}"/>" required></td>
                                </tr>
                                <tr>
                                    <td>empAddress</td>
                                    <td><textarea required name="empAddress" cols="30" rows="5"><c:out value="${employee.empAddress}"/></textarea></td>
                                </tr>
                                <c:if test="${employee == null}">
                                    <tr>
                                        <td>empImage</td>
                                        <td>
                                            <input type="file" name="empImage" id="empImage" required>
                                            <img id="preview" src="#" alt="" width="200" height="200">
                                            <script>
                                                empImage.onchange = evt => {
                                                    const [file] = empImage.files
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
                                    <td>empEmail</td>
                                    <td><input required type="email" name="empEmail" value="<c:out value="${employee.empEmail}"/>"></td>
                                </tr>
                                <tr>
                                    <td>empGender</td>
                                    <td>
                                        <input type="radio" name="empGender" value="Male" id="Male" checked>
                                        <label for="Male">Male</label>
                                        <input type="radio" name="empGender" value="Female" id="Female">
                                        <label for="Female">Female</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>empBank</td>
                                    <td><input type="text" name="empBank" value="<c:out value="${employee.empBank}"/>" required></td>
                                </tr>
                                <tr>
                                    <td>empBankAcc</td>
                                    <td><input type="text" name="empBankAcc" value="<c:out value="${employee.empBankAcc}"/>" required></td>
                                </tr>
                                <tr>
                                    <td>empBasicSalary</td>
                                    <td><input type="number" name="empBasicSalary" value="<c:out value="${employee.empBasicSalary}"/>" min="1200" required></td>
                                </tr>
                                <tr>
                                    <td>empBranch</td>
                                    <td>
                                        <select name="empBranch" id="empBranch" required>
                                            <option value="Melaka Raya">Melaka Raya</option>
                                            <option value="Kota Laksamana">Kota Laksamana</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>empPosition</td>
                                    <td>
                                        <select name="empPosition" id="empPosition" required>
                                            <option value="Staff">Staff</option>
                                            <option value="Supervisor">Supervisor</option>
                                            <option value="Manager">Manager</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>empHireDate</td>
                                    <td><input type="date" name="empHireDate" id="empHireDate" required></td>
                                </tr>
                                <script>
                                    radioElement('<c:out value="${employee.empGender}" />')
                                    selectElement('empBranch', '<c:out value="${employee.empBranch}" />')
                                    selectElement('empPosition', '<c:out value="${employee.empPosition}" />')
                                    setDate()

                                    function selectElement(id, valueToSelect) {
                                        let element = document.getElementById(id);
                                        element.value = valueToSelect;
                                    }

                                    function radioElement(id) {
                                        let radiobtn = document.getElementById(id);
                                        radiobtn.checked = true;
                                    }

                                    function setDate() {
                                        document.getElementById("empHireDate").value = "<c:out value="${employee.empHireDate}"/>";
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
                                        <button type="button" id="cancel" >Cancel</button>
                                    </td>
                                    <td></td>
                                </tr>
                                <script>
                                    document.getElementById("cancel").onclick = function () {
                                        location.href = "<%=request.getContextPath()%>/emplist";
                                    };
                                </script>
                            </table>
                        </form>
                </div>
            </div>
        </div>
    </body>
</html>