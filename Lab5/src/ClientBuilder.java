
public class ClientBuilder {
	private Client newClient;
	Bank bank;
	ClientBuilder(Bank bank) {
		this.bank = bank;
		newClient = new Client(bank);
	}

	ClientBuilder withFullname(String name, String surname) {
		newClient.setFullName(name, surname);
		return this;
	}

	ClientBuilder withAddress(String address) {
		newClient.setAddress(address);
		return this;
	}

	ClientBuilder withInNumber(String IDNumber) {
		newClient.setIDNumber(IDNumber);
		return this;
	}

	Client build() {
		return newClient;
	}
}
