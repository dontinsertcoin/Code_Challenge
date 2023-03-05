package code_challenge.business.actions;

import code_challenge.model.Account;
import code_challenge.model.Transaction;
import code_challenge.model.TransactionStatus;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchTransactionsShould {

    @Test
    public void return_an_empty_list_when_there_are_no_transactions_for_an_account(){
        Account account = givenAnyAccount();

        SearchTransactions searchTransactions = new SearchTransactions();
        List<Transaction> transactions = searchTransactions.searchFor(account);

        assertTrue(transactions.isEmpty());
    }

    @Test
    public void return_the_transactions_list_when_there_are_previous_transactions_for_an_account(){
        Account account = givenAnAccountWithTransactions();
        Transaction expectedTransaction1 = givenAFirstTransaction();
        Transaction expectedTransaction2 = givenASecondTransaction();
        Transaction expectedTransaction3 = givenAnInvalidTransaction();
        ArrayList<Transaction> expectedList = new ArrayList<>();
        expectedList.add(expectedTransaction1);
        expectedList.add(expectedTransaction2);
        expectedList.add(expectedTransaction3);

        SearchTransactions searchTransactions = new SearchTransactions();
        List<Transaction> transactions = searchTransactions.searchFor(account);

        assertTrue(transactions.size() == expectedList.size());
        assertTrue(transactions.get(0).getReference().equals(expectedList.get(0).getReference()));
        assertTrue(transactions.get(1).getReference().equals(expectedList.get(1).getReference()));
        assertTrue(transactions.get(2).getReference().equals(expectedList.get(2).getReference()));
    }

    @Test
    public void return_the_transactions_sorted_by_amount_asc() {
        Account account = givenAnAccountWithTransactions();
        Transaction expectedTransaction1 = givenAFirstTransaction();
        Transaction expectedTransaction2 = givenASecondTransaction();
        Transaction expectedTransaction3 = givenAnInvalidTransaction();
        ArrayList<Transaction> expectedList = new ArrayList<>();
        expectedList.add(expectedTransaction3);
        expectedList.add(expectedTransaction2);
        expectedList.add(expectedTransaction1);

        SearchTransactions searchTransactions = new SearchTransactions();
        List<Transaction> sortedTransactions = searchTransactions.ascSortFor(account);

        assertTrue(sortedTransactions.size() == expectedList.size());
        assertTrue(sortedTransactions.get(0).getReference().equals(expectedList.get(0).getReference()));
        assertTrue(sortedTransactions.get(1).getReference().equals(expectedList.get(1).getReference()));
        assertTrue(sortedTransactions.get(2).getReference().equals(expectedList.get(2).getReference()));
    }

    @Test
    public void return_the_transactions_sorted_by_amount_desc() {
        Account account = givenAnAccountWithTransactions();
        Transaction expectedTransaction1 = givenAFirstTransaction();
        Transaction expectedTransaction2 = givenASecondTransaction();
        Transaction expectedTransaction3 = givenAnInvalidTransaction();
        ArrayList<Transaction> expectedList = new ArrayList<>();
        expectedList.add(expectedTransaction1);
        expectedList.add(expectedTransaction2);
        expectedList.add(expectedTransaction3);

        SearchTransactions searchTransactions = new SearchTransactions();
        List<Transaction> sortedTransactions = searchTransactions.descSortFor(account);

        assertTrue(sortedTransactions.size() == expectedList.size());
        assertTrue(sortedTransactions.get(0).getReference().equals(expectedList.get(0).getReference()));
        assertTrue(sortedTransactions.get(1).getReference().equals(expectedList.get(1).getReference()));
        assertTrue(sortedTransactions.get(2).getReference().equals(expectedList.get(2).getReference()));
    }

    private Account givenAnAccountWithTransactions() {
        CreateTransaction createTransaction = new CreateTransaction();
        Account account = new Account();
        createTransaction.createTransaction(givenAFirstTransaction(), account);
        createTransaction.createTransaction(givenASecondTransaction(), account);
        createTransaction.createTransaction(givenAnInvalidTransaction(), account);
        return account;
    }

    private Account givenAnyAccount() {
        return new Account();
    }

    private Transaction givenAFirstTransaction() {
        try {
            return new Transaction(
                    "12345",
                    "ES123456789",
                    new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2023"),
                    1052.0,
                    0d,
                    "Test",
                    TransactionStatus.SETTLED
            );
        } catch (ParseException e) {
            e.getMessage();
            return null;
        }
    }

    private Transaction givenASecondTransaction() {
        try{
            return new Transaction(
                "12346",
                "ES123456789",
                new SimpleDateFormat("dd/MM/yyyy").parse("02/02/2023"),
                -50.0,
                2d,
                "Test 1",
                TransactionStatus.SETTLED
            );
        } catch (ParseException e) {
            e.getMessage();
            return null;
        }
    }

    private Transaction givenAnInvalidTransaction() {
        try{
            return new Transaction(
                    "12347",
                    "ES123456789",
                    new SimpleDateFormat("dd/MM/yyyy").parse("03/03/2023"),
                    -5000.0,
                    2d,
                    "Test Invalid",
                    TransactionStatus.INVALID
            );
        } catch (ParseException e) {
            e.getMessage();
            return null;
        }
    }
}
