import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TransactionTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testUndo() {
		Bank bank = new Bank(100, null);
		ClientBuilder newClient = new ClientBuilder(bank).withFullname("name", "surname").withAddress("address")
				.withInNumber("IDNumber");
		Client client = newClient.build();
		DebitAccount account = new DebitAccount(client, 10);
		DebitAccount account2 = new DebitAccount(client, 10);
		account.putIn(100);
		account.transfer(50, account2.id);
		Transaction.undo(Transaction.transactions.get(1).id);
		double expected = account.moneySumm;
		double actual = 100;
		double expected2 = account2.moneySumm;
		double actual2 = 0;
		Assert.assertEquals(expected == actual, true);
		Assert.assertEquals(expected2 == actual2, true);
	}

}
