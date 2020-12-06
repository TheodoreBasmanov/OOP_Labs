import static org.junit.Assert.*;

import java.time.LocalDate;

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
		Bank bank = new Bank(100, null);
		ClientBuilder newClient = new ClientBuilder(bank).withFullname("name", "surname").withAddress("address")
				.withInNumber("IDNumber");
		Client client = newClient.build();
		CreditAccount account = new CreditAccount(client, 10, 100);
		account.putIn(100);
		double expected = account.moneySumm;
		double actual = 100;
		Assert.assertEquals(expected == actual, true);
	}
	@Test
	public void testWithdrawNegative() {
		Bank bank = new Bank(100, null);
		ClientBuilder newClient = new ClientBuilder(bank).withFullname("name", "surname").withAddress("address")
				.withInNumber("IDNumber");
		Client client = newClient.build();
		CreditAccount account = new CreditAccount(client, 10, 100);
		account.putIn(100);
		account.withdraw(200);
		double expected = account.moneySumm;
		double actual = -100;
		Assert.assertEquals(expected == actual, true);
	}
	@Test(expected = Exceptions.BelowTheLimit.class)
	public void testBelowLimitException() {
		Bank bank = new Bank(100, null);
		ClientBuilder newClient = new ClientBuilder(bank).withFullname("name", "surname").withAddress("address")
				.withInNumber("IDNumber");
		Client client = newClient.build();
		CreditAccount account = new CreditAccount(client, 10, 100);
		account.putIn(100);
		account.withdraw(500);
	}
	

}
