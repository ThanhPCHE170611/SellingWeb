
package dao;
import connecttest.DBConnect;
import entity.Category;
import entity.Customer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
public class DAOCategory extends DBConnect{
    public int addCategory(Category category){
        int n = 0;
        String sql = "INSERT INTO [dbo].[Category]\n" +
"           ([cateName]\n" +
"     VALUES ('"+category.getCateName()+"')";
        try {
            
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    public int addCategoryByPre(Category category){
        int n = 0;
        String sql = "INSERT INTO [dbo].[Category]\n" +
"           ([cateName]\n" +
"     VALUES (?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, category.getCateName());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    public int UpdateCategory(Category category){
        int n = 0;
        String sql ="UPDATE [dbo].[Category]\n" +
"   SET [cateName] = ?\n" +
" WHERE [cateId] = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, category.getCateName());
            pre.setInt(2, category.getCateId());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    public void displayAll(){
        String sql = "select * from Category";
        try {
            //default: con tro chi di xuong
            //read: chi doc khong sua
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while(rs.next()){
                /* dataType varName = rs.getDataType("fieldName|index"); */
                String name = rs.getString("cateName");
                int cateid = rs.getInt("cateId");
                Category cat = new Category(cateid, name);
                System.out.println(cat.toString());
            }
            // scroll sens: con tro 2 chieu
            //sensitive: thread safe
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public Vector<Category> getAllCategory(String sql){
        Vector<Category> vector = new Vector<Category>();
        ResultSet rs = this.getData(sql);
        try {
            while(rs.next()){
                String name = rs.getString("cateName");
                int cateid = rs.getInt("cateId");
                Category cat = new Category(cateid, name);
                vector.add(cat);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    public int removeCategory(String id){
        int n = 0;
        String sql = "delete from Category where cateId ='" + id +"'";
        try {
            //note: Customer --1 ----n--> Bill -> Khong xoa duoc
            //neu cid ton tai tren Bill
            ResultSet rs = this.getData("Select * from Product where cateID='" +id+"'");
            if(rs.next()){
                n=-1;
            }
            else {
                Statement state = conn.createStatement();
                n = state.executeUpdate(sql);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    public static void main(String[] args) {
        DAOCategory dao = new DAOCategory();
        int n = dao.removeCategory("1");
        if(n>0){
            System.out.println("removed");
        }
    }
}
