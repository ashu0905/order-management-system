package rest.springboot.restfulWebService.serviceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rest.springboot.restfulWebService.model.Orders;
import rest.springboot.restfulWebService.repository.OrdersRepository;
import rest.springboot.restfulWebService.service.OrderService;

@Service
public class OrdersServiceImpl implements OrderService {
	/** Logger instance for OrderServiceImpl class. */
	static final Logger LOGGER = LoggerFactory.getLogger(OrdersServiceImpl.class);

	/** Injecting object dependency of OrdersRepository. */
	@Autowired
	OrdersRepository ordersRepository;

	/**
	 * Creates a new order in Orders entity in H2 db.
	 * Implements save() from OrdersRepository interface to create new order 
	 * in ORDERS.
	 * @param order: Orders JSON containing product id & user id.
	 * @return Orders: New order created.
	 */
	@Transactional
	public Orders addNewOrder(Orders order) {
		LOGGER.trace("Creating a new order in ORDERS repo.");

		Orders _order = ordersRepository
				.save(new Orders(new Date(), order.getProductId(), order.getUserId()));
		LOGGER.debug("New order created as: {}", _order);

		return _order;	
	}

	/**
	 * Retrieves list of all orders from Orders entity in H2 db.
	 * Implements findAll() from OrdersRepository interface to get the list of all 
	 * orders from ORDERS.
	 * @return List<Orders>: List of all orders.
	 */
	@Transactional
	public List<Orders> getAllOrders() {	
		LOGGER.trace("Retrieving list of orders from ORDERS repo.");

		List<Orders> orders = new ArrayList<Orders>();
		ordersRepository.findAll().forEach(orders::add);

		if (orders.isEmpty()) {
			LOGGER.error("No Order data exists in ORDERS repo.");
			return null;
		}

		LOGGER.debug("Orders retrieved from ORDERS repo: {}", orders);
		return orders;
	}

	/**
	 * Retrieves order from Orders entity in H2 db based on order id.
	 * Implements findById() from OrdersRepository interface to retrieve order from 
	 * ORDERS.
	 * @param id: Id of order to be retrieved.
	 * @return Orders: Order data retrieved.
	 */
	@Transactional
	public Optional<Orders> getOrderById(long id) {
		LOGGER.trace("Retrieving order by id from ORDERS repo.");

		Optional<Orders> orderData = ordersRepository.findById(id);

		if (orderData.isPresent()) {
			LOGGER.debug("Order retrieved from ORDERS repo: {}", orderData);
			return orderData;
		} else {
			LOGGER.error("No such order id exists in ORDERS repo.");
			return null;
		}
	}

	/**
	 * Retrieves order from Orders entity in H2 db based on order date.
	 * Implements findByDate() from OrdersRepository interface to retrieve order from 
	 * ORDERS.
	 * @param orderDate: Date of order to be retrieved.
	 * @return Orders: Order data retrieved.
	 */
	@Transactional
	public List<Orders> getOrderByDate(Date orderDate) {
		LOGGER.trace("Retrieving order by date from ORDERS repo.");

		Optional<Orders> orderData = ordersRepository.findByOrderDate(orderDate);

		if (orderData.isPresent()) {
			LOGGER.debug("Order retrieved from ORDERS repo: {}", orderData);
			return Collections.singletonList(orderData.get());
		} else {
			LOGGER.error("No such order for {} exists in ORDERS repo.", orderDate);
			return null;
		}
	}

	@Transactional
	public Orders updateOrderData(Orders order) {
		LOGGER.trace("Updating order data in ORDERS repo.");

		Orders _order = ordersRepository.save(order);
		LOGGER.debug("Order updated to: {}", _order);

		return _order;
	}

	@Transactional
	public void removeOrder(long id) {
		LOGGER.trace("Deleting order data from ORDERS repo.");

		ordersRepository.deleteById(id);
	}

	public void removeOrders() {
		LOGGER.trace("Deleteing all orders from ORDERS repo.");

		ordersRepository.deleteAll();
	}
}