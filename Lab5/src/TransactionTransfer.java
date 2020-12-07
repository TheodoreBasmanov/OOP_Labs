
public class TransactionTransfer extends Transaction {
	int accountIDOne;
	int accountIDTwo;

	TransactionTransfer(Account one, Account two, double summ) {
		this.accountIDOne = one.id;
		this.accountIDTwo = two.id;
		this.summ = summ;
		id = ID;
		ID++;
		transactions.add(this);
		one.client.bank.transactionIDs.add(id);
		two.client.bank.transactionIDs.add(id);
	}

	void undo() throws Exceptions.AlreadyCanceled {
		if (this.isCanceled) {
			throw new Exceptions.AlreadyCanceled();
		}
		for (int j = 0; j < Account.accounts.size(); j++) {
			if (Account.accounts.get(j).id == this.accountIDOne) {
				Account.accounts.get(j).moneySumm += this.summ;
			}
		}
		for (int j = 0; j < Account.accounts.size(); j++) {
			if (Account.accounts.get(j).id == this.accountIDTwo) {
				Account.accounts.get(j).moneySumm -= this.summ;
			}
		}
		this.isCanceled = true;
	}
}
