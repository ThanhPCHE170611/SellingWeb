/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package mvcController;

import dao.DAOCustomer;
import dao.DAOProduct;
import entity.Customer;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pthanh
 */
@WebServlet(name = "ShopController", urlPatterns = {"/ShopController"})
public class ShopController extends HttpServlet {

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
            DAOProduct dao = new DAOProduct();
            String go = request.getParameter("go");
            DAOCustomer daoc = new DAOCustomer();
            if (go == null) {
                go = "listAll";
            }
            if(go.equals("listAll")){
                //data for Product
                String sql = "select * from Product";
                ResultSet rs = dao.getData(sql);
                //chuan bi du lieu cho jsp
                request.setAttribute("dataProduct", rs);
                
                //data for menu
                String sqlMenu = "select * from Category";
                ResultSet rsMenu = dao.getData(sqlMenu);
//                String titleTable = "List of Product";
                //chuan bi du lieu cho jsp
                request.setAttribute("dataMenu", rsMenu);
                //call jsp
                RequestDispatcher dispath = request.getRequestDispatcher("/Client/shop.jsp");
                //run(goi hoac chan)
                dispath.forward(request, response);
            }
            if (go.equals("displayProduct")) {
                int cid = Integer.parseInt(request.getParameter("cid"));
                String sql = "select * from Product as a join Category as b on a.cateId = b.cateID where a.cateid ='" + cid + "'";
                ResultSet rs = dao.getData(sql);
//                String titleTable = "List of Product";
                //chuan bi du lieu cho jsp
                request.setAttribute("dataProduct", rs);
                //data for menu

                String sqlMenu = "select * from Category";
                ResultSet rsMenu = dao.getData(sqlMenu);
                request.setAttribute("dataMenu", rsMenu);
                RequestDispatcher dispath
                        = request.getRequestDispatcher("/Client/shop.jsp");
                //run
                dispath.forward(request, response);
            }
            if (go.equals("search")) {
                String pname = request.getParameter("pname");
                String sql = "select * from Product as a join Category as b on a.cateId = b.cateID where a.pname LIKE'%" + pname + "%'";
                ResultSet rs = dao.getData(sql);
//                String titleTable = "List of Product";
                //chuan bi du lieu cho jsp
                request.setAttribute("dataProduct", rs);
                //data for menu

                String sqlMenu = "select * from Category";
                ResultSet rsMenu = dao.getData(sqlMenu);
                request.setAttribute("dataMenu", rsMenu);
                request.setAttribute("searchDefault", pname);
                RequestDispatcher dispath
                        = request.getRequestDispatcher("/Client/shop.jsp");
                //run
                dispath.forward(request, response);
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
