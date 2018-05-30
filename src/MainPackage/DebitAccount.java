package MainPackage;

public class DebitAccount extends Account{

    //debit account is an account and it can't have a negative balance
    
    public DebitAccount(String name, double balance, int pin) {
        super(name, balance, pin);
    }
    
    public DebitAccount(String name, String dateCreated, double balance, int id, int pin) {
        super(name, dateCreated, balance, id, pin);
    }

    @Override
    public String accountType() {
        return "Debit Account";
    }
    
    //if it was a debit account the balance shouldnt be negative
    //so an if statment checks for negative values and prevents negative input
    
    @Override
    public void setBalance(double balance){
        if(balance < 0){
            javax.swing.JOptionPane.showMessageDialog(null, "Not enough money");
        }else{
            this.balance = balance;
        }
    }
}
