import java.util.ArrayList;

public class Account implements IAccount {
	static ArrayList<Account> accounts = new ArrayList<Account>();
	private static int ID = 1;
	int id;
	double moneySumm;
	Client client;
	double unreliableSumm;

	// double percent;
	Account(Client client) {
		id = ID;
		ID++;
		moneySumm = 0;
		accounts.add(this);
		client.accountIDs.add(id);
		this.client = client;
		unreliableSumm = client.bank.unrealiableSumm;
	}

	@Override
	public void withdraw(double summ) {

	}

	@Override
	public void putIn(double summ) {

	}

	@Override
	public void transfer(double summ, int accountID) {
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof Account)) {
			return false;
		}
		Account c = (Account) o;
		return id == c.id;
	}

	void checkSummReliable(double summ) throws Exceptions.OverUnrealible {
		if (!client.isReliable && summ > unreliableSumm) {
			throw new Exceptions.OverUnrealible();
		}
	}

	void checkSummNegative(double summ) throws Exceptions.NegativeMoneySumm {
		if (summ < 0) {
			throw new Exceptions.NegativeMoneySumm();
		}
	}

}
