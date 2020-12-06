import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DebitAccount extends Account {
	double percent;
	double summToAdd;
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

	final Runnable dailyPercentCount = new Runnable() {
		public void run() {
			dailyPercent();
		}
	};
	final Runnable monthlyPercentCount = new Runnable() {
		public void run() {
			monthlyPercent();
		}
	};

	DebitAccount(Client client) {
		super(client);
		summToAdd = 0;
		scheduler.scheduleAtFixedRate(dailyPercentCount, 1, 1, TimeUnit.DAYS);
		scheduler.scheduleAtFixedRate(monthlyPercentCount, 30, 30, TimeUnit.DAYS);
	}

	DebitAccount(Client client, double percent) {
		this(client);
		this.percent = percent;
	}

	void setPercent(double percent) {
		this.percent = percent;
	}

	@Override
	public void withdraw(double summ) throws Exceptions.CantBeNegative, Exceptions.OverUnrealible {
		checkSummReliable(summ);
		checkSummNegative(summ);
		if (summ <= moneySumm) {
			moneySumm -= summ;
			Transaction transaction = new Transaction(this, null, summ);
		} else {
			throw new Exceptions.CantBeNegative();
		}
	}

	@Override
	public void putIn(double summ) {
		moneySumm += summ;
		Transaction transaction = new Transaction(null, this, summ);
	}

	@Override
	public void transfer(double summ, int accountID)
			throws Exceptions.IncorrectIDAccount, Exceptions.CantBeNegative, Exceptions.OverUnrealible {
		checkSummReliable(summ);
		checkSummNegative(summ);
		if (summ <= moneySumm) {
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
			throw new Exceptions.CantBeNegative();
		}
	}

	void dailyPercent() {
		summToAdd += moneySumm * percent / 365;
	}

	void monthlyPercent() {
		moneySumm += summToAdd;
		summToAdd = 0;
	}

}
