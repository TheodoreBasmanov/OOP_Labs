import java.time.LocalDateTime;
import java.util.ArrayList;

public class Account implements IAccount {
	static ArrayList<Account> accounts = new ArrayList<Account>();
	private static int ID = 1;
	int id;
	double moneySumm;
	Client client;
	double unreliableSumm;
	protected DateGiver date;
	protected LocalDateTime time;
	protected int daysCounter = 0;
	Account(Client client, DateGiver date) {
		id = ID;
		ID++;
		moneySumm = 0;
		accounts.add(this);
		client.accountIDs.add(id);
		this.client = client;
		this.date = date;
		time = date.getDate();
		unreliableSumm = client.bank.unrealiableSumm;
	}

	void setDate(DateGiver date) {
		this.date = date;
	}

	double getBalance() {
		this.updateMoneySumm();
		return moneySumm;
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

	protected void updateMoneySumm() {
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
