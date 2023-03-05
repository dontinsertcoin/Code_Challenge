package code_challenge.infra.database;

import code_challenge.model.Account;
import code_challenge.model.Transaction;

public interface Persistence {
    int saveTransaction(Transaction transaction);
    int updateAccount(Account account);
}
