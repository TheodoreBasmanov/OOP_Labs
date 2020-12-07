import java.time.Duration;
import java.time.YearMonth;

public class DebitAccount extends Account {
	double percent;
	double summToAdd;

	DebitAccount(Client client, DateGiver date) {
		super(client, date);
		summToAdd = 0;
	}

	DebitAccount(Client client, DateGiver date, double percent) {
		this(client, date);
		this.percent = percent;
	}

	void setPercent(double percent) {
		this.percent = percent;
	}

	public void withdraw(double summ) throws Exceptions.CantBeNegative, Exceptions.OverUnrealible {
		checkSummReliable(summ);
		checkSummNegative(summ);
		if (summ <= moneySumm) {
			updateMoneySumm();
			moneySumm -= summ;
			Transaction transaction = new Transaction(this, null, summ);
		} else {
			throw new Exceptions.CantBeNegative();
		}
	}

	public void putIn(double summ) {
		checkSummNegative(summ);
		updateMoneySumm();
		moneySumm += summ;
		Transaction transaction = new Transaction(null, this, summ);
	}

	public void transfer(double summ, int accountID)
			throws Exceptions.IncorrectIDAccount, Exceptions.CantBeNegative, Exceptions.OverUnrealible {
		checkSummReliable(summ);
		checkSummNegative(summ);
		if (summ <= moneySumm) {
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
				Transaction transaction = new Transaction(this, Account.accounts.get(toWhere), summ);
			}
		} else {
			throw new Exceptions.CantBeNegative();
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
