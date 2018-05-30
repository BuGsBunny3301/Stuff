package MainPackage;

import java.util.Date;

    //Transcations have a string to define what the transactio is and a date to know when
    //the tranasaction happened

public class Transactions {
    private String transactions;
    private Date date;

    public Transactions(int id, String transactions) {
        this.transactions = transactions;
        this.date = new Date();
    }
    
    public Transactions(Date date, String transactions) {
        this.transactions = transactions;
        this.date = date;
    }
    
    public Date getDate(){
        return date;
    }
    
    public void setDate(Date date){
        this.date = date;
    }
   
    public String getTransactions() {
        return transactions;
    }

    public void setTransactions(String transactions) {
        this.transactions = transactions;
    }
}
