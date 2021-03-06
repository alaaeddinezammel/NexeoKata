package nexeo;

import nexeo.acccount.Account;
import nexeo.transaction.Transactions;
import nexeo.utils.DateToString;
import nexeo.utils.Console;
import nexeo.utils.StatementPrinter;

public class Main {

	public static void main(String[] args) {
		DateToString clock = new DateToString();
		Console console = new Console();
		Transactions transactions = new Transactions();
		StatementPrinter statementPrinter = new StatementPrinter(console);
		Account account = new Account(clock, transactions, statementPrinter);

		account.deposit(1000);
		account.withdraw(300);
		account.withdraw(50);
		account.deposit(500);

		account.printStatement();
	}

}
