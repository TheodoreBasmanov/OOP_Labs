
public class Products {
	public static class Product {
		private static int NextCode = 1;
		int code;
		String name;

		public Product(String name) {
			this.code = NextCode;
			NextCode++;
			this.name = name;
		}

		protected Product(Product product) {
			if (product != null) {
				this.code = product.code;
				this.name = product.name;
			} else {
				this.code = 0;
				this.name = "";
			}
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

		public ProductInShop(Product product, int number) {
			this(product, 0, number);
		}

		public ProductInShop(ProductInShop ProductInShop) {
			this(null, 0);
			this.code = ProductInShop.code;
			this.name = ProductInShop.name;
			this.number = ProductInShop.number;
			this.price = ProductInShop.price;
		}

		@Override
		public boolean equals(Object o) {
			if (getClass() != o.getClass()) {
				return false;
			}
			ProductInShop product = (ProductInShop) o;
			return this.code == product.code && this.name.equals(product.name) && this.number == product.number
					&& this.price == product.price;
		}
	}
}
