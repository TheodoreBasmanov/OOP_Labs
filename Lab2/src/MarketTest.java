import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MarketTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAdd() {
		Market.market.shops.clear();
		Shop vegetableShop = new Shop("vegetalbe shop", "Market Square 1");
		Shop armorShop = new Shop("armor shop", "Market Square 2");
		Shop alchemyShop = new Shop("alchemy shop", "Market Square 3");
		Shop generalShop = new Shop("general shop", "Market Alley 2");
		Shop expected1 = Market.market.shops.get(0);
		Shop actual1 = vegetableShop;
		Shop expected2 = Market.market.shops.get(Market.market.shops.size()-1);
		Shop actual2 = generalShop;
		Assert.assertEquals(expected1, actual1);
		Assert.assertEquals(expected2, actual2);
	}

	@Test
	public void testWhatCanYouBuy() {
		Market.market.shops.clear();
		Products.Product carrot = new Products.Product("carrot");
		Products.Product potato = new Products.Product("potato");
		Products.Product cabbage = new Products.Product("cabbage");
		Products.Product sword = new Products.Product("sword");
		Products.Product breastplate = new Products.Product("breastplate");
		Products.Product shield = new Products.Product("shield");
		Products.Product helmet = new Products.Product("helmet");
		Products.Product healthPotion = new Products.Product("health potion");
		Products.Product magickaPotion = new Products.Product("magicka potion");
		Products.Product staminaPotion = new Products.Product("stamina potion");
		Products.Product poison = new Products.Product("poison");
		Shop vegetableShop = new Shop("vegetalbe shop", "Market Square 1");
		Shop armorShop = new Shop("armor shop", "Market Square 2");
		Shop alchemyShop = new Shop("alchemy shop", "Market Square 3");
		vegetableShop.consignment(carrot, 10, 1);
		vegetableShop.consignment(potato, 15, 1);
		vegetableShop.consignment(cabbage, 5, 2);
		armorShop.consignment(sword, 4, 25);
		armorShop.consignment(breastplate, 5, 125);
		armorShop.consignment(shield, 1, 60);
		armorShop.consignment(helmet, 3, 60);
		alchemyShop.consignment(healthPotion, 10, 17);
		alchemyShop.consignment(magickaPotion, 7, 20);
		alchemyShop.consignment(staminaPotion, 9, 20);
		alchemyShop.consignment(poison, 5, 58);
		ArrayList<Products.ProductInShop> expected = Market.market.whatCanYouBuy(100);
		ArrayList<Products.ProductInShop> actual = new ArrayList<Products.ProductInShop>();
		actual.add(new Products.ProductInShop(carrot, 1, 100));
		actual.add(new Products.ProductInShop(potato, 1, 100));
		actual.add(new Products.ProductInShop(cabbage, 2, 50));
		actual.add(new Products.ProductInShop(sword, 25, 4));
		actual.add(new Products.ProductInShop(shield, 60, 1));
		actual.add(new Products.ProductInShop(helmet, 60, 1));
		actual.add(new Products.ProductInShop(healthPotion, 17, 5));
		actual.add(new Products.ProductInShop(magickaPotion, 20, 5));
		actual.add(new Products.ProductInShop(staminaPotion, 20, 5));
		actual.add(new Products.ProductInShop(poison, 58, 1));
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testFindCheapestProduct() {
		Market.market.shops.clear();
		Products.Product carrot = new Products.Product("carrot");
		Products.Product potato = new Products.Product("potato");
		Products.Product cabbage = new Products.Product("cabbage");
		Shop vegetableShop = new Shop("vegetalbe shop", "Market Square 1");
		Shop generalShop = new Shop("general shop", "Market Alley 2");
		vegetableShop.consignment(carrot, 10, 1);
		vegetableShop.consignment(potato, 15, 1);
		vegetableShop.consignment(cabbage, 5, 2);
		generalShop.consignment(potato, 10, 3);
		Shop expected = Market.market.findCheapestProduct(potato);
		Shop actual = vegetableShop;
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testFindCheapestConsingment() {
		Market.market.shops.clear();
		Products.Product sword = new Products.Product("sword");
		Products.Product breastplate = new Products.Product("breastplate");
		Products.Product shield = new Products.Product("shield");
		Products.Product helmet = new Products.Product("helmet");
		Shop armorShop = new Shop("armor shop", "Market Square 2");
		Shop generalShop = new Shop("general shop", "Market Alley 2");
		armorShop.consignment(sword, 4, 25);
		armorShop.consignment(breastplate, 5, 125);
		armorShop.consignment(shield, 1, 60);
		armorShop.consignment(helmet, 3, 60);
		generalShop.consignment(sword, 2, 28);
		generalShop.consignment(shield, 3, 65);
		Products.ProductInShop[] purchase = new Products.ProductInShop[2];
		purchase[0] = new Products.ProductInShop(sword, 1);
		purchase[1] = new Products.ProductInShop(shield, 1);
		Shop expected = Market.market.findCheapestConsingment(purchase);
		Shop actual = armorShop;
		Assert.assertEquals(expected, actual);
	}

}
