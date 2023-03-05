package code_challenge.business.actions;

import code_challenge.model.Account;
import code_challenge.model.Transaction;
import code_challenge.model.TransactionStatus;

public class CreateTransaction {
    public TransactionStatus createTransaction(Transaction transaction, Account account) {
        Double newAccountBalance = calculateNewAccountBalance(account.getBalance(), transaction);
        TransactionStatus status;
        if (newAccountBalance < 0.0) {
            //If the transaction is invalid we want it to be in the account list but the balance does not change
            status = TransactionStatus.INVALID;
        } else {
            account.setBalance(newAccountBalance);
            status = TransactionStatus.SETTLED;
        }
        transaction.setStatus(status);
        account.getTransactions().add(transaction);
        return status;
    }

    private Double calculateNewAccountBalance(Double initialBalance, Transaction transaction) {
        return initialBalance + transaction.getAmount() - transaction.getFee();
    }
}
