package code_challenge.business.actions;

import code_challenge.infra.database.JsonFileSearcher;
import code_challenge.model.Transaction;
import code_challenge.model.TransactionStatus;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchTransactionStatusShould {

    @Test
    public void be_able_to_get_a_transaction_using_a_reference(){
        JsonFileSearcher searcher = new JsonFileSearcher();
        Transaction expectedTransaction = givenADeditTransaction();
        Transaction transaction = new SearchTransactionStatus(searcher).searchFor(expectedTransaction.getReference());

        assertEquals(transaction.getReference(), expectedTransaction.getReference());
    }

    private Transaction givenADeditTransaction() {
        try{
            return new Transaction(
                    "12345A",
                    "ES9820385778983000760236",
                    new SimpleDateFormat("yyyy-dd-MM'T'HH:mm:ss.SSS'Z'").parse("2019-07-16T16:55:42.000Z"),
                    193.38,
                    3.18,
                    "Restaurant payment 1",
                    TransactionStatus.SETTLED);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        return new Transaction();
    }
}
