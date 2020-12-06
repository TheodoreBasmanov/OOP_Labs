import static org.junit.Assert.*;

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
		Bank bank = new Bank(100, null);
		ClientBuilder newClient = new ClientBuilder(bank).withFullname("name", "surname").withAddress("address")
				.withInNumber("IDNumber");
		Client client = newClient.build();
		DebitAccount account = new DebitAccount(client, 10);
		account.putIn(100);
		double expected = account.moneySumm;
		double actual = 100;
		Assert.assertEquals(expected == actual, true);
	}
	@Test
	public void testWithraw() {
		Bank bank = new Bank(100, null);
		ClientBuilder newClient = new ClientBuilder(bank).withFullname("name", "surname").withAddress("address")
				.withInNumber("IDNumber");
		Client client = newClient.build();
		DebitAccount account = new DebitAccount(client, 10);
		account.putIn(100);
		account.withdraw(50);
		double expected = account.moneySumm;
		double actual = 50;
		Assert.assertEquals(expected == actual, true);
	}
	@Test
	public void testTransfer() {
		Bank bank = new Bank(100, null);
		ClientBuilder newClient = new ClientBuilder(bank).withFullname("name", "surname").withAddress("address")
				.withInNumber("IDNumber");
		Client client = newClient.build();
		DebitAccount account = new DebitAccount(client, 10);
		DebitAccount account2 = new DebitAccount(client, 10);
		account.putIn(100);
		account.transfer(50, account2.id);
		double expected = account.moneySumm;
		double actual = 50;
		double expected2 = account2.moneySumm;
		double actual2 = 50;
		Assert.assertEquals(expected == actual, true);
		Assert.assertEquals(expected2 == actual2, true);
	}
	@Test(expected = Exceptions.CantBeNegative.class)
	public void testCantGoNegativeException() {
	    Bank bank = new Bank(100, null);
		ClientBuilder newClient = new ClientBuilder(bank).withFullname("name", "surname").withAddress("address")
				.withInNumber("IDNumber");
		Client client = newClient.build();
		DebitAccount account = new DebitAccount(client, 10);
		account.putIn(100);
		account.withdraw(500);
	}
	@Test(expected = Exceptions.OverUnrealible.class)
	public void testUnreliableException() {
	    Bank bank = new Bank(20, null);
		ClientBuilder newClient = new ClientBuilder(bank).withFullname("name", "surname");
		Client client = newClient.build();
		DebitAccount account = new DebitAccount(client, 10);
		account.putIn(100);
		account.withdraw(50);
	}

}
