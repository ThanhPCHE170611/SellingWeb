package entity;

public class Bill {

    private String bid;
    private String dateCreate;
    private String recAddress;
    private String recPhone;
    private String note;
    private double totalMoney;
    private int status;
    private int cid;
    
    public Bill(String bid, String dateCreate, String recAddress, String recPhone, String note, double totalMoney, int cid) {
        this.bid = bid;
        this.dateCreate = dateCreate;
        this.recAddress = recAddress;
        this.recPhone = recPhone;
        this.note = note;
        this.totalMoney = totalMoney;
        this.status = 0;
        this.cid = cid;
    }
    public Bill(String bid, String dateCreate, String recAddress, String recPhone, String note, double totalMoney,int status, int cid) {
        this.bid = bid;
        this.dateCreate = dateCreate;
        this.recAddress = recAddress;
        this.recPhone = recPhone;
        this.note = note;
        this.totalMoney = totalMoney;
        this.status = status;
        this.cid = cid;
    }

    public Bill(String bid, String recAddress, String recPhone, String note, int cid) {
        this.bid = bid;
        this.dateCreate = "";
        this.recAddress = recAddress;
        this.recPhone = recPhone;
        this.note = note;
        this.cid = cid;
    }

    public Bill(String bid, String recDate, String recAddress, String recPhone, String note, int cid) {
        this.bid = bid;
        this.dateCreate = recDate;
        this.recAddress = recAddress;
        this.recPhone = recPhone;
        this.note = note;
        this.cid = cid;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getRecAddress() {
        return recAddress;
    }

    public void setRecAddress(String recAddress) {
        this.recAddress = recAddress;
    }

    public String getRecPhone() {
        return recPhone;
    }

    public void setRecPhone(String recPhone) {
        this.recPhone = recPhone;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }


    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
}
