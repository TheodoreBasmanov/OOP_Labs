import java.util.ArrayList;

public abstract class Transaction {
	static ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	protected static int ID = 1;
	int id;
	double summ;
	boolean isCanceled = false;

	abstract void undo() throws Exceptions.AlreadyCanceled;

	static void undo(int id) throws Exceptions.IncorrectIDTransaction, Exceptions.AlreadyCanceled {
		for (int i = 0; i < transactions.size(); i++) {
			if (transactions.get(i).id == id) {
				transactions.get(i).undo();
				return;
			}
		}
		throw new Exceptions.IncorrectIDTransaction();
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof Transaction)) {
			return false;
		}
		Transaction c = (Transaction) o;
		return id == c.id;
	}

}
