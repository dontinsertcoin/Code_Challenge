package code_challenge.infra.database;

import code_challenge.model.Account;
import code_challenge.model.Transaction;

import java.io.FileNotFoundException;
import java.util.Optional;

public interface Searcher {
    Optional<Transaction> searchTransaction(String reference) throws FileNotFoundException;
    Optional<Account> searchAccount(String iban) throws FileNotFoundException;
}
