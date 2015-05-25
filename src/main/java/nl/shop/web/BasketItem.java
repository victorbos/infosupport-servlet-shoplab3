package nl.shop.web;

import java.io.Serializable;

import nl.shop.domain.Product;

public class BasketItem implements Serializable {

	private static final long serialVersionUID = -7236456125940968072L;

	private Product product;

	private int amount = 0;

	public BasketItem(Product product) {
		this.product = product;
	}

	public Product getProduct() {
		return product;
	}

	public void incrementamount(int increment) {
		amount += increment;
	}

	public void incrementamount() {
		amount++;
	}

	public int getamount() {
		return amount;
	}
}
