package nl.shop.web;

import java.util.ArrayList;
import java.util.List;

import nl.shop.domain.Product;

public class ShoppingBasket {

	private List<BasketItem> items = new ArrayList<>();

	public double getTotal() {
		double total = 0.0;
		for (BasketItem item : items) {
			total += item.getProduct().getPrice() * item.getamount();
		}
		return total;
	}

	public List<BasketItem> getItems() {
		return items;
	}

	public void addProduct(Product product) {
		BasketItem productBasketItem = findExistingBasketItem(product);
		if (productBasketItem == null) {
			productBasketItem = new BasketItem(product);
			items.add(productBasketItem);
		}
		productBasketItem.incrementamount();
	}

	private BasketItem findExistingBasketItem(Product product) {
		BasketItem productBasketItem = null;
		for (BasketItem item : items) {
			if (item.getProduct().getId().equals(product.getId())) {
				productBasketItem = item;
				break;
			}
		}
		return productBasketItem;
	}

}
