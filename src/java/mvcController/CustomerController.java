/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package mvcController;

import dao.DAOCategory;
import dao.DAOCustomer;
import entity.Customer;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pthanh
 */
@WebServlet(name = "CustomerController", urlPatterns = {"/CustomerController"})
public class CustomerController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            DAOCustomer dao = new DAOCustomer();
            String go = request.getParameter("go"); 
            if (go == null) {
                go = "listAll";
            }
            if(go.equals("listAll")){
                String sql = "select * from Customer";
                ResultSet rs = dao.getData(sql);
                String titleTable = "List of Customer";
                request.setAttribute("data", rs);
                request.setAttribute("title", titleTable);
                RequestDispatcher dispatch = request.getRequestDispatcher("/adminJSP/ViewCustomer.jsp");
                dispatch.forward(request, response);
            }
            if(go.equals("update")){
                String submit = request.getParameter("submit");
                if(submit == null){
                    String id = request.getParameter("id");
                    String sql = "select * from Customer where cid='" + id + "'";
                    Vector vec = dao.getAllCustomer(sql);
                    request.setAttribute("data", vec);
                    dispatch(request, response, "/adminJSP/updateCustomer.jsp");
                } else {
                    String Cid = request.getParameter("cid");
                    String cname = request.getParameter("cname");
                    String username = request.getParameter("username");
                    String password = request.getParameter("password");
                    String address = request.getParameter("address");
                    int status = Integer.parseInt(request.getParameter("status"));
                    int n = dao.updateCustomer(new Customer(Cid, cname, username, password, address, status));
                    response.sendRedirect("CustomerController");
                }
            }
            if(go.equals("delete")){
                int id = Integer.parseInt(request.getParameter("id"));
                int n = dao.removeCustomer(id);
                response.sendRedirect("CustomerController");
            }
            if(go.equals("search")){
                String name = request.getParameter("name");
                String sql = "select * from Customer where cname like '%" + name +"%'";
                ResultSet rs = dao.getData(sql);
                request.setAttribute("data", rs);
                String titleTable = "List of Customer";
                dispatch(request, response, "/adminJSP/ViewCustomer.jsp");
                return;
            }
        }
    }
    void dispatch(HttpServletRequest request, HttpServletResponse response, String url){
        RequestDispatcher disp = request.getRequestDispatcher(url);
        try {
            //run
            disp.forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
