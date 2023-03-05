package code_challenge.business;

import code_challenge.business.actions.CreateTransaction;
import code_challenge.business.actions.SearchTransactionStatus;
import code_challenge.business.actions.SearchTransactions;
import code_challenge.infra.database.JsonFileSearcher;
import code_challenge.infra.database.Searcher;
import code_challenge.model.Account;
import code_challenge.model.Transaction;
import code_challenge.model.TransactionStatus;

import java.util.List;

public class TransactionsManager {

    Searcher searcher = new JsonFileSearcher();

    public TransactionStatus createTransaction(Transaction transaction, Account account) {
        return new CreateTransaction().createTransaction(transaction, account);
    }

    public Transaction searchTransaction(String reference) {
        return new SearchTransactionStatus(searcher).searchFor(reference);
    }

    public List<Transaction> getTransactions(Account account) {
        return new SearchTransactions().searchFor(account);
    }

}
