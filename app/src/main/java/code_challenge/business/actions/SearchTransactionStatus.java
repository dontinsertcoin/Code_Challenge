package code_challenge.business.actions;

import code_challenge.infra.database.Searcher;
import code_challenge.model.Transaction;

import java.io.FileNotFoundException;
import java.util.Optional;

public class SearchTransactionStatus {

    private final Searcher searcher;

    public SearchTransactionStatus (Searcher searcher) {
        this.searcher = searcher;
    }

    public Transaction searchFor(String reference) {
        try {
            Optional<Transaction> transaction = searcher.searchTransaction(reference);
            if (transaction.isPresent()) {
                return transaction.get();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
