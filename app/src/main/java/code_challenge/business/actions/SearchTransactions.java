package code_challenge.business.actions;

import code_challenge.model.Account;
import code_challenge.model.Transaction;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SearchTransactions {
    //The account is mandatory

    public List<Transaction> searchFor(Account account) {
        //searchFor will sort the items chronologically, in the order they were added to the list
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
