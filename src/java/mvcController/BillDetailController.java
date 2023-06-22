/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package mvcController;
import dao.DAOBill;
import dao.DAOBillDetail;
import dao.DAOProduct;
import entity.BillDetail;
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
@WebServlet(name = "BillDetailController", urlPatterns = {"/BillDetailController"})
public class BillDetailController extends HttpServlet {

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
            String go = request.getParameter("go");
            DAOBillDetail dao = new DAOBillDetail();
            if(go == null){
                go = "listAll";
            }
            if(go.equals("insert")){
                String submit = request.getParameter("submit");
                if(submit == null){
                    DAOBill daoB = new DAOBill();
                    ResultSet rsB = daoB.getData("select * from Bill");
                    
                    DAOProduct daoP = new DAOProduct();
                    ResultSet rsP = daoP.getData("select * from Product");
                    request.setAttribute("dataP", rsP);
                    request.setAttribute("dataB", rsB);

                    RequestDispatcher dispatch = request.getRequestDispatcher("/adminJSP/insertBillDetail.jsp");
                    dispatch.forward(request, response);
                } else {
                    String pid = request.getParameter("pid");
                    String bid = request.getParameter("bid");
                    int quantity = Integer.parseInt(request.getParameter("buyQuantity"));
                    double buyPrice = Double.parseDouble(request.getParameter("buyPrice"));
                    double subtotal = Double.parseDouble(request.getParameter("subtotal"));
                    int n = dao.addBillDetailByPre(new BillDetail(bid, pid, quantity, buyPrice, subtotal));
                    response.sendRedirect("BillDetailController");
                }
            }
            if(go.equals("listAll")){
                String sql = "select * from BillDetail";
                ResultSet rs = dao.getData(sql);
                String titleTable = "List of BillDetail";
                request.setAttribute("data", rs);
                request.setAttribute("title", titleTable);
                RequestDispatcher dispatch = request.getRequestDispatcher("/adminJSP/ViewBillDetail.jsp");
                dispatch.forward(request, response);
            }
            if(go.equals("update")){
                String submit = request.getParameter("update");
                if(submit == null){
                    String pid = request.getParameter("pid");
                    String bid = request.getParameter("bid");
                    Vector<BillDetail> vec = dao.getAllBillDetail("select * from BillDetail where pid='" + pid+"' and bid ='" + bid +"'");
                    request.setAttribute("data", vec);
                    dispatch(request, response, "/adminJSP/updateBillDetail.jsp");
                } else {
                    String bid = request.getParameter("bid");
                    String pid = request.getParameter("pid");
                    int buyQuantity = Integer.parseInt(request.getParameter("buyQuantity"));
                    double buyPrice = Double.parseDouble(request.getParameter("buyPrice"));
                    double subtotal = Double.parseDouble(request.getParameter("subtotal"));
                    int n = dao.UpdateBillDetail(new BillDetail(bid, pid, buyQuantity, buyPrice, subtotal));
                    response.sendRedirect("BillDetailController");
                }
            }
            if(go.equals("delete")){
                String pid = request.getParameter("pid");
                String bid = request.getParameter("bid");
                int n = dao.removeBillDetail(pid, bid);
                response.sendRedirect("BillDetailController");
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
