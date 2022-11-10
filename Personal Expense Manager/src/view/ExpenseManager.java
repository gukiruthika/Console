package view;

public class ExpenseManager {

	private ViewMenu menu = new ViewMenu();

	public static void main(String[] args) {
		ExpenseManager expenseManager = new ExpenseManager();
		expenseManager.init();
	}

	private void init() {
		menu.init();
	}

}
