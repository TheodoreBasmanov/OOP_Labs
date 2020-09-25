import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static Market market = new Market();
	public static AllProducts allProducts = new AllProducts();

	public static class Product {
		int code;
		String name;

		public Product(int code, String name) throws Exceptions.NotUniqueCode {
			if (allProducts.checkProduct(code)) {
				this.code = code;
				this.name = name;
				allProducts.add(this);
			} else {
				throw new Exceptions.NotUniqueCode();
			}
		}

		protected Product(Product product) {
			this.code = product.code;
			this.name = product.name;
		}
	}

	public static class AllProducts {
		ArrayList<Product> products;

		public AllProducts() {
			products = new ArrayList<Product>();
		}

		public boolean checkProduct(int code) {
			for (int i = 0; i < products.size(); i++) {
				if (products.get(i).code == code) {
					return false;
				}
			}
			return true;
		}

		public void add(Product product) {
			products.add(product);
		}
	}

	public static class ProductInShop extends Product {
		int price;
		int number;

		public ProductInShop(Product product, int price, int number) {
			super(product);
			this.price = price;
			this.number = number;
		}
		public ProductInShop(Product product, int number){
			this(product, 0, number);
		}
	}

	public static class Shop {
		int code;
		String name;
		String address;
		ArrayList<ProductInShop> catalogue;

		public Shop(int code, String name, String address) throws Exceptions.NotUniqueCode {
			if (market.checkShop(code)) {
				this.code = code;
				this.name = name;
				this.address = address;
				catalogue = new ArrayList<ProductInShop>();
				market.add(this);
			} else {
				throw new Exceptions.NotUniqueCode();
			}
		}

		public void consignment(Product product, int number, int price) {
			for (int i = 0; i < catalogue.size(); i++) {
				if (catalogue.get(i).code == product.code) {
					catalogue.get(i).number += number;
					catalogue.get(i).price = price;
					return;
				}
			}
			ProductInShop newProduct = new ProductInShop(product, price, number);
			catalogue.add(newProduct);
		}
		public void consignment(Product product, int number) {
			for (int i = 0; i < catalogue.size(); i++) {
				if (catalogue.get(i).code == product.code) {
					catalogue.get(i).number += number;
					return;
				}
			}
			ProductInShop newProduct = new ProductInShop(product, number);
			catalogue.add(newProduct);
		}

		public void whatCanYouBuy(int summ) {
			ArrayList<ProductInShop> purchase = new ArrayList<ProductInShop>();
			for (int i = 0; i < catalogue.size(); i++) {
				if (catalogue.get(i).price < summ) {
					purchase.add(catalogue.get(i));
					purchase.get(purchase.size() - 1).number = summ / catalogue.get(i).price;
				}
			}
			if (purchase.size() == 0) {
				System.out.println("You can't buy anything.");
			} else {
				System.out.println("You can buy:");
				for (int i = 0; i < purchase.size() - 1; i++) {
					System.out.println(purchase.get(i).number + " " + purchase.get(i).name + " or");
				}
				System.out.println(
						purchase.get(purchase.size() - 1).number + " " + purchase.get(purchase.size() - 1).name + ".");
			}
		}

		public int purchase(Product product, int number) {
			for (int i = 0; i < catalogue.size(); i++) {
				if (catalogue.get(i).code == product.code) {
					if (catalogue.get(i).number >= number) {
						return number * catalogue.get(i).price;
					} else {
						System.out.println("There's not enough product.");
						return 0;
					}
				}
			}
			System.out.println("There's no such product in the shop.");
			return 0;
		}

	}

	public static class Market {
		ArrayList<Shop> shops;

		public Market() {
			shops = new ArrayList<Shop>();
		}

		public boolean checkShop(int code) {
			for (int i = 0; i < shops.size(); i++) {
				if (shops.get(i).code == code) {
					return false;
				}
			}
			return true;
		}

		public void add(Shop shop) {
			shops.add(shop);
		}

		public Shop findCheapestProduct(Product product) {
			int minPrice = Integer.MAX_VALUE;
			Shop resultShop = null;
			for (int i = 0; i < shops.size(); i++) {
				for (int j = 0; j < shops.get(i).catalogue.size(); j++) {
					if ((shops.get(i).catalogue.get(j).code == product.code)
							&& (shops.get(i).catalogue.get(j).price < minPrice)) {
						minPrice = shops.get(i).catalogue.get(j).price;
						resultShop = shops.get(i);
					}
				}
			}
			if (resultShop.equals(null)) {
				System.out.println("All of the shops are out of the searched product.");
			}
			return resultShop;
		}

		public Shop findCheapestConsingment(ProductInShop[] purchase) {
			Shop resultShop = null;
			int minPrice = Integer.MAX_VALUE;
			for (int i = 0; i < shops.size(); i++) {
				int purchasePrice = 0;
				boolean enoughProduct = true;
				boolean productExists = true;
				for (int k = 0; k < purchase.length; k++) {
					if (enoughProduct && productExists) {
						productExists = false;
						for (int j = 0; j < shops.get(i).catalogue.size(); j++) {
							if (shops.get(i).catalogue.get(j).code == purchase[k].code) {
								if (shops.get(i).catalogue.get(j).number >= purchase[k].number) {
									purchasePrice += purchase[k].number * shops.get(i).catalogue.get(j).price;
									productExists = true;
									// System.out.println(purchase[k].product.name);
								} else {
									enoughProduct = false;
									break;
								}
							}
						}
					} else {
						break;
					}
				}
				// System.out.println(productExists);
				if (enoughProduct && productExists) {
					// System.out.println(purchasePrice);
					if (purchasePrice < minPrice) {
						minPrice = purchasePrice;
						resultShop = shops.get(i);
					}
				}
			}
			if (resultShop == null) {
				System.out.println("There's no shop in which you can purchase such a consingment.");
			}
			return resultShop;
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Product carrot = new Product(1, "carrot");
		// Product anotherCarrot = new Product(1, "another carrot");
		Product potato = new Product(2, "potato");
		Product cabbage = new Product(3, "cabbage");
		Product sword = new Product(4, "sword");
		Product breastplate = new Product(5, "breastplate");
		Product shield = new Product(6, "shield");
		Product helmet = new Product(7, "helmet");
		Product healthPotion = new Product(8, "health potion");
		Product magickaPotion = new Product(9, "magicka potion");
		Product staminaPotion = new Product(10, "stamina potion");
		Product poison = new Product(11, "poison");
		Shop vegetableShop = new Shop(1, "vegetalbe shop", "Market Square 1");
		Shop armorShop = new Shop(2, "armor shop", "Market Square 2");
		Shop alchemyShop = new Shop(3, "alchemy shop", "Market Square 3");
		Shop generalShop = new Shop(4, "general shop", "Market Alley 2");
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
		generalShop.consignment(potato, 10, 3);
		generalShop.consignment(sword, 2, 28);
		generalShop.consignment(shield, 3, 65);
		generalShop.consignment(healthPotion, 6, 20);
		System.out.println(
				"You can buy the cheapest " + shield.name + " in the " + market.findCheapestProduct(shield).name);
		System.out.println("In the" + armorShop.name + " for 100 septims");
		armorShop.whatCanYouBuy(100);
		System.out.println("Buying 3 " + poison.name + " in the" + alchemyShop.name + " will cost "
				+ alchemyShop.purchase(poison, 3) + " septims.");
		ProductInShop[] purchase = new ProductInShop[2];
		purchase[0] = new ProductInShop(sword, 1);
		purchase[1] = new ProductInShop(shield, 1);
		System.out.println(
				"You can buy the cheapest " + purchase[0].number + " " + purchase[0].name + " and " + purchase[1].number
						+ " " + purchase[1].name + " in the " + market.findCheapestConsingment(purchase).name);
		purchase[1] = new ProductInShop(cabbage, 10);
		market.findCheapestConsingment(purchase);
	}
}
