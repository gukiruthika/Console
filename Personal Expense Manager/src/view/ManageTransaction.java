package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import controller.InputValidation;
import controller.TransactionController;
import model.TransactionDatabase;
import model.Transaction;

public class ManageTransaction {
	private TransactionController transactionController;
	private Transaction transaction;
	private InputValidation inputController = new InputValidation();
	

	public ManageTransaction(){
		transactionController = new TransactionController();
	}
	public void addTransaction() {
		transactionController.toAddTransaction();;
		System.out.print("Transaction added Successfully!!");
	}

	public void viewTransaction() {
		System.out.printf("\n%-5s %-15s %-15s %-20s %-15s %-20s", "S.no", "Date", "Kind", "Category", "Amount",
				"Description");
		transactionController.toViewTransaction();
		viewBalance();
	}

	public void viewBalance() {
		int totalIncome = 0, totalExpense = 0;
		for (int i = 0; i < TransactionDatabase.getInstance().getTransactionList().size(); i++) {
			transaction = TransactionDatabase.getInstance().getTransactionList().get(i);

			if (transaction.getKind().equalsIgnoreCase("Income")) {
				totalIncome += transaction.getAmount();
			} else {
				totalExpense += transaction.getAmount();
			}
		}
		System.out.println("\n\nTotal Income Earned ----> Rs." + totalIncome);
		System.out.println("\nTotal Expense Made ----> Rs." + totalExpense);
		if (totalIncome > totalExpense) {
			System.out.println("\nBalance (surplus) ----> Rs." + (totalIncome - totalExpense));
		} else if (totalIncome < totalExpense) {
			System.out.println("\nBalance (deficit) ----> Rs." + (totalExpense - totalIncome));
		} else {
			System.out.println("\nBalance ----> Rs.0");
		}

	}

	public void searchTransaction() {
		String category = inputController.getInput("\nCategory : ", "[A-Za-z ]+");
		List<Transaction> transactionList = TransactionDatabase.getInstance().getTransactionList();
		List<Transaction> transactionSubList = new ArrayList<Transaction>();
		for (Transaction transaction : transactionList) {
			if ((transaction.getCategory().equalsIgnoreCase(category))) {
				transactionSubList.add(transaction);
			}
		}
		if(transactionSubList.size()>0) {
		System.out.printf("\n%-5s %-10s %-10s %-20s %-15s %-20s", "S.no", "Date", "Kind", "Category", "Amount",
				"Description");
		for (int i = 0; i < transactionSubList.size(); i++) {
			transaction = transactionSubList.get(i);
			System.out.printf("\n%-5s %-10s %-10s %-20s %-15s %-20s", (i+1), transaction.getDate(), transaction.getKind(),
					transaction.getCategory(), transaction.getAmount(), transaction.getDescription());
		}
		}
		else {
		System.out.println("No such category!!");
		searchTransaction();
		}
	}

	
}
