import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DepositTest {

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
		Deposit account = new Deposit(client, date, 1, 0, 10, 100);
		account.putIn(100);
		double expected = account.moneySumm;
		double actual = 200;
		Assert.assertEquals(expected == actual, true);
	}
	@Test(expected = Exceptions.NotAfterThePeriod.class)
	public void testPeriodException() {
		TestDate date = new TestDate();
		Bank bank = new Bank(100, null);
		ClientBuilder newClient = new ClientBuilder(bank).withFullname("name", "surname").withAddress("address")
				.withInNumber("IDNumber");
		Client client = newClient.build();
		Deposit account = new Deposit(client, date, 1, 0, 10, 100);
		account.putIn(100);
		account.withdraw(50);
	}
	@Test
	public void testWithdrawAndPercent() {
		TestDate date = new TestDate();
		date.setDate(LocalDateTime.of(2020, 11, 1, 00, 00));
		Bank bank = new Bank(100, null);
		ClientBuilder newClient = new ClientBuilder(bank).withFullname("name", "surname").withAddress("address")
				.withInNumber("IDNumber");
		Client client = newClient.build();
		Deposit account = new Deposit(client, date, 1, 0, 10, 100);
		account.putIn(100);
		((TestDate)account.date).setDate(LocalDateTime.of(2021, 05, 1, 00, 00));
		account.withdraw(50);
		int expected = (int) account.moneySumm;
		int actual = 160;
		Assert.assertEquals(expected == actual, true);
	}

}
