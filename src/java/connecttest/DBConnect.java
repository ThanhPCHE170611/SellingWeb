package connecttest;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class DBConnect {
    public Connection conn = null;
    public DBConnect(){
        this("jdbc:sqlserver://localhost:1433;databaseName=project","sa","123456");
//        try {
//            //call driver
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            // connect
//            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=SE1704","sa","123456");
//            System.out.println("Connected");
//        } catch (ClassNotFoundException ex) {
//            // simple code for debug (show bug)
//            ex.printStackTrace();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }  
    }
    
    public DBConnect(String url, String userName, String pass){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(url, userName, pass);
            System.out.println("Connected");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ResultSet getData(String sql){
        ResultSet rs = null;
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs = state.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
//    public DBConnect(String url, String username, String password){
//        try {
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            conn = DriverManager.getConnection(url, url, password);
//            System.out.println("Connection");
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//    }
    public PreparedStatement pre(String sql) throws SQLException{
        return conn.prepareStatement(sql);
    }
    public static void main(String[] args) {
        DBConnect test = new DBConnect();
    }
}
