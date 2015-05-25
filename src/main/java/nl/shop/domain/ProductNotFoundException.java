package nl.shop.domain;

public class ProductNotFoundException extends Exception {

	private static final long serialVersionUID = -7715109692200198649L;

	private Long productId;

	public ProductNotFoundException(Long productId) {
		this.productId = productId;
	}

	public Long getProductId() {
		return productId;
	}

}
