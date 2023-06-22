
package dao;
import connecttest.DBConnect;
import entity.Admin;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
public class DAOAdmin extends DBConnect{
    public int addAdmin(Admin admin){
        int n = 0;
    String sql = "INSERT INTO [dbo].[admin]\n" +
"           ([admin]\n" +
"           ,[password])\n" +
"     VALUES('"+admin.getAdmin()+"','"+admin.getPassword()+"')";
        try {
            Statement statemen = conn.createStatement();
            n = statemen.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    public int addAdminByPre(Admin admin){
        int n = 0;
        String sql = "INSERT INTO [dbo].[admin]\n" +
"           ([admin]\n" +
"           ,[password])\n" +
"     VALUES(?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, admin.getAdmin());
            pre.setString(2, admin.getPassword());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    public int UpdateAdmin(Admin admin){
        int n = 0;
        String sql = "UPDATE [dbo].[admin]\n" +
"   SET [password] = ?\n" +
" WHERE [admin] = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, admin.getPassword());
            pre.setString(2, admin.getAdmin());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    public Vector<Admin> getAllAdmin(String sql){
        Vector<Admin> vector = new Vector<Admin>();
        ResultSet rs = this.getData(sql);
        try {
            while(rs.next()){
                String admin = rs.getString("admin");
                String password = rs.getString("password");
                Admin Admin = new Admin(admin, password);
                vector.add(Admin);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    public int removeAdmin(String admin){
        int n = 0;
        String sql = "Delete from admin where admin = '" + admin+"'";
        Statement state;
        try {
            state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    public static void main(String[] args) {
        DAOAdmin dao = new DAOAdmin();
        int n = dao.removeAdmin("admin1");
        if(n>0){
            System.out.println("Success");
        }
    }
}
