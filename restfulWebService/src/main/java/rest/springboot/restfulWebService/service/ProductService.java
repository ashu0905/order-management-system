package rest.springboot.restfulWebService.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rest.springboot.restfulWebService.model.Product;
import rest.springboot.restfulWebService.repository.ProductRepository;

/**
 * ProductService class provides methods containing business logic for PRODUCT entity.
 * It interacts with DAO of PRODUCT entity. 
 * @author adash
 * @version Java: 1.8, Spring boot: 2.3.1
 */
@Component
public class ProductService {

	/** Logger instance for ProductService class. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

	/** Injecting object dependency of ProductRepository. */
	@Autowired
	ProductRepository productRepository;

	/**
	 * Retrieves list of all products from Product entity in H2 db.
	 * Implements findAll() from ProductRepository interface to get the list of all 
	 * products from PRODUCT.
	 * @return List<Product>: List of all products.
	 */
	public List<Product> getAllProducts() {	
		LOGGER.trace("Retrieving list of products from PRODUCT repo.");

		List<Product> products = new ArrayList<Product>();
		productRepository.findAll().forEach(products::add);

		if (products.isEmpty()) {
			LOGGER.error("No Product data exists in PRODUCT repo.");
			return null;
		}

		LOGGER.debug("Products retrieved from PRODUCT repo: {}", products);
		return products;
	}
}