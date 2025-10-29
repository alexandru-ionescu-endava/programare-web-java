package org.example.model;

public class BankAccount {
    private String accountOwner;
    private Double balance;

    public BankAccount(String accountOwner, Double balance) {
        this.accountOwner = accountOwner;
        this.balance = balance;
    }

    public String getAccountOwner() {
        return accountOwner;
    }

    public Double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "accountOwner='" + accountOwner + '\'' +
                ", balance=" + balance +
                '}';
    }
}
