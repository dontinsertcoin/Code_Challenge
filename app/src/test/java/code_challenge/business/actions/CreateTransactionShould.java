package code_challenge.business.actions;

import code_challenge.business.TransactionsManager;
import code_challenge.model.Account;
import code_challenge.model.Transaction;
import code_challenge.model.TransactionStatus;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateTransactionShould {

    @Test
    public void be_able_to_manage_a_credit_transaction (){
        Account account = givenAnyAccount();
        Double originalBalance = account.getBalance();
        Transaction creditTransaction = givenACreditTransaction();

        CreateTransaction transactionManager = new CreateTransaction();
        transactionManager.createTransaction(creditTransaction, account);

        assertTrue(
    account.getBalance() ==
            creditTransaction.getAmount() + originalBalance - creditTransaction.getFee()
        );
        assertTrue(account.getTransactions().contains(creditTransaction));
        assertTrue(creditTransaction.getStatus() == TransactionStatus.SETTLED);
    }

    @Test
    public void be_able_to_manage_a_debit_transaction (){
        Account account = givenAnAccountWithPositiveBalance();
        Double originalBalance = account.getBalance();
        Transaction debitTransaction = givenADeditTransaction();

        CreateTransaction transactionManager = new CreateTransaction();
        transactionManager.createTransaction(debitTransaction, account);

        assertTrue(
    account.getBalance() ==
            debitTransaction.getAmount() + originalBalance - debitTransaction.getFee());
        assertTrue(account.getTransactions().contains(debitTransaction));
        assertTrue(debitTransaction.getStatus() == TransactionStatus.SETTLED);
    }

    @Test
    public void not_allow_a_transaction_when_balance_is_bellow_0 (){
        Account account = givenAnyAccount();
        Transaction debitTransaction = givenADeditTransaction();

        CreateTransaction transactionManager = new CreateTransaction();
        TransactionStatus status = transactionManager.createTransaction(debitTransaction, account);

        assertTrue(status == TransactionStatus.INVALID);
        assertTrue(account.getTransactions().contains(debitTransaction));
    }

    private Transaction givenADeditTransaction() {
        return new Transaction(
                "12345",
                "ES123456789",
                new Date(),
                -100.0,
                2.0,
                "Test credit transaction",
                TransactionStatus.PENDING);
    }

    private Account givenAnAccountWithPositiveBalance() {
        ArrayList<Transaction> transactions = new ArrayList<>();
        transactions.add(givenACreditTransaction());
        return new Account("ES123456789", 1000.0, transactions);
    }

    private Transaction givenACreditTransaction() {
        return new Transaction(
                "12345",
                "ES123456789",
                new Date(),
                1000.0,
                2.0,
                "Test credit transaction",
                TransactionStatus.PENDING);
    }

    private Account givenAnyAccount() {
        return new Account();
    }


}
