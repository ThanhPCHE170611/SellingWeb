
package dao;
import connecttest.DBConnect;
import entity.BillDetail;
import entity.Customer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
public class DAOBillDetail extends DBConnect{
    public int addBillDetail(BillDetail billdetail){
        int n = 0;
        String sql = "INSERT INTO [dbo].[BillDetail]\n" +
"           ([bid]\n" +
"           ,[pid]\n" +
"           ,[buyQuantity]\n" +
"           ,[buyPrice]\n" +
"           ,[subtotal])\n" +
"     VALUES('"+billdetail.getBid()+"','"+billdetail.getPid()+
                "','"+billdetail.getBuyQuantity()+"','"+billdetail.getBuyPrice()+
                "','"+billdetail.getSubtotal()+"')";
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOBillDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    public int addBillDetailByPre(BillDetail billdetail){
        int n = 0;
        String sql = "INSERT INTO [dbo].[BillDetail]\n" +
"           ([bid]\n" +
"           ,[pid]\n" +
"           ,[buyQuantity]\n" +
"           ,[buyPrice]\n" +
"           ,[subtotal])\n" +
"     VALUES(?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, billdetail.getBid());
            pre.setString(2, billdetail.getPid());
            pre.setInt(3, billdetail.getBuyQuantity());
            pre.setDouble(4, billdetail.getBuyPrice());
            pre.setDouble(5, billdetail.getSubtotal());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOBillDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    public int UpdateBillDetail(BillDetail billdetail){
        int n = 0;
        String sql = "UPDATE [dbo].[BillDetail]\n" +
"   SET [buyQuantity] = ?\n" +
"      ,[buyPrice] = ?\n" +
"      ,[subtotal] = ?\n" +
" WHERE [bid] = ? and [pid] = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, billdetail.getBuyQuantity());
            pre.setDouble(2, billdetail.getBuyPrice());
            pre.setDouble(3, billdetail.getSubtotal());
            pre.setString(4, billdetail.getBid());
            pre.setString(5, billdetail.getPid());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOBillDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    public Vector<BillDetail> getAllBillDetail(String sql){
        Vector<BillDetail> vector = new Vector<BillDetail>();
        ResultSet rs = this.getData(sql);
        try {
            while(rs.next()){
//                pre.setInt(1, billdetail.getBuyQuantity());
//            pre.setDouble(2, billdetail.getBuyPrice());
//            pre.setDouble(3, billdetail.getSubtotal());
//            pre.setString(4, billdetail.getBid());
//            pre.setString(5, billdetail.getPid());
              int buyquantity = rs.getInt("buyQuantity");
              double buyprice = rs.getDouble("buyPrice");
              double subtotal = rs.getDouble("subtotal");
              String bid = rs.getString("bid");
              String pid = rs.getString("pid");
                BillDetail bl = new BillDetail(bid, pid, buyquantity, buyprice, subtotal);
                vector.add(bl);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    public int removeBillDetail(String pid, String bid){
        int n = 0;
        String sql = "delete from BillDetail where pid ='" + pid +"' and bid ='" + bid +"'";
        try {
                Statement state = conn.createStatement();
                n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOBillDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    public static void main(String[] args) {
        DAOBillDetail dao = new DAOBillDetail();
        int n = dao.addBillDetailByPre(new BillDetail("B01", "P01", 2, 10.5, 20.1));
        if(n>0){
            System.out.println("Inserted");
        }
    }

    public int removeBillDetail(String bid) {
        int n = 0;
        String sql = "delete from BillDetail where bid ='" + bid +"'";
        try {
                Statement state = conn.createStatement();
                n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOBillDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
}
