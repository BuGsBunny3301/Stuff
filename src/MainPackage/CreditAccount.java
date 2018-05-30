package MainPackage;


//credit account is an account and it can has negative balance

public class CreditAccount extends Account{

    public CreditAccount(String name, double balance, int pin) {
        super(name, balance, pin);
    }
    
    public CreditAccount(String name, String dateCreated, double balance, int id, int pin) {
        super(name, dateCreated, balance, id, pin);
    }

    @Override
    public String accountType() {
        return "Credit Account";
    }
    
    
   //Account can have negative values
    @Override
    public void setBalance(double balance){
            this.balance = balance;
        }
}
