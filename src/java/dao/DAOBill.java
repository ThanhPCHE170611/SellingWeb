package dao;

import connecttest.DBConnect;
import entity.Bill;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOBill extends DBConnect {

    public int addBill(Bill bill) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Bill]\n"
                + "           ([bid]\n"
                + "           ,[dateCreate]\n"
                + "           ,[recAddress]\n"
                + "           ,[recPhone]\n"
                + "           ,[note]\n"
                + "           ,[totalMoney]\n"
                + "           ,[cid])\n"
                + "     VALUES('" + bill.getBid() + "','" + bill.getDateCreate() + "','" + bill.getRecAddress()
                + "','" + bill.getRecPhone() + "','" + bill.getNote() + "','" + bill.getTotalMoney()
                + "','" + bill.getCid() + "')";
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int addBillByPre(Bill bill) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Bill]\n"
                + "           ([bid]\n"
                + "           ,[dateCreate]\n"
                + "           ,[recAddress]\n"
                + "           ,[recPhone]\n"
                + "           ,[note]\n"
                + "           ,[totalMoney]\n"
                + "           ,[status]\n"
                + "           ,[cid])\n"
                + "     VALUES(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, bill.getBid());
            pre.setString(2, bill.getDateCreate());
            pre.setString(3, bill.getRecAddress());
            pre.setString(4, bill.getRecPhone());
            pre.setString(5, bill.getNote());
            pre.setDouble(6, bill.getTotalMoney());
            pre.setInt(7, bill.getStatus());
            pre.setInt(8, bill.getCid());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }
    public int updateBillStatus(String bid, int status){
        int n = 0;
        String sql = "UPDATE [dbo].[Bill]\n" +
"   SET [status] = ?" +
" WHERE [bid] = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, status);
            pre.setString(2, bid);
            if(pre.execute()){
                n = 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    public int UpdateBill(Bill bill) {
        int n = 0;
        String sql = "UPDATE [dbo].[Bill]\n"
                + "   SET [dateCreate] = ?\n"
                + "      ,[recAddress] = ?\n"
                + "      ,[recPhone] = ?\n"
                + "      ,[note] = ?\n"
                + "      ,[totalMoney] = ?\n"
                + "      ,[status] = ?\n"
                + "      ,[cid] = ?\n"
                + " WHERE [bid] = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, bill.getDateCreate());
            pre.setString(2, bill.getRecAddress());
            pre.setString(3, bill.getRecPhone());
            pre.setString(4, bill.getNote());
            pre.setDouble(5, bill.getTotalMoney());
            pre.setInt(6, bill.getStatus());
            pre.setInt(7, bill.getCid());
            pre.setString(8, bill.getBid());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public Vector<Bill> getAllBill(String sql) {
        Vector<Bill> vector = new Vector<Bill>();
        ResultSet rs = this.getData(sql);
        try {
            while (rs.next()) {
//            pre.setString(1, bill.getDateCreate());
//            pre.setString(2, bill.getRecAddress());
//            pre.setString(3, bill.getRecPhone());
//            pre.setString(4, bill.getNote());
//            pre.setDouble(5, bill.getTotalMoney());
//            pre.setInt(6, bill.getStatus());
//            pre.setString(7, bill.getCid());
//            pre.setString(8, bill.getBid());
                String dateCreate = rs.getString("dateCreate");
                String recAddress = rs.getString("recAddress");
                String recPhone = rs.getString("recPhone");
                String note = rs.getString("note");
                double totalMoney = rs.getDouble("totalMoney");
                int status = rs.getInt("status");
                int cid = rs.getInt("cid");
                String bid = rs.getString("bid");
                Bill bill = new Bill(bid, dateCreate, recAddress, recPhone, note, totalMoney, status, cid);
                vector.add(bill);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    public int removeBill(String bid){
        int n = 0;
        String sql = "delete from Bill where bid = '" + bid + "'";
        ResultSet rs = this.getData("select * from BillDetail where bid ='" +bid +"'");
        try {
            if(rs.next()){
                n = -1;
            } else {
                Statement state = conn.createStatement();
                n = state.executeUpdate(sql);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    public static void main(String[] args) {
        DAOBill dao = new DAOBill();
        int n = dao.removeBill("Bill1");
        if(n>0){
            System.out.println("Removed");
        }
    }
        
}
