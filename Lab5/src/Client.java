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

	Client(Bank bank) {
		id = ID;
		ID++;
		accountIDs = new ArrayList<Integer>();
		isReliable = false;
		this.bank = bank;
	}

	void setName(String name) {
		this.name = name;
	}

	void setSurname(String surname) {
		this.surname = surname;
	}

	void setFullName(String name, String surname) {
		this.setName(name);
		this.setSurname(surname);
	}

	void makeReliable(String address, String IDNumber) throws Exceptions.AlreadyReliable {
		if (isReliable) {
			throw new Exceptions.AlreadyReliable();
		}
		this.setAddress(address);
		this.setIDNumber(IDNumber);
		isReliable = true;
	}

	void setAddress(String address) {
		this.address = address;
		if (this.IDNumber != null) {
			isReliable = true;
		}
	}

	void setIDNumber(String IDNumber) {
		this.IDNumber = IDNumber;
		if (this.address != null) {
			isReliable = true;
		}
	}

	void addAcctount(Account account) {
		accountIDs.add(account.id);
		account.client = this;
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
