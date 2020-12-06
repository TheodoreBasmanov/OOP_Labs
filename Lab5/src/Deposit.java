import java.time.LocalDate;
import java.time.Period;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Deposit extends Account {
	double percent;
	LocalDate creationDate;
	Period period;
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

	Deposit(Client client) {
		super(client);
		creationDate = LocalDate.now();
		summToAdd = 0;
		scheduler.scheduleAtFixedRate(dailyPercentCount, 1, 1, TimeUnit.DAYS);
		scheduler.scheduleAtFixedRate(monthlyPercentCount, 30, 30, TimeUnit.DAYS);
	}

	Deposit(Client client, int months, int days, double percent, double initialSumm) {
		this(client);
		period = Period.ofMonths(months).plusDays(days);
		this.percent = percent;
		this.moneySumm = initialSumm;
	}

	void setPeriod(int months, int days) {
		period = Period.ofMonths(months).plusDays(days);
	}

	void setPercent(double percent) {
		this.percent = percent;
	}

	public void withdraw(double summ)
			throws Exceptions.CantBeNegative, Exceptions.NotAfterThePeriod, Exceptions.OverUnrealible {
		checkSummReliable(summ);
		checkSummNegative(summ);
		if (!LocalDate.now().isBefore(creationDate.plus(period))) {
			if (summ <= moneySumm) {
				moneySumm -= summ;
				Transaction transaction = new Transaction(this, null, summ);
			} else {
				throw new Exceptions.CantBeNegative();
			}
		} else {
			throw new Exceptions.NotAfterThePeriod();
		}
	}

	@Override
	public void putIn(double summ) {
		checkSummNegative(summ);
		moneySumm += summ;
		Transaction transaction = new Transaction(null, this, summ);
	}

	@Override
	public void transfer(double summ, int accountID) throws Exceptions.IncorrectIDAccount, Exceptions.CantBeNegative,
			Exceptions.NotAfterThePeriod, Exceptions.OverUnrealible {
		checkSummReliable(summ);
		checkSummNegative(summ);
		if (LocalDate.now().isBefore(creationDate.plus(period))) {
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
		} else {
			throw new Exceptions.NotAfterThePeriod();
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
