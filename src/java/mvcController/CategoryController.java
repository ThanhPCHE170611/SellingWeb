/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package mvcController;
import dao.DAOCategory;
import entity.Category;
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
@WebServlet(name = "CategoryController", urlPatterns = {"/CategoryController"})
public class CategoryController extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            DAOCategory dao = new DAOCategory();
            String go = request.getParameter("go");
            if (go == null) {
                go = "listAll";
            }
            if(go.equals("listAll")){
                String sql = "select * from Category";
                ResultSet rs = dao.getData(sql);
                String titleTable = "List of Category";
                request.setAttribute("data", rs);
                request.setAttribute("title", titleTable);
                RequestDispatcher dispatch = request.getRequestDispatcher("/adminJSP/ViewCategory.jsp");
                dispatch.forward(request, response);
            }
            if(go.equals("update")){
                String submit = request.getParameter("submit");
                if(submit == null){
                    String id = request.getParameter("id");
                    String sql = "select * from Category where cateId='" + id + "'";
                    Vector vec = dao.getAllCategory(sql);
                    request.setAttribute("data", vec);
                    dispatch(request, response, "/adminJSP/updateCategory.jsp");
                } else {
                    int cateID = Integer.parseInt(request.getParameter("cusID"));
                    String cateName = request.getParameter("cateName");
                    int n = dao.UpdateCategory(new Category(cateID, cateName));
                    response.sendRedirect("CategoryController");
                }
            }
            if(go.equals("delete")){
                String id = request.getParameter("id");
                int n = dao.removeCategory(id);
                response.sendRedirect("CategoryController");
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
