/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.planing;

import DAO.DepartmentDAO;
import DAO.EmployeeDAO;
import controller.auth.AuthenticationServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Department;
import model.User;

@WebServlet("/add-plan")
public class AddPlanController extends AuthenticationServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        try {
            DepartmentDAO departmentDAO = new DepartmentDAO();
            EmployeeDAO employeeDAO = new EmployeeDAO();
            List<Department> listde = departmentDAO.getAllProduction();
            req.setAttribute("de", listde);
            req.setAttribute("manager", employeeDAO.getAllEmployeesByRoleId(5));
            req.getRequestDispatcher("view/planning/createPlan.jsp").forward(req, resp);
           
        } catch (SQLException ex) {
            Logger.getLogger(AddPlanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
