package code_challenge;

import code_challenge.business.TransactionsManager;
import code_challenge.business.actions.SearchAccount;
import code_challenge.business.actions.SearchTransactionStatus;
import code_challenge.business.actions.SearchTransactions;
import code_challenge.infra.database.JsonFileSearcher;
import code_challenge.infra.database.Searcher;
import code_challenge.model.Account;
import code_challenge.model.Transaction;
import code_challenge.model.TransactionStatus;
import com.google.gson.GsonBuilder;
import spark.Request;

import java.util.List;

import static spark.Spark.*;

public class App {

    public static void main(String[] args) {
        TransactionsManager transactionsManager = new TransactionsManager();
        Searcher searcher = new JsonFileSearcher();

        post("/createTransaction", (request, response) -> {
            Transaction transaction = getTransactionFromRequest(request);
            Account account = new SearchAccount(searcher).searchFor(transaction.getAccountIban());
            TransactionStatus status = transactionsManager.createTransaction(transaction, account);
            if (status.equals(TransactionStatus.SETTLED))
                return "OK";
            else
                return "NOK";
        });

        get("/transactionStatus", (request, response) -> {
            String reference = new GsonBuilder().create().fromJson(request.body(), String.class);
            Transaction transaction = transactionsManager.searchTransaction(reference);
            return "OK";
        });

        get("/searchTransactions", (request, response) -> {
            String iban = new GsonBuilder().create().fromJson(request.body(), String.class);
            Account account = new SearchAccount(searcher).searchFor(iban);
            List<Transaction> transactions = transactionsManager.getTransactions(account);
            return "OK";
        });
    }

    private static Transaction getTransactionFromRequest(Request request) {
        return new GsonBuilder().create().fromJson(request.body(), Transaction.class);
    }
}
