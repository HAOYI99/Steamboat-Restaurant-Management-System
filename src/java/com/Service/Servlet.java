/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Service;

import com.Controller.*;
import com.Model.*;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author IM10
 */
@WebServlet("/")
@MultipartConfig(maxFileSize = 41943040)//40mb
public class Servlet extends HttpServlet {

    private EmployeeDAO employeeDAO;
    private MenuDAO menuDAO;
    private WorkSectionDAO workSectionDAO;
    private OrderDAO orderDAO;

    public void init() {
        employeeDAO = new EmployeeDAO();
        menuDAO = new MenuDAO();
        workSectionDAO = new WorkSectionDAO();
        orderDAO = new OrderDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        try {
            switch (action) {
                /**
                 * ********************************
                 */
                case "/emplist":
                    employeeList(request, response);
                    break;
                case "/newEmp":
                    newEmployee(request, response);
                    break;
                case "/updateEmp":
                    updateEmployee(request, response);
                    break;
                case "/updateEmpImage":
                    updateEmpImage(request, response);
                    break;
                case "/deleteEmp":
                    deleteEmployee(request, response);
                    break;
                case "/newEmpForm":
                    NewEmpForm(request, response);
                    break;
                case "/editEmpForm":
                    EditEmpForm(request, response);
                    break;
                /**
                 * **********************************
                 */
                case "/menulist":
                    menuList(request, response);
                    break;
                case "/newMenu":
                    newMenu(request, response);
                    break;
                case "/updateMenu":
                    updateMenu(request, response);
                    break;
                case "/updateMenuImage":
                    updateMenuImage(request, response);
                    break;
                case "/deleteMenu":
                    deleteMenu(request, response);
                    break;
                case "/newMenuForm":
                    NewMenuForm(request, response);
                    break;
                case "/editMenuForm":
                    EditMenuForm(request, response);
                    break;
                /**
                 * ************************************
                 */
                case "/worksectionlist":
                    workSectionList(request, response);
                    break;
                case "/AssignWork":
                    AssignWork(request, response);
                    break;
                case "/updateWS":
                    updateWorkSection(request, response);
                    break;
                case "/deleteWS":
                    deleteWorkSection(request, response);
                    break;
                case "/newWSForm":
                    NewWSForm(request, response);
                    break;
                case "/editWSForm":
                    EditWSForm(request, response);
                    break;
                /**
                 * ************************************
                 */
                case "/orderpage":
                    orderPage(request, response);
                    break;
                case "/createOrder":
                    createOrder(request, response);
                    break;
                case "/deleteOrder":
                    deleteOrder(request, response);
                    break;
                case "/confirmOrder":
                    ConfirmOrder(request, response);
                    break;
                case "/myOrder":
                    showMyOrder(request, response);
                    break;
                case "/cancelMyOrder":
                    cancelOrder(request, response);
                    break;
                case "/cancelAllOrder":
                    cancelAllOrder(request, response);
                    break;
                case "/makePayment":
                    makePayment(request, response);
                    break;
                case "/completePayment":
                    completePayment(request, response);
                    break;
                case "/chooseTable":
                    chooseTable(request, response);
                    break;
                default:

                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    /**
     * ***********************************************************************
     */
    /*                               Employee                                     */
    /**
     * ***********************************************************************
     */
    private void NewEmpForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("EmployeeForm.jsp");
        dispatcher.forward(request, response);
    }

    private void EditEmpForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {

        String empID = request.getParameter("empID");
        Employee existingEmployee = employeeDAO.selectEmployee(empID);
        RequestDispatcher dispatcher = request.getRequestDispatcher("EmployeeForm.jsp");
        request.setAttribute("employee", existingEmployee);
        dispatcher.forward(request, response);
    }

    private void newEmployee(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        String empID = request.getParameter("empID");
        String empName = request.getParameter("empName");
        String empIC = request.getParameter("empIC");
        String empHPno = request.getParameter("empHPno");
        String empAddress = request.getParameter("empAddress");
        Part part = request.getPart("empImage");
        InputStream empImage = part.getInputStream();
        String empEmail = request.getParameter("empEmail");
        String empGender = request.getParameter("empGender");
        String empBank = request.getParameter("empBank");
        String empBankAcc = request.getParameter("empBankAcc");
        double empBasicSalary = Double.parseDouble(request.getParameter("empBasicSalary"));
        String empBranch = request.getParameter("empBranch");
        String empPosition = request.getParameter("empPosition");
        String empHireDate = request.getParameter("empHireDate");

        Employee newEmployee = new Employee(empID, empName, empIC, empHPno, empAddress,
                empImage, empEmail, empGender, empBank, empBankAcc,
                empBasicSalary, empBranch, empPosition, empHireDate);
        boolean success = employeeDAO.insertEmployee(newEmployee);
        if (success) {
            response.sendRedirect("emplist");
        }
    }

    private void employeeList(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        List<Employee> employeelist = employeeDAO.selectAllEmployees();
        request.setAttribute("employeelist", employeelist);
        RequestDispatcher dispatcher = request.getRequestDispatcher("EmployeeList.jsp");
        dispatcher.forward(request, response);
    }

    private void updateEmployee(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        String empID = request.getParameter("empID");
        String empName = request.getParameter("empName");
        String empIC = request.getParameter("empIC");
        String empHPno = request.getParameter("empHPno");
        String empAddress = request.getParameter("empAddress");
        String empEmail = request.getParameter("empEmail");
        String empGender = request.getParameter("empGender");
        String empBank = request.getParameter("empBank");
        String empBankAcc = request.getParameter("empBankAcc");
        double empBasicSalary = Double.parseDouble(request.getParameter("empBasicSalary"));
        String empBranch = request.getParameter("empBranch");
        String empPosition = request.getParameter("empPosition");
        String empHireDate = request.getParameter("empHireDate");

        Employee employee = new Employee(empID, empName, empIC, empHPno,
                empAddress, empEmail, empGender, empBank, empBankAcc,
                empBasicSalary, empBranch, empPosition, empHireDate);
        boolean success = employeeDAO.updateEmployee(employee);
        if (success) {
            response.sendRedirect("emplist");
        }
    }

    private void updateEmpImage(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        String empID = request.getParameter("empID");
        Part part = request.getPart("empImage");
        InputStream empImage = part.getInputStream();
        boolean success = employeeDAO.updateEmpImage(empID, empImage);
        if (success) {
            response.sendRedirect("editEmpForm?empID=" + empID);
        }
    }

    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        String empID = request.getParameter("empID");
        employeeDAO.deleteEmployee(empID);
        response.sendRedirect("emplist");
    }

    /**
     * ***********************************************************************
     */
    /*                               Menu                                     */
    /**
     * ***********************************************************************
     */
    private void NewMenuForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("MenuForm.jsp");
        dispatcher.forward(request, response);
    }

    private void EditMenuForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {

        String menuID = request.getParameter("menuID");
        Menu existingMenu = menuDAO.selectMenu(menuID);
        RequestDispatcher dispatcher = request.getRequestDispatcher("MenuForm.jsp");
        request.setAttribute("menu", existingMenu);
        dispatcher.forward(request, response);
    }

    private void newMenu(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        String menuID = request.getParameter("menuID");
        String menuName = request.getParameter("menuName");
        String menuDesc = request.getParameter("menuDesc");
        String menuCategory = request.getParameter("menuCategory");
        double menuPrice = Double.parseDouble(request.getParameter("menuPrice"));
        Part part = request.getPart("menuImage");
        InputStream menuImage = part.getInputStream();
        String menuAvailable = request.getParameter("menuAvailable");

        Menu newMenu = new Menu(menuID, menuName, menuDesc, menuCategory, menuPrice, menuImage, menuAvailable);
        boolean success = menuDAO.insertMenu(newMenu);
        if (success) {
            response.sendRedirect("menulist");
        }
    }

    private void menuList(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        List<Menu> menulist = menuDAO.selectAllMenus();
        request.setAttribute("menulist", menulist);
        RequestDispatcher dispatcher = request.getRequestDispatcher("MenuList.jsp");
        dispatcher.forward(request, response);
    }

    private void updateMenu(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        String menuID = request.getParameter("menuID");
        String menuName = request.getParameter("menuName");
        String menuDesc = request.getParameter("menuDesc");
        String menuCategory = request.getParameter("menuCategory");
        double menuPrice = Double.parseDouble(request.getParameter("menuPrice"));
        String menuAvailable = request.getParameter("menuAvailable");

        Menu menu = new Menu(menuID, menuName, menuDesc, menuCategory, menuPrice, menuAvailable);
        boolean success = menuDAO.updateMenu(menu);
        if (success) {
            response.sendRedirect("menulist");
        }
    }

    private void updateMenuImage(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        String menuID = request.getParameter("menuID");
        Part part = request.getPart("menuImage");
        InputStream menuImage = part.getInputStream();
        boolean success = menuDAO.updateMenuImage(menuID, menuImage);
        if (success) {
            response.sendRedirect("editMenuForm?menuID=" + menuID);
        }
    }

    private void deleteMenu(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        String menuID = request.getParameter("menuID");
        menuDAO.deleteMenu(menuID);
        response.sendRedirect("menulist");
    }

    /**
     * ***********************************************************************
     */
    /*                               Work Section                             */
    /**
     * ***********************************************************************
     */
    private void NewWSForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<WorkSection> worksection = workSectionDAO.selectAllWorkSection();
        List<Employee> employee = employeeDAO.selectEmpName();

        RequestDispatcher dispatcher = request.getRequestDispatcher("WorkSectionForm.jsp");
        request.setAttribute("worksection", worksection);
        request.setAttribute("employee", employee);
        dispatcher.forward(request, response);
    }

    private void EditWSForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {

        int ewID = Integer.parseInt(request.getParameter("ewID"));
        WorkSection existingEmpWork = workSectionDAO.selectEmpWork(ewID);
        List<WorkSection> worksection = workSectionDAO.selectAllWorkSection();
        List<Employee> employee = employeeDAO.selectEmpName();
        RequestDispatcher dispatcher = request.getRequestDispatcher("WorkSectionForm.jsp");
        request.setAttribute("empwork", existingEmpWork);
        request.setAttribute("worksection", worksection);
        request.setAttribute("employee", employee);
        dispatcher.forward(request, response);
    }

    private void workSectionList(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<WorkSection> empWorkList = workSectionDAO.selectAllEmpWork();
        request.setAttribute("empworklist", empWorkList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WorkSectionList.jsp");
        dispatcher.forward(request, response);
    }

    private void AssignWork(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        int sectionID = Integer.parseInt(request.getParameter("sectionID"));
        String empID = request.getParameter("empID");
        String duty = request.getParameter("duty");

        WorkSection newWorkSection = new WorkSection(sectionID, empID, duty);
        boolean success = workSectionDAO.insertEmpWork(newWorkSection);
        if (success) {
            response.sendRedirect("worksectionlist");
        }
    }

    private void updateWorkSection(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        int sectionID = Integer.parseInt(request.getParameter("sectionID"));
        String empID = request.getParameter("empID");
        String duty = request.getParameter("duty");
        int ewID = Integer.parseInt(request.getParameter("ewID"));

        WorkSection workSection = new WorkSection(ewID, sectionID, empID, duty);
        boolean success = workSectionDAO.updateEmpWork(workSection);
        if (success) {
            response.sendRedirect("worksectionlist");
        }
    }

    private void deleteWorkSection(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        int ewID = Integer.parseInt(request.getParameter("ewID"));
        workSectionDAO.deleteWorkSection(ewID);
        response.sendRedirect("worksectionlist");
    }

    /**
     * ***********************************************************************
     */
    /*                               Order                                  */
    /**
     * ***********************************************************************
     */
    private void orderPage(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String tableID = request.getParameter("tableID");
        List<Order> orderlist = orderDAO.selectOrderList(tableID);
        List<Menu> menulist = menuDAO.selectAvailableMenu();
        request.setAttribute("menulist", menulist);
        request.setAttribute("orderlist", orderlist);
        request.setAttribute("tableID", tableID);
        RequestDispatcher dispatcher = request.getRequestDispatcher("OrderPage.jsp");
        dispatcher.forward(request, response);
    }

    private void createOrder(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        String tableID = request.getParameter("tableID");
        String menuID = request.getParameter("menuID");
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        Order newOrder = new Order(menuID, quantity, tableID);
        boolean success = orderDAO.insertMenu(newOrder);
        if (success) {
            response.sendRedirect("orderpage?tableID=" + tableID);
        }
    }

    private void deleteOrder(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        String tableID = request.getParameter("tableID");
        int orderID = Integer.parseInt(request.getParameter("orderID"));
        orderDAO.deleteOrder(orderID);
        response.sendRedirect("orderpage?tableID=" + tableID);
    }

    private void ConfirmOrder(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        String tableID = request.getParameter("tableID");
        boolean success = orderDAO.confirmOrder(tableID);
        if (success) {
            response.sendRedirect("myOrder?tableID=" + tableID);
        }
    }

    private void showMyOrder(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String tableID = request.getParameter("tableID");
        List<Order> customerorderlist = orderDAO.showCustomerOrder(tableID);
        if (customerorderlist.isEmpty()) {
            System.out.println("empty list");
        } else {
            request.setAttribute("customerorderlist", customerorderlist);
            request.setAttribute("tableID", tableID);
            RequestDispatcher dispatcher = request.getRequestDispatcher("myOrder.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void cancelOrder(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        String tableID = request.getParameter("tableID");
        int orderID = Integer.parseInt(request.getParameter("orderID"));
        boolean success = orderDAO.cancelOneOrder(orderID, tableID);
        if (success) {
            response.sendRedirect("myOrder?tableID=" + tableID);
        }
    }

    private void cancelAllOrder(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        String tableID = request.getParameter("tableID");
        boolean success = orderDAO.cancelAllOrder(tableID);
        if (success) {
            response.sendRedirect("myOrder?tableID=" + tableID);
        }
    }

    private void makePayment(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {

        String tableID = request.getParameter("tableID");
        List<Order> receiptlist = orderDAO.getReceiptList(tableID);
        double receiptTotalPrice = orderDAO.getReceiptTotalPrice(tableID);
        RequestDispatcher dispatcher = request.getRequestDispatcher("makePayment.jsp");
        request.setAttribute("receiptlist", receiptlist);
        request.setAttribute("tableID", tableID);
        request.setAttribute("receiptTotalPrice", receiptTotalPrice);
        dispatcher.forward(request, response);
    }

    private void completePayment(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        String tableID = request.getParameter("tableID");
        double balance = Double.parseDouble(request.getParameter("balance"));
        double paymentAmount = Double.parseDouble(request.getParameter("paymentAmount"));
        orderDAO.generateReceipt(tableID, balance, paymentAmount);
        orderDAO.completePayment(tableID);
        chooseTable(request, response);
    }
    
    private void chooseTable(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("chooseTable.jsp");
        dispatcher.forward(request, response);
    }
}
