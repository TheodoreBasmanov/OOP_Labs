import java.util.ArrayList;

public class Bank {
	ArrayList<Integer> transactionIDs;
	double unrealiableSumm;
	ArrayList<PercentOnTheRest> depositPercents;
	ArrayList<Client> clients;
	ArrayList<Integer> accountIDs;

	Bank(double unreliableSumm, ArrayList<PercentOnTheRest> depositPercents) {
		this.transactionIDs = new ArrayList<Integer>();
		this.unrealiableSumm = unreliableSumm;
		this.depositPercents = depositPercents;
		this.clients = new ArrayList<Client>();
		this.accountIDs = new ArrayList<Integer>();
	}

	void setUnrelialbeSumm(double unreliableSumm) {
		this.unrealiableSumm = unreliableSumm;
	}

	void setDepositPercent(ArrayList<PercentOnTheRest> depositPercents) {
		this.depositPercents = depositPercents;
	}

	void addClient(ClientBuilder newClient) throws Exceptions.WrongBank {
		if (!newClient.bank.equals(this)) {
			throw new Exceptions.WrongBank();
		}
		clients.add(newClient.build());
	}

	void makeClientReliable(int id, String address, String IDNumber)
			throws Exceptions.AlreadyReliable, Exceptions.IncorrectIDClient {
		for (int i = 0; i < clients.size(); i++) {
			if (clients.get(i).id == id) {
				clients.get(i).makeReliable(address, IDNumber);
				break;
			}
		}
		throw new Exceptions.IncorrectIDClient();
	}

	void setClientAddress(int id, String address) throws Exceptions.IncorrectIDClient {
		for (int i = 0; i < clients.size(); i++) {
			if (clients.get(i).id == id) {
				clients.get(i).setAddress(address);
				break;
			}
		}
		throw new Exceptions.IncorrectIDClient();
	}

	void setClientIDNumber(int id, String IDNumber) throws Exceptions.IncorrectIDClient {
		for (int i = 0; i < clients.size(); i++) {
			if (clients.get(i).id == id) {
				clients.get(i).setIDNumber(IDNumber);
				break;
			}
		}
		throw new Exceptions.IncorrectIDClient();
	}

	void createDebitAccount(int clientID, double percent)
			throws Exceptions.IncorrectIDClient, Exceptions.NegativePercent {
		if (percent < 0) {
			throw new Exceptions.NegativePercent();
		}
		for (int i = 0; i < clients.size(); i++) {
			if (clients.get(i).id == clientID) {
				DebitAccount newAccount = new DebitAccount(clients.get(i), percent);
				accountIDs.add(newAccount.id);
				break;
			}
		}
		throw new Exceptions.IncorrectIDClient();
	}

	void createDeposit(int clientID, int months, int days, double initialSumm)
			throws Exceptions.IncorrectIDClient, Exceptions.WrongDepositPercents, Exceptions.NegativeTime,
			Exceptions.NegativeMoneySumm, Exceptions.NegativePercent {
		if (months < 0 || days < 0) {
			throw new Exceptions.NegativeTime();
		}
		if (initialSumm < 0) {
			throw new Exceptions.NegativeMoneySumm();
		}
		double percent = -1;
		for (int i = 0; i < clients.size(); i++) {
			if (clients.get(i).id == clientID) {
				for (int j = 0; j < depositPercents.size(); j++) {
					if (depositPercents.get(j).from <= initialSumm && initialSumm < depositPercents.get(j).to) {
						percent = depositPercents.get(j).percent;
						break;
					}
				}
				if (percent == -1) {
					throw new Exceptions.WrongDepositPercents();
				}
				if (percent < 0) {
					throw new Exceptions.NegativePercent();
				}
				Deposit newAccount = new Deposit(clients.get(i), months, days, percent, initialSumm);
				accountIDs.add(newAccount.id);
				break;
			}
		}
		throw new Exceptions.IncorrectIDClient();
	}

	void createCreditAccount(int clientID, double comission, double limit)
			throws Exceptions.IncorrectIDClient, Exceptions.NegativeComission, Exceptions.NegativeLimit {
		if (comission < 0) {
			throw new Exceptions.NegativeComission();
		}
		if (limit < 0) {
			throw new Exceptions.NegativeLimit();
		}
		for (int i = 0; i < clients.size(); i++) {
			if (clients.get(i).id == clientID) {
				CreditAccount newAccount = new CreditAccount(clients.get(i), comission, limit);
				accountIDs.add(newAccount.id);
				break;
			}
		}
		throw new Exceptions.IncorrectIDClient();
	}
}
