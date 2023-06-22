package dao;
import connecttest.DBConnect;
import entity.Customer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
public class DAOCustomer extends DBConnect{
//    public int AddCustomer(Customer customer){
//        int n = 0;
//        String sql = "INSERT INTO [Customer]\n" +
//"           ([cid]\n" +
//"           ,[cname]\n" +
//"           ,[username]\n" +
//"           ,[password]\n" +
//"           ,[address]\n" +
//"           ,[status])\n" +
//"     VALUES('"+customer.getCid()+"','"+customer.getCname()+
//       "','"+customer.getUsername()+"','"+customer.getPassword()+
//       "','"+customer.getAddress()+"',"+customer.getStatus()+")";
//        try {
//            // tao lenh truyen du lieu
//            Statement state = conn.createStatement();
//            // chay va nhan ket qua
//            n = state.executeUpdate(sql);
//        } catch (SQLException ex) {
//            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return n;
//    }
    public int addCustomerByPre(Customer customer){
        int n = 0;
        String sql = "INSERT INTO [Customer]\n" +
"           ([cname]\n" +
"           ,[username]\n" +
"           ,[password]\n" +
"           ,[phone]\n" +
"           ,[address]\n" +
"           ,[admin])\n" +
"     VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            //set parameter
            //pre.setDataType(index?,value);
            //dataType là kieu giu lieu cua truong;
            //index of? start 1
            pre.setString(1, customer.getCname());
            pre.setString(2, customer.getUsername());
            pre.setString(3, customer.getPassword());
            pre.setString(4, customer.getPhone());
            pre.setString(5, customer.getAddress());
            pre.setInt(6, customer.getAdmin());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    public int updateCustomer(Customer customer){
        int n = 0;
        String sql = "UPDATE [dbo].[Customer]\n" +
"   SET [cname] = ?\n" +
"      ,[username] = ?\n" +
"      ,[password] = ?\n" +
"      ,[phone] = ?\n" +
"      ,[address] = ?\n" +
" WHERE [cid] = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, customer.getCname());
            pre.setString(2, customer.getUsername());
            pre.setString(3, customer.getPassword());
            pre.setString(5, customer.getAddress());
            pre.setString(4, customer.getPhone());
            pre.setInt(6, customer.getCid());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    public void displayAll(){
        String sql = "select * from Customer";
//        Statement state = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY,
//                ResultSet.CONCUR_READ_ONLY);
        try {
            //default: con tro chi di xuong
            //read: chi doc khong sua
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while(rs.next()){
                /* dataType varName = rs.getDataType("fieldName|index"); */
                int cid = rs.getInt("cid");
                String name = rs.getString("cname");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                Customer cus = new Customer(cid, name, username, password, phone, address);
                System.out.println(cus.toString());
            }
            // scroll sens: con tro 2 chieu
            //sensitive: thread safe
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public Vector<Customer> getAllCustomer(String sql){
        Vector<Customer> vector = new Vector<Customer>();
        ResultSet rs = this.getData(sql);
        try {
            while(rs.next()){
                int cid = rs.getInt("cid");
                String name = rs.getString("cname");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                int admin = rs.getInt("admin");
                Customer cus = new Customer(cid, name, username, password, phone, address, admin);
                vector.add(cus);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    public int login(String user, String pass){
        String sql = "select * from Customer where username=? and "
               + " password = ?";
        PreparedStatement pre;
        try {
            pre = conn.prepareStatement(sql);
            pre.setString(1, user); pre.setString(2, pass);
            ResultSet rs = pre.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    public int removeCustomer(int id){
        int n = 0;
        String sql = "delete from customer where cid ='" + id +"'";
        try {
            //note: Customer --1 ----n--> Bill -> Khong xoa duoc
            //neu cid ton tai tren Bill
            ResultSet rs = this.getData("Select * from Bill where cid='" +id +"'");
            if(rs.next()){
                n=-1;
            }
            else {
                Statement state = conn.createStatement();
                n = state.executeUpdate(sql);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    public boolean checkUsername(String username){
        String sql = "select * from Customer where username=?";
        PreparedStatement pre;
        try {
            pre = conn.prepareStatement(sql);
            pre.setString(1, username);
            ResultSet rs = pre.executeQuery();
            if(rs.next()){
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
//    public static void main(String[] args) {
//        DAOCustomer dao = new DAOCustomer();
//        int n = dao.insertCustomer(new Customer("c103", "test", "C102", "123", "HN", 1));
//        if(n>0){
//            System.out.println("Inserted");
//        }  
//    }
    public static void main(String[] args) {
        DAOCustomer dao = new DAOCustomer();
        int n = dao.addCustomerByPre(new Customer("Cus2", "Cus2", "123456", "123456789", "HN", 0));
        if(n>0){
            System.out.println("Inserted");
        }
    }

    
}
