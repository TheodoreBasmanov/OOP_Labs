import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ShopTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFindProduct() {
		Products.Product carrot = new Products.Product("carrot");
		Products.Product potato = new Products.Product("potato");
		Products.Product cabbage = new Products.Product("cabbage");
		Shop vegetableShop = new Shop("vegetalbe shop", "Market Square 1");
		vegetableShop.consignment(carrot, 10, 1);
		vegetableShop.consignment(potato, 15, 1);
		vegetableShop.consignment(cabbage, 5, 2);
		Products.ProductInShop expected = vegetableShop.findProduct(potato.code);
		Products.ProductInShop actual = new Products.ProductInShop(potato, 1, 15);
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testConsignmentProductIntInt() {
		Products.Product iron = new Products.Product("iron ingot");
		Products.Product steel = new Products.Product("steel ingot");
		Products.Product silver = new Products.Product("silver ingot");
		Shop materialShop = new Shop("material shop", "Market Square 4");
		materialShop.consignment(iron, 20, 7);
		materialShop.consignment(steel, 15, 20);
		materialShop.consignment(silver, 5, 50);
		Products.ProductInShop expected1 = materialShop.findProduct(iron.code);
		Products.ProductInShop actual1 = new Products.ProductInShop(iron, 7, 20);
		Products.ProductInShop expected2 = materialShop.findProduct(steel.code);
		Products.ProductInShop actual2 = new Products.ProductInShop(steel, 20, 15);
		Products.ProductInShop expected3 = materialShop.findProduct(silver.code);
		Products.ProductInShop actual3 = new Products.ProductInShop(silver, 50, 5);
		Assert.assertEquals(expected1, actual1);
		Assert.assertEquals(expected2, actual2);
		Assert.assertEquals(expected3, actual3);
	}

	@Test
	public void testConsignmentProductInt() {
		Products.Product iron = new Products.Product("iron ingot");
		Products.Product steel = new Products.Product("steel ingot");
		Products.Product silver = new Products.Product("silver ingot");
		Shop materialShop = new Shop("material shop", "Market Square 4");
		materialShop.consignment(iron, 20, 7);
		materialShop.consignment(steel, 15, 20);
		materialShop.consignment(silver, 5, 50);
		materialShop.consignment(iron, 20);
		Products.ProductInShop expected = materialShop.findProduct(iron.code);
		Products.ProductInShop actual = new Products.ProductInShop(iron, 7, 40);
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testWhatCanYouBuy() {
		Products.Product sword = new Products.Product("sword");
		Products.Product breastplate = new Products.Product("breastplate");
		Products.Product shield = new Products.Product("shield");
		Products.Product helmet = new Products.Product("helmet");
		Shop armorShop = new Shop("armor shop", "Market Square 2");
		armorShop.consignment(sword, 4, 25);
		armorShop.consignment(breastplate, 5, 125);
		armorShop.consignment(shield, 1, 60);
		armorShop.consignment(helmet, 3, 60);
		ArrayList<Products.ProductInShop> expected = armorShop.whatCanYouBuy(100);
		ArrayList<Products.ProductInShop> actual = new ArrayList<Products.ProductInShop>();
		actual.add(new Products.ProductInShop(sword, 25, 4));
		actual.add(new Products.ProductInShop(shield, 60, 1));
		actual.add(new Products.ProductInShop(helmet, 60, 1));
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testPurchase() {
		Products.Product healthPotion = new Products.Product("health potion");
		Products.Product magickaPotion = new Products.Product("magicka potion");
		Products.Product staminaPotion = new Products.Product("stamina potion");
		Products.Product poison = new Products.Product("poison");
		Shop alchemyShop = new Shop("alchemy shop", "Market Square 3");
		alchemyShop.consignment(healthPotion, 10, 17);
		alchemyShop.consignment(magickaPotion, 7, 20);
		alchemyShop.consignment(staminaPotion, 9, 20);
		alchemyShop.consignment(poison, 5, 58);
		int expected = alchemyShop.purchase(poison, 3);
		int actual = 174;
		Products.ProductInShop expectedProd = alchemyShop.findProduct(poison.code);
		Products.ProductInShop actualProd = new Products.ProductInShop(poison, 58, 2);
		Assert.assertEquals(expected, actual);
		Assert.assertEquals(expectedProd, actualProd);
	}

	@Test
	public void testFindConsignment() {
		Products.Product sword = new Products.Product("sword");
		Products.Product breastplate = new Products.Product("breastplate");
		Products.Product shield = new Products.Product("shield");
		Products.Product helmet = new Products.Product("helmet");
		Shop armorShop = new Shop("armor shop", "Market Square 2");
		armorShop.consignment(sword, 4, 25);
		armorShop.consignment(breastplate, 5, 125);
		armorShop.consignment(shield, 1, 60);
		armorShop.consignment(helmet, 3, 60);
		Products.ProductInShop[] purchase = new Products.ProductInShop[2];
		purchase[0] = new Products.ProductInShop(sword, 1);
		purchase[1] = new Products.ProductInShop(shield, 1);
		int expected = armorShop.findConsignment(purchase);
		int actual = 85;
		Assert.assertEquals(expected, actual);
	}

}
