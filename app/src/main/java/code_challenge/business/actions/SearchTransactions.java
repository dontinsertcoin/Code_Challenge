package code_challenge.business.actions;

import code_challenge.model.Account;
import code_challenge.model.Transaction;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SearchTransactions {

    public List<Transaction> searchFor(Account account) {
        return account.getTransactions();
    }

    public List<Transaction> ascSortFor(Account account) {
        return account.getTransactions().stream().sorted().collect(Collectors.toList());
    }

    public List<Transaction> descSortFor(Account account) {
        List<Transaction> sortedTransactions = ascSortFor(account);
        Collections.reverse(sortedTransactions);
        return sortedTransactions;
    }
}
