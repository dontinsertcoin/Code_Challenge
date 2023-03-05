package code_challenge.business;

import code_challenge.business.actions.CreateTransaction;
import code_challenge.model.Account;
import code_challenge.model.Transaction;
import code_challenge.model.TransactionStatus;

public class TransactionsManager {

    public TransactionStatus createTransaction(Transaction transaction, Account account) {
        return new CreateTransaction().createTransaction(transaction, account);
    }
}
