package rest.springboot.restfulWebService.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Product class provides holder for PRODUCT entity attributes.
 * @author adash
 * @version Java: 1.8, Spring boot: 2.3.1
 */
@Entity
@Table
public class Product {
	/** Logger instance for Product class. */
	private static final Logger LOGGER = LoggerFactory.getLogger(Product.class);
	
	/** Primary key for PRODUCT entity. */
	@Id
	@GeneratedValue
	private long id;
	
	/** Stores name of the product. */
	@Column
	private String name;
	
	/** Stores quantity of the product. */
	@Column
	private int qty;
	
	/** Stores price of the product. */
	@Column
	private long price;
	
	/** Stores category id of the product. */
	@Column
	private long categoryId;

	/** Default constructor for PRODUCT entity. */
	public Product() {
		super();
	}

	/** Parameterized constructor for PRODUCT entity. */
	public Product(String name, int qty, long price, long categoryId) {
		super();
		this.name = name;
		this.qty = qty;
		this.price = price;
		this.categoryId = categoryId;
	}

	/**
	 * Retrieves product's qty from product's data.
	 * @return qty: Quantity of product.
	 */
	public int getQty() {
		LOGGER.trace("Retrieving product's quantity from PRODUCT data.");
		
		return qty;
	}

	/**
	 * Updates quantity of product.
	 */
	public void setQty(int qty) {
		LOGGER.trace("Setting product's quantity in PRODUCT data.");
		
		this.qty = qty;
	}

	/**
	 * Retrieves product's price from product's data.
	 * @return price: Price of product.
	 */
	public long getPrice() {
		LOGGER.trace("Retrieving product's price from PRODUCT data.");
		
		return price;
	}

	/**
	 * Updates price of product.
	 */
	public void setPrice(long price) {
		LOGGER.trace("Setting product's price in PRODUCT data.");
		
		this.price = price;
	}

	/**
	 * Retrieves product's category id from product's data.
	 * @return categoryId: Category id of product.
	 */
	public long getCategoryId() {
		LOGGER.trace("Retrieving product's category id from PRODUCT data.");
		
		return categoryId;
	}

	/**
	 * Updates category id of product.
	 */
	public void setCategoryId(long categoryId) {
		LOGGER.trace("Setting product's category id in PRODUCT data.");
		
		this.categoryId = categoryId;
	}
}