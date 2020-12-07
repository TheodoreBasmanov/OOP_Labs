import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DebitAccountTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPutIn() {
		TestDate date = new TestDate();
		Bank bank = new Bank(100, null);
		ClientBuilder newClient = new ClientBuilder().withFullname("name", "surname").withAddress("address")
				.withIDNumber("IDNumber");
		Client client = newClient.build();
		bank.addClient(client);
		DebitAccount account = new DebitAccount(client, date, 10);
		account.putIn(100);
		double expected = account.getBalance();
		double actual = 100;
		Assert.assertEquals(expected == actual, true);
	}

	@Test
	public void testWithraw() {
		TestDate date = new TestDate();
		Bank bank = new Bank(100, null);
		ClientBuilder newClient = new ClientBuilder().withFullname("name", "surname").withAddress("address")
				.withIDNumber("IDNumber");
		Client client = newClient.build();
		bank.addClient(client);
		DebitAccount account = new DebitAccount(client, date, 10);
		account.putIn(100);
		account.withdraw(50);
		double expected = account.getBalance();
		double actual = 50;
		Assert.assertEquals(expected == actual, true);
	}

	@Test
	public void testTransfer() {
		TestDate date = new TestDate();
		Bank bank = new Bank(100, null);
		ClientBuilder newClient = new ClientBuilder().withFullname("name", "surname").withAddress("address")
				.withIDNumber("IDNumber");
		Client client = newClient.build();
		bank.addClient(client);
		DebitAccount account = new DebitAccount(client, date, 10);
		DebitAccount account2 = new DebitAccount(client, date, 10);
		account.putIn(100);
		account.transfer(50, account2.id);
		double expected = account.getBalance();
		double actual = 50;
		double expected2 = account2.getBalance();
		double actual2 = 50;
		Assert.assertEquals(expected == actual, true);
		Assert.assertEquals(expected2 == actual2, true);
	}

	@Test(expected = Exceptions.CantBeNegative.class)
	public void testCantGoNegativeException() {
		TestDate date = new TestDate();
		Bank bank = new Bank(100, null);
		ClientBuilder newClient = new ClientBuilder().withFullname("name", "surname").withAddress("address")
				.withIDNumber("IDNumber");
		Client client = newClient.build();
		bank.addClient(client);
		DebitAccount account = new DebitAccount(client, date, 10);
		account.putIn(100);
		account.withdraw(500);
	}

	@Test(expected = Exceptions.OverUnrealible.class)
	public void testUnreliableException() {
		TestDate date = new TestDate();
		Bank bank = new Bank(20, null);
		ClientBuilder newClient = new ClientBuilder().withFullname("name", "surname");
		Client client = newClient.build();
		bank.addClient(client);
		DebitAccount account = new DebitAccount(client, date, 10);
		account.putIn(100);
		account.withdraw(50);
	}

	@Test
	public void testPercent() {
		TestDate date = new TestDate();
		date.setDate(LocalDateTime.of(2020, 11, 1, 00, 00));
		Bank bank = new Bank(100, null);
		ClientBuilder newClient = new ClientBuilder().withFullname("name", "surname").withAddress("address")
				.withIDNumber("IDNumber");
		Client client = newClient.build();
		bank.addClient(client);
		DebitAccount account = new DebitAccount(client, date, 10);
		account.putIn(100);
		((TestDate) account.date).setDate(LocalDateTime.of(2021, 05, 1, 00, 00));
		account.putIn(100);
		((TestDate) account.date).setDate(LocalDateTime.of(2021, 11, 1, 00, 00));
		int expected = (int) account.getBalance();
		int actual = 215;
		Assert.assertEquals(expected == actual, true);
	}

}
