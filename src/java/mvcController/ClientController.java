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
import entity.BillDetail;
import entity.Customer;
import entity.Product;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pthanh
 */
@WebServlet(name = "ClientController", urlPatterns = {"/ClientController"})
public class ClientController extends HttpServlet {

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
            DAOCustomer daoc = new DAOCustomer();
            String go = request.getParameter("go");
            if (go == null) {
                go = "listAll";
            }
            if (go.equals("listAll")) {
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
                RequestDispatcher dispath = request.getRequestDispatcher("/Client/index.jsp");
                //run(goi hoac chan)
                dispath.forward(request, response);
            }
            if (go.equals("displayProduct")) {
                int cid = Integer.parseInt(request.getParameter("cid"));
                String sql = "select * from Product as a join Category as b on a.cateId = b.cateID where a.cateid =" + cid;
                ResultSet rs = dao.getData(sql);
//                String titleTable = "List of Product";
                //chuan bi du lieu cho jsp
                request.setAttribute("dataProduct", rs);
                //data for menu

                String sqlMenu = "select * from Category";
                ResultSet rsMenu = dao.getData(sqlMenu);
                request.setAttribute("dataMenu", rsMenu);
                RequestDispatcher dispath
                        = request.getRequestDispatcher("/Client/index.jsp");
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
                        = request.getRequestDispatcher("/Client/index.jsp");
                //run
                dispath.forward(request, response);
            }
//            if (go.equals("addcart")) {
//                dispatch(request, response, "Client/add2Cart.jsp");
//            }
//            if (go.equals("checkout")) {
//                HttpSession sesstion = request.getSession();
//                sesstion.invalidate();
//                response.sendRedirect("ClientController");
//            }
            if (go.equals("login")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    dispatch(request, response, "Client/login.jsp");
                } else {
                    String username = request.getParameter("username");
                    String password = request.getParameter("password");
                    int cid = daoc.login(username, password);
                    if (cid == -1) {
                        String error = "Your username or password isn't correct! Try again";
                        request.setAttribute("error", error);
                        dispatch(request, response, "Client/login.jsp");
                    } else {
                        Customer cus = daoc.getAllCustomer("select * from Customer where cid='" + cid +"'").get(0);
                        if(cus.getAdmin() == 0){
                            HttpSession session = request.getSession();
                            session.setAttribute("cid", cid);
                            response.sendRedirect("ClientController");
                        } else {
                            HttpSession session = request.getSession();
                            session.setAttribute("cid", cid);
                            response.sendRedirect("AdminController");
                        }
                    }
                }
            }
            if (go.equals("register")) {
                String submit = request.getParameter("submit");
                if (submit == null) {

                    dispatch(request, response, "Client/register.jsp");
                } else {
                    String name = request.getParameter("cname");
                    String username = request.getParameter("username");
                    String password = request.getParameter("password");
                    String phone = request.getParameter("phone");
                    String address = request.getParameter("address");
                    int admin = 0;
                    boolean duplicateUsername = daoc.checkUsername(username);
                    if (duplicateUsername) {
                        String error = "The register is failed because the username is dupplicate";
                        request.setAttribute("error", error);
                        dispatch(request, response, "Client/register.jsp");
                    } else {
                        int n = daoc.addCustomerByPre(new Customer(name, username, password, phone, address, admin));
                        String error = "Register successfull! Please login to continue!";
                        request.setAttribute("error", error);
                        dispatch(request, response, "Client/login.jsp");
                    }
                }
            }
            if (go.equals("logout")) {
                HttpSession session = request.getSession();
                session.invalidate();
                response.sendRedirect("ClientController");
            }
            if (go.equals("cart")) {
                HttpSession session = request.getSession();
                //not login yet
                if (session.getAttribute("cid") == null) {
                    dispatch(request, response, "ClientController?go=login");
                } else {
                    Hashtable<String, Integer> cart = getCart(session);
                    Vector<Product> vec = new Vector<Product>();
                    Enumeration<String> ems = cart.keys();
                    while (ems.hasMoreElements()) {
                        String pid = ems.nextElement();
                        Product pro = dao.getAllProduct("select * from product where pid='" + pid + "'").get(0);
                        pro.setQuantity((int) cart.get(pid));
                        vec.add(pro);
                    }
                    request.setAttribute("cartlist", vec);
                    String sqlMenu = "select * from Category";
                    ResultSet rsMenu = dao.getData(sqlMenu);
//                String titleTable = "List of Product";
                    //chuan bi du lieu cho jsp
                    request.setAttribute("dataMenu", rsMenu);
                    dispatch(request, response, "Client/cart.jsp");
                }
            }
            if (go.equals("addcart")) {
                HttpSession session = request.getSession();
                if (session.getAttribute("cid") == null) {
                    dispatch(request, response, "ClientController?go=login");
                } else {
                    Hashtable<String, Integer> cart = getCart(session);
                    String pid = request.getParameter("pid");
                    String a = request.getParameter("amount");
                    int amount = a == null ? 1 : Integer.parseInt(a);
                    Object value = cart.get(pid);
                    if (value == null) {
                        cart.put(pid, amount);
                    } else {
                        cart.put(pid, (Integer) (value) + amount);
                    }
                    setCart(session, cart);
                    dispatch(request, response, "ClientController?go=cart");
                }
            }
            if (go.equals("updateCart")) {
                HttpSession session = request.getSession();
                Hashtable<String, Integer> cart = getCart(session);
                //Check if the action is to remove an item
                String remove = request.getParameter("remove");
                //if remove
                if (remove != null) {
                    cart.remove(remove);
                } else {
                    Enumeration<String> ems = request.getParameterNames();
                    while (ems.hasMoreElements()) {
                        try {
                            String key = ems.nextElement();
                            int newValue = Integer.parseInt(request.getParameter(key));
                            if (newValue <= 0) {
                                cart.remove(key);
                            } else {
                                cart.put(key, newValue);
                            }
                        } catch (NumberFormatException ex) {
                        }
                    }
                }
                setCart(session, cart);
                dispatch(request, response, "ClientController?go=cart");
            }
            if (go.equals("checkout")) {
                HttpSession session = request.getSession();
                Hashtable<String, Integer> cart = getCart(session);
                boolean canProceedtoCheckout = true;
                Enumeration<String> ems = cart.keys();
                if (cart.isEmpty()) {
                    //Cart is empty
                    dispatch(request, response, "ClientController?go=cart");
                    return;
                }
                //get pid and quantity from cart
                while (ems.hasMoreElements()) {
                    String pid = ems.nextElement();
                    int quantity = cart.get(pid);
                    Product pro = dao.getAllProduct("select * from Product where pid='" + pid + "'").get(0);
                    int stockAmount = pro.getQuantity();
                    if (stockAmount < quantity) {
                        String msg = "Cannot buy because the quantity in stock is not enough! please degree the quantity";
                        canProceedtoCheckout = false;
                        session.setAttribute("msg", msg);
                    }
                }
                // 
                if (canProceedtoCheckout) {
                    // sent bill to bill controller and save to database
                    placeOrder(session);
                    String msg = "Order successful!";
                    session.setAttribute("msg", msg);
                } else {
                    String msg = "Failed to Order";
                    session.setAttribute("msg", msg);
                }
                dispatch(request, response, "ClientController?go=cart");
            }
            if(go.equals("showAllCart")){
                int cid = Integer.parseInt(request.getParameter("cid"));
                String sql = "select * from Bill where cid=" + cid;
                DAOBill daoBill = new DAOBill();
                ResultSet rs = daoBill.getData(sql);
                request.setAttribute("dataBill", rs);
                dispatch(request, response, "/Client/showAllBill.jsp");
            }
            if(go.equals("removeBill")){
                String bid = request.getParameter("bid");
                DAOBill daoBill = new DAOBill();
                DAOBillDetail daoBillDetail = new DAOBillDetail();
                String sql = "select * from BillDetail where bid='" + bid +"'";
                ResultSet rs = dao.getData(sql);
                try {
                    while(rs.next()){
                        String pid = rs.getString(2);
                        int quantity = rs.getInt(3);
                        int oldQuantity = dao.getAllProduct("select * from Product where pid='" + pid +"'").get(0).getQuantity();
                        int n = dao.updateProductQuantity(pid, (oldQuantity + quantity));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
                }
                int n = daoBill.updateBillStatus(bid,3);
                String cid = request.getParameter("cid");
                dispatch(request, response, "ClientController?go=showAllCart&cid=" + cid);
            }
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

        public Hashtable<String, Integer> getCart(HttpSession session) {
        Hashtable<String, Integer> cart = (Hashtable<String, Integer>) session.getAttribute("cart");
        if (cart == null) {
            cart = new Hashtable<String, Integer>();
            setCart(session, cart);
        }
        return cart;
    }

    public void setCart(HttpSession session, Hashtable<String, Integer> cart) {
        session.setAttribute("cart", cart);
    }

    public void placeOrder(HttpSession session) {
        Hashtable<String, Integer> cart = getCart(session);
        // get pid set
        Enumeration<String> keys = cart.keys();
        //DAOs
        DAOProduct daoPro = new DAOProduct();
        DAOBill daoBill = new DAOBill();
        DAOBillDetail daoBd = new DAOBillDetail();
        DAOCustomer daoCus = new DAOCustomer();
        //Infos
        int cid = (int) session.getAttribute("cid");
        Customer cus = daoCus.getAllCustomer("select * from Customer where cid='" + cid + "'").get(0);
        //Create a new bill
        SimpleDateFormat formatter = new SimpleDateFormat("_dd/MM/yyyy_HH:mm:ss");
        String bid = "" + cid + formatter.format(new Date());
        String recAddress = cus.getAddress();
        String recPhone = cus.getPhone();
        String note = "";
        SimpleDateFormat formatdate = new SimpleDateFormat("yyyy-MM-dd dd:HH:mm:ss");
        String dateCreate = formatdate.format(new Date());
        Bill bill = new Bill(bid, dateCreate, recAddress, recPhone, note, cid);
        daoBill.addBillByPre(bill);
        double totalPay = 0;
        //Go through all elements
        while (keys.hasMoreElements()) {
            String pid = keys.nextElement();
            int amount = cart.get(pid);
            //get this product
            Product pro = daoPro.getAllProduct("select * from Product where pid='" + pid + "'").get(0);
            //Construct a bill detail
            double buyPrice = pro.getPrice();
            double subtotal = buyPrice * amount;
            totalPay += subtotal;
            BillDetail bd = new BillDetail(bid, pid, amount, buyPrice, subtotal);
            //Confirm buying
            daoBd.addBillDetailByPre(bd);
            //Reduce stock of items
            int oldQuantity = pro.getQuantity();
            pro.setQuantity(oldQuantity - amount);
            daoPro.updateProduct(pro);
        }
        daoBill.UpdateBill(new Bill(bid, dateCreate, recAddress, recPhone, note, totalPay, cid));
        //Wipe cart
        cart.clear();
        session.setAttribute("cart", cart);
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
