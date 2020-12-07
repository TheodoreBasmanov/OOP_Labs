import java.util.ArrayList;

public class ClientBuilder {
	Client client;

	ClientBuilder(Client client) {
		this.client = client;
	}

	ClientBuilder() {
		this.client = new Client();
	}

	ClientBuilder withFullname(String name, String surname) {
		client.name = name;
		client.surname = surname;
		return this;
	}

	ClientBuilder withAddress(String address) {
		client.address = address;
		return this;
	}

	ClientBuilder withIDNumber(String IDNumber) {
		client.IDNumber = IDNumber;
		return this;
	}

	Client build() {
		client.checkReliability();
		return client;
	}
}
