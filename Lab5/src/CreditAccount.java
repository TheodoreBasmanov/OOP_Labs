import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CreditAccount extends Account {
	double comission;
	double creditLimit;
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	final Runnable monthlyComission = new Runnable() {
		public void run() {
			comission();
		}
	};

	CreditAccount(Client client) {
		super(client);
		scheduler.scheduleAtFixedRate(monthlyComission, 30, 30, TimeUnit.DAYS);
	}

	void setComission(double comission) {
		this.comission = comission;
	}

	void setLimit(double limit) {
		this.creditLimit = limit;
	}

	@Override
	public void withdraw(double summ) throws Exceptions.BelowTheLimit, Exceptions.OverUnrealible {
		checkSumm(summ);
		if (summ <= moneySumm + creditLimit) {
			moneySumm -= summ;
			Transaction transaction = new Transaction(this, null, summ);
		} else {
			throw new Exceptions.BelowTheLimit();
		}
	}

	@Override
	public void putIn(double summ) {
		moneySumm += summ;
		Transaction transaction = new Transaction(null, this, summ);
	}

	@Override
	public void transfer(double summ, int accountID)
			throws Exceptions.IncorrectIDAccount, Exceptions.BelowTheLimit, Exceptions.OverUnrealible {
		checkSumm(summ);
		if (summ <= moneySumm + creditLimit) {
			moneySumm -= summ;
			int toWhere = -1;
			for (int i = 0; i < Account.accounts.size(); i++) {
				if (Account.accounts.get(i).id == accountID) {
					toWhere = i;
					break;
				}
			}
			if (toWhere == -1) {
				throw new Exceptions.IncorrectIDAccount();
			} else {
				Account.accounts.get(toWhere).moneySumm += summ;
				Transaction transaction = new Transaction(this, Account.accounts.get(toWhere), summ);
			}
		} else {
			throw new Exceptions.BelowTheLimit();
		}
	}

	void comission() {
		if (moneySumm < 0) {
			moneySumm -= comission;
		}
	}

}
