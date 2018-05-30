/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPackage;

import static MainPackage.LoginFrame.accounts;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class File {
    
    //Function that reads all the data from the file and inputs it to the program
    public static void readData () {
        
        //This is the file that the data is read from
        final String FILENAME = "/home/bug/Desktop/files.txt";
        
        //Buffered reader and file reader to read from file
        BufferedReader br = null;
        FileReader fr = null;
        
        try {
            //Initialise buffered reader and fileReader
                fr = new FileReader(FILENAME);
                br = new BufferedReader(fr);

                //Each string reads a new line in the saved file and then saved accordingly
                String sCurrentLine;
                String name, accountType;
                int pin, id;
                String dob;
                Date date;
                double balance;
                String transaction;
                final SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

                while ((sCurrentLine = br.readLine()) != null) {
                                name = br.readLine();
                                dob = br.readLine();
                                id = Integer.valueOf(br.readLine());
                                balance = Double.valueOf(br.readLine());
                                pin = Integer.parseInt(br.readLine());
                                accountType = br.readLine();
                                
                                //new account is made according to the type of it in the file 
                                switch(accountType){
                                        case "Debit Account":
                                            LoginFrame.accounts.add(new DebitAccount(name, dob, balance, id, pin));
                                            break;
                                        case "Credit Account":
                                            LoginFrame.accounts.add(new CreditAccount(name, dob, balance, id, pin));
                                            break;
                                    }
                                
                                String sCurrentLind = br.readLine();
                                String temp;
                                
                                //Keep reading lines of transactions until the word done is reached and then stop reading
                                //and read the second account if there's one
                                while(!(temp = br.readLine()).equals("Done")){
                                    if(temp.equals("Done")){
                                        break;
                                    }else{
                                        date = sdf.parse(temp);
                                    }
                                    transaction = br.readLine();
                                    LoginFrame.accounts.get(LoginFrame.accounts.size() - 1).addTransaction(new Transactions(date, transaction));
                                }
                }
        }catch(IOException e){
            javax.swing.JOptionPane.showMessageDialog(null, "File not found");
        } catch (ParseException ex) {
            javax.swing.JOptionPane.showMessageDialog(null, "Parse Error");
        }finally{
            try{
                //close buffered reader
                if (br != null)
                    br.close();
                //close file reader
                if (fr != null)
                    fr.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
    
    //Function that writes all the transactions that into the file
    public static void writeTransactions(){
        
        //The filename to insert info to
        final String FILENAME = "/home/bug/Desktop/files.txt";
        
        //simple date format to convert Date variable to String variable
        final SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        
        //printwriter to print the transactions to the file
        PrintWriter writer = null;

        try {
            //initialise the printwriter
            writer = new PrintWriter(FILENAME, "UTF-8");
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
        
        //initilise the bufferWriter to start writing to the file
        try (BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
            
            //loop through all accounts and write all the info of an account to the file in this order
            //then when the file is read it is imported correctly to the GUI
            for(Account account : LoginFrame.accounts){
                writer.write(
                    "Account:\n" +
                    account.getName() + "\n" +
                    account.getDateCreated() + "\n" +
                    account.getId()+ "\n" +
                    account.getBalance()+ "\n" +
                    account.getPin() + "\n" +
                    account.accountType() + "\nTransactions:\n");
                
                    if(account.getTransactions().isEmpty()){
                        writer.write("Done\n");
                    }else{
                            for(int i = 0; i < account.getTransactions().size(); i++){
                                writer.write(
                                        sdf.format(account.getTransactions().get(i).getDate()) + "\n" +
                                                account.getTransactions().get(i).getTransactions() + "\n"
                                );
                            }
                            writer.write("Done\n");
                    }
            }
        }catch (Exception ex) {
            javax.swing.JOptionPane.showMessageDialog(null, "Something went wrong");
        }

        javax.swing.JOptionPane.showMessageDialog(null, "Transaction Succefull");
        
        //close the buffered writer
        writer.flush();
        writer.close();
    }
    
}
