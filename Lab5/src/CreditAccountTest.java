import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CreditAccountTest {

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
		ClientBuilder newClient = new ClientBuilder(bank).withFullname("name", "surname").withAddress("address")
				.withInNumber("IDNumber");
		Client client = newClient.build();
		CreditAccount account = new CreditAccount(client, date, 10, 100);
		account.putIn(100);
		double expected = account.getBalance();
		double actual = 100;
		Assert.assertEquals(expected == actual, true);
	}
	@Test
	public void testWithdrawNegative() {
		TestDate date = new TestDate();
		Bank bank = new Bank(100, null);
		ClientBuilder newClient = new ClientBuilder(bank).withFullname("name", "surname").withAddress("address")
				.withInNumber("IDNumber");
		Client client = newClient.build();
		CreditAccount account = new CreditAccount(client, date, 10, 100);
		account.putIn(100);
		account.withdraw(200);
		double expected = account.getBalance();
		double actual = -100;
		Assert.assertEquals(expected == actual, true);
	}
	@Test(expected = Exceptions.BelowTheLimit.class)
	public void testBelowLimitException() {
		TestDate date = new TestDate();
		Bank bank = new Bank(100, null);
		ClientBuilder newClient = new ClientBuilder(bank).withFullname("name", "surname").withAddress("address")
				.withInNumber("IDNumber");
		Client client = newClient.build();
		CreditAccount account = new CreditAccount(client, date, 10, 100);
		account.putIn(100);
		account.withdraw(500);
	}
	@Test
	public void testComission() {
		TestDate date = new TestDate();
		date.setDate(LocalDateTime.of(2020, 11, 1, 00, 00));
		Bank bank = new Bank(100, null);
		ClientBuilder newClient = new ClientBuilder(bank).withFullname("name", "surname").withAddress("address")
				.withInNumber("IDNumber");
		Client client = newClient.build();
		CreditAccount account = new CreditAccount(client, date, 10, 100);
		account.putIn(100);
		account.withdraw(110);
		((TestDate)account.date).setDate(LocalDateTime.of(2020, 12, 1, 00, 00));
		double expected = account.getBalance();
		double actual = -20;
		Assert.assertEquals(expected == actual, true);
	}

}
