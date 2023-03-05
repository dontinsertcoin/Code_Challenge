package code_challenge.business.actions;

import code_challenge.infra.database.Searcher;
import code_challenge.model.Account;

import java.io.FileNotFoundException;
import java.util.Optional;

public class SearchAccount {

    private final Searcher searcher;

    public SearchAccount(Searcher searcher) {
        this.searcher = searcher;
    }

    public Account searchFor(String iban) {
        try {
            Optional<Account> account = searcher.searchAccount(iban);
            if (account.isPresent()) {
                return account.get();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
