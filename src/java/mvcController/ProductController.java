/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package mvcController;

import dao.DAOCategory;
import dao.DAOProduct;
import entity.Product;
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
@WebServlet(name = "ProductController", urlPatterns = {"/ProductController"})
public class ProductController extends HttpServlet {

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
            DAOProduct dao = new DAOProduct();
            String go = request.getParameter("go");
            if (go == null) {
                go = "listAll";
            }
             if (go.equals("listAll")) {
                String sql = "select * from Product as a join Category as b on a.cateID = b.cateID";
                ResultSet rs = dao.getData(sql);
                String titleTable = "List of Product";
                //chuan bi du lieu cho jsp
                request.setAttribute("data", rs);
                request.setAttribute("title", titleTable);
                
                //call jsp
                RequestDispatcher dispath = request.getRequestDispatcher("/adminJSP/ViewProduct.jsp");
                //run(goi hoac chan)
                dispath.forward(request, response);
                
             }
             if(go.equals("insert")){
                 String submit = request.getParameter("submit");
                 if(submit == null){
                    DAOCategory daoC = new DAOCategory();
                    ResultSet rs = daoC.getData("select * from Category");
                    request.setAttribute("dataCat", rs);
                    //call jsp
                    RequestDispatcher dispath = request.getRequestDispatcher("/adminJSP/insertProduct.jsp");
                //run(goi hoac chan)
                    dispath.forward(request, response);
                 } else {
                     String pid = request.getParameter("pid");
                     String pname = request.getParameter("pname");
                     int CateID = Integer.parseInt(request.getParameter("CateID"));
                     int quantity = Integer.parseInt(request.getParameter("quantity"));
                     double price = Double.parseDouble(request.getParameter("price"));
                     String image = request.getParameter("image");
                     String description = request.getParameter("description");
                     int n = dao.addProductByPre(new Product(pid, pname, quantity, price, image, description, CateID));
                     response.sendRedirect("ProductController");
                 }
             }
             if(go.equals("update")){
                 String submit = request.getParameter("submit");
                 if(submit == null){
                     String pid = request.getParameter("id");
                     int cateID = Integer.parseInt(request.getParameter("cateID"));
                     String sql = "select * from Product where pid='" + pid +"'";
                     Vector<Product> vec = dao.getAllProduct(sql);
                     ResultSet rs = dao.getData("select * from Category");
                     request.setAttribute("data", vec);
                     request.setAttribute("cateId", cateID);
                     request.setAttribute("cateData", rs);
                     dispatch(request, response, "/adminJSP/updateProduct.jsp");
                 } else {
                     int cateID = Integer.parseInt(request.getParameter("CateID"));
                     String pid = request.getParameter("pid");
                     String pname = request.getParameter("pname");
                     int quantity = Integer.parseInt(request.getParameter("quantity"));
                     double price = Double.parseDouble(request.getParameter("price"));
                     String image = request.getParameter("image");
                     String description = request.getParameter("description");
                     int n = dao.updateProduct(new Product(pid, pname, quantity, price, image, description, cateID));
                     response.sendRedirect("ProductController");
                 }
             }
             if(go.equals("delete")){
                 String id = request.getParameter("id");
                 int n = dao.removeProduct(id);
                 response.sendRedirect("ProductController");
             }
             if(go.equals("search")){
                String name = request.getParameter("name");
                String sql = "select * from Product as a join Category as b on a.cateID = b.cateID where pname like '%" + name +"%'";
                ResultSet rs = dao.getData(sql);
                request.setAttribute("data", rs);
                String titleTable = "List of Product";
                dispatch(request, response, "/adminJSP/ViewProduct.jsp");
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
