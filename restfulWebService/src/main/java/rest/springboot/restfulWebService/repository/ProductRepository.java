package rest.springboot.restfulWebService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rest.springboot.restfulWebService.model.Product;

/**
 * ProductRepository interface provides methods for ProductService class to 
 * implement.
 * @author adash
 * @version Java: 1.8, Spring boot: 2.3.1
 */
public interface ProductRepository extends JpaRepository<Product, Long> {}