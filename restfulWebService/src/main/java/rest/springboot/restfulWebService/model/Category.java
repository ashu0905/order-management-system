package rest.springboot.restfulWebService.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Category class provides holder for CATEGORY entity attributes.
 * @author adash
 * @version Java: 1.8, Spring boot: 2.3.1
 */
@Entity
@Table
public class Category {
	/** Logger instance for Category class. */
	private static final Logger LOGGER = LoggerFactory.getLogger(Category.class);
	
	/** Primary key for CATEGORY entity. */
	@Id
	@GeneratedValue
	private long id;
	
	/** Stores name of the product category. */
	@Column
	private String name;
	
	/** Default constructor for CATEGORY entity. */
	public Category() {
		super();
	}

	/** Parameterized constructor for CATEGORY entity. */
	public Category(String name) {
		super();
		this.name = name;
	}

	/**
	 * Retrieves category's name from category's data.
	 * @return name: Name of product category.
	 */
	public String getName() {
		LOGGER.trace("Retrieving product category's name from CATEGORY data.");
		
		return name;
	}

	/**
	 * Updates name of product category.
	 */
	public void setName(String name) {
		LOGGER.trace("Setting product category's name in CATEGORY data.");
		
		this.name = name;
	}
}