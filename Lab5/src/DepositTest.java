import static org.junit.Assert.*;

import java.time.LocalDate;

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
		Bank bank = new Bank(100, null);
		ClientBuilder newClient = new ClientBuilder(bank).withFullname("name", "surname").withAddress("address")
				.withInNumber("IDNumber");
		Client client = newClient.build();
		Deposit account = new Deposit(client, 1, 0, 10, 100);
		account.putIn(100);
		double expected = account.moneySumm;
		double actual = 200;
		Assert.assertEquals(expected == actual, true);
	}
	@Test(expected = Exceptions.NotAfterThePeriod.class)
	public void testPeriodException() {
		Bank bank = new Bank(100, null);
		ClientBuilder newClient = new ClientBuilder(bank).withFullname("name", "surname").withAddress("address")
				.withInNumber("IDNumber");
		Client client = newClient.build();
		Deposit account = new Deposit(client, 1, 0, 10, 100);
		account.putIn(100);
		account.withdraw(50);
	}

}
