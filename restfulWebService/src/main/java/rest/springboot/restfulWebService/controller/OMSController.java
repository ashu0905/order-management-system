package rest.springboot.restfulWebService.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rest.springboot.restfulWebService.model.Orders;
import rest.springboot.restfulWebService.model.Product;
import rest.springboot.restfulWebService.model.User;
import rest.springboot.restfulWebService.service.OrderService;
import rest.springboot.restfulWebService.service.ProductService;
import rest.springboot.restfulWebService.service.UserService;

/**
 * OMSController class provides request handling methods for User, Product & Order APIs. 
 * @author adash
 * @version Java: 1.8, Spring boot: 2.3.1
 */
@RestController
@RequestMapping("/oms")
public class OMSController {

	/** Logger instance for OMSController class. */
	private static final Logger LOGGER = LoggerFactory.getLogger(OMSController.class);

	/** Injecting object dependency of ProductService. */
	@Autowired
	ProductService productService;

	/** Injecting object dependency of UserService. */
	@Autowired
	OrderService orderService;

	/**
	 * Retrieves list of all products from Product entity in H2 db.
	 * Calls getAllProducts() from ProductService class to get the list of all 
	 * products from PRODUCT.
	 * @return List<Product>: List of all products.
	 */
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllProducts() {
		LOGGER.trace("Retrieving list of all products.");

		try {
			List<Product> products = new ArrayList<Product>();
			LOGGER.info("Calling ProductService.getAllProducts method to list all products.");
			productService.getAllProducts().forEach(products::add);

			if (products.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(products, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Creates a new order in Orders entity in H2 db.
	 * Calls addNewOrder() from OrderService class to create new order
	 * in ORDER.
	 * @param order: Orders JSON containing product id & user id.
	 * @return Orders: New order created.
	 */
	@PostMapping("/order")
	public ResponseEntity<Orders> createOrder(@RequestBody Orders order) {
		LOGGER.trace("Creating a new order.");

		try {
			LOGGER.info("Calling OrderService.addNewOrder method to create a new order.");
			Orders _order = orderService.addNewOrder(order);

			return new ResponseEntity<>(_order, HttpStatus.CREATED);
		} catch (Exception e) {
			LOGGER.error("Failed to create order in repo.");

			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Retrieves list of all orders from Orders entity in H2 db.
	 * Calls getAllOrders() from OrderService class to get the list of all 
	 * users from ORDERS.
	 * @return List<Orders>: List of all orders.
	 */
	@GetMapping("/orders")
	public ResponseEntity<List<Orders>> getAllOrders() {
		try {
			LOGGER.trace("Retrieving list of all orders.");

			List<Orders> orders = new ArrayList<Orders>();
			LOGGER.info("Calling OrderService.getAllOrders method to list all orders.");
			orderService.getAllOrders().forEach(orders::add);

			if (orders.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(orders, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Retrieves order from Orders entity in H2 db based on order id.
	 * Calls getOrder() from OrderService class to retrieve order from 
	 * ORDERS.
	 * @param id: Id of order to be retrieved.
	 * @return Orders: Order data retrieved.
	 */
	@GetMapping("/order/{id}")
	public ResponseEntity<Orders> getOrderById(@PathVariable("id") long id) {
		LOGGER.trace("Retrieving order by Order Id.");

		LOGGER.info("Calling orderService.getOrder method to retrieve order data by id.");
		Optional<Orders> orderData = orderService.getOrderById(id);

		if (orderData.isPresent()) {
			return new ResponseEntity<>(orderData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/order")
	public ResponseEntity<List<Orders>> getOrdersByOrderDate(@RequestParam(value="orderDate") Date orderDate) {
		LOGGER.trace("Retrieving order by Order date.");

		try {
			List<Orders> orders = new ArrayList<Orders>();
			LOGGER.info("Calling orderService.getOrderByDate method to retrieve order data by date.");
			orderService.getOrderByDate(orderDate).forEach(orders::add);

			if (orders.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(orders, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/order/{id}")
	public ResponseEntity<Orders> updateOrder(@PathVariable("id") long id, @RequestBody Orders order) {
		LOGGER.trace("Updating order.");

		LOGGER.info("Calling orderService.getOrder method to retrieve order data by id.");
		Optional<Orders> orderData = orderService.getOrderById(id);
		//		Optional<Orders> orderData = ordersRepository.findById(id);

		if (orderData.isPresent()) {
			Orders _order = orderData.get();
			_order.setOrderDate(new Date());
			_order.setProductId(order.getProductId());
			_order.setUserId(order.getUserId());
			LOGGER.info("Calling orderService.updateOrderData method to update order data.");

			return new ResponseEntity<>(orderService.updateOrderData(_order), HttpStatus.OK);
		} else {
			LOGGER.error("Failed to update order in repo: No such order found in repo.");

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/orders/{id}")
	public ResponseEntity<HttpStatus> deleteOrder(@PathVariable("id") long id) {
		LOGGER.trace("Deleting order.");

		try {
			LOGGER.info("Calling orderService.removeOrder method to delete order.");
			orderService.removeOrder(id);
			LOGGER.info("Order successfully deleted from repo.");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			LOGGER.error("Failed to delete order from repo.");

			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/orders")
	public ResponseEntity<HttpStatus> deleteAllOrders() {
		LOGGER.trace("Deleting all orders.");

		try {
			LOGGER.info("Calling orderService.removeOrders method to delete all orders.");
			orderService.removeOrders();
			LOGGER.info("All orders successfully deleted from repo.");

			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			LOGGER.error("Failed to delete user from repo.");

			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}