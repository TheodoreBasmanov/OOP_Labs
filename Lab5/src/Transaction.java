import java.util.ArrayList;

public class Transaction {
	static ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	private static int ID = 1;
	int id;
	int accountIDOne;
	int accountIDTwo;
	double summ;

	Transaction(Account one, Account two, double summ) {
		if (!one.equals(null)) {
			this.accountIDOne = one.id;
		} else {
			this.accountIDOne = -1;
		}
		if (!two.equals(null)) {
			this.accountIDTwo = two.id;
		} else {
			this.accountIDTwo = -1;
		}
		this.summ = summ;
		id = ID;
		ID++;
		transactions.add(this);
		if (!one.equals(null)) {
			one.client.bank.transactionIDs.add(id);
		}
		if (!two.equals(null)) {
			two.client.bank.transactionIDs.add(id);
		}
	}

	static void undo(int id) throws Exceptions.IncorrectIDTransaction{
		for (int i = 0; i < transactions.size(); i++) {
			if (transactions.get(i).id == id) {
				if (transactions.get(i).accountIDOne != -1) {
					for (i = 0; i < Account.accounts.size(); i++) {
						if (Account.accounts.get(i).id == transactions.get(i).accountIDOne) {
							Account.accounts.get(i).moneySumm += transactions.get(i).summ;
						}
					}
				}
				if (transactions.get(i).accountIDTwo != -1) {
					for (i = 0; i < Account.accounts.size(); i++) {
						if (Account.accounts.get(i).id == transactions.get(i).accountIDTwo) {
							Account.accounts.get(i).moneySumm -= transactions.get(i).summ;
						}
					}
				}
				break;
			}
		}
		throw new Exceptions.IncorrectIDTransaction();

		/*
		 * for(int i =0; i<transactions.size();i++){
		 * if(transactions.get(i).id==id){ transactions.remove(i); } }
		 */
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
