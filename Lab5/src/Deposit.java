import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.YearMonth;

public class Deposit extends Account {
	double percent;
	LocalDateTime creationDate;
	Period period;
	double summToAdd;

	Deposit(Client client, DateGiver date) {
		super(client, date);
		creationDate = date.getDate();
		summToAdd = 0;
	}

	Deposit(Client client, DateGiver date, int months, int days, double percent, double initialSumm) {
		this(client, date);
		period = Period.ofMonths(months).plusDays(days);
		this.percent = percent;
		this.moneySumm = initialSumm;
	}

	void setPeriod(int months, int days) {
		period = Period.ofMonths(months).plusDays(days);
	}

	public void withdraw(double summ)
			throws Exceptions.CantBeNegative, Exceptions.NotAfterThePeriod, Exceptions.OverUnrealible {
		checkSummReliable(summ);
		checkSummNegative(summ);
		if (date.getDate().isBefore(creationDate.plus(period))) {
			throw new Exceptions.NotAfterThePeriod();
		}
		if (summ > moneySumm) {
			throw new Exceptions.CantBeNegative();
		}
		updateMoneySumm();
		moneySumm -= summ;
		TransactionSingleOperation transaction = new TransactionSingleOperation(this, -summ);
	}

	public void putIn(double summ) {
		checkSummNegative(summ);
		updateMoneySumm();
		moneySumm += summ;
		TransactionSingleOperation transaction = new TransactionSingleOperation(this, summ);
	}

	public void transfer(double summ, int accountID) throws Exceptions.IncorrectIDAccount, Exceptions.CantBeNegative,
			Exceptions.NotAfterThePeriod, Exceptions.OverUnrealible {
		checkSummReliable(summ);
		checkSummNegative(summ);
		if (date.getDate().isBefore(creationDate.plus(period))) {
			throw new Exceptions.NotAfterThePeriod();
		}
		if (summ > moneySumm) {
			throw new Exceptions.CantBeNegative();
		}
		updateMoneySumm();
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
			TransactionTransfer transaction = new TransactionTransfer(this, Account.accounts.get(toWhere), summ);
		}
	}

	protected void updateMoneySumm() {
		long days = Duration.between(time, date.getDate()).toDays();
		YearMonth month = YearMonth.of(date.getDate().getYear(), date.getDate().getMonthValue() - 1);
		if (days == 0) {
			return;
		}
		for (int i = 0; i < days; i++) {
			summToAdd += (moneySumm + summToAdd) * percent / month.lengthOfYear() / 100;
		}
		time = date.getDate();
		daysCounter += days;
		if (daysCounter >= month.lengthOfMonth()) {
			moneySumm += summToAdd;
			summToAdd = 0;
			daysCounter = daysCounter - month.lengthOfMonth();
		}
	}

}
