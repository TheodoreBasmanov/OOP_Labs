import java.util.ArrayList;

public class Client {
	private static int ID = 1;
	int id;
	String name;
	String surname;
	String address;
	String IDNumber;
	boolean isReliable;
	Bank bank;
	ArrayList<Integer> accountIDs;

	Client() {
		id = ID;
		ID++;
		accountIDs = new ArrayList<Integer>();
		isReliable = false;
	}

	void setBank(Bank bank) {
		this.bank = bank;
	}

	void makeReliable(String address, String IDNumber) throws Exceptions.AlreadyReliable {
		if (isReliable) {
			throw new Exceptions.AlreadyReliable();
		}
		this.address = address;
		this.IDNumber = IDNumber;
		isReliable = true;
	}

	void addAcctount(Account account) {
		accountIDs.add(account.id);
		account.client = this;
	}

	void checkReliability() {
		if (address != null && IDNumber != null) {
			isReliable = true;
		} else {
			isReliable = false;
		}
	}

	String whyUnreliable() {
		if (isReliable) {
			return null;
		}
		if (this.address.equals(null) && this.IDNumber.equals(null)) {
			return "both";
		}
		if (this.address.equals(null)) {
			return "address";
		}
		if (this.IDNumber.equals(null)) {
			return "ID number";
		}
		return null;
	}
}
