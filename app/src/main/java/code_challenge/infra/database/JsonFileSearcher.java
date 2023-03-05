package code_challenge.infra.database;

import code_challenge.model.Account;
import code_challenge.model.Transaction;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Optional;

public class JsonFileSearcher implements Searcher {

    @Override
    public Optional<Transaction> searchTransaction(String reference) {
        try {
           Transaction[] transactions = retrieveTransactionsData();
           return Arrays.stream(transactions).filter( transaction -> transaction.getReference().equals(reference)).findFirst();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return Optional.empty();
    }

    @Override
    public Optional<Account> searchAccount(String iban) {
        try {
            Account[] accounts = retrieveAccountsData();
            return Arrays.stream(accounts).filter( account -> account.getIban().equals(iban)).findFirst();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return Optional.empty();
    }

    private Transaction[] retrieveTransactionsData() throws FileNotFoundException {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(new FileReader("./src/main/resources/transactions.json"), Transaction[].class);
    }

    private Account[] retrieveAccountsData() throws FileNotFoundException {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(new FileReader("./src/main/resources/accounts.json"), Account[].class);
    }
}
