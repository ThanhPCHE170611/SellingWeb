package entity;
public class Customer {
    private int cid; 
    private String cname; 
    private String username;
    private String password;
    private String phone;
    private String address;
    private int admin;

    public Customer(int cid, String cname, String username, String password,String phone, String address) {
        this.cid = cid;
        this.cname = cname;
        this.username = username;
        this.password = password;
        this.address = address;
        this.phone = phone;
        this.admin = 0;
    }

    public Customer(String cname, String username, String password, String phone, String address, int admin) {
        this.cname = cname;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.admin = admin;
    }
    public Customer(int cid, String cname, String username, String password,String phone, String address, int admin) {
        this.cid = cid;
        this.cname = cname;
        this.username = username;
        this.password = password;
        this.address = address;
        this.phone = phone;
        this.admin = admin;
    }
    
    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    

  

   
    
}
