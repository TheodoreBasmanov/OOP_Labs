import java.util.ArrayList;


	public class Shop {
		static int NextCode = 1;
		int code;
		String name;
		String address;
		ArrayList<Products.ProductInShop> catalogue;

		public Shop(String name, String address) {
			this.code = NextCode;
			NextCode++;
			this.name = name;
			this.address = address;
			catalogue = new ArrayList<Products.ProductInShop>();
			Main.market.add(this);
		}

		public Products.ProductInShop findProduct(int code) {
			for (int i = 0; i < catalogue.size(); i++) {
				if (catalogue.get(i).code == code) {
					return catalogue.get(i);
				}
			}
			return null;
		}

		public void consignment(Products.Product product, int number, int price)
				throws Exceptions.NegativePrice, Exceptions.NegativeNumber {
			if (price < 0) {
				throw new Exceptions.NegativePrice();
			}
			if (number < 0) {
				throw new Exceptions.NegativeNumber();
			}
			for (int i = 0; i < catalogue.size(); i++) {
				if (catalogue.get(i).code == product.code) {
					catalogue.get(i).number += number;
					catalogue.get(i).price = price;
					return;
				}
			}
			Products.ProductInShop newProduct = new Products.ProductInShop(product, price, number);
			catalogue.add(newProduct);
		}

		public void consignment(Products.Product product, int number) {
			for (int i = 0; i < catalogue.size(); i++) {
				if (catalogue.get(i).code == product.code) {
					catalogue.get(i).number += number;
					return;
				}
			}
			Products.ProductInShop newProduct = new Products.ProductInShop(product, number);
			catalogue.add(newProduct);
		}

		public ArrayList<Products.ProductInShop> whatCanYouBuy(int summ) {
			ArrayList<Products.ProductInShop> purchase = new ArrayList<Products.ProductInShop>();
			for (int i = 0; i < catalogue.size(); i++) {
				if (catalogue.get(i).price < summ) {
					Products.ProductInShop currentProduct = new Products.ProductInShop(catalogue.get(i));
					purchase.add(currentProduct);
					purchase.get(purchase.size() - 1).number = summ / catalogue.get(i).price;
				}
			}
			return purchase;
		}

		public int purchase(Products.Product product, int number) throws Exceptions.NotEnoughProduct, Exceptions.NoSuchProduct {
			for (int i = 0; i < catalogue.size(); i++) {
				if (catalogue.get(i).code == product.code) {
					if (catalogue.get(i).number >= number) {
						int price = catalogue.get(i).price;

						if (catalogue.get(i).number > number) {
							catalogue.get(i).number -= number;
						} else {
							catalogue.remove(i);
						}
						return number * price;
					} else {
						throw new Exceptions.NotEnoughProduct();
					}
				}
			}
			throw new Exceptions.NoSuchProduct();
		}

		public int findConsignment(Products.ProductInShop[] purchase) {
			int purchasePrice = 0;
			boolean enoughProduct = true;
			boolean productExists = true;
			for (int k = 0; k < purchase.length; k++) {
				if (enoughProduct && productExists) {
					productExists = false;
					for (int j = 0; j < catalogue.size(); j++) {
						if (catalogue.get(j).code == purchase[k].code) {
							if (catalogue.get(j).number >= purchase[k].number) {
								purchasePrice += purchase[k].number * catalogue.get(j).price;
								productExists = true;
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
			if (enoughProduct && productExists) {
				return purchasePrice;
			} else {
				return -1;
			}
		}

	}

	

