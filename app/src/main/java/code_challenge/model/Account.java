package code_challenge.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Account {

    private String iban;
    private Double balance;
    private List<Transaction> transactions;

    public Account(){
        //We don't need to create new accounts this way, so this will only be used in tests
        this.iban = "ES123456789";
        this.balance= 0.0;
        this.transactions = new ArrayList<Transaction>();
    }

    public Account(String iban, Double balance, List<Transaction> transactions){
        this.transactions = transactions;
        this.balance = balance;
        this.iban = iban;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        iban = iban;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
