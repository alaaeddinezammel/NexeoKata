package nexeo.test.behaviour;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import nexeo.acccount.Account;
import nexeo.transaction.Transaction;
import nexeo.transaction.Transactions;
import nexeo.utils.DateToString;
import nexeo.utils.StatementPrinter;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AccountShould {

	private static final String SYSTEM_DATE = "01/04/2004";
	private static final List<Transaction> TRANSACTION_LIST = new ArrayList<>();

	@Mock Transactions transactions;
	@Mock DateToString dateString;
	@Mock StatementPrinter statementPrinter;

	private Account account;

	@Before
	public void initialise() {
		given(dateString.dateAsString()).willReturn(SYSTEM_DATE);
	    account = new Account(dateString, transactions, statementPrinter);
	}

	@Test public void
	store_a_deposit_transaction() {
		Transaction deposit = new Transaction(SYSTEM_DATE, 100);

		account.deposit(100);

		verify(transactions).add(eq(deposit));
	}

	@Test public void
	store_a_withdrawal_transaction() {
		given(dateString.dateAsString()).willReturn(SYSTEM_DATE);
		Transaction withdrawal = new Transaction(SYSTEM_DATE, -100);

		account.withdraw(100);

		verify(transactions).add(eq(withdrawal));
	}

	@Test public void
	print_a_statement_containing_all_transactions() {
		given(transactions.all()).willReturn(TRANSACTION_LIST);

		account.printStatement();

		verify(statementPrinter).print(TRANSACTION_LIST);
	}
}
