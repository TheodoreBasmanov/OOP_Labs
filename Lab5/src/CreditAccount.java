import java.time.Duration;
import java.time.Period;
import java.time.YearMonth;

public class CreditAccount extends Account {
	double comission;
	double creditLimit;

	CreditAccount(Client client, DateGiver date) {
		super(client, date);
	}

	CreditAccount(Client client, DateGiver date, double comission, double limit) {
		this(client, date);
		this.comission = comission;
		this.creditLimit = limit;
	}

	void setComission(double comission) {
		this.comission = comission;
	}

	void setLimit(double limit) {
		this.creditLimit = limit;
	}

	@Override
	public void withdraw(double summ) throws Exceptions.BelowTheLimit, Exceptions.OverUnrealible {
		checkSummReliable(summ);
		checkSummNegative(summ);
		if (summ <= moneySumm + creditLimit) {
			updateMoneySumm();
			moneySumm -= summ;
			Transaction transaction = new Transaction(this, null, summ);
		} else {
			throw new Exceptions.BelowTheLimit();
		}
	}

	@Override
	public void putIn(double summ) {
		checkSummNegative(summ);
		updateMoneySumm();
		moneySumm += summ;
		Transaction transaction = new Transaction(null, this, summ);
	}

	@Override
	public void transfer(double summ, int accountID)
			throws Exceptions.IncorrectIDAccount, Exceptions.BelowTheLimit, Exceptions.OverUnrealible {
		checkSummReliable(summ);
		checkSummNegative(summ);
		if (summ <= moneySumm + creditLimit) {
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
			throw new Exceptions.BelowTheLimit();
		}
	}

	protected void updateMoneySumm() {
		long days = Duration.between(time, date.getDate()).toDays();
		YearMonth month = YearMonth.of(date.getDate().getYear(), date.getDate().getMonthValue() - 1);
		if (days == 0) {
			return;
		}
		daysCounter += days;
		if (daysCounter >= month.lengthOfMonth()) {
			moneySumm -= comission * Period.between(time.toLocalDate(), date.getDate().toLocalDate()).getMonths();
			daysCounter = daysCounter - month.lengthOfMonth();
		}
		time = date.getDate();
	}

}
