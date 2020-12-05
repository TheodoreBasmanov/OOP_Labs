import java.util.ArrayList;

public class Client {
	String name;
	String surname;
	String address;
	String IDNumber;
	boolean isReliable;
	Bank bank;
	ArrayList<Integer> accountIDs;

	Client(Bank bank) {
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

	void makeReliable(String address, String IDNumber) {
		this.setAddress(address);
		this.setIDNumber(IDNumber);
		isReliable = true;
	}

	void setAddress(String address) {
		this.address = address;
	}

	void setIDNumber(String IDNumber) {
		this.IDNumber = IDNumber;
	}

	void addAcctount(Account account) {
		accountIDs.add(account.id);
		account.client = this;
	}
}
