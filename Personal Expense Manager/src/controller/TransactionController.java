package controller;

import java.util.List;

import model.Transaction;
import model.TransactionDatabase;

public class TransactionController {
	private Transaction transaction;
	private InputController inputController = new InputController();
	private List<Transaction> transactionList = TransactionDatabase.getInstance().getTransactionList();

	public void toAddTransaction() {
		transaction = new Transaction();
		transaction.setDate(inputController.getInput("Date (DD/MM/YYYY) : ",
				"(0[1-9]|[12][0-9]|3[01])[/](0[1-9]|1[012])[/][0-9]{4}"));
		transaction.setKind(inputController.getInput("Kind (Income/Expense) : ", "Income|Expense|income|expense"));
		transaction.setCategory(inputController.getInput("Category : ", "[A-Za-z ]+"));
		transaction.setAmount(Integer.valueOf(inputController.getInput("Amount : Rs.", "[0-9]+")));
		transaction.setDescription(inputController.getInput("Description : ", ".+"));
		TransactionDatabase.getInstance().insertTransaction(transaction);
	}

	public void toViewTransaction() {
		int i = 1;
		for (Transaction transaction : transactionList) {
			System.out.printf("\n%-5s %-15s %-15s %-20s %-15s %-20s", (i++), transaction.getDate(),
					transaction.getKind(), transaction.getCategory(), transaction.getAmount(),
					transaction.getDescription());
		}
	}

}
