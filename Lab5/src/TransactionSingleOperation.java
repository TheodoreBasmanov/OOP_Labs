
public class TransactionSingleOperation extends Transaction {
	int accountID;

	TransactionSingleOperation(Account one, double summ) {
		this.accountID = one.id;
		this.summ = summ;
		id = ID;
		ID++;
		transactions.add(this);
		one.client.bank.transactionIDs.add(id);
	}

	void undo() throws Exceptions.AlreadyCanceled {
		if (this.isCanceled) {
			throw new Exceptions.AlreadyCanceled();
		}
		for (int j = 0; j < Account.accounts.size(); j++) {
			if (Account.accounts.get(j).id == this.accountID) {
				Account.accounts.get(j).moneySumm += this.summ;
			}
		}
		this.isCanceled = true;
	}

}
