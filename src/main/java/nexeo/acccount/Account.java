package nexeo.acccount;

import nexeo.transaction.Transaction;
import nexeo.transaction.Transactions;
import nexeo.utils.Clock;
import nexeo.utils.StatementPrinter;

public class Account {

	private Clock clock;
	private Transactions transactions;
	private StatementPrinter statementPrinter;

	public Account(Clock clock, Transactions transactions, StatementPrinter statementPrinter) {
		this.clock = clock;
		this.transactions = transactions;
		this.statementPrinter = statementPrinter;
	}

	public void deposit(int amount) {
		Transaction deposit = new Transaction(clock.dateAsString(), amount);
		transactions.add(deposit);
	}

	public void withdraw(int amount) {
		Transaction deposit = new Transaction(clock.dateAsString(), -amount);
		transactions.add(deposit);
	}

	public void printStatement() {
		statementPrinter.print(transactions.all());
	}

}
