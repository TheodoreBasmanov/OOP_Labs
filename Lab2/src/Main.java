import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Products.Product carrot = new Products.Product("carrot");
		// Product anotherCarrot = new Product(1, "another carrot");
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
		Shop generalShop = new Shop("general shop", "Market Alley 2");
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
		// System.out.println(alchemyShop.catalogue.get(3).number);
		generalShop.consignment(potato, 10, 3);
		generalShop.consignment(sword, 2, 28);
		generalShop.consignment(shield, 3, 65);
		generalShop.consignment(healthPotion, 6, 20);
		System.out.println(
				"You can buy the cheapest " + shield.name + " in the " + Market.market.findCheapestProduct(shield).name);
		System.out.println("For 100 septims you can buy");
		// System.out.println(alchemyShop.catalogue.get(3).number);

		ArrayList<Products.ProductInShop> purchase1 = Market.market.whatCanYouBuy(100);
		for (int i = 0; i < purchase1.size(); i++) {
			System.out.println(purchase1.get(i).name + " " + purchase1.get(i).number);
		}
		// alchemyShop.whatCanYouBuy(100);
		// System.out.println(alchemyShop.catalogue.get(3).number);
		System.out.println("Buying 3 " + poison.name + " in the " + alchemyShop.name + " will cost "
				+ alchemyShop.purchase(poison, 3) + " septims.");
		Products.ProductInShop[] purchase = new Products.ProductInShop[2];
		purchase[0] = new Products.ProductInShop(sword, 1);
		purchase[1] = new Products.ProductInShop(shield, 1);
		System.out.println(
				"You can buy the cheapest " + purchase[0].number + " " + purchase[0].name + " and " + purchase[1].number
						+ " " + purchase[1].name + " in the " + Market.market.findCheapestConsingment(purchase).name);
		purchase[1] = new Products.ProductInShop(cabbage, 10);
		Market.market.findCheapestConsingment(purchase);
	}
}
