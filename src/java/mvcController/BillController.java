/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package mvcController;

import dao.DAOBill;
import dao.DAOBillDetail;
import dao.DAOCustomer;
import dao.DAOProduct;
import entity.Bill;
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
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pthanh
 */
@WebServlet(name = "BillController", urlPatterns = {"/BillController"})
public class BillController extends HttpServlet {

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
            DAOBill dao = new DAOBill();
            String go = request.getParameter("go");
            HttpSession session = request.getSession();
            if (go == null) {
                go = "listAll";
            }
            if (go.equals("insert")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    DAOCustomer daoC = new DAOCustomer();
                    ResultSet rs = daoC.getData("select * from Customer");
                    request.setAttribute("dataCus", rs);
                    //call jsp
                    RequestDispatcher dispath = request.getRequestDispatcher("/adminJSP/insertBill.jsp");
                    //run(goi hoac chan)
                    dispath.forward(request, response);
                } else {
                    String bid = request.getParameter("bid");
                    String recAddress = request.getParameter("recAddress");
                    String recPhone = request.getParameter("recPhone");
                    String note = request.getParameter("note");
                    double totalmoney = Double.parseDouble(request.getParameter("totalmoney"));
                    int status = Integer.parseInt(request.getParameter("status"));
                    int cusID = Integer.parseInt(request.getParameter("cusID"));
                    int n = dao.addBillByPre(new Bill(bid, recPhone, recAddress, recPhone, note, cusID));
                    response.sendRedirect("BillDetail");
                }
            }
            if (go.equals("listAll")) {
                String sql = "select * from Bill";
                ResultSet rs = dao.getData(sql);
                String titleTable = "List of Bill";
                //chuan bi du lieu cho jsp
                request.setAttribute("data", rs);
                request.setAttribute("title", titleTable);
                String error = (String) session.getAttribute("error");
                //call jsp
                RequestDispatcher dispath = request.getRequestDispatcher("/adminJSP/ViewBill.jsp");
                //run(goi hoac chan)
                dispath.forward(request, response);
            }
            if (go.equals("update")) {
                String submit = request.getParameter("submit");
                DAOBillDetail daob = new DAOBillDetail();
                DAOBill daobill = new DAOBill();
                String bid = "";
                if (submit == null) {
                    String id = request.getParameter("id");
                    String sql = "select * from BillDetail where bid='" + id + "'";
                    ResultSet rs = daob.getData(sql);
                    String sqlB = "select * from Bill where bid='" + id + "'";
                    ResultSet rsB = daobill.getData(sqlB);
                    int status_old = 0;
                    while (rsB.next()) {
                        status_old = rsB.getInt(7);
                    }
                    request.setAttribute("statusB", status_old);
                    request.setAttribute("data", rs);
                    dispatch(request, response, "/adminJSP/updateBill.jsp");
                } else {
                    bid = request.getParameter("bid");
                    Bill bi = dao.getAllBill("select * from Bill where bid='" + bid + "'").get(0);
                    String dateCreate = bi.getDateCreate();
                    String recAddress = bi.getRecAddress();
                    String recPhone = bi.getRecPhone();
                    String note = bi.getNote();
                    int oldStatus = bi.getStatus();
                    double totalMoney = bi.getTotalMoney();
                    int status = Integer.parseInt(request.getParameter("status"));
                    // the status is accept and can change
                    if (status >= oldStatus) {
                        int cid = bi.getCid();
                        int n = dao.UpdateBill(new Bill(bid, dateCreate, recAddress, recPhone, note, totalMoney, status, cid));
                        out.print("Status was change successfull!");
                        response.sendRedirect("BillController");
                    } else {
                        String error = "Can't not change the status to the previous!";
                        request.setAttribute("error", error);
                        String sql = "select * from BillDetail where bid='" + bid + "'";
                        ResultSet rs = daob.getData(sql);
                        request.setAttribute("data", rs);
                        String sqlB = "select * from Bill where bid='" + bid + "'";
                        ResultSet rsB = daobill.getData(sqlB);
                        int status_old = 0;
                        while (rsB.next()) {
                            status_old = rsB.getInt(7);
                        }
                        request.setAttribute("statusB", status_old);
                        dispatch(request, response, "/adminJSP/updateBill.jsp");
                    }

                }

            }
            if (go.equals("search")) {
                String name = request.getParameter("name");
                int status = 0;
                if(name.equalsIgnoreCase("wait")){
                    status = 0;
                } else if(name.equalsIgnoreCase("process")){
                    status = 1;
                } else if(name.equalsIgnoreCase("done")){
                    status = 2;
                } else if(name.equalsIgnoreCase("removed")){
                    status = 3;
                }
                String sql = "select * from Bill where status=" +status;
                ResultSet rs = dao.getData(sql);
                request.setAttribute("data", rs);
                String titleTable = "List of Product";
                dispatch(request, response, "/adminJSP/ViewBill.jsp");
                return;
            }
            if (go.equals("delete")) {
                String id = request.getParameter("id");
                int n = dao.removeBill(id);
                dispatch(request, response, "BillController?go=listAll");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BillController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void dispatch(HttpServletRequest request, HttpServletResponse response, String url) {
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
