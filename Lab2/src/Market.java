import java.util.ArrayList;

public class Market {
	public static Market market = new Market();
	ArrayList<Shop> shops;

	public Market() {
		shops = new ArrayList<Shop>();
	}

	public void add(Shop shop) {
		shops.add(shop);
	}

	public ArrayList<Products.ProductInShop> whatCanYouBuy(int summ) {
		ArrayList<Products.ProductInShop> purchase = new ArrayList<Products.ProductInShop>();
		for (int i = 0; i < shops.size(); i++) {
			purchase.addAll(shops.get(i).whatCanYouBuy(summ));
		}
		return purchase;
	}

	public Shop findCheapestProduct(Products.Product product) {
		int minPrice = Integer.MAX_VALUE;
		Shop resultShop = null;
		for (int i = 0; i < shops.size(); i++) {
			Products.ProductInShop current = shops.get(i).findProduct(product.code);
			if ((current != null) && (current.price < minPrice)) {
				minPrice = current.price;
				resultShop = shops.get(i);
			}
		}
		if (resultShop.equals(null)) {
			System.out.println("All of the shops are out of the searched product.");
		}
		return resultShop;
	}

	public Shop findCheapestConsingment(Products.ProductInShop[] purchase) {
		Shop resultShop = null;
		int minPrice = Integer.MAX_VALUE;
		for (int i = 0; i < shops.size(); i++) {
			int purchasePrice = shops.get(i).findConsignment(purchase);
			if (purchasePrice > -1) {
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