package MainPackage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//An account has:
    // Name, date the account was created in, the balance, the id of each account, and a list of transactions.

public abstract class Account {
    private String name;
    private String dateCreated;
    protected double balance;
    static int ID;
    private int id;
    private int pin;
    private List<Transactions> transactions = new ArrayList<>();
    private final SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

    //constructors
    public Account(String name, String dateCreated, double balance, int id, int pin) {
        this.name = name;
        this.dateCreated = dateCreated;
        this.balance = balance;
        this.id = id;
        this.pin = pin;
    }
    
    public Account(String name, double balance, int pin) {
        this.name = name;
        this.dateCreated = sdf.format(new Date());
        this.balance = balance;
        this.pin = pin;
        ID++;
        this.id = ID;
    }

    //Getters and setters
    public List<Transactions> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transactions> transactions) {
        this.transactions = transactions;
    }
    
    public void addTransaction(Transactions trans){
        this.transactions.add(trans);
    }

    public String getName() {
        return name;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public double getBalance() {
        return balance;
    }

    public static int getID() {
        return ID;
    }

    public int getId() {
        return id;
    }

    public int getPin() {
        return pin;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public static void setID(int ID) {
        Account.ID = ID;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }
    
    public abstract String accountType();
    public abstract void setBalance(double balance);
}
